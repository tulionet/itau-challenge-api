package br.com.itau.itau_challenge_api.service;

import br.com.itau.itau_challenge_api.exceptions.TransacaoInvalidaException;
import br.com.itau.itau_challenge_api.model.Estatistica;
import br.com.itau.itau_challenge_api.model.Transacao;
import br.com.itau.itau_challenge_api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    public ResponseEntity<?> validarTransacao(Transacao transacao) {

        if (transacao.dataHora().isAfter(OffsetDateTime.now())) {
            throw new TransacaoInvalidaException("A transação tem data futura e não é válida.");
        }
        if (transacao.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransacaoInvalidaException("A transação contêm valor negativo ou zero.");
        }

        transacaoRepository.listaTransacao.put(UUID.randomUUID(), transacao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("A transação foi aceita.");
    };

    public ResponseEntity<?> buscaTodasTransacoes() {
        System.out.println(transacaoRepository.listaTransacao);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Toma tudo");
    }


    public ResponseEntity<?> deletarTodasTransacoes() {
        transacaoRepository.listaTransacao.clear();
        return ResponseEntity.status(HttpStatus.OK)
                .body("Todas as informações foram apagadas com sucesso.");
    }

    public ResponseEntity<?> calcularEstatiscas() {

        List<Transacao> listaSelecionada = transacaoRepository.listaTransacao.values().stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(OffsetDateTime.now().minusMinutes(1)))
                .toList();

        DoubleSummaryStatistics teste = new DoubleSummaryStatistics();

        listaSelecionada.forEach(transacao -> teste.accept(transacao.valor().doubleValue()));

        double count = teste.getCount();
        double sum = teste.getSum();
        double avg = teste.getAverage();
        double min = teste.getMin();
        if (min == Double.POSITIVE_INFINITY) min = 0;
        double max = teste.getMax();
        if (max == Double.NEGATIVE_INFINITY) max = 0;

        Estatistica valores = new Estatistica(count, sum, avg, min ,max);
        return ResponseEntity.status(HttpStatus.OK)
                .body(valores);
    }
}


