package gui;

import domein.DomeinController;
import javafx.scene.image.Image;
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
        BackgroundImage bgImg = new BackgroundImage(new Image("/images/kaartPaneel.png"),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(bgImg));
       // maakKaartPaneel();
    }
       
}

      
