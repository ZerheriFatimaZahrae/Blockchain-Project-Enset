package zerheri.fatimazahrae.blockchainapp;

import zerheri.fatimazahrae.blockchainapp.entity.*;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(4); // Création de la blockchain avec une difficulté de 4

        // Ajout de quelques transactions
        blockchain.addTransaction(new Transaction("Alice", "Bob", 10, "Signature1"));
        blockchain.addTransaction(new Transaction("Bob", "Charlie", 5, "Signature2"));

        // Minage des transactions en attente
        blockchain.minePendingTransactions();

        // Validation de la blockchain
        System.out.println("Blockchain valid: " + blockchain.validateChain());

        // Affichage des blocs
        for (int i = 0; i < blockchain.getChain().size(); i++) {
            Block block = blockchain.getBlockByIndex(i);
            System.out.println("Block #" + block.getIndex() + " [hash: " + block.getCurrentHash() + "]");
        }

        // Configuration du réseau P2P
        P2PNetwork network = new P2PNetwork();
        P2PNetwork.Node node1 = new P2PNetwork.Node("Node1");
        P2PNetwork.Node node2 = new P2PNetwork.Node("Node2");
        network.addNode(node1);
        network.addNode(node2);

        // Propagation d'un message dans le réseau
        network.broadcastMessage("Nouvelle transaction");

        // Gestion de portefeuille
        Wallet wallet = new Wallet();
        String address = wallet.getAddress();
        System.out.println("Adresse du portefeuille: " + address);
    }
}
