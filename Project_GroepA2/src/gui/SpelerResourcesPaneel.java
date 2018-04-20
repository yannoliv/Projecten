package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SpelerResourcesPaneel extends VBox 
{

    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;

    
     private Label lbl_hout;
     private Label lbl_hout_speler;
     private Label lbl_leem;
     private Label lbl_leem_speler;
     private Label lbl_steen;
     private Label lbl_steen_speler;     
     private Label lbl_goud;
     private Label lbl_goud_speler;
     

    

    public SpelerResourcesPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        
        //Labels en gegevens
        

        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
        {
            lbl_hout = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(0).getNaam()));       
            lbl_hout_speler = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(0).getAantal()));
            lbl_leem = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(1).getNaam()));       
            lbl_leem_speler = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(1).getAantal()));
            lbl_steen = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(2).getNaam()));       
            lbl_steen_speler = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(2).getAantal()));
            lbl_goud = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(3).getNaam()));       
            lbl_goud_speler = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(3).getAantal()));
              
            HBox hbox = new HBox(8);
            hbox.spacingProperty().add(20);
            hbox.getChildren().addAll(
                    lbl_hout,lbl_hout_speler, 
                    lbl_leem, lbl_leem_speler, 
                    lbl_steen, lbl_steen_speler,
                    lbl_goud, lbl_goud_speler
            );
            this.getChildren().add(hbox);// hbox toevoegen bij vbox
            
        }
        this.setSpacing(70);
        
    }

}

/*
        resourceLijst.add(new Resource("hout",          0));
        resourceLijst.add(new Resource("leem",          0));
        resourceLijst.add(new Resource("steen",         0));
        resourceLijst.add(new Resource("goud",          0));
        resourceLijst.add(new Resource("akkerbouw",     0));
        resourceLijst.add(new Resource("gereedschap",   0));
        resourceLijst.add(new Resource("voedsel",       12));
        resourceLijst.add(new Resource("stamleden",     5));
        resourceLijst.add(new Resource("punten",        0));
    */