package gui;

import javafx.scene.layout.VBox;


public class HoofdPaneel extends VBox
{
    private AantalSpelersControlePaneel aantalSpelers = new AantalSpelersControlePaneel(this);;
    
    public HoofdPaneel(){
        setSpacing(10);
        getChildren().add(aantalSpelers);
    }
    
    public void toonControleNamenSpelers(){
        
        
    }
}
    
    
    
    
    
    
    
    
 