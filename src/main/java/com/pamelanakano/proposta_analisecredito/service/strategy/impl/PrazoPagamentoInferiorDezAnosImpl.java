package com.pamelanakano.proposta_analisecredito.service.strategy.impl;

import com.pamelanakano.proposta_analisecredito.domain.Proposta;
import com.pamelanakano.proposta_analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class PrazoPagamentoInferiorDezAnosImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() < 120 ? 80 : 0;
    }

}
