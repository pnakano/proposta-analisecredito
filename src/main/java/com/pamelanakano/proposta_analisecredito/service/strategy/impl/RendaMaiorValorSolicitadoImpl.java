package com.pamelanakano.proposta_analisecredito.service.strategy.impl;

import com.pamelanakano.proposta_analisecredito.domain.Proposta;
import com.pamelanakano.proposta_analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class RendaMaiorValorSolicitadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorValorSolicitado(Proposta proposta) {
        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }

}
