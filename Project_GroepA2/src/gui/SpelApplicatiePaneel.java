package gui;

import domein.DomeinController;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;


public class SpelApplicatiePaneel extends GridPane 
{
    //declaraties
    private DomeinController dc;
    GridPane gridPane;
    
    //panelen
    private MapSpel mapSpel = new MapSpel();
    private Scorebord scorebord = new Scorebord(this, dc);
    
    public SpelApplicatiePaneel(DomeinController dc)
    {
        this.dc = dc;
        
        gridPane = new GridPane();
        
        buildGui();
    }
    
    private void buildGui() {
        this.add(mapSpel, 0, 0);
        this.add(scorebord, 1, 0);
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
        Region spring = new Region();
        HBox.setHgrow(spring, Priority.ALWAYS);
        this.getChildren().addAll(gridPane);
    }
    
}

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
        //getChildren().addAll(gridPane);
        
        
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
