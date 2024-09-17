package br.com.vr.card.transaction.service;

import br.com.vr.card.transaction.container.TestcontainersConfiguration;
import br.com.vr.card.transaction.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCardService extends TestcontainersConfiguration {

    @Autowired
    private CardService service;

    @Test
    public void testCardService() {
        Card inputCard = new Card();
        inputCard.setNumber("556456789012345");
        inputCard.setPassword("12345");
        Card card = service.createNewCard(inputCard);
        assertNotNull(card);
    }

}
