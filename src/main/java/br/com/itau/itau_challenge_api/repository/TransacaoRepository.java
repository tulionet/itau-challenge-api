package br.com.itau.itau_challenge_api.repository;

import br.com.itau.itau_challenge_api.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.UUID;


@Component
public class TransacaoRepository {
    public HashMap<UUID, Transacao> listaTransacao = new HashMap<>();
}
