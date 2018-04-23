package main;

import domein.DomeinController;
import gui.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class StartUpGui extends Application {
    private DomeinController dc = new DomeinController();
    @Override
    public void start(Stage primaryStage) {
        MainMenu starten = new MainMenu(dc, primaryStage);
        Scene scene = new Scene(starten);
        primaryStage.setScene(scene);
        String cssURL = this.getClass().getResource("/gui/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);
    }
public static void main(String[] args)
    {
        launch(args);
    }
}