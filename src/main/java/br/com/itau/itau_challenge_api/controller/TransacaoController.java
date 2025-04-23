package br.com.itau.itau_challenge_api.controller;


import br.com.itau.itau_challenge_api.model.Transacao;
import br.com.itau.itau_challenge_api.service.TransacaoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> receberTransacao(@RequestBody Transacao transacao) {
        return transacaoService.validarTransacao(transacao);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodasTransacoes() {
        return transacaoService.buscaTodasTransacoes();
    }

    @DeleteMapping
    public ResponseEntity<?> deletarTodasTransacoes() {
        return transacaoService.deletarTodasTransacoes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTrransacao(@PathVariable UUID id) {
        return transacaoService.deletarTransacao(id);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<?> calcularEstatiscas() {
        return transacaoService.calcularEstatiscas();
    }
}
