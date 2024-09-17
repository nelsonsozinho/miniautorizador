package br.com.vr.card.transaction.service;

import br.com.vr.card.transaction.container.TestcontainersConfiguration;
import br.com.vr.card.transaction.exception.InsufficientFoundsException;
import br.com.vr.card.transaction.model.Card;
import br.com.vr.card.transaction.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestTransactionService extends TestcontainersConfiguration {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testNewTransactionWithSuccess() {
        BigDecimal transactionAmount = new BigDecimal("400.00");
        String password = "12345";
        String cardNumber = "123456789012345";

        Transaction transaction = transactionService.newTransaction(cardNumber, transactionAmount, password);
        Card card = transaction.getCard();

        assertNotNull(transaction);
        assertNotNull(card);
        assertEquals(card.getBalance(), new BigDecimal("100.00"));
    }

    @Test
    public void testTransactionWithInsufficientFounds() {
        BigDecimal transactionAmount = new BigDecimal("600.00");
        String password = "12345";
        String cardNumber = "123456789012345";

        assertThrows(InsufficientFoundsException.class, () -> {
            transactionService.newTransaction(cardNumber, transactionAmount, password);
        });

    }

}