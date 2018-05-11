
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
    private Button btn_loadGame;
    private Button btn_start;
    private Button btn_scorebord;
//    private SpelerMapper spelerMapper;
   
    
    public MainMenu(DomeinController dc, Stage stage)
    {
        this.dc = dc;
        this.stage = stage;
        this.maakMainMenu();
    }
    
    private void loadGame(ActionEvent ae)
    {
        
    }
    
    private void startSpel(ActionEvent ae)
    {
        getChildren().removeAll(btn_loadGame, btn_start, btn_scorebord);
        KeuzePaneel st = new KeuzePaneel(dc, stage, this);
        getChildren().add(st);
    }
    
    private void scorebord(ActionEvent ae)
    {
        getChildren().removeAll(btn_loadGame, btn_start, btn_scorebord);
        ScoreBord sb = new ScoreBord(dc, stage, this);
        getChildren().add(sb);
    }
    
    public void maakMainMenu()
    {
        btn_loadGame = new Button("Laad spel");
        btn_start = new Button("Start nieuw spel");
        btn_scorebord = new Button("Scorebord");
        btn_loadGame.setMinWidth(250);
        btn_start.setMinWidth(250);
        btn_scorebord.setMinWidth(250);
        btn_loadGame.setMinHeight(50);
        btn_start.setMinHeight(50);
        btn_scorebord.setMinHeight(50);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(btn_start, new Insets(20,0,40,0));
        //VBox.setMargin(btn_scorebord, new Insets(20,0,20,0));
        getChildren().addAll(btn_loadGame,btn_start, btn_scorebord);
        btn_loadGame.setOnAction(this::loadGame);
        btn_start.setOnAction(this::startSpel);
        btn_scorebord.setOnAction(this::scorebord);
        stage.setTitle("Stenen Tijdperk");
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.show();
    }
    
    
}
