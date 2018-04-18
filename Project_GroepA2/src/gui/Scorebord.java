
package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class Scorebord extends VBox 
{
    
    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;
    
    private Label lbl_spelerBord;
    
    public Scorebord(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {
        
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        
        
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
        {
            lbl_spelerBord = new Label();
            
        }
    }
    
}
