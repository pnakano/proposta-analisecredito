package com.pamelanakano.proposta_analisecredito.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private String email;

    private Double renda;

}
