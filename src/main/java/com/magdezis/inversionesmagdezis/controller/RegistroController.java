package main.java.com.magdezis.inversionesmagdezis.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import main.java.com.magdezis.inversionesmagdezis.service.AuthService;
import main.java.com.magdezis.inversionesmagdezis.utils.SceneManager;

public class RegistroController implements Initializable {

    private AuthService authService;
    private SceneManager sceneManager;

    public RegistroController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
