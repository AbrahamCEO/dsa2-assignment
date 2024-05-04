public class ClientNode {
    private int id;

    public ClientNode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void send(ServerNode server, ClientNode recipient, String message) {
        server.receiveMessage(this, recipient, message);
    }
}
