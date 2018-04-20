package main;

import domein.DomeinController;
import gui.MainMenu;
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
    }
public static void main(String[] args)
    {
        launch(args);
    }
}