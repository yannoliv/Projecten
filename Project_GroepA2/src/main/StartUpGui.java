package main;

import domein.DomeinController;
import gui.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class StartUpGui extends Application {
    private DomeinController dc = new DomeinController();
    @Override
    public void start(Stage primairyStage) {
        MainMenu starten = new MainMenu(dc, primairyStage);
        Scene scene = new Scene(starten);
        primairyStage.setScene(scene);
        String cssURL = this.getClass().getResource("/gui/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
    }
public static void main(String[] args)
    {
        launch(args);
    }
}