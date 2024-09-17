package br.com.vr.card.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;


public class CardAlreadyExistsException extends ResponseStatusException {

    public CardAlreadyExistsException(HttpStatusCode status) {
        super(status);
    }

    public CardAlreadyExistsException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

}

