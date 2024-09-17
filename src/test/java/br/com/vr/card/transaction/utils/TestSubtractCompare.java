package br.com.vr.card.transaction.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSubtractCompare {

    @Test
    public void negativeOperationWithSuccess() {
        BigDecimal value = new BigDecimal("500.00");
        BigDecimal expected = new BigDecimal("600.00");
        BigDecimal result = value.subtract(expected);
        assertEquals(result, new BigDecimal("-100.00"));
    }

    @Test
    public void avoidNegativeOperation() {
        BigDecimal value = new BigDecimal("500.00");
        BigDecimal expected = new BigDecimal("600.00");

        BigDecimal result =  Optional.of(value.subtract(expected))
                .filter(balance -> balance.compareTo(BigDecimal.ZERO) >= 0)
                .orElse(value);

        assertNotNull(result);
        assertEquals(value, result);
    }

    @Test
    public void naturalTransactionOperation() {
        BigDecimal value = new BigDecimal("500.00");
        BigDecimal expected = new BigDecimal("500.00");

        BigDecimal result =  Optional.of(value.subtract(expected))
                .filter(balance -> balance.compareTo(BigDecimal.ZERO) >= 0)
                .orElse(value);

        assertNotNull(result);
        assertEquals(new BigDecimal("0.00"), result);
    }

}
