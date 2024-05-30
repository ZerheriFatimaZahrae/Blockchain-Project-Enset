package zerheri.fatimazahrae.blockchainapp.entity;

import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private List<Transaction> pendingTransactions; // Liste des transactions en attente

    public TransactionPool() {
        this.pendingTransactions = new ArrayList<>();
    }

    // Ajout d'une transaction au pool
    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    // Récupération de toutes les transactions en attente
    public List<Transaction> getPendingTransactions() {
        return new ArrayList<>(pendingTransactions);
    }

    // Suppression d'une transaction confirmée du pool
    public void removeTransaction(Transaction transaction) {
        pendingTransactions.remove(transaction);
    }
}

