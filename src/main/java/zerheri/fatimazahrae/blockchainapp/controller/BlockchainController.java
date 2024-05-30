package zerheri.fatimazahrae.blockchainapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerheri.fatimazahrae.blockchainapp.entity.Block;
import zerheri.fatimazahrae.blockchainapp.entity.Blockchain;
import zerheri.fatimazahrae.blockchainapp.entity.Transaction;
import zerheri.fatimazahrae.blockchainapp.service.BlockchainService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlockchainController {
    @Autowired
    private final Blockchain blockchain;
    private final BlockchainService blockchainService;



    @GetMapping("/blockchain")
    public List<Block> getBlockchain() {
        return blockchain.getChain();
    }

    @PostMapping("/blockchain/transaction")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        blockchain.addTransaction(transaction);
        return ResponseEntity.ok("Transaction added successfully.");
    }

    @PostMapping("/blockchain/mine")
    public ResponseEntity<String> mineBlock(@RequestBody Block block) {
        blockchain.mineBlock(block);
        return ResponseEntity.ok("Block mined successfully. Block hash: " + block.getCurrentHash());
    }


    @GetMapping("/blockchain/block/{index}")
    public ResponseEntity<Block> getBlockByIndex(@PathVariable int index) {
        if (index >= 0 && index < blockchain.getChain().size()) {
            Block block = blockchain.getChain().get(index);
            return ResponseEntity.ok(block);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/blockchain/transaction-pool")
    public List<Transaction> getTransactionPool() {
        return blockchain.getTransactionPool().getPendingTransactions();
    }

    @GetMapping("/blockchain/validate")
    public ResponseEntity<String> validateChain() {
        boolean isValid = blockchain.validateChain();
        if (isValid) {
            return ResponseEntity.ok("Blockchain is valid.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Blockchain is invalid.");
        }
    }

    @PostMapping("/blockchain/blocks")
    public Block addBlock(@RequestBody Block block) {
        blockchainService.addBlock(block);
        return block;
    }

    @GetMapping("/blockchain/blocks/latest")
    public Block getLatestBlock() {
        return blockchainService.getLatestBlock();
    }

    @GetMapping("/blockchain/blocks/validate")
    public boolean validateBlockchain() {
        return blockchainService.validateBlockchain();
    }
}