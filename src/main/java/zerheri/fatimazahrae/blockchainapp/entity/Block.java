package zerheri.fatimazahrae.blockchainapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.MessageDigest;
import java.util.Date;

import java.security.MessageDigest;
import java.util.Date;
  @NoArgsConstructor
@Getter @Setter
public class Block {

    private int index; // Position du bloc dans la blockchain
    private long timestamp; // Heure de création du bloc
    private String previousHash; // Hash du bloc précédent
    private String currentHash; // Hash du bloc actuel
    private String data; // Données associées au bloc
    private int nonce; // Valeur utilisée pour le proof of work





    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.currentHash = calculateHash(); // Calcul du hash initial
    }

    // Calcul du hash du bloc en utilisant SHA-256
    public String calculateHash() {
        String input = index + previousHash + timestamp + data + nonce;
        return applySha256(input);
    }

    // Génération d'un nouveau bloc avec la difficulté donnée
    public void generateBlock(int difficulty) {
        mineBlock(difficulty);
    }

    // Minage du bloc jusqu'à ce que le hash satisfasse la difficulté requise
    public void mineBlock(int difficulty) {
        nonce = 0;
        String target = new String(new char[difficulty]).replace('\0', '0'); // Cible pour la difficulté
        while (!currentHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currentHash = calculateHash();
        }
    }

    // Validation du bloc en vérifiant si le hash actuel correspond au hash calculé
    public boolean validateBlock() {
        return currentHash.equals(calculateHash());
    }


    // Fonction utilitaire pour appliquer SHA-256 à une chaîne de caractères
    private String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
