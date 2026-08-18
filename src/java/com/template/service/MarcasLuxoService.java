package com.template.service;

// Importa o DAO para acessar o banco de dados.
import com.template.model.dao.MarcasLuxoDAO;

// Importa o DTO que representa os dados da marca.
import com.template.model.dto.MarcasLuxoDTO;

import java.util.List;

public class MarcasLuxoService {

    // O Service possui o DAO para realizar as operações no banco.
    private final MarcasLuxoDAO marcasLuxoDAO;

    // Cria o DAO que será utilizado pelo Service.
    public MarcasLuxoService() {
        this.marcasLuxoDAO = new MarcasLuxoDAO();
    }

    // Responsável pela lógica de cadastro da marca.
    public void cadastrarMarca(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        // Monta o objeto DTO com os dados recebidos.
        MarcasLuxoDTO dto = montarDTO(
                null,
                nome,
                estilista,
                paisOrigem,
                anoFundacao,
                tipo
        );

        // Envia o DTO para o DAO salvar no banco.
        marcasLuxoDAO.cadastrarMarca(dto);
    }

    // Responsável pela lógica de alteração da marca.
    public void alterarMarca(
            int id,
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        // Monta o DTO com os dados atualizados.
        MarcasLuxoDTO dto = montarDTO(
                id,
                nome,
                estilista,
                paisOrigem,
                anoFundacao,
                tipo
        );

        // Envia o DTO para o DAO atualizar no banco.
        marcasLuxoDAO.alterarMarca(dto);
    }

    // Envia a marca selecionada para o DAO excluir do banco.
    public void excluirMarca(MarcasLuxoDTO marca) {
        marcasLuxoDAO.excluirMarca(marca);
    }

    // Busca todas as marcas através do DAO.
    public List<MarcasLuxoDTO> listarMarcas() {
        return marcasLuxoDAO.visualizarMarcas();
    }

    // Método auxiliar responsável por montar o DTO.
    private MarcasLuxoDTO montarDTO(
            Integer id,
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        // Cria um novo objeto para armazenar os dados da marca.
        MarcasLuxoDTO marcasLuxoDto = new MarcasLuxoDTO();

        // Define o ID somente quando ele existe.
        if (id != null) {
            marcasLuxoDto.setIdMarca(id);
        }

        // Preenche o DTO com os dados recebidos.
        marcasLuxoDto.setNomeMarca(nome);
        marcasLuxoDto.setEstilista(estilista);
        marcasLuxoDto.setPaisOrigem(paisOrigem);

        // Converte o ano de String para inteiro.
        marcasLuxoDto.setAnoFundacao(
                Integer.parseInt(anoFundacao)
        );

        marcasLuxoDto.setTipo(tipo);

        // Retorna o DTO pronto para ser usado pelo DAO.
        return marcasLuxoDto;
    }
}