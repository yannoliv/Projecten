package gui;

import domein.DomeinController;
import javafx.scene.layout.HBox;

public class KaartPaneel extends HBox{
      private DomeinController dc;
      private SpelApplicatiePaneel spelApplicatiePaneel;
      
      

public KaartPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
       // maakKaartPaneel();
    }
       
}

      
