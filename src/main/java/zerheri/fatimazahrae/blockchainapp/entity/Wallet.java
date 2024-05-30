package zerheri.fatimazahrae.blockchainapp.entity;

import java.security.*;
import java.util.Base64;

public class Wallet {
    private PrivateKey privateKey; // Clé privée du portefeuille
    private PublicKey publicKey; // Clé publique du portefeuille

    public Wallet() {
        generateKeyPair();
    }

    // Génère une paire de clés publique/privée
    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(256, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Signe une transaction avec la clé privée
    public byte[] signTransaction(String data) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Retourne l'adresse du portefeuille (base64 de la clé publique)
    public String getAddress() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Vérifie une signature avec la clé publique
    public static boolean verifySignature(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature sig = Signature.getInstance("SHA256withECDSA");
            sig.initVerify(publicKey);
            sig.update(data.getBytes());
            return sig.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
