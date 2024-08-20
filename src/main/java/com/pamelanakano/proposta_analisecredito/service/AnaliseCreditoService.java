package com.pamelanakano.proposta_analisecredito.service;

import com.pamelanakano.proposta_analisecredito.contants.MensagemContants;
import com.pamelanakano.proposta_analisecredito.domain.Proposta;
import com.pamelanakano.proposta_analisecredito.exceptions.StrategyException;
import com.pamelanakano.proposta_analisecredito.service.strategy.CalculoPonto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {

    private final List<CalculoPonto> calculoPontoList;

    private final NotificacaoRabbitMQService notificacaoRabbitMQService;

    private final String propostaConcluidaExchange;

    public AnaliseCreditoService(List<CalculoPonto> calculoPontoList,
                                 NotificacaoRabbitMQService notificacaoRabbitMQService,
                                 @Value("${rabbitmq.propostaconcluida.exchange}") String propostaConcluidaExchange) {
        this.calculoPontoList = calculoPontoList;
        this.notificacaoRabbitMQService = notificacaoRabbitMQService;
        this.propostaConcluidaExchange = propostaConcluidaExchange;
    }

    public void analisar(Proposta proposta) {
        try {
            int totalPontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            boolean propostaAprovada = totalPontos > 400;
            proposta.setAprovada(propostaAprovada);
            if (!propostaAprovada) {
                proposta.setObservacao(String.format(MensagemContants.PONTUACAO_INSUFICIENTE, proposta.getUsuario().getNome()));
            }
        } catch (StrategyException e) {
            proposta.setAprovada(false);
            proposta.setObservacao(e.getMessage());
        }
        notificacaoRabbitMQService.notificar(propostaConcluidaExchange, proposta);
    }

}
