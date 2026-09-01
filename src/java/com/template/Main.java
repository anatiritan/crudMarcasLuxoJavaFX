package com.template;

import com.template.controller.MainController;
import com.template.model.dao.MarcasLuxoDAO;
import com.template.service.IMarcasLuxoService;
import com.template.service.MarcasLuxoService;
import com.template.validator.IMarcasLuxoValidator;
import com.template.validator.MarcasLuxoValidator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Cria o DAO responsável pelo acesso ao banco.
        MarcasLuxoDAO marcasLuxoDAO = new MarcasLuxoDAO();

        // Cria o Service através da interface.
        IMarcasLuxoService marcasLuxoService =
                new MarcasLuxoService(marcasLuxoDAO);

        // Cria o Validator através da interface.
        IMarcasLuxoValidator marcasLuxoValidator =
                new MarcasLuxoValidator();

        // Carrega o arquivo FXML.
        FXMLLoader loader =
                new FXMLLoader(Main.class.getResource("main.fxml"));

        // Injeta as dependências no Controller.
        loader.setControllerFactory(controllerClass -> {

            if (controllerClass == MainController.class) {

                return new MainController(
                        marcasLuxoService,
                        marcasLuxoValidator
                );
            }

            try {
                return controllerClass.getDeclaredConstructor().newInstance();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Cria a tela.
        Scene scene =
                new Scene(loader.load(), 600, 400);

        stage.setTitle("Cadastro de Marcas de Luxo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}