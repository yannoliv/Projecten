package main;

import domein.DomeinController;
import gui.AantalSpelersControlePaneel;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;


public class StartUpGui extends Application {
    private DomeinController dc = new DomeinController();
    @Override
    public void start(Stage primairyStage) {
        AantalSpelersControlePaneel startSpelRoot = new AantalSpelersControlePaneel(dc);
        Scene scene = new Scene(startSpelRoot);
        primairyStage.setScene(scene);
        primairyStage.setTitle("Stenen Tijdperk Groep A2");
        primairyStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
