package main.java.com.magdezis.inversionesmagdezis;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.magdezis.inversionesmagdezis.utils.SceneManager;

public class App extends Application {

    Stage primaryStage;
    SceneManager scene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        SceneManager scene = new SceneManager(primaryStage);
        scene.showLoginView();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
