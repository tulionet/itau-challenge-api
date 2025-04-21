package br.com.itau.itau_challenge_api.model;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public record Transacao (@NonNull BigDecimal valor, @NonNull OffsetDateTime dataHora){

    @Override
    public String toString() {
        return "Valor = " + valor + " DataHora = " + dataHora;
    }
}
