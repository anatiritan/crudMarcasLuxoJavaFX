package com.template.service;

import com.template.model.dao.MarcasLuxoDAO;
import com.template.model.dto.MarcasLuxoDTO;

import java.util.ArrayList;

public class MarcasLuxoService {

    private final MarcasLuxoDAO dao;

    public MarcasLuxoService() {
        dao = new MarcasLuxoDAO();
    }

    public ArrayList<MarcasLuxoDTO> listarMarcas() {
        return dao.visualizarMarcas();
    }

    public void cadastrarMarca(
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        MarcasLuxoDTO marca = new MarcasLuxoDTO();

        marca.setNomeMarca(nome);
        marca.setEstilista(estilista);
        marca.setPaisOrigem(paisOrigem);
        marca.setTipo(tipo);
        marca.setAnoFundacao(Integer.parseInt(anoFundacao));

        dao.cadastrarMarca(marca);
    }

    public void alterarMarca(
            int idMarca,
            String nome,
            String estilista,
            String paisOrigem,
            String anoFundacao,
            String tipo) {

        MarcasLuxoDTO marca = new MarcasLuxoDTO();

        marca.setIdMarca(idMarca);
        marca.setNomeMarca(nome);
        marca.setEstilista(estilista);
        marca.setPaisOrigem(paisOrigem);
        marca.setTipo(tipo);
        marca.setAnoFundacao(Integer.parseInt(anoFundacao));

        dao.alterarMarca(marca);
    }

    public void excluirMarca(MarcasLuxoDTO marca) {
        dao.excluirMarca(marca);
    }
}

