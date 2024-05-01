import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerNode {
    private Map<ClientNode, String> clients;

    public ServerNode() {
        this.clients = new HashMap<>();
    }

    public void receiveMessage(ClientNode sender, ClientNode recipient, String message) {
        System.out.println("Message received from client " + sender.getId() + ": " + message);
        brokerMessage(sender, message);
    }

    private void brokerMessage(ClientNode sender, String message) {
        for (ClientNode recipient : clients.keySet()) {
            if (!recipient.equals(sender)) {
                recipient.receiveMessage(sender, message);
            }
        }
    }

    public void addClient(ClientNode client) {
        clients.put(client, null); // Initially no message associated with the client
        System.out.println("Client " + client.getId() + " connected.");
    }

    public static void main(String[] args) {
        ServerNode server = new ServerNode();
        Scanner scanner = new Scanner(System.in);

        // Input client IDs
        System.out.print("Enter Client 1 ID: ");
        int client1Id = scanner.nextInt();
        System.out.print("Enter Client 2 ID: ");
        int client2Id = scanner.nextInt();

        // Create client nodes
        ClientNode client1 = new ClientNode(client1Id);
        ClientNode client2 = new ClientNode(client2Id);

        server.addClient(client1);
        server.addClient(client2);

        // Simulate message exchange
        scanner.nextLine(); // Consume newline character left by previous nextInt() calls
        System.out.print("Enter message from Client 1 to Client 2: ");
        String message1To2 = scanner.nextLine();
        client1.send(server, client2, message1To2);

        System.out.print("Enter message from Client 2 to Client 1: ");
        String message2To1 = scanner.nextLine();
        client2.send(server, client1, message2To1);

        // Close scanner
        scanner.close();
    }
}

class ClientNode {
    private int id;

    public ClientNode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void receiveMessage(ClientNode sender, String message) {
        System.out.println("Client " + id + " received message from client " + sender.getId() + ": " + message);
    }

    public void send(ServerNode server, ClientNode recipient, String message) {
        server.receiveMessage(this, recipient, message);
    }
}
