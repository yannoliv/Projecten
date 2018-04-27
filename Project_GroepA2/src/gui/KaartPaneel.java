package gui;

import domein.DomeinController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KaartPaneel extends VBox{
      private DomeinController dc;
      private SpelApplicatiePaneel spelApplicatiePaneel;
      
      

public KaartPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage bgImg = new BackgroundImage(new Image("/images/kaartPaneel_H.png"),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(bgImg));
       // maakKaartPaneel();
       
       
       //main hbox breedte nog instellen
       HBox box1 = new HBox(
               new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
               new VBox());
       box1.setStyle("-fx-background-image: url('/images/hutFrame.png');");
       HBox box2 = new HBox(
               new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
               new VBox());
       box2.setStyle("-fx-background-image: url('/images/hutFrame.png');");
       HBox box3 = new HBox(
               new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
               new VBox());
       box3.setStyle("-fx-background-image: url('/images/hutFrame.png');");
       HBox hbox = new HBox(
                   box1, 
                   new HBox(
                   box2, 
                   new HBox(
                   box3)));
       this.getChildren().add(hbox);
       hbox.minWidth(1000);
    }
       
}

      
