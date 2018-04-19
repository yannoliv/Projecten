package gui;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;


public class SpelApplicatiePaneel extends GridPane 
{
    private HoofdPaneel hoofdpaneel;
    private DomeinController dc;
    private Scorebord scorebord;
    private MapSpel mapSpel = new MapSpel();
    
    public SpelApplicatiePaneel(HoofdPaneel hoofdpaneel, DomeinController dc)
    {
        this.hoofdpaneel = hoofdpaneel;
        this.dc = dc;
        

        GridPane gridPane = new GridPane();
        
        this.add(mapSpel, 0, 0);
        //this.add(spelerstats, 1, 0);
        //this.add(hutkaartscherm, 0, 1);
        //this.add(infoscherm, 1, 1)
        
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(65);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        getColumnConstraints().addAll(column0,column1);
        
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(75);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
        gridPane.getRowConstraints().addAll(row1,row2);  
        
        setGridLinesVisible(true);
        
        //top pane
        //Pane top = new Pane();
        //top.setPrefSize(100,100);
        //top.setStyle("-fx-background-color: rgb(255,0,0)");
        //borderPane.setTop(top);

        //bottom pane
        //Pane bottom = new Pane();
        //bottom.setStyle("-fx-background-color: rgb(0,0,255)");
        //borderPane.setBottom(bottom);

        //center pane
        //MapSpel achtergrond = new MapSpel();
        //borderPane.setCenter(achtergrond);

        //right pane
        //Pane right = new Pane();
        //right.setStyle("-fx-background-color: #00FF00");
        //borderPane.setRight(right);
        
        
        //add borderpane
        getChildren().addAll(gridPane);
        
        
        /*
        
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(65);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        getColumnConstraints().addAll(column0,column1);
        
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(75);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
        getRowConstraints().addAll(row1,row2)    
        
        */
    }
    
}
