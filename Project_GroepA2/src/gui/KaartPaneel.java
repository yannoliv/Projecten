package gui;

import domein.DomeinController;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KaartPaneel extends HBox{
      private DomeinController dc;
      private SpelApplicatiePaneel spelApplicatiePaneel;
      private String stamledenAantal;
      private MapSpel mapSpel;
      private boolean aantalSpotsHK1 = false;
      private boolean aantalSpotsHK2 = false;
      private boolean aantalSpotsHK3 = false;
      
      
public KaartPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc, MapSpel mapSpel)
    {
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        this.mapSpel = mapSpel;
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage bgImg = new BackgroundImage(new Image("/images/kaartPaneel_H.png"),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(bgImg));
        
        //individuele kaarten
        HBox kaart1 = new HBox(5);
        HBox kaart2 = new HBox(5);
        HBox kaart3 = new HBox(5);
        
        //De afbeelding
        ImageView hutImage;
      
        //vbox voor de 3 resources
        VBox vResources = new VBox(5);
        
        //HUT 1
        Label resource1 = new Label (dc.getHuttenLijst().get(0).getResource1() + ": " + dc.getHuttenLijst().get(0).getAantalResource1());
        Label resource2 = new Label (dc.getHuttenLijst().get(0).getResource2() + ": " + dc.getHuttenLijst().get(0).getAantalResource2());
        Label resource3 = new Label (dc.getHuttenLijst().get(0).getResource3() + ": " + dc.getHuttenLijst().get(0).getAantalResource3());
        vResources.getChildren().addAll(resource1,resource2,resource3);
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        kaart1.getChildren().addAll(hutImage, vResources);
        
        //HUT 2
        resource1 = new Label (dc.getHuttenLijst().get(1).getResource1() + ": " + dc.getHuttenLijst().get(1).getAantalResource1());
        resource2 = new Label (dc.getHuttenLijst().get(1).getResource2() + ": " + dc.getHuttenLijst().get(1).getAantalResource2());
        resource3 = new Label (dc.getHuttenLijst().get(1).getResource3() + ": " + dc.getHuttenLijst().get(1).getAantalResource3());
        vResources = new VBox(5);
        vResources.getChildren().addAll(resource1,resource2,resource3);
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        kaart2.getChildren().addAll(hutImage, vResources);
        
        //HUT 3
        resource1 = new Label (dc.getHuttenLijst().get(2).getResource1() + ": " + dc.getHuttenLijst().get(2).getAantalResource1());
        resource2 = new Label (dc.getHuttenLijst().get(2).getResource2() + ": " + dc.getHuttenLijst().get(2).getAantalResource2());
        resource3 = new Label (dc.getHuttenLijst().get(2).getResource3() + ": " + dc.getHuttenLijst().get(2).getAantalResource3());
        vResources = new VBox(5);
        vResources.getChildren().addAll(resource1,resource2,resource3);
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        kaart3.getChildren().addAll(hutImage, vResources);
        
        this.getChildren().addAll(kaart1, kaart2, kaart3);
        
        kaart1.setOnMouseClicked(this::kaart1Click);
        kaart2.setOnMouseClicked(this::kaart2Click);
        kaart3.setOnMouseClicked(this::kaart3Click);
        //dit werkt nog niet
        kaart1.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       ImageView img_hout;
//       ImageView img_leem;
//       ImageView img_steen;
//       ImageView img_goud;
//       box1 = new HBox(new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/stamlid.png")))), new VBox(new ImageView(), new ImageView(), new ImageView()), new VBox(new Label(), new Label(), new Label()));
//       box2 = new HBox(new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/stamlid.png")))), new VBox(new ImageView(), new ImageView(), new ImageView()), new VBox(new Label(), new Label(), new Label()));
//       box3 = new HBox(new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/stamlid.png")))), new VBox(new ImageView(), new ImageView(), new ImageView()), new VBox(new Label(), new Label(), new Label()));
//       box1.setOnMouseClicked(this::kaart1Click);
//       box2.setOnMouseClicked(this::kaart2Click);
//       box3.setOnMouseClicked(this::kaart3Click);
//       box1.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       box2.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       box3.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       this.getChildren().addAll(box1,box2,box3);
       

    }

    private void getHutResources()
    {
        
    }
       
    private boolean controleerVolzet(int kaartNr)
    {
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
            switch (kaartNr) {
                case 1:
                    if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart1()) {
                        return aantalSpotsHK1 = true;
                    }
                    break;
                case 2:
                    if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart2()) {
                        return aantalSpotsHK2 = true;
                    }
                    break;
                case 3:
                    if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart3()) {
                        return aantalSpotsHK3 = true;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return false;
    }
    
    private void controlePlaats(int plaatsNr)
    {
        switch (plaatsNr) {
            case 11:
                if (controleerVolzet(1)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("Deze plek staat vol...");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else if(dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() < 1)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("u heeft geen stamleden");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else
                {
                    dc.doePlaatsOpPlek(dc.getSpelerBeurt(), plaatsNr, 1);
                    mapSpel.updateButtons();
                    mapSpel.volgendeBeurt();
                }
                break;
            case 12:
                if (controleerVolzet(2)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("Deze plek staat vol...");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else if(dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() < 1)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("u heeft geen stamleden");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else
                {
                    dc.doePlaatsOpPlek(dc.getSpelerBeurt(), plaatsNr, 1);
                    mapSpel.updateButtons();
                    mapSpel.volgendeBeurt();
                }
                break;
            case 13:
                if (controleerVolzet(3)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("Deze plek staat vol...");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else if(dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() < 1)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("u heeft geen stamleden");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else
                {
                    dc.doePlaatsOpPlek(dc.getSpelerBeurt(), plaatsNr, 1);
                    mapSpel.updateButtons();
                    mapSpel.volgendeBeurt();
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void kaart1Click(MouseEvent ae)
    {
        controlePlaats(11);
    }
    
    private void kaart2Click(MouseEvent ae)
    {
        controlePlaats(12);
    }
    
    private void kaart3Click(MouseEvent ae)
    {
        controlePlaats(13);
    }
}

      
