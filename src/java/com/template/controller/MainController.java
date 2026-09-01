package com.template.controller;

import com.template.model.dto.MarcasLuxoDTO;
import com.template.service.IMarcasLuxoService;
import com.template.util.DialogUtil;
import com.template.validator.IMarcasLuxoValidator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    // O Controller depende das interfaces, e não das implementações.
    private final IMarcasLuxoService service;
    private final IMarcasLuxoValidator validator;

    // Construtor recebe as dependências.
    public MainController(
            IMarcasLuxoService service,
            IMarcasLuxoValidator validator) {

        this.service = service;
        this.validator = validator;
    }

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

        // Liga cada coluna ao atributo correspondente do DTO.
        colId.setCellValueFactory(
                new PropertyValueFactory<>("idMarca")
        );

        colNomeMarca.setCellValueFactory(
                new PropertyValueFactory<>("nomeMarca")
        );

        colEstilista.setCellValueFactory(
                new PropertyValueFactory<>("estilista")
        );

        colAnoFundacao.setCellValueFactory(
                new PropertyValueFactory<>("anoFundacao")
        );

        colPaisOrigem.setCellValueFactory(
                new PropertyValueFactory<>("paisOrigem")
        );

        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        // Carrega os dados do banco quando a tela inicia.
        carregarMarcas();
    }


    @FXML
    private void bloquearBtn() {

        if (txtNome.getText().isEmpty()
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

        try {

            // O Controller utiliza a interface do Service.
            tblMarcasLuxo.setItems(
                    FXCollections.observableArrayList(
                            service.listarMarcas()
                    )
            );

            bloquearBtn();

        } catch (Exception e) {

            DialogUtil.mostrarErro(
                    "Não foi possível carregar as marcas."
            );
        }
    }


    @FXML
    private void carregarCampos() {

        MarcasLuxoDTO marca =
                tblMarcasLuxo.getSelectionModel()
                        .getSelectedItem();

        if (marca != null) {

            txtId.setText(
                    String.valueOf(marca.getIdMarca())
            );

            txtNome.setText(
                    marca.getNomeMarca()
            );

            txtEstilista.setText(
                    marca.getEstilista()
            );

            txtPaisOrigem.setText(
                    marca.getPaisOrigem()
            );

            txtTipo.setText(
                    marca.getTipo()
            );

            txtAnoFundacao.setText(
                    String.valueOf(marca.getAnoFundacao())
            );

            bloquearBtn();
        }
    }


    @FXML
    private void btnSalvarAction(ActionEvent event) {

        // Utiliza o Validator através da interface.
        if (!validator.validarCampos(
                txtNome.getText(),
                txtEstilista.getText(),
                txtPaisOrigem.getText(),
                txtAnoFundacao.getText(),
                txtTipo.getText())) {

            return;
        }

        // Valida especificamente o ano de fundação.
        if (!validator.validarAnoFundacao(
                txtAnoFundacao.getText())) {

            return;
        }

        try {

            // Utiliza o Service através da interface.
            service.cadastrarMarca(
                    txtNome.getText(),
                    txtEstilista.getText(),
                    txtPaisOrigem.getText(),
                    txtAnoFundacao.getText(),
                    txtTipo.getText()
            );

            DialogUtil.mostrarSucesso(
                    "Marca cadastrada com sucesso!"
            );

            carregarMarcas();
            limparCampos();

        } catch (Exception e) {

            DialogUtil.mostrarErro(
                    "Não foi possível cadastrar a marca."
            );
        }
    }


    @FXML
    private void btnEditarAction(ActionEvent event) {

        MarcasLuxoDTO marcaSelecionada =
                tblMarcasLuxo.getSelectionModel()
                        .getSelectedItem();

        if (marcaSelecionada == null) {
            return;
        }

        if (!DialogUtil.confirmar(
                "Deseja realmente atualizar esta marca?")) {

            return;
        }

        if (!validator.validarCampos(
                txtNome.getText(),
                txtEstilista.getText(),
                txtPaisOrigem.getText(),
                txtAnoFundacao.getText(),
                txtTipo.getText())) {

            return;
        }

        if (!validator.validarAnoFundacao(
                txtAnoFundacao.getText())) {

            return;
        }

        try {

            service.alterarMarca(
                    marcaSelecionada.getIdMarca(),
                    txtNome.getText(),
                    txtEstilista.getText(),
                    txtPaisOrigem.getText(),
                    txtAnoFundacao.getText(),
                    txtTipo.getText()
            );

            DialogUtil.mostrarSucesso(
                    "Marca atualizada com sucesso!"
            );

            carregarMarcas();
            limparCampos();

        } catch (Exception e) {

            DialogUtil.mostrarErro(
                    "Não foi possível atualizar a marca."
            );
        }
    }


    @FXML
    private void btnDeletarAction(ActionEvent event) {

        MarcasLuxoDTO marcaSelecionada =
                tblMarcasLuxo.getSelectionModel()
                        .getSelectedItem();

        if (marcaSelecionada == null) {
            return;
        }

        if (!DialogUtil.confirmar(
                "Deseja realmente excluir esta marca?")) {

            return;
        }

        try {

            service.excluirMarca(marcaSelecionada);

            DialogUtil.mostrarSucesso(
                    "Marca excluída com sucesso!"
            );

            carregarMarcas();
            limparCampos();

        } catch (Exception e) {

            DialogUtil.mostrarErro(
                    "Não foi possível excluir a marca."
            );
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

        tblMarcasLuxo.getSelectionModel()
                .clearSelection();

        bloquearBtn();
    }
}