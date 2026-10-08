package main.java.com.magdezis.inversionesmagdezis.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.java.com.magdezis.inversionesmagdezis.controller.LoginController;
import main.java.com.magdezis.inversionesmagdezis.controller.RegistroController;
import main.java.com.magdezis.inversionesmagdezis.repository.AuthRepository;
import main.java.com.magdezis.inversionesmagdezis.service.AuthService;

public class SceneManager {

    private Stage primaryStage;
    private String FXML_PATH = "/main/resources/view/";

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FunctionalInterface
    public interface ControllerFactory {

        Object create(Class<?> clazz);
    }

    private void loadView(String fxmlFile, String title, ControllerFactory customFactory) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + fxmlFile));

            loader.setControllerFactory(clazz -> {
                Object controller = customFactory.create(clazz);
                if (controller != null) {
                    return controller;
                }
                try {
                    return clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Error al instanciar el controlador " + clazz.getName() + ": " + e.getMessage(), e);
                }
            });

            Parent root = loader.load();
            Scene scene = new Scene(root, 700, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.centerOnScreen();
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Error al cargar la vista " + fxmlFile + ": " + e.getMessage());
        }
    }

    public void showLoginView() {
        loadView("login-view.fxml", "INCIO DE SESION", clazz -> {
            if (clazz == LoginController.class) {
                AuthRepository repo = new AuthRepository();
                AuthService service = new AuthService(repo);
                return new LoginController(service, this);
            }
            return null;
        });
    }

    public void showRegistroView() {
        loadView("registro-view.fxml ", "REGISTRO", clazz -> {
            if (clazz == RegistroController.class) {
                AuthRepository repo = new AuthRepository();
                AuthService service = new AuthService(repo);
                return new RegistroController(service, this);

            }
            return null;

        }
        );

    }

}
