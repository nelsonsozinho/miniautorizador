package br.com.vr.card.transaction.controller.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CardRest {

    @JsonProperty("numeroCartao")
    @NotBlank
    @NotNull
    private String number;

    @JsonProperty("senha")
    @NotBlank
    @NotNull
    private String password;

    private BigDecimal balance;

}
