package com.pamelanakano.proposta_analisecredito.service.strategy.impl;

import com.pamelanakano.proposta_analisecredito.contants.MensagemContants;
import com.pamelanakano.proposta_analisecredito.domain.Proposta;
import com.pamelanakano.proposta_analisecredito.exceptions.StrategyException;
import com.pamelanakano.proposta_analisecredito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        int score = score();

        if (score < 200) {
            throw new StrategyException(String.format(MensagemContants.PONTUACAO_BAIXA_SERASA, proposta.getUsuario().getNome()));
        } else if (score <= 400) {
            return 150;
        } else if (score <= 600) {
            return 200;
        } else {
            return 250;
        }
    }

    /** A API do Serasa é paga, então está sendo implmentado
     * com um mock que retorna um valor randomico */
    private int score() {
        return new Random().nextInt(0,1000);
    }

}
