import java.util.*;

public class Huffman {
    static class HuffmanNode implements Comparable<HuffmanNode> {
        char character;
        int frequency;
        HuffmanNode left, right;

        public HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
            this.character = character;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return this.frequency - o.frequency;
        }
    }

    public static Map<Character, String> buildHuffmanCodes(String message) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();
            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
            minHeap.offer(merged);
        }

        Map<Character, String> huffmanCodes = new HashMap<>();
        buildCodes(minHeap.peek(), "", huffmanCodes);
        return huffmanCodes;
    }

    private static void buildCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }
        buildCodes(node.left, code + "0", huffmanCodes);
        buildCodes(node.right, code + "1", huffmanCodes);
    }

    public static String encode(String message, Map<Character, String> huffmanCodes) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(huffmanCodes.get(c));
        }
        return encodedMessage.toString();
    }

    public static String decode(String encodedMessage, HuffmanNode root) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedMessage.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decodedMessage.append(current.character);
                current = root;
            }
        }
        return decodedMessage.toString();
    }
}
