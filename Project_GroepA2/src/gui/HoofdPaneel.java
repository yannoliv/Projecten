package gui;

import domein.DomeinController;
import static java.lang.Integer.parseInt;
import javafx.scene.layout.VBox;


public class HoofdPaneel extends VBox
{
    private AantalSpelersControlePaneel aantalSpelersPaneel;
    private NaamSpelersControlePaneel naamControlePaneel;
    private int aantalSpelers;
    
    public HoofdPaneel(DomeinController dc){
        setSpacing(10);
        
        aantalSpelersPaneel = new AantalSpelersControlePaneel(this, dc);
        getChildren().addAll(aantalSpelersPaneel);
        
//        
//        while(aantalSpelersPaneel.aantalSpelersOK == false)
//        {
//            toonAantalSpelersControlePaneel();
//        }
//        aantalSpelers = aantalSpelersPaneel.getAantalSpelers();
//        
//        toonNaamSpelersControlePaneel();
//        
    }
    
    public void toonAantalSpelersControlePaneel()
    {
        
        
        
    }
    
    public void toonNaamSpelersControlePaneel()
    {
        naamControlePaneel = new NaamSpelersControlePaneel(this, aantalSpelers);
        getChildren().remove(aantalSpelersPaneel);
        getChildren().add(naamControlePaneel);
        
    }
}
    
    
    
    
    
    
    
    
 