package br.com.vr.card.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CardInvalidPasswordException extends ResponseStatusException {

    public CardInvalidPasswordException(HttpStatusCode status) {
        super(status);
    }

    public CardInvalidPasswordException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

}