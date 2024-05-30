package zerheri.fatimazahrae.blockchainapp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zerheri.fatimazahrae.blockchainapp.entity.Blockchain;
import zerheri.fatimazahrae.blockchainapp.entity.P2PNetwork;


@SpringBootApplication


public class BlockchainAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainAppApplication.class, args);
    }

    @Bean
    public Blockchain blockchain() {
        return new Blockchain(2);
    }


}
