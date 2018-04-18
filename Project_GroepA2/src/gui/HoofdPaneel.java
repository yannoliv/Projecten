package gui;

import domein.DomeinController;
import javafx.scene.layout.VBox;


public class HoofdPaneel extends VBox
{
    private DomeinController dc;
    private AantalSpelersControlePaneel aantalSpelers;
    
    
    public HoofdPaneel(DomeinController dc){
        this.dc = dc;
        setSpacing(10);
        aantalSpelers = new AantalSpelersControlePaneel(this, dc);
        getChildren().add(aantalSpelers);
    }
    
    public void toonControleNamenSpelers(int aantal){
        getChildren().remove(aantalSpelers);
        
    }
}
    
    
    
    
    
    
    
    
 