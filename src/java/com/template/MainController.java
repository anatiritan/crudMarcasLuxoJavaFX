package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainController {

    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnLimpar;

    @FXML
    private TableView<MarcasLuxoDTO> tblMarcasLuxo;

    @FXML
    private TableColumn<MarcasLuxoDTO, Integer> colId;
    @FXML
    private TableColumn<MarcasLuxoDTO, String> colNomeMarca;
    @FXML
    private TableColumn<MarcasLuxoDTO, String> colEstilista;
    @FXML
    private TableColumn<MarcasLuxoDTO, Integer> colAnoFundacao;
    @FXML
    private TableColumn<MarcasLuxoDTO, String> colPaisOrigem;
    @FXML
    private TableColumn<MarcasLuxoDTO, String> colTipo;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtAnoFundacao;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEstilista;
    @FXML
    private TextField txtPaisOrigem;
    @FXML
    private TextField txtTipo;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idMarca"));
        colNomeMarca.setCellValueFactory(new PropertyValueFactory<>("nomeMarca"));
        colEstilista.setCellValueFactory(new PropertyValueFactory<>("estilista"));
        colAnoFundacao.setCellValueFactory(new PropertyValueFactory<>("anoFundacao"));
        colPaisOrigem.setCellValueFactory(new PropertyValueFactory<>("paisOrigem"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        carregarMarcas();
    }

    @FXML
    private void bloquearBtn() {
        if (
                txtNome.getText().isEmpty()
                        || txtAnoFundacao.getText().isEmpty()
                        || txtEstilista.getText().isEmpty()
                        || txtPaisOrigem.getText().isEmpty()
                        || txtTipo.getText().isEmpty()) {
            btnSalvar.setDisable(true);
            btnDeletar.setDisable(true);
            btnEditar.setDisable(true);
            return;
        }

        btnSalvar.setDisable(false);
        btnEditar.setDisable(false);
        btnDeletar.setDisable(false);
    }

    @FXML
    private void carregarMarcas() {

        MarcasLuxoDAO dao = new MarcasLuxoDAO();

        ArrayList<MarcasLuxoDTO> lista =
                dao.visualizarMarcas();

        tblMarcasLuxo.setItems(
                FXCollections.observableArrayList(lista)
        );

        bloquearBtn();
    }

    @FXML
    private void carregarCampos() {

        MarcasLuxoDTO marca = tblMarcasLuxo.getSelectionModel().getSelectedItem();

        if (marca != null) {

            txtId.setText(String.valueOf(marca.getIdMarca()));
            txtNome.setText(marca.getNomeMarca());
            txtEstilista.setText(marca.getEstilista());
            txtPaisOrigem.setText(marca.getPaisOrigem());
            txtTipo.setText(marca.getTipo());
            txtAnoFundacao.setText(Integer.toString(marca.getAnoFundacao()));

            bloquearBtn();

            /*
            txtAnoFundacao.setValue(
                    LocalDate.of(
                            marca.getAnoFundacao(),
                            1,
                            1
                    )
            );
            */

        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {

        MarcasLuxoDTO marcaDto = new MarcasLuxoDTO();

        marcaDto.setNomeMarca(txtNome.getText());
        marcaDto.setEstilista(txtEstilista.getText());
        marcaDto.setPaisOrigem(txtPaisOrigem.getText());
        marcaDto.setTipo(txtTipo.getText());
        marcaDto.setAnoFundacao(Integer.parseInt(txtAnoFundacao.getText()));
        /*
        if (txtAnoFundacao.getValue() != null) {
            marca.setAnoFundacao(
                    txtAnoFundacao.getValue().getYear()
            );
        }
        */


        MarcasLuxoDAO dao = new MarcasLuxoDAO();

        dao.cadastrarMarca(marcaDto);

        carregarMarcas();
        limparCampos();
        bloquearBtn();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {

        MarcasLuxoDTO marcaSelecionada =
                tblMarcasLuxo.getSelectionModel().getSelectedItem();

        if (marcaSelecionada != null) {

            MarcasLuxoDTO marca =
                    new MarcasLuxoDTO();

            marca.setIdMarca(
                    marcaSelecionada.getIdMarca()
            );

            marca.setNomeMarca(txtNome.getText());
            marca.setEstilista(txtEstilista.getText());
            marca.setPaisOrigem(txtPaisOrigem.getText());
            marca.setTipo(txtTipo.getText());
            marca.setAnoFundacao(Integer.parseInt(txtAnoFundacao.getText()));

            /*
            if (txtAnoFundacao.getValue() != null) {
                marca.setAnoFundacao(
                        txtAnoFundacao.getValue().getYear()
                );
            }
            */

            MarcasLuxoDAO dao =
                    new MarcasLuxoDAO();

            dao.alterarMarca(marca);

            carregarMarcas();
            limparCampos();
            bloquearBtn();
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {

        MarcasLuxoDTO marcaSelecionada =
                tblMarcasLuxo.getSelectionModel().getSelectedItem();

        if (marcaSelecionada != null) {

            MarcasLuxoDAO dao =
                    new MarcasLuxoDAO();

            dao.excluirMarca(marcaSelecionada);

            carregarMarcas();
            limparCampos();
            bloquearBtn();
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    private void limparCampos() {

        txtId.clear();
        txtNome.clear();
        txtEstilista.clear();
        txtPaisOrigem.clear();
        txtTipo.clear();
        txtAnoFundacao.clear();

        // txtAnoFundacao.setText(null);

        tblMarcasLuxo.getSelectionModel()
                .clearSelection();

        bloquearBtn();
    }
}