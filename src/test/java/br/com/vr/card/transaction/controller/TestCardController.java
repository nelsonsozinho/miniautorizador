package br.com.vr.card.transaction.controller;

import br.com.vr.card.transaction.container.TestcontainersConfiguration;
import br.com.vr.card.transaction.controller.rest.CardRest;
import br.com.vr.card.transaction.service.CardService;
import br.com.vr.card.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestCardController extends TestcontainersConfiguration {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CardService cardService;

    @Test
    public void testGetCard() throws Exception {
        CardRest cardRest = new CardRest();
        cardRest.setNumber("432156789012345");
        cardRest.setPassword("12345");

        this.mockMvc.perform(
                        get("/cartoes/{number}", cardRest.getNumber())
                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCardIsTheBalanceIsTheStart() throws Exception {
        CardRest cardRest = new CardRest();
        cardRest.setNumber("432156789012345");
        cardRest.setPassword("12345");

        this.mockMvc.perform(
                        get("/cartoes/{number}", cardRest.getNumber())

                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("500.00"));
    }

    @Test
    public void testGetCardAfterWithdraw() throws Exception {
        String cardNumber = "123456789012345";
        String password = "12345";
        BigDecimal transactionAmount = new BigDecimal("100.00");

        transactionService.newTransaction(cardNumber, transactionAmount, password);

        this.mockMvc.perform(
                        get("/cartoes/{number}", cardNumber)
                                .header("ContentType", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("400.00"));
    }

    @Test
    public void createNewCard() throws Exception {
        CardRest cardRest = new CardRest();
        cardRest.setNumber("432143214321432");
        cardRest.setPassword("12345");

        this.mockMvc.perform(
                        post("/cartoes")
                                .content(objectMapper.writeValueAsString(cardRest))
                                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
