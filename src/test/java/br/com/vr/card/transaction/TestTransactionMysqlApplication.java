package br.com.vr.card.transaction;

import br.com.vr.card.transaction.container.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestTransactionMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransactionMysqlApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
