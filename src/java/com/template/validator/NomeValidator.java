package com.template.validator;

import java.util.regex.Pattern;

public class NomeValidator  implements Validator<String>{
    private static final String NOME_REGEX = "^[a-zA-ZáéíóúàèìòùâêîôûãõçÇÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕ\\s]+$";
    private final Pattern pattern = Pattern.compile(NOME_REGEX);
    private final String nome;

    public NomeValidator(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean validar(String valorAtual) {
        return this.nome != null && pattern.matcher(this.nome).matches();
    }

    @Override
    public String getMensagemErro() {
        return "Digite um nome válido!";
    }

    @Override
    public String getValor() {
        return nome;
    }
}
