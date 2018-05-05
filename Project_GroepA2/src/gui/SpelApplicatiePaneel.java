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
    private MapSpel mapSpel;
    private MenuBar menuBar = new MenuBar();
    private KaartPaneel kaartPaneel;
    private GereedschapPaneel gereedschapPaneel;
    
    
    private RowConstraints row0 = new RowConstraints();
    private RowConstraints row1 = new RowConstraints();
    private RowConstraints row2 = new RowConstraints();

    
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
        mapSpel = new MapSpel(dc, this);
        kaartPaneel = new KaartPaneel(this, dc, mapSpel);
        buildGui();
        System.out.println(dc.getSpelerLijst().get(0).getKleur());
    }

    private void buildGui() 
    {
        BackgroundSize bgs = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage bgImg = new BackgroundImage(new Image("/images/kaartPaneel_V.png"), 
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER, bgs);
        resourcePaneel.setBackground(new Background(bgImg));
        
        scroll_resourcePaneel.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll_resourcePaneel.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scroll_resourcePaneel.setFitToWidth(true);
        scroll_resourcePaneel.setContent(resourcePaneel);
//        this.add(menuBar, 0, 0);
        //scroll_resourcePaneel.applyCss();
        
        this.add(mapSpel, 0, 1);
        this.add(scroll_resourcePaneel, 1, 1);
        this.add(kaartPaneel, 0, 2);
//        this.add(infoscherm, 1, 2)
        
       
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(70);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        getColumnConstraints().addAll(column0,column1);
        
        //.setPadding(new Insets(10, 10, 10, 10));
        
        row0.setPercentHeight(0);
        row1.setPercentHeight(75);
        row2.setPercentHeight(20);
        getRowConstraints().addAll(row0,row1,row2);
        
        setGridLinesVisible(true); 
        
    }
    
    public void formRefresh()
    {
        resourcePaneel.updateResourcePaneel();
    }
    
    public RowConstraints getRow0() {
        return row0;
    }
}

