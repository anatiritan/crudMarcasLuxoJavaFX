package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/MarcasLuxo"; // endereço do banco
    private static final String USUARIO = "postgres"; // usuário do banco
    private static final String SENHA = "postgres";   // senha do banco

    // Método que retorna a conexão com o banco
    public Connection getConnection() {
        try {
            // Tenta estabelecer a conexão
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (SQLException e) {
            // Caso dê erro, lança uma exceção com mensagem
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage());
        }
    }
}
