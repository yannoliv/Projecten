package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GereedschapPaneel extends HBox {
    
    private DomeinController dc;
    private int spelerNr;
    private String resource;
    private int aantal = 0;
    private VBox box;
    
    public GereedschapPaneel(DomeinController dc, int spelerNr, String resource)
    {
        this.dc = dc;
        this.spelerNr = spelerNr;
        this.resource = resource;
        buildPaneel();
    }
    
    private void buildPaneel()
    {
        if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal() <= 2) {
           for (int i = 0; i < dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal(); i++) {
               box = new VBox();
               box.setOnMouseClicked(this::verhoogGereedschap);
            } 
        }
        else
        {
             for (int i = 0; i < dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal()/3; i++) {
                 VBox box = new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/pickaxe_2.png"))));
                 box.setOnMouseClicked(this::gebruikGereedschapAdvanced);
            } 
        }
        
    }
    
    private void verhoogGereedschap(MouseEvent me)
    {
        aantal++;
        box.setStyle("-fx-background-color: rgba(255,255,255,0.5);");
    }
    
    private void gebruikGereedschapAdvanced(MouseEvent me)
    {
        
    }
}
