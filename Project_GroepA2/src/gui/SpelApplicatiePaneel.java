package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private KaartPaneel kaartPaneel;
    
    public SpelApplicatiePaneel(DomeinController dc, Stage stage)
    {
        this.dc = dc;
        this.stage = stage;
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setMinWidth(1280*0.5);
        stage.setMinHeight(720*0.5);
        stage.show();
        
        scroll_resourcePaneel = new ScrollPane();
        resourcePaneel = new SpelerResourcesPaneel(this, dc);
        spelApplicatie = new MapSpel(dc, this);
        kaartPaneel = new KaartPaneel(this, dc);
        buildGui();
    }

    private void buildGui() 
    {
        BackgroundSize bgs = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage bgImg = new BackgroundImage(new Image("/images/stoneWall.png"), 
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER, bgs);
        resourcePaneel.setBackground(new Background(bgImg));

        
        //help die shit wilt legit ni sizen xD btx bgs bandos godsword confirmed?
        
        BackgroundImage bgImg2 = new BackgroundImage(new Image("/images/kaartPaneel.png"),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, bgs);
        kaartPaneel.setBackground(new Background(bgImg2));
        kaartPaneel.setMinSize(100, 200);
        
        scroll_resourcePaneel.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll_resourcePaneel.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scroll_resourcePaneel.setFitToWidth(true);
        scroll_resourcePaneel.setContent(resourcePaneel);
//        this.add(menuBar, 0, 0);
        scroll_resourcePaneel.applyCss();
        this.add(spelApplicatie, 0, 1);
        this.add(scroll_resourcePaneel, 1, 1);
        
        this.add(kaartPaneel, 0, 2);
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

