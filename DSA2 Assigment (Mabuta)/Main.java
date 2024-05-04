import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Star star = new Star();
        ServerNode server = new ServerNode(); // Create ServerNode instance

        while (true) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("|                                           DSA2 Assignment                                   |");
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("| Options:                                                                                    |");
            System.out.println("|        1. Add Client Node                                                                   |");
            System.out.println("|        2. Delete Client Node                                                                |");
            System.out.println("|        3. Show Clients in Network                                                           |");
            System.out.println("|        4. Send Messages                                                                     |");
            System.out.println("|        5. Quit                                                                              |");
            System.out.println("-------------------------------------------------------------------------------------------------");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addClientNode(scanner, star);
                    break;
                case 2:
                    deleteClientNode(scanner, star);
                    break;
                case 3:
                    showClientsInNetwork(star);
                    break;
                case 4:
                    sendMessages(scanner, server, star); // Pass server instance to sendMessages method
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void addClientNode(Scanner scanner, Star star) {
        System.out.print("Enter Client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        ClientNode clientNode = new ClientNode(clientId);
        star.insertNode(clientNode);
        System.out.println("Client Node with ID " + clientId + " added to the star network.");
    }

    public static void deleteClientNode(Scanner scanner, Star star) {
        System.out.print("Enter Client ID to delete: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        star.deleteNode(clientId);
    }

    public static void showClientsInNetwork(Star star) {
        System.out.println("Clients in the network:");
        for (ClientNode node : star.getNodes()) {
            System.out.println("Client ID: " + node.getId());
        }
    }

    public static void sendMessages(Scanner scanner, ServerNode server, Star star) {
        System.out.println("Send Messages:");
        if (star.getNodes().size() < 2) {
            System.out.println("At least two clients are required to send messages.");
            return;
        }

        System.out.print("Enter sender Client ID: ");
        int senderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        ClientNode sender = findClientNode(star, senderId);
        if (sender == null) {
            System.out.println("Sender not found.");
            return;
        }

        System.out.print("Enter recipient Client ID: ");
        int recipientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        ClientNode recipient = findClientNode(star, recipientId);
        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter message: ");
        String message = scanner.nextLine();

        sender.send(server, recipient, message); // Pass server instance to sender.send method
    }

    private static ClientNode findClientNode(Star star, int clientId) {
        for (ClientNode node : star.getNodes()) {
            if (node.getId() == clientId) {
                return node;
            }
        }
        return null;
    }
}
