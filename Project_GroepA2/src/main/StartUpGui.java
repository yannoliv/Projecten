package main;

import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import gui.HoofdPaneel;


public class StartUpGui extends Application {
    DomeinController dc = new DomeinController();
    @Override
    public void start(Stage primairyStage) {
        HoofdPaneel root = new HoofdPaneel(dc);
        Scene scene = new Scene(root);
        primairyStage.setScene(scene);
        primairyStage.setTitle("Stenen Tijdperk Groep A2");
        primairyStage.show();
    }

}
