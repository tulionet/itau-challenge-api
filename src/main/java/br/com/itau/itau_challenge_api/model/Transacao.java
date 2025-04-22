package br.com.itau.itau_challenge_api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public record Transacao (BigDecimal valor,  OffsetDateTime dataHora){

    @Override
    public String toString() {
        return "Valor = " + valor + " DataHora = " + dataHora;
    }
}
