package main;

import domein.DomeinController;
import gui.SpelPaneel;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;


public class StartUpGui extends Application {
    private DomeinController dc = new DomeinController();
    @Override
    public void start(Stage primairyStage) {
        SpelPaneel startSpelRoot = new SpelPaneel(dc, primairyStage);
        Scene scene = new Scene(startSpelRoot);
        primairyStage.setScene(scene);
    }

}
