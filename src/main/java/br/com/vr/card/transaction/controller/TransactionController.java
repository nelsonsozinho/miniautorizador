package br.com.vr.card.transaction.controller;

import br.com.vr.card.transaction.controller.rest.TransactionRest;
import br.com.vr.card.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/transacoes")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> newTransaction(@Valid @RequestBody final TransactionRest transaction) {
        var newTransaction = transactionService.newTransaction(transaction.getNumber(), transaction.getAmount(), transaction.getPassword());
        return ResponseEntity.created(URI.create("transaction/" + newTransaction.getId())).build();
    }

}
