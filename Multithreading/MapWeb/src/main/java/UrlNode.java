import java.util.ArrayList;
import java.util.List;

class UrlNode {
    String url;
    List<UrlNode> children;
    int depth;

    public UrlNode(String url, int depth) {
        this.url = url;
        this.children = new ArrayList<>();
        this.depth = depth;
    }

    public void addChild(UrlNode node) {
        children.add(node);
    }
}