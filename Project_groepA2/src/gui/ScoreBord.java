package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Persistentie.SpelerMapper;


public class ScoreBord extends VBox
{
    private DomeinController dc;
    private Stage stage;
    private MainMenu mainMenu;
    private Label lbl_label;
    private SpelerMapper spelerMapper;
    private Button btn_home;
    
    public ScoreBord(DomeinController dc, Stage stage, MainMenu mainMenu) 
    {
        this.dc = dc;
        this.stage = stage;
        this.mainMenu = mainMenu;
        this.spelerMapper = new SpelerMapper();
        
        btn_home = new Button("Home");
        btn_home.setMinWidth(250);
        btn_home.setMinHeight(50);
        
        lbl_label = new Label("HighScores");
        lbl_label.setStyle("-fx-font-size:4em; -fx-padding:15px;");
        this.getChildren().addAll(btn_home,lbl_label);
        
        for (int i = 0; i < 10; i++) {
            lbl_label = new Label(String.format("%-5d  %-10s   %30d", spelerMapper.geefHighScores(dc).get(i).getSpelerNummer() + 1, spelerMapper.geefHighScores(dc).get(i).getNaam(), spelerMapper.geefHighScores(dc).get(i).getTotaalScore()));
            lbl_label.setStyle("-fx-padding: 10px;-fx-font-size:2em;");
            this.getChildren().add(lbl_label);
        }
        this.setStyle("-fx-fill:rgb(255,255,255);-fx-alignment: center;-fx-background-color:rgba(255,255,255,0.7);");
        
        btn_home.setOnAction(this::home);
    }
    
    public void home(ActionEvent ae)
    {
        this.setStyle(null);
        getChildren().clear();
        MainMenu mm = new MainMenu(dc, stage);
        getChildren().add(mm);
    }
    
}