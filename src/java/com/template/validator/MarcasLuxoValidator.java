package com.template.validator;

// Importa apenas o método de aviso do DialogUtil.
import static com.template.util.DialogUtil.mostrarAviso;

public class MarcasLuxoValidator {

    // Valida se todos os campos obrigatórios foram preenchidos.
    public static boolean validarCampos(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        // Verifica se algum campo está vazio.
        if (nome.isEmpty()
                || estilista.isEmpty()
                || paisOrigem.isEmpty()
                || anoFundacao.isEmpty()
                || tipo.isEmpty()) {

            // Mostra um aviso caso algum campo esteja vazio.
            mostrarAviso("Preencha todos os campos antes de prosseguir");

            // Impede que a operação continue.
            return false;
        }

        // Indica que os campos estão preenchidos.
        return true;
    }

    // Valida especificamente o ano de fundação.
    public static boolean validarAnoFundacao(String anoFundacao) {

        // Verifica se o campo está vazio ou nulo.
        if (anoFundacao == null || anoFundacao.isEmpty()) {

            mostrarAviso("Digite o ano de fundação");

            return false;
        }

        try {

            // Tenta converter o ano para número inteiro.
            Integer.parseInt(anoFundacao.trim());

            // Se conseguiu converter, o valor é válido.
            return true;

        } catch (NumberFormatException e) {

            // Caso não seja um número, mostra um aviso.
            mostrarAviso(
                    "O ano de fundação deve ser um número válido"
            );

            return false;
        }
    }
}