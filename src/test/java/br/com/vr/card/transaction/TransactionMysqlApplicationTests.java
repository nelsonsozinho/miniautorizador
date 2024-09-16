package br.com.vr.card.transaction;

import br.com.vr.card.transaction.container.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TransactionMysqlApplicationTests {

	@Test
	void contextLoads() {
	}

}
