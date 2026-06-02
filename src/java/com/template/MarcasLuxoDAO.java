package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarcasLuxoDAO {

    private static final Logger logger =
            Logger.getLogger(MarcasLuxoDAO.class.getName());

    public void cadastrarMarca(MarcasLuxoDTO marca) {

        String sql =
                "INSERT INTO marcas_luxo(nome_marca, estilista, pais_origem, ano_fundacao, tipo) VALUES(?, ?, ?, ?, ?)";

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, marca.getNomeMarca());
            ps.setString(2, marca.getEstilista());
            ps.setString(3, marca.getPaisOrigem());
            ps.setInt(4, marca.getAnoFundacao());
            ps.setString(5, marca.getTipo());

            ps.execute();

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao cadastrar marca!", e);
        }
    }

    public ArrayList<MarcasLuxoDTO> visualizarMarcas() {

        String sql = "SELECT * FROM marcas_luxo";

        ArrayList<MarcasLuxoDTO> listaMarcas =
                new ArrayList<>();

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet resultado = ps.executeQuery()
        ) {

            while (resultado.next()) {

                MarcasLuxoDTO marca =
                        new MarcasLuxoDTO();

                marca.setIdMarca(resultado.getInt("id"));
                marca.setNomeMarca(resultado.getString("nome_marca"));
                marca.setEstilista(resultado.getString("estilista"));
                marca.setPaisOrigem(resultado.getString("pais_origem"));
                marca.setAnoFundacao(resultado.getInt("ano_fundacao"));
                marca.setTipo(resultado.getString("tipo"));

                listaMarcas.add(marca);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao listar marcas!", e);
        }

        return listaMarcas;
    }

    public void alterarMarca(MarcasLuxoDTO marca) {

        String sql =
                "UPDATE marcas_luxo SET estilista=?, pais_origem=?, ano_fundacao=?, tipo=? WHERE nome_marca=?";

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, marca.getEstilista());
            ps.setString(2, marca.getPaisOrigem());
            ps.setInt(3, marca.getAnoFundacao());
            ps.setString(4, marca.getTipo());
            ps.setString(5, marca.getNomeMarca());

            ps.executeUpdate();

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao alterar marca!", e);
        }
    }

    public void excluirMarca(MarcasLuxoDTO marca) {

        String sql =
                "DELETE FROM marcas_luxo WHERE nome_marca=?";

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, marca.getNomeMarca());

            ps.execute();

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao excluir marca!", e);
        }
    }
}