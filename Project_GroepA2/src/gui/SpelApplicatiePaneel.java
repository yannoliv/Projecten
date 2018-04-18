
package gui;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;


public class SpelApplicatiePaneel extends GridPane 
{
    private HoofdPaneel hoofdpaneel;
    private DomeinController dc;
    private Scorebord scorebord;
    
    public SpelApplicatiePaneel(HoofdPaneel hoofdpaneel, DomeinController dc)
    {
        this.hoofdpaneel = hoofdpaneel;
        this.dc = dc;
        
//        GridPane.setMargin(this, new Insets(20,20,20,20));
//        setPrefSize(225,250);
//        
//        //Scorebord
//        scorebord = new Scorebord(this,dc);
//        GridPane.setConstraints(scorebord, 2, 1);

        BorderPane borderPane = new BorderPane();
        
        this.setMinSize(500.0, 500.0);
        setMinSize(800, 800);
        setPrefSize(2000, 2000);

        Pane top = new Pane();
        top.setPrefHeight(100);
        top.setStyle("-fx-background-color: rgb(255,0,0)");
        borderPane.setTop(top);

        Pane bottom = new Pane();
        bottom.setPrefHeight(100);
        bottom.setStyle("-fx-background-color: rgb(0,0,255)");
        borderPane.setBottom(bottom);

        
        
        ImageView png_spelMap;
        png_spelMap = new ImageView(new Image(getClass().getResourceAsStream("/images/mapSpel.png")));
        png_spelMap.setFitWidth(1536/2);
        png_spelMap.setFitHeight(1024/2);
        
        Pane center = new Pane();
        center.setPrefSize(600, 400);
        center.setStyle("-fx-background-color: #FFFF00");
        borderPane.setCenter(png_spelMap);


        
        
        
        
        
        
        Pane right = new Pane();
        right.setPrefWidth(200);
        right.setStyle("-fx-background-color: #00FF00");
        borderPane.setRight(right);

        getChildren().addAll(borderPane);
        
    }
    
}
