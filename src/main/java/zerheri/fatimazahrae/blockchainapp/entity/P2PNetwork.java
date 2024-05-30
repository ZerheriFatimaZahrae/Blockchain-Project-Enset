package zerheri.fatimazahrae.blockchainapp.entity;

import java.util.ArrayList;
import java.util.List;

public class P2PNetwork {
    private List<Node> nodes; // Liste des nœuds dans le réseau

    public P2PNetwork() {
        this.nodes = new ArrayList<>();
    }

    // Ajoute un nœud au réseau
    public void addNode(Node node) {
        nodes.add(node);
    }

    // Propage un message à tous les nœuds du réseau
    public void broadcastMessage(String message) {
        for (Node node : nodes) {
            node.receiveMessage(message);
        }
    }

    // Classe représentant un nœud dans le réseau
    public static class Node {
        private String address;

        public Node(String address) {
            this.address = address;
        }

        // Reçoit un message
        public void receiveMessage(String message) {
            System.out.println("Message reçu à " + address + ": " + message);
        }
    }
}

