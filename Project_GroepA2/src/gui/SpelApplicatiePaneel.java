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

    //panelen
    private MapSpel mapSpel = new MapSpel();
    private SpelerResourcesPaneel scorebord = new SpelerResourcesPaneel(this, dc);

    public SpelApplicatiePaneel(DomeinController dc)
    {
        this.dc = dc;
        buildGui();
    }

    private void buildGui() 
    {
        this.add(mapSpel, 1, 1);
        this.add(scorebord, 2, 1);
        //this.add(hutkaartscherm, 0, 1);
        //this.add(infoscherm, 1, 1)

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(30);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        getColumnConstraints().addAll(column0,column1, column2);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(20);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(80);
        getRowConstraints().addAll(row0,row1);

        setGridLinesVisible(true);
    }

}