package com.pamelanakano.proposta_analisecredito.service.strategy.impl;

import com.pamelanakano.proposta_analisecredito.contants.MensagemContants;
import com.pamelanakano.proposta_analisecredito.domain.Proposta;
import com.pamelanakano.proposta_analisecredito.exceptions.StrategyException;
import com.pamelanakano.proposta_analisecredito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class NomeNegativadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) {
            throw new StrategyException(String.format(MensagemContants.CLIENTE_NEGATIVADO, proposta.getUsuario().getNome()));
        }
        return 100;
    }

    /** A API do Serasa é paga, então está sendo implmentado
      * com um mock que retorna um valor randomico */
    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }
}
