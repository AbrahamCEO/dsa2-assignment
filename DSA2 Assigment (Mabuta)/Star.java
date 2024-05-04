import java.util.ArrayList;
import java.util.List;

public class Star {
    private List<ClientNode> nodes;

    public Star() {
        this.nodes = new ArrayList<>();
    }

    public void insertNode(ClientNode node) {
        nodes.add(node);
    }

    public void deleteNode(int id) {
        ClientNode nodeToRemove = null;
        for (ClientNode node : nodes) {
            if (node.getId() == id) {
                nodeToRemove = node;
                break;
            }
        }
        if (nodeToRemove != null) {
            nodes.remove(nodeToRemove);
            System.out.println("Client with ID " + id + " deleted.");
        } else {
            System.out.println("Client with ID " + id + " not found.");
        }
    }

    public List<ClientNode> getNodes() {
        return nodes;
    }

    public ClientNode findNodeById(int id) {
        for (ClientNode node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }
}
