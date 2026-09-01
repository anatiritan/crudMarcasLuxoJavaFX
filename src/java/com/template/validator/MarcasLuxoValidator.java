package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.mostrarAviso;

public class MarcasLuxoValidator implements IMarcasLuxoValidator {

    @Override
    public boolean validarCampos(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        // Cria uma lista de validadores
        List<Validator<String>> validadores = new ArrayList<>();

        // Valida os campos obrigatórios
        validadores.add(new CampoObrigatorioValidator("Nome", nome));
        validadores.add(new CampoObrigatorioValidator("Estilista", estilista));
        validadores.add(new CampoObrigatorioValidator("País de origem", paisOrigem));
        validadores.add(new CampoObrigatorioValidator("Ano de fundação", anoFundacao));
        validadores.add(new CampoObrigatorioValidator("Tipo", tipo));

        // Valida especificamente o nome
        validadores.add(new NomeValidator(nome));

        // Percorre todos os validadores
        for (Validator<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {

                mostrarAviso(validador.getMensagemErro());

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean validarAnoFundacao(String anoFundacao) {

        if (anoFundacao == null || anoFundacao.trim().isEmpty()) {

            mostrarAviso("Digite o ano de fundação");

            return false;
        }

        try {

            Integer.parseInt(anoFundacao.trim());

            return true;

        } catch (NumberFormatException e) {

            mostrarAviso("O ano de fundação deve ser um número válido");

            return false;
        }
    }
}