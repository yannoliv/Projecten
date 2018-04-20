
package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        
        btn_start = new Button("Start");
        btn_scorebord = new Button("Scorebord");
        btn_start.setMinWidth(250);
        btn_scorebord.setMinWidth(250);
        btn_start.setMinHeight(50);
        btn_scorebord.setMinHeight(50);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(btn_start, new Insets(100,0,25,0));
        getChildren().addAll(btn_start, btn_scorebord);
        stage.setTitle("Main menu");
        stage.setWidth(720);
        stage.setHeight(530);
        stage.show();
        
        btn_start.setOnAction(this::startSpel);
    }
    
    private void startSpel(ActionEvent ae)
    {
        getChildren().removeAll(btn_start, btn_scorebord);
        KeuzePaneel st = new KeuzePaneel(dc, stage);
        getChildren().add(st);
    }
}
