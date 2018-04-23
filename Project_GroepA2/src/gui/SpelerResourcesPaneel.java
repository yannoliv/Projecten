package gui;

import com.sun.prism.paint.Color;
import com.sun.prism.paint.Paint;
import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SpelerResourcesPaneel extends VBox 
{

    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;

    private Label lbl_spelerNaam;
     private String[] str_kleuren = {
         "rgba(255,205,205,0.5)","rgba(205,255,205,0.5)", "rgba(205,205,255,0.5)", "rgba(255,255,205,0.5)"};
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
        
        maakResourcePaneel();
    }
    
    public void updateResourcePaneel()
    {
        this.getChildren().clear();
        maakResourcePaneel();
    }
    
    public void maakResourcePaneel()
    {
        
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
        {
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
            //hbox 1
            lbl_spelerNaam = new Label(String.format("%s: ", dc.getSpelerLijst().get(i).getNaam()));
            hbox = new HBox(8);
            hbox.getChildren().add(lbl_spelerNaam);
            if (dc.getSpelerBeurt() == dc.getSpelerLijst().get(i).getSpelerNummer()) {
                hbox.setStyle(String.format("-fx-background-color: %s;",spelerKleur));
            }
            this.getChildren().add(hbox);
            hbox.setPadding(new Insets(10,10,10,10));
            
            //hbox 2
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
            
            lbl_akkerbouw = new Label(":");
            lbl_akkerbouw.setGraphic(new ImageView(new Image("/images/Graan.png", 20, 20, false, true)));
            lbl_akkerbouwAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(4).getAantal()));
  
            
            hbox = new HBox(8);
            hbox.getChildren().addAll(
                    lbl_hout,lbl_houtAantal, 
                    lbl_leem, lbl_leemAantal, 
                    lbl_steen, lbl_steenAantal,
                    lbl_goud, lbl_goudAantal,
                    lbl_akkerbouw, lbl_akkerbouwAantal
            );
            this.getChildren().addAll(hbox);
            if (dc.getSpelerBeurt() == dc.getSpelerLijst().get(i).getSpelerNummer()) {
                hbox.setStyle(String.format("-fx-background-color: %s;",spelerKleur));
            }
            hbox.setPadding(new Insets(10,10,10,10));
            
            
            //hbox3
            lbl_gereedschap = new Label(":");
            lbl_gereedschap.setGraphic(new ImageView(new Image("/images/pickaxe_1.png", 20, 20, false, true)));
            lbl_gereedschapAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(5).getAantal()));
            
            lbl_voedsel = new Label(":"); 
            lbl_voedsel.setGraphic(new ImageView(new Image("/images/Food.png", 20, 20, false, true)));
            lbl_voedselAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(6).getAantal())); 
            
            lbl_stamleden = new Label(":");
            lbl_stamleden.setGraphic(new ImageView(new Image("/images/pickaxe_1.png", 20, 20, false, true)));
            lbl_stamledenAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(7).getAantal()));
            
            lbl_punten = new Label(":");
            lbl_punten.setGraphic(new ImageView(new Image("/images/points.gif", 20, 20, false, true)));
            lbl_puntenAantal = new Label(String.format("%s ",dc.getSpelerLijst().get(i).getResourceLijst().get(8).getAantal())); 
            
            hbox = new HBox(8);
            hbox.getChildren().addAll(
                    lbl_gereedschap, lbl_gereedschapAantal,
                    lbl_stamleden, lbl_stamledenAantal,
                    lbl_punten, lbl_puntenAantal,
                    lbl_voedsel, lbl_voedselAantal
            );
            
            this.getChildren().addAll(hbox);// hbox toevoegen bij vbox
            hbox.setPadding(new Insets(10,10,40,10));
            if (dc.getSpelerBeurt() == dc.getSpelerLijst().get(i).getSpelerNummer()) {
                hbox.setStyle(String.format("-fx-background-color: %s;",spelerKleur));
            }
            System.out.println(dc.getSpelerLijst().get(i).getNaam() + "/n" + dc.getSpelerLijst().get(i).getKleur());
        }
        
        BackgroundSize bgs = new BackgroundSize( 1, 1, true, true, false, false);

        BackgroundImage bgImg = new BackgroundImage(new Image("/images/stoneWall.png"), 
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, bgs);
        this.setBackground(new Background(bgImg));
    }
    
}
