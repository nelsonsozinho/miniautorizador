package br.com.vr.card.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CardNotFoundException extends ResponseStatusException {

    public CardNotFoundException(HttpStatusCode status) {
        super(status);
    }

    public CardNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

}
