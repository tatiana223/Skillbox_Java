import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class SiteMapper {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final int DELAY_MS = 200;
    private static final int MAX_RETRIES = 2;
    private static final Set<String> FILE_EXTENSIONS = Set.of(
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "jpg", "jpeg", "png", "gif", "zip", "rar", "exe", "dmg"
    );



    public static void generateSiteMap(String startUrl, String outputFile) throws IOException {
        SiteNode root = new SiteNode(startUrl);
        Set<String> visitedLinks = Collections.newSetFromMap(new ConcurrentHashMap<>());
        visitedLinks.add(normalizeUrl(startUrl));

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new LinkProcessor(root, visitedLinks));
        pool.shutdown();

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile), true)) {
            printTree(root, writer, 0);
        }
    }

    private static class SiteNode {
        String url;
        List<SiteNode> children = new ArrayList<>();

        public SiteNode(String url) {
            this.url = url;
        }
    }

    private static class LinkProcessor extends RecursiveAction {
        private final SiteNode node;
        private final Set<String> visitedLinks;

        public LinkProcessor(SiteNode node, Set<String> visitedLinks) {
            this.node = node;
            this.visitedLinks = visitedLinks;
        }

        @Override
        protected void compute() {
            try {
                Thread.sleep(DELAY_MS);

                if (shouldSkip(node.url)) return;

                for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
                    try {
                        Document doc = Jsoup.connect(node.url)
                                .userAgent(USER_AGENT)
                                .timeout(10000)
                                .ignoreHttpErrors(true)
                                .get();

                        if (doc.connection().response().statusCode() != 200) {
                            if (attempt == MAX_RETRIES) return;
                            Thread.sleep(DELAY_MS * attempt);
                            continue;
                        }

                        processLinks(doc);
                        break;
                    } catch (IOException e) {
                        if (attempt == MAX_RETRIES) return;
                        try {
                            Thread.sleep(DELAY_MS * attempt);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void processLinks(Document doc) {
            Elements links = doc.select("a[href]");
            List<LinkProcessor> subtasks = new ArrayList<>();

            for (Element link : links) {
                String childUrl = normalizeUrl(link.absUrl("href"));

                if (isValid(childUrl) && isSameDomain(node.url, childUrl)) {
                    synchronized (visitedLinks) {
                        if (visitedLinks.add(childUrl)) {
                            SiteNode childNode = new SiteNode(childUrl);
                            node.children.add(childNode);
                            subtasks.add(new LinkProcessor(childNode, visitedLinks));
                        }
                    }
                }
            }

            invokeAll(subtasks);
        }
    }

    private static void printTree(SiteNode node, PrintWriter writer, int level) {
        writer.println("\t".repeat(level) + node.url);
        for (SiteNode child : node.children) {
            printTree(child, writer, level + 1);
        }
    }

    private static boolean shouldSkip(String url) {
        return FILE_EXTENSIONS.stream().anyMatch(ext -> url.toLowerCase().endsWith("." + ext));
    }

    private static boolean isValid(String url) {
        return url != null && !url.isEmpty() &&
                !url.startsWith("javascript:") &&
                !url.startsWith("mailto:") &&
                !url.contains("#");
    }

    private static boolean isSameDomain(String parentUrl, String childUrl) {
        try {
            URL parent = new URL(parentUrl);
            URL child = new URL(childUrl);
            return parent.getHost().equalsIgnoreCase(child.getHost());
        } catch (Exception e) {
            return false;
        }
    }

    private static String normalizeUrl(String url) {
        if (url == null) return "";
        url = url.split("[?#]")[0];
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}