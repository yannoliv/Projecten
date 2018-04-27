package gui;

import domein.DomeinController;
import static java.lang.Integer.parseInt;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

public class KaartPaneel extends VBox{
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
       // maakKaartPaneel();
       
       
       //main hbox breedte nog instellen
       HBox box1 = new HBox(
               new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
               new VBox());
       box1.setOnMouseClicked(this::kaart1Click);
       box1.setStyle("-fx-background-image: url('/images/hutFrame.png');");
       HBox box2 = new HBox(
               new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
               new VBox());
       box2.setOnMouseClicked(this::kaart2Click);
       box2.setStyle("-fx-background-image: url('/images/hutFrame.png');");
       HBox box3 = new HBox(
               new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
               new VBox());
       box3.setOnMouseClicked(this::kaart3Click);
       box3.setStyle("-fx-background-image: url('/images/hutFrame.png');");
       HBox hbox = new HBox(
                   box1, 
                   new HBox(
                   box2, 
                   new HBox(
                   box3)));
       this.getChildren().add(hbox);
       hbox.minWidth(1000);
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

      
