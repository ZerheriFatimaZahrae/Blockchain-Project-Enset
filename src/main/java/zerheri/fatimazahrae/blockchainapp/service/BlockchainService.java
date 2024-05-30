package zerheri.fatimazahrae.blockchainapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import zerheri.fatimazahrae.blockchainapp.entity.Blockchain;



import org.springframework.beans.factory.annotation.Autowired;

import zerheri.fatimazahrae.blockchainapp.entity.Block;

import zerheri.fatimazahrae.blockchainapp.entity.Transaction;


@Service
public class BlockchainService {

    private final Blockchain blockchain;

    @Autowired
    public BlockchainService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public List<Block> getAllBlocks() {
        return blockchain.getChain();
    }

    public Block getBlockByIndex(int index) {
        return blockchain.getBlockByIndex(index);
    }

    public Block getLatestBlock() {
        return blockchain.getLatestBlock();
    }

    public boolean addBlock(Block block) {
        blockchain.addBlock(block);
        return true;
    }

    public boolean validateBlockchain() {
        return blockchain.validateChain();
    }

    public void addTransaction(Transaction transaction) {
        blockchain.addTransaction(transaction);
    }

    public void minePendingTransactions() {
        blockchain.minePendingTransactions();
    }

    public void adjustDifficulty() {
        blockchain.adjustDifficulty();
    }

    public boolean validateBlock(Block block) {
        return blockchain.validateBlock(block);
    }
}
