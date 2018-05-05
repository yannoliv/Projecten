package gui;

import domein.DomeinController;
import javafx.scene.layout.HBox;

public class GereedschapPaneel extends HBox {
    
    private DomeinController dc;
    private int resourceNr;
    
    public GereedschapPaneel(DomeinController dc, int resourceNr)
    {
        this.dc = dc;
        this.resourceNr = resourceNr;
        
    }
}
