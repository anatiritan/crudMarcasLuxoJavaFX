package com.template.model.dao;

import com.template.model.dto.Conexao;
import com.template.model.dto.MarcasLuxoDTO;

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
                "INSERT INTO MarcasLuxo(nomemarca, estilista, paisorigem, anofundacao, tipo) VALUES(?, ?, ?, ?, ?)";

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

            throw new RuntimeException(
                    "Erro ao cadastrar marca!", e
            );
        }
    }

    public ArrayList<MarcasLuxoDTO> visualizarMarcas() {

        String sql = "SELECT * FROM MarcasLuxo";

        ArrayList<MarcasLuxoDTO> listaMarcas =
                new ArrayList<>();

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet resultado = ps.executeQuery()  //recebe os dados que vieram do banco
        ) {

            while (resultado.next()) {     //laço percorre todos os registros encontrados

                MarcasLuxoDTO marca = new MarcasLuxoDTO();

                marca.setIdMarca(resultado.getInt("id"));
                marca.setNomeMarca(resultado.getString("nomeMarca"));
                marca.setEstilista(resultado.getString("estilista"));
                marca.setPaisOrigem(resultado.getString("paisOrigem"));
                marca.setAnoFundacao(resultado.getInt("anoFundacao"));
                marca.setTipo(resultado.getString("tipo"));

                listaMarcas.add(marca);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao listar marcas!", e);

            throw new RuntimeException(
                    "Erro ao listar marcas!", e
            );
        }

        return listaMarcas;
    }

    public void alterarMarca(MarcasLuxoDTO marca) {

        String sql =
                "UPDATE MarcasLuxo SET estilista=?, paisorigem=?, anofundacao=?, tipo=? WHERE nomemarca=?";

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, marca.getEstilista());   //enviam os valores digitados pelo usuário para o banco
            ps.setString(2, marca.getPaisOrigem());
            ps.setInt(3, marca.getAnoFundacao());
            ps.setString(4, marca.getTipo());
            ps.setString(5, marca.getNomeMarca());

            ps.executeUpdate();

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao alterar marca!", e);

            throw new RuntimeException(
                    "Erro ao alterar marca!", e
            );
        }
    }

    public void excluirMarca(MarcasLuxoDTO marca) {

        String sql =
                "DELETE FROM MarcasLuxo WHERE nomemarca=?";

        try (
                Connection c = new Conexao().getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {

            ps.setString(1, marca.getNomeMarca());  //enviam os valores digitados pelo usuário para o banco

            ps.execute();

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Erro ao excluir marca!", e);

            throw new RuntimeException(
                    "Erro ao excluir marca!", e
            );
        }
    }
}
