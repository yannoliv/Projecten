
package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends VBox {
    
    private DomeinController dc;
    private Stage stage;
    private Button btn_start;
    private Button btn_scorebord;
   
    
    public MainMenu(DomeinController dc, Stage stage)
    {
        this.dc = dc;
        this.stage = stage;
        maakMainMenu();       
    }
    
    private void startSpel(ActionEvent ae)
    {
        getChildren().removeAll(btn_start, btn_scorebord);
        KeuzePaneel st = new KeuzePaneel(dc, stage, this);
        getChildren().add(st);
    }
    private void scorebord(ActionEvent ae)
    {
        
        stage.hide();
        Stage scoreBord = new Stage();
        ScoreBord sb = new ScoreBord(dc, scoreBord, this);
        Scene scene = new Scene(sb);
        scoreBord.setScene(scene);
        String cssURL = this.getClass().getResource("/gui/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        scoreBord.setMinWidth(300);
        scoreBord.setMinHeight(300);
        getChildren().add(sb);
       

    }
    
    public void maakMainMenu()
    {
        btn_start = new Button("Start");
        btn_scorebord = new Button("Scorebord");
        btn_start.setMinWidth(250);
        btn_scorebord.setMinWidth(250);
        btn_start.setMinHeight(50);
        btn_scorebord.setMinHeight(50);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(btn_start, new Insets(100,0,25,0));
        getChildren().addAll(btn_start, btn_scorebord);
        btn_start.setOnAction(this::startSpel);
        btn_scorebord.setOnAction(this::scorebord);
        stage.setTitle("Stenen Tijdperk");
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.show();
    }
}
