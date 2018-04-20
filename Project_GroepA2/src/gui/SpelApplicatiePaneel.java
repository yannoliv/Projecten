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
    private SpelerResourcesPaneel resourcePaneel;
    //panelen
    private MapSpel mapSpel = new MapSpel();
    

    public SpelApplicatiePaneel(DomeinController dc)
    {
        this.dc = dc;
        resourcePaneel = new SpelerResourcesPaneel(this, dc);
        buildGui();
    }

    private void buildGui() 
    {
        this.add(mapSpel, 0, 0);
        this.add(resourcePaneel, 1, 0);
        //this.add(hutkaartscherm, 0, 1);
        //this.add(infoscherm, 1, 1)

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(70);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        getColumnConstraints().addAll(column0,column1);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(80);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(20);
        getRowConstraints().addAll(row0,row1);

        setGridLinesVisible(true);
    }

}