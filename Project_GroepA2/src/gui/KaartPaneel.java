package gui;

import domein.DomeinController;
import static java.lang.Integer.parseInt;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
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
      private ImageView hutImage = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
      
      
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
        this.setSpacing(-5);
        HBox kaart1 = new HBox(5);
        HBox kaart2 = new HBox(5);
        HBox kaart3 = new HBox(5);
        
        kaart1.getChildren().addAll(hutImage);
        kaart2.getChildren().addAll(hutImage);
        kaart3.getChildren().addAll(hutImage);
        
        this.getChildren().addAll(kaart1, kaart2, kaart3);
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
       
       
       //       //main hbox breedte nog instellen
//       HBox box1 = new HBox(new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
//               new VBox());
//       box1.setOnMouseClicked(this::kaart1Click);
//       box1.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       HBox box2 = new HBox(new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
//               new VBox());
//       box2.setOnMouseClicked(this::kaart2Click);
//       box2.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       HBox box3 = new HBox(new VBox(new ImageView(new Image(getClass().getResourceAsStream("/images/points.gif")))), 
//               new VBox());
//       box3.setOnMouseClicked(this::kaart3Click);
//       box3.setStyle("-fx-background-image: url('/images/hutFrame.png');");
//       HBox hbox = new HBox(
//                   box1, 
//                   new HBox(
//                   box2, 
//                   new HBox(
//                   box3)));
//       this.getChildren().add(hbox);
//       hbox.minWidth(1000);
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

      
