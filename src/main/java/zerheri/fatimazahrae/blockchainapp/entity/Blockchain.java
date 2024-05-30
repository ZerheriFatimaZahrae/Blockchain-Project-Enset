package zerheri.fatimazahrae.blockchainapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class Blockchain {

    private ArrayList<Block> chain; // Liste des blocs de la blockchain

    private int difficulty; // Niveau de difficulté pour le mining
    private TransactionPool transactionPool; // Pool de transactions en attente

    public Blockchain( int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        this.transactionPool = new TransactionPool();
        // Création du bloc genesis (le premier bloc)
        Block genesisBlock = new Block(0, "0", "Genesis Block");
        genesisBlock.generateBlock(difficulty);
        chain.add(genesisBlock);
    }

    // Ajout d'un bloc à la blockchain
    public void addBlock(Block newBlock) {
        newBlock.generateBlock(difficulty);
        chain.add(newBlock);
    }

    // Obtention d'un bloc par son index
    public Block getBlockByIndex(int index) {
        return chain.get(index);
    }

    // Validation de l'intégrité de toute la blockchain
    public boolean validateChain() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);
            // Vérification de la validité du bloc actuel et du lien avec le bloc précédent
            if (!currentBlock.validateBlock() || !currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())) {
                return false;
            }
        }
        return true;
    }

    // Obtention du dernier bloc de la chaîne
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // Processus de minage d'un bloc
    public void mineBlock(Block block) {
        block.generateBlock(difficulty);
    }

    // Ajout d'une transaction au pool de transactions en attente
    public void addTransaction(Transaction transaction) {
        transactionPool.addTransaction(transaction);
    }

    // Minage des transactions en attente et ajout d'un nouveau bloc à la blockchain
    public void minePendingTransactions() {
        List<Transaction> transactions = transactionPool.getPendingTransactions();
        String data = transactions.toString();
        Block newBlock = new Block(chain.size(), getLatestBlock().getCurrentHash(), data);
        mineBlock(newBlock);
        chain.add(newBlock);
        transactionPool = new TransactionPool(); // Réinitialisation du pool de transactions
    }

    // Ajustement de la difficulté de minage si nécessaire
    public void adjustDifficulty() {
        int interval = 10; // Par exemple, ajuster la difficulté tous les 10 blocs
        if (chain.size() % interval == 0) {
            Block lastBlock = chain.get(chain.size() - 1);
            Block blockBeforeLast = chain.get(chain.size() - interval);
            long timeExpected = interval * 60000; // Par exemple, 1 minute par bloc
            long timeTaken = lastBlock.getTimestamp() - blockBeforeLast.getTimestamp();

            if (timeTaken < timeExpected / 2) {
                difficulty++;
            } else if (timeTaken > timeExpected * 2) {
                difficulty--;
            }
        }
    }

    // Validation d'un bloc spécifique
    public boolean validateBlock(Block block) {
        String calculatedHash = block.calculateHash();
        // Vérifie si le hash calculé correspond au hash stocké et respecte la difficulté
        return calculatedHash.equals(block.getCurrentHash()) && calculatedHash.substring(0, difficulty).equals(new String(new char[difficulty]).replace('\0', '0'));
    }


}

