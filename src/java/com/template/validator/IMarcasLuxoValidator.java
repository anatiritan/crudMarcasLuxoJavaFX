package com.template.validator;

public interface IMarcasLuxoValidator {

    boolean validarCampos(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo
    );

    boolean validarAnoFundacao(String anoFundacao);
}