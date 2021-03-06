package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SpelerResourcesPaneel extends VBox 
{

    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;

    private Label lbl_spelerNaam;
     private String[] str_kleuren = {
         "rgba(255,150,150,0.5)","rgba(205,255,205,0.5)", "rgba(205,205,255,0.5)", "rgba(255,255,205,0.5)"};
    private String spelerKleur;
     
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
        maakResourcePaneel();
    }
    
    public void updateResourcePaneel()
    {
        this.getChildren().clear();
        maakResourcePaneel();
    }
    
    public void checkSpelerKleur(int spelerNr)
    {
        switch (dc.getSpelerLijst().get(spelerNr).getKleur()) {
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
    }
    
    public void maakResourcePaneel()
    {
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
            checkSpelerKleur(i);
            HBox hbox = new HBox(10);
            lbl_spelerNaam = new Label(String.format("%s: ", dc.getSpelerLijst().get(i).getNaam()));
            lbl_punten = new Label(":");
            lbl_punten.setGraphic(new ImageView(new Image("/images/points.gif", 20, 20, false, true)));
            lbl_puntenAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(8).getAantal()));
            if (dc.getHuidigeSpeler() == dc.getSpelerLijst().get(i).getSpelerNummer()) {
                hbox.setStyle(String.format("-fx-background-color: %s", spelerKleur));
            }
            hbox.getChildren().addAll(lbl_spelerNaam, lbl_punten, lbl_puntenAantal);
            hbox.setPadding(new Insets(0,0,10,55));
            this.getChildren().add(hbox);
            
            hbox = new HBox(10);
            lbl_hout = new Label(":");
            lbl_hout.setGraphic(new ImageView(new Image("/images/Hout.png", 20, 20, false, true)));
            lbl_houtAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(0).getAantal()));
            
            lbl_leem = new Label(":");
            lbl_leem.setGraphic(new ImageView(new Image("/images/Leem.png", 20, 20, false, true)));
            lbl_leemAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(1).getAantal()));
            
            lbl_steen = new Label(":");
            lbl_steen.setGraphic(new ImageView(new Image("/images/Steen.png", 20, 20, false, true)));
            lbl_steenAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(2).getAantal()));
            
            lbl_goud = new Label(":");
            lbl_goud.setGraphic(new ImageView(new Image("/images/Goud.png", 20, 20, false, true)));
            lbl_goudAantal = new Label(String.format("%d",dc.getSpelerLijst().get(i).getResourceLijst().get(3).getAantal()));
            if (dc.getHuidigeSpeler() == dc.getSpelerLijst().get(i).getSpelerNummer()) {
                hbox.setStyle(String.format("-fx-background-color: %s", spelerKleur));
            }
            hbox.getChildren().addAll(lbl_hout, lbl_houtAantal, lbl_leem, lbl_leemAantal,lbl_steen, lbl_steenAantal, lbl_goud, lbl_goudAantal);
            hbox.setPadding(new Insets(0,0,20,55));
            this.getChildren().add(hbox);
            
            hbox = new HBox(10);
            lbl_akkerbouw = new Label(":");
            lbl_akkerbouw.setGraphic(new ImageView(new Image("/images/Graan.png", 20, 20, false, true)));
            lbl_akkerbouwAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(4).getAantal()));
            
            lbl_gereedschap = new Label(":");
            lbl_gereedschap.setGraphic(new ImageView(new Image("/images/pickaxe_1.png", 20, 20, false, true)));
            lbl_gereedschapAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(5).getAantal()));
            
            lbl_voedsel = new Label(":"); 
            lbl_voedsel.setGraphic(new ImageView(new Image("/images/Food.png", 20, 20, false, true)));
            lbl_voedselAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(6).getAantal() < 0 ? "0":dc.getSpelerLijst().get(i).getResourceLijst().get(6).getAantal())); 
            
            lbl_stamleden = new Label(":");
            lbl_stamleden.setGraphic(new ImageView(new Image("/images/stamlid.png", 13, 20, false, true)));
            lbl_stamledenAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(7).getAantal()));
            if (dc.getHuidigeSpeler() == dc.getSpelerLijst().get(i).getSpelerNummer()) {
                hbox.setStyle(String.format("-fx-background-color: %s", spelerKleur));
            }
            hbox.getChildren().addAll(lbl_akkerbouw, lbl_akkerbouwAantal, lbl_gereedschap, lbl_gereedschapAantal, lbl_voedsel, lbl_voedselAantal, lbl_stamleden, lbl_stamledenAantal);
            hbox.setPadding(new Insets(0,0,20,55));
            this.getChildren().add(hbox);
        } 
    }
    
}
