package br.com.vr.card.transaction.controller.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class TransactionRest {

    @NotBlank
    @JsonProperty("numeroCartao")
    private String number;

    @NotBlank
    @JsonProperty("senhaCartao")
    private String password;

    @NotNull
    @JsonProperty("valor")
    private BigDecimal amount;

}
