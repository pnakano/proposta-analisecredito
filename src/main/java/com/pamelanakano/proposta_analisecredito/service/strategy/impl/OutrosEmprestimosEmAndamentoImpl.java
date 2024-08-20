package com.pamelanakano.proposta_analisecredito.service.strategy.impl;

import com.pamelanakano.proposta_analisecredito.domain.Proposta;
import com.pamelanakano.proposta_analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OutrosEmprestimosEmAndamentoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimosEmAndamento() ? -25 : 0;
    }

    /** Mock simulando consulta no BACEN **/
    private boolean outrosEmprestimosEmAndamento(){
        return new Random().nextBoolean();
    }

}
