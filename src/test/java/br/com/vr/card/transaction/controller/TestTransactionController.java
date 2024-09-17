package br.com.vr.card.transaction.controller;

import br.com.vr.card.transaction.container.TestcontainersConfiguration;
import br.com.vr.card.transaction.controller.rest.TransactionRest;
import br.com.vr.card.transaction.enums.TransactionErrorsEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestTransactionController extends TestcontainersConfiguration {

    @Test
    public void testSimpleTransaction() throws Exception {
        TransactionRest transactionRest = new TransactionRest();
        transactionRest.setNumber("123456789012345");
        transactionRest.setPassword("12345");
        transactionRest.setAmount(BigDecimal.valueOf(100.00));

        this.mockMvc.perform(
                        post("/transacoes")
                                .content(objectMapper.writeValueAsString(transactionRest))
                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testTransactionWithInsufficientFounds() throws Exception {
        TransactionRest transactionRest = new TransactionRest();
        transactionRest.setNumber("521352135213521");
        transactionRest.setPassword("12345");
        transactionRest.setAmount(BigDecimal.valueOf(600.00));

        this.mockMvc.perform(
                        post("/transacoes")
                                .content(objectMapper.writeValueAsString(transactionRest))
                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(TransactionErrorsEnum.INSUFFICIENT_FOUNDS.getKey()));
        ;
    }

    @Test
    public void testTransactionWithCardNotFound() throws Exception {
        TransactionRest transactionRest = new TransactionRest();
        transactionRest.setNumber("000000000000000");
        transactionRest.setPassword("12345");
        transactionRest.setAmount(BigDecimal.valueOf(100.00));

        this.mockMvc.perform(
                        post("/transacoes")
                                .content(objectMapper.writeValueAsString(transactionRest))
                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(TransactionErrorsEnum.CARD_NOT_FOUND.getKey()));
        ;
    }

    @Test
    public void testTransactionWithInvalidPassword() throws Exception {
        TransactionRest transactionRest = new TransactionRest();
        transactionRest.setNumber("521352135213521");
        transactionRest.setPassword("00000");
        transactionRest.setAmount(BigDecimal.valueOf(100.00));

        this.mockMvc.perform(
                        post("/transacoes")
                                .content(objectMapper.writeValueAsString(transactionRest))
                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(TransactionErrorsEnum.INVALID_PASSWORD.getKey()));
        ;
    }

}
