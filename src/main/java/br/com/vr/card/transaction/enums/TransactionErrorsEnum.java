package br.com.vr.card.transaction.enums;

public enum TransactionErrorsEnum {

    INVALID_PASSWORD("SENHA_INVALIDA"),
    INSUFFICIENT_FOUNDS("SALDO_INSUFICIENTE"),
    CARD_NOT_FOUND("CARTAO_INEXISTENTE");

    private String key;

    TransactionErrorsEnum(final String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
