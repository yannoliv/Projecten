package main;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import gui.HoofdPaneel;


public class StartUpGui extends Application {
    
    @Override
    public void start(Stage primairyStage) {
        HoofdPaneel root = new HoofdPaneel();
        Scene scene = new Scene(root);
        primairyStage.setScene(scene);
        primairyStage.setTitle("Stenen Tijdperk Groep A2");
        primairyStage.show();
    }

}
