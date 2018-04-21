package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SpelerResourcesPaneel extends VBox 
{

    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;

    private Label lbl_spelerNaam;
     private String[] str_kleuren = {
         "rgb(255,205,205)","rgb(205,255,205)", "rgb(205,205,255)", "rgb(255,255,205)"};
    private String spelerKleur;
     private HBox hbox;
     
    private Label lbl_hout;
    private Label lbl_houtAantal;
    private Label lbl_leem;
    private Label lbl_leemAantal;
    private Label lbl_steen;
    private Label lbl_steenAantal;     
    private Label lbl_goud;
    private Label lbl_goudAantal;
    private Label lbl_voedsel;
    private Label lbl_voedselAantal;
    private Label lbl_stamleden;
    private Label lbl_stamledenAantal;
    private Label lbl_akkerbouw;
    private Label lbl_akkerbouwAantal;
    private Label lbl_gereedschap;
    private Label lbl_gereedschapAantal;
    private Label lbl_punten;
    private Label lbl_puntenAantal;
    
    
    

    public SpelerResourcesPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        
        //Labels en gegevens
        

        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
        {
            
            //hbox 1
            lbl_spelerNaam = new Label(String.format("%s: ", dc.getSpelerLijst().get(i).getNaam()));
            
            hbox = new HBox(8);
            hbox.getChildren().add(lbl_spelerNaam);
            this.getChildren().add(hbox);
            hbox.setStyle(String.format("-fx-fill-color: %s;", "red"));
            hbox.setPadding(new Insets(10,10,10,10));
            
            //hbox 2
            lbl_hout = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(0).getNaam()));       
            lbl_houtAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(0).getAantal()));
            lbl_leem = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(1).getNaam()));       
            lbl_leemAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(1).getAantal()));
            lbl_steen = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(2).getNaam()));       
            lbl_steenAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(2).getAantal()));
            lbl_goud = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(3).getNaam()));       
            lbl_goudAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(3).getAantal()));
            lbl_akkerbouw = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(4).getNaam()));       
            lbl_akkerbouwAantal = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(4).getAantal()));    
            
            hbox = new HBox(8);
            hbox.getChildren().addAll(
                    lbl_hout,lbl_houtAantal, 
                    lbl_leem, lbl_leemAantal, 
                    lbl_steen, lbl_steenAantal,
                    lbl_goud, lbl_goudAantal,
                    lbl_akkerbouw, lbl_akkerbouwAantal
            );
            this.getChildren().addAll(hbox);
            switch (dc.getSpelerLijst().get(i).getKleur()) {
                case "rood":
                    spelerKleur = str_kleuren[0];
                    break;
                case "groen":
                    spelerKleur = str_kleuren[1];
                    break;
                case "blauw":
                    spelerKleur = str_kleuren[2];
                    break;
                case "geel":
                    spelerKleur = str_kleuren[3];
                    break;
            }
            hbox.setStyle(String.format("-fx-background-color: %s;",spelerKleur));
            hbox.setPadding(new Insets(10,10,10,10));
            
            
            //hbox3
            lbl_gereedschap = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(5).getNaam()));    
            lbl_gereedschapAantal = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(5).getAantal()));    
            lbl_voedsel = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(6).getNaam())); 
            lbl_voedselAantal = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(6).getAantal())); 
            lbl_stamleden = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(7).getNaam())); 
            lbl_stamledenAantal = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(7).getAantal())); 
            lbl_punten = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(8).getNaam())); 
            lbl_puntenAantal = new Label(String.format("%s: ",dc.getSpelerLijst().get(i).getResourceLijst().get(8).getAantal())); 
            
            hbox = new HBox(8);
            hbox.getChildren().addAll(
                    lbl_gereedschap, lbl_gereedschapAantal,
                    lbl_stamleden, lbl_stamledenAantal,
                    lbl_punten, lbl_puntenAantal,
                    lbl_voedsel, lbl_voedselAantal
            );
            
            this.getChildren().addAll(hbox);// hbox toevoegen bij vbox
            hbox.setPadding(new Insets(10,10,40,10));
            hbox.setStyle(String.format("-fx-background-color: %s;", spelerKleur));
            System.out.println(dc.getSpelerLijst().get(i).getNaam() + "/n" + dc.getSpelerLijst().get(i).getKleur());
        }
        
        //opmaak vbox___________________
    }
    
    /*
    

    

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
            
            hbox.getChildren().addAll(
                    lbl_hout,lbl_hout_speler, 
                    lbl_leem, lbl_leem_speler, 
                    lbl_steen, lbl_steen_speler,
                    lbl_goud, lbl_goud_speler
            );
            hbox.setStyle(String.format("-fx-background-color: %s;",str_kleuren[i]));
            this.getChildren().add(hbox);// hbox toevoegen bij vbox
            
        }
        this.setPadding(new Insets(10,10,40,10));
        
    }

}

    */

}
