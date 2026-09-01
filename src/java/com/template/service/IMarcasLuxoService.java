package com.template.service;

import com.template.model.dto.MarcasLuxoDTO;

import java.util.List;

public interface IMarcasLuxoService {

    void cadastrarMarca(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo
    );

    void alterarMarca(
            int id,
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo
    );

    void excluirMarca(MarcasLuxoDTO marca);

    List<MarcasLuxoDTO> listarMarcas();
}