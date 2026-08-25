package com.template.validator;

import com.template.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class UsuarioValidator {

    // Reúne todas as validações do usuário
    public boolean validarUsuario(String nome, String email, String senha, String login) {

        // Lista de validadores
        List<Validator<String>> validadores = new ArrayList<>();

        // Valida os campos obrigatórios
        validadores.add(new CampoObrigatorioValidator("Nome", nome));
        validadores.add(new CampoObrigatorioValidator("E-mail", email));
        validadores.add(new CampoObrigatorioValidator("Senha", senha));
        validadores.add(new CampoObrigatorioValidator("Login", login));

        // Valida o formato do nome
        validadores.add(new NomeValidator(nome));

        // Percorre todos os validadores
        for (Validator<String> validador : validadores) {

            // Verifica se a validação passou
            if (!validador.validar(validador.getValor())) {

                // Mostra a mensagem do erro
                DialogUtil.mostrarAviso(validador.getMensagemErro());

                return false;
            }
        }

        return true;
    }
}