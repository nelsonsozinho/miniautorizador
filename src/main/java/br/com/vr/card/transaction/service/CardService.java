package br.com.vr.card.transaction.service;

import br.com.vr.card.transaction.exception.CardAlreadyExistsException;
import br.com.vr.card.transaction.model.Card;
import br.com.vr.card.transaction.repository.CardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CardService {

    private final CardRepository cardRepository;

    private final ObjectMapper objectMapper;

    public CardService(CardRepository cardRepository, ObjectMapper objectMapper) {
        this.cardRepository = cardRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Card createNewCard(final Card card) {

        cardRepository.findByNumber(card.getNumber()).ifPresent(c -> {
            try {
                throw new CardAlreadyExistsException(objectMapper.writeValueAsString(card));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        card.setBalance(new BigDecimal("500.00"));
        card.setLastModifiedAt(LocalDateTime.now());
        return this.cardRepository.save(card);
    }

    public Optional<Card> findCardByNumber(final String number) {
        return cardRepository.findByNumber(number);
    }

}
