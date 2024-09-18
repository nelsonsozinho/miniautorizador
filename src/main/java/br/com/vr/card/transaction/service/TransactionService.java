package br.com.vr.card.transaction.service;

import br.com.vr.card.transaction.enums.TransactionErrorsEnum;
import br.com.vr.card.transaction.exception.CardInvalidPasswordException;
import br.com.vr.card.transaction.exception.CardNotFoundException;
import br.com.vr.card.transaction.exception.InsufficientFoundsException;
import br.com.vr.card.transaction.model.Card;
import br.com.vr.card.transaction.model.Transaction;
import br.com.vr.card.transaction.repository.CardRepository;
import br.com.vr.card.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;

    public TransactionService(final TransactionRepository transactionRepository, final CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Transaction newTransaction(final String cardNumber, final BigDecimal transactionAmount, final String password) {
        final Card card = cardRepository.findByNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException(TransactionErrorsEnum.CARD_NOT_FOUND.getKey()));
        final BigDecimal newBalance = subtractBalance(card.getBalance(), transactionAmount);

        validateCardPassword(password, card);

        //update card balance
        card.setBalance(newBalance);
        cardRepository.save(card);

        //create a new transaction
        final Transaction newTransaction = new Transaction(card, transactionAmount, LocalDateTime.now());

        return transactionRepository.save(newTransaction);
    }

    private BigDecimal subtractBalance(final BigDecimal cardBalance, final BigDecimal transactionAmount) {
        return Optional.of(cardBalance.subtract(transactionAmount))
                .filter(balance -> balance.compareTo(BigDecimal.ZERO) >= 0)
                .orElseThrow(() -> new InsufficientFoundsException(TransactionErrorsEnum.INSUFFICIENT_FOUNDS.getKey()));
    }

    private void validateCardPassword(final String password, final Card card) {
        Optional.of(password)
                .filter(p -> p.equals(card.getPassword()))
                .orElseThrow(() -> new CardInvalidPasswordException(TransactionErrorsEnum.INVALID_PASSWORD.getKey()));
    }

}
