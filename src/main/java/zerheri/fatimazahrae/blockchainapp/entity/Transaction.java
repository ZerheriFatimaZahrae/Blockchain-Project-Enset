package zerheri.fatimazahrae.blockchainapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor  @Getter
@Setter
public class Transaction {

    private String sender; // Adresse de l'expéditeur
    private String recipient; // Adresse du destinataire
    private double amount; // Montant de la transaction
    private String signature; // Signature numérique pour authentifier la transaction

    public Transaction(String sender, String recipient, double amount, String signature) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.signature = signature;
    }


}

