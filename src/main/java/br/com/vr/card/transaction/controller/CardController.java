package br.com.vr.card.transaction.controller;

import br.com.vr.card.transaction.controller.mapper.CardMapper;
import br.com.vr.card.transaction.controller.rest.CardRest;
import br.com.vr.card.transaction.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/cartoes")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;

    private final CardMapper mapper;


    @PostMapping()
    public ResponseEntity<CardRest> createNewCard(@Valid @RequestBody final CardRest cardRest) {
        final var card = mapper.toCard(cardRest);
        final var newCard = service.createNewCard(card);
        return ResponseEntity.created(URI.create("/cartoes/" + newCard.getNumber())).body(mapper.toResponse(card));
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<String> findCardByNumber(@PathVariable("numeroCartao") final String number) {
        return service.findCardByNumber(number)
                .map(card -> ResponseEntity.ok(mapper.toResponse(card).getBalance().toString()))
                .orElse(ResponseEntity.notFound().build());
    }

}
