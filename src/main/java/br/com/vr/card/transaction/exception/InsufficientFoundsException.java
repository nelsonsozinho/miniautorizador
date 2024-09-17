package br.com.vr.card.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientFoundsException extends ResponseStatusException {

    public InsufficientFoundsException(HttpStatusCode status) {
        super(status);
    }

    public InsufficientFoundsException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

}
