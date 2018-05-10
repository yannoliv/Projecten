package gui;

import domein.DomeinController;
import java.awt.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ScoreBord extends HBox
{
    private DomeinController dc;
    private Stage stage;
    private MainMenu mainMenu;
    private Button btn_confirm = new Button();
    
    public ScoreBord(DomeinController dc, Stage stage, MainMenu mainMenu) {
        this.dc = dc;
        this.stage = stage;
        this.mainMenu = mainMenu;
        btn_confirm.setText("Confirm");
        Button btn = new Button("hey");
        getChildren().addAll(btn_confirm,btn);
        VBox vertBox = new VBox();
        vertBox.setPrefSize(400, 400);
        vertBox.setAlignment(Pos.CENTER);

        // horizontal box for the players
        HBox PlayerName = new HBox();
        PlayerName.setAlignment(Pos.CENTER);
        PlayerName.setPadding(new Insets(25, 25, 25, 25));

        // horizontal box for the scores
        HBox Score = new HBox();
        Score.setAlignment(Pos.CENTER);

        // horizontal box to show a goal
        HBox Goal = new HBox();
        Goal.setAlignment(Pos.CENTER);

        // player1 name
        Label team1 = new Label("Player 1");
      
        team1.setAlignment(Pos.TOP_LEFT);
        team1.setPadding(new Insets(25, 25, 25, 25));

        // player2 name
        Label team2 = new Label("Player 2");
       
        team2.setAlignment(Pos.TOP_RIGHT);
        team2.setPadding(new Insets(25, 25, 25, 25));

        // player1 score
        Label text1 = new Label();
        text1.setAlignment(Pos.CENTER_LEFT);
       // text1.setText(Integer.toString());
        text1.setPadding(new Insets(25, 30, 50, 25));

        // player2 score
        Label text2 = new Label();
        text2.setAlignment(Pos.CENTER_RIGHT);
        //text2.setText(Integer.toString());
        text2.setPadding(new Insets(25, 30, 50, 25));
      
}
    
}