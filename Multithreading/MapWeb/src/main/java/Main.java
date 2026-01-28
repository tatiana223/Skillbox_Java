import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://skillbox.ru";
        String outputPath = "sitemap.txt";

        SiteMapper mapper = new SiteMapper();
        try {
            mapper.generateSiteMap(baseUrl, outputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
