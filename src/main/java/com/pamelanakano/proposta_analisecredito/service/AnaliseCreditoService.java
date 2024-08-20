package com.pamelanakano.proposta_analisecredito.service;

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
            boolean propostaAprovada = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum() > 400;
            proposta.setAprovada(propostaAprovada);
        } catch (StrategyException e) {
            proposta.setAprovada(false);
            proposta.setObservacao(e.getMessage());
        }
        notificacaoRabbitMQService.notificar(propostaConcluidaExchange, proposta);
    }

}
