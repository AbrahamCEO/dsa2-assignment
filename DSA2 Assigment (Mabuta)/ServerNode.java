import java.util.HashMap;
import java.util.Map;

public class ServerNode {
    private Map<Integer, ClientNode> clients;
    private Map<Character, String> huffmanCodes; // Add Huffman codes field

    public ServerNode() {
        this.clients = new HashMap<>();
    }

    public void receiveMessage(ClientNode sender, ClientNode recipient, String message) {
        if (huffmanCodes == null) {
            System.out.println("Huffman codes are not set. Please set Huffman codes before receiving messages.");
            return;
        }
        String encodedMessage = encodeMessage(message); // Encode the message using Huffman codes
        System.out.println("Message received from client " + sender.getId() + " to client " + recipient.getId() + ": " + "(Huffman encoding) " + encodedMessage);
        // Add additional processing if needed
    }

    private String encodeMessage(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(huffmanCodes.get(c)); // Encode each character using Huffman codes
        }
        return encodedMessage.toString();
    }

    // Method to set Huffman codes
    public void setHuffmanCodes(Map<Character, String> huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }

    public void addClient(ClientNode client) {
        clients.put(client.getId(), client);
    }

    public boolean containsClient(ClientNode client) {
        return clients.containsKey(client.getId());
    }
}
