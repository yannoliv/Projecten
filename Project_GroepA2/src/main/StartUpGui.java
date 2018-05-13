package main;

import Persistentie.SpelerMapper;
import domein.DomeinController;
import gui.*;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    
    
    @Override
    public void stop(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Spel opslaan...");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText(String.format("Wilt u het spel opslaan?"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("Stage is closing");
            SpelerMapper spelerMapper = new SpelerMapper();
            spelerMapper.slaSpelOp(dc);
        }
    
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}