package com.template.validator;

public class MarcasLuxoValidator {

    public boolean validarCampos(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        return !nome.isEmpty()
                && !estilista.isEmpty()
                && !paisOrigem.isEmpty()
                && !anoFundacao.isEmpty()
                && !tipo.isEmpty();
    }

    public boolean validarAnoFundacao(String anoFundacao) {

        try {
            Integer.parseInt(anoFundacao);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}

