package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class SpelApplicatiePaneel extends GridPane 
{
    //declaraties
    private DomeinController dc;
    ScrollPane scroll_resourcePaneel;
    private SpelerResourcesPaneel resourcePaneel;
    private Stage stage;
    //panelen
    private MapSpel spelApplicatie;
    private MenuBar menuBar = new MenuBar();
    

    public SpelApplicatiePaneel(DomeinController dc, Stage stage)
    {
        this.dc = dc;
        this.stage = stage;
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.show();
        resourcePaneel = new SpelerResourcesPaneel(this, dc);
        spelApplicatie = new MapSpel(dc, this);
        
        //zorgt ervoor da de resourcepaneel kan scrollen
        scroll_resourcePaneel = new ScrollPane();
        scroll_resourcePaneel.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll_resourcePaneel.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scroll_resourcePaneel.setContent(resourcePaneel);
        
        buildGui();
    }

    private void buildGui() 
    {
//        this.add(menuBar, 0, 0);
        this.add(spelApplicatie, 0, 1);
        this.add(scroll_resourcePaneel, 1, 1);
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
    
    public void formRefresh()
    {
        resourcePaneel.updateResourcePaneel();
    }
}

