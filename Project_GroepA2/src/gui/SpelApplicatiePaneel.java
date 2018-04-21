package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class SpelApplicatiePaneel extends GridPane 
{
    //declaraties
    private DomeinController dc;
    private SpelerResourcesPaneel resourcePaneel;
    private Stage stage;
    //panelen
    private MapSpel mapSpel = new MapSpel();
    private MenuBar menuBar = new MenuBar();
    

    public SpelApplicatiePaneel(DomeinController dc, Stage stage)
    {
        this.dc = dc;
        this.stage = stage;
        stage.setWidth(1245);
        stage.setHeight(787);
        stage.show();
        resourcePaneel = new SpelerResourcesPaneel(this, dc);
        buildGui();
    }

    private void buildGui() 
    {
//        this.add(menuBar, 0, 0);
        this.add(mapSpel, 0, 1);
        this.add(resourcePaneel, 1, 1);
//        this.add(hutkaartscherm, 0, 2);
//        this.add(infoscherm, 1, 2)
        
       
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(70);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        getColumnConstraints().addAll(column0,column1);
        
        //.setPadding(new Insets(10, 10, 10, 10));

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(0);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(75);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(20);
        getRowConstraints().addAll(row0,row1,row2);
        

        setGridLinesVisible(true); 
    }
    }
