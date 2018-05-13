package gui;

import domein.DomeinController;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MapSpel extends GridPane 
{
    private DomeinController dc;
    private SpelApplicatiePaneel spelAppPaneel;
    private String stamledenAantal;
    private boolean isEindeRonde = false;
    
    //buttons aanmaken
    Button btn_leemGroeve;
    Button btn_bos;
    Button btn_steenGroeve;
    Button btn_jachtGebied;
    Button btn_hut;
    Button btn_akkerbouw;
    Button btn_goudMijn;
    Button btn_smith;
    Button btn_confirm;
    
    
    public MapSpel(DomeinController dc, SpelApplicatiePaneel spelAppPaneel)
    {
        this.dc = dc;
        this.spelAppPaneel = spelAppPaneel;
        buildMap();
    }
    
    public void updateButtons()
    {
        btn_bos.setText(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(0).getAantalSpots()));
        btn_leemGroeve.setText(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(1).getAantalSpots()));
        btn_steenGroeve.setText(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(2).getAantalSpots()));
        btn_goudMijn.setText(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(3).getAantalSpots()));
        btn_jachtGebied.setText(String.format("%d / 40", 40 - dc.getPlaatsenLijst().get(4).getAantalSpots()));
        btn_akkerbouw.setText(String.format("%d / 1", 1 - dc.getPlaatsenLijst().get(5).getAantalSpots()));
        btn_smith.setText(String.format("%d / 1", 1 - dc.getPlaatsenLijst().get(6).getAantalSpots()));
        btn_hut.setText(String.format("%d / 2", 2 - dc.getPlaatsenLijst().get(7).getAantalSpots()));
    }
    
    private void toonKeuzeStamleden(int plaatsNr)
    {
        if (dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() < 1) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol...");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
        else
        {
            TextInputDialog inputAantalStamleden = new TextInputDialog("u heeft "+ dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + String.format(" %s",dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() > 1 ? "stamleden":"stamlid"));
            inputAantalStamleden.setTitle("Kies het aantal stamleden");
            inputAantalStamleden.setHeaderText(null);
            inputAantalStamleden.setContentText(String.format("(0 - %d)",dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() <= 7 ? dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal(): 7));
            Optional<String> result = inputAantalStamleden.showAndWait();
            if (result.isPresent() && parseInt(result.get()) > 0) {
              stamledenAantal = result.get();
              plaatsClicked(plaatsNr);
            }
            else
            {
                result = inputAantalStamleden.showAndWait();
            }
        }
    }
    /*
    if (dc.getHuttenLijst4().size() < 1) {
                        switch (dc.getSpelerLijst().size()) {
                            case 2:
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Het spel is gedaan");
                                alert.setHeaderText("Easy peasy lemon squeezy!");
                                if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(dc.getHuidigeSpeler() + 1 > dc.getSpelerLijst().size() ? 0:dc.getHuidigeSpeler() + 1).getResourceLijst().get(8).getAantal()) {
                                    alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getNaam(), dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).getAantal()));
                                }
                                else
                                {
                                    alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(dc.getHuidigeSpeler() + 1 > dc.getSpelerLijst().size() ? 0:dc.getHuidigeSpeler() + 1).getNaam(), dc.getSpelerLijst().get(dc.getHuidigeSpeler() + 1 > dc.getSpelerLijst().size() ? 0:dc.getHuidigeSpeler() + 1).getResourceLijst().get(8).getAantal()));
                                }
                                alert.show();
                                break;
                        }
                     }   
    */
    private void toonKeuzeStamledenSpeciaal(int plaatsNr)
    {
        switch (plaatsNr) {
            case 5:
            case 6:
                if (dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() < 1) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("Deze plek staat vol...");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else
                {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Stamleden plaatsen..");
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("Wilt u hier 1 stamlid plaatsen?");
                Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        stamledenAantal = "1";
                        plaatsClicked(plaatsNr);
                        spelAppPaneel.doeLichtAan(plaatsNr-10);
                    }
                }
                break;
            case 7:
                if (dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() < 1) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("Deze plek staat vol...");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() < 2)
                {
                     Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("Je moet minstens 2 stamleden hebben");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() >= 10){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Er is een fout opgetreden");
                    alert.setHeaderText("U kan maximum 10 stamleden hebben");
                    alert.setContentText("Kies een andere plek");
                    alert.show();
                }
                else
                {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Stamleden plaatsen..");
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("Wilt u hier 2 stamleden plaatsen?");
                Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        stamledenAantal = "2";
                        plaatsClicked(plaatsNr);
                    }
                }
                break;
            case 11:
                if (dc.getHuttenLijst1().get(0).getAantalSpots() < 1) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Stamleden plaatsen..");
                        alert.setHeaderText(null);
                        alert.setGraphic(null);
                        alert.setContentText("Wilt u hier 1 stamlid plaatsen?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            stamledenAantal = "1";
                            plaatsClicked(plaatsNr);
                            spelAppPaneel.doeLichtAan(plaatsNr - 10);
                        }
                    }
                break;
            case 12:
                if (dc.getHuttenLijst2().get(0).getAantalSpots() < 1) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Stamleden plaatsen..");
                        alert.setHeaderText(null);
                        alert.setGraphic(null);
                        alert.setContentText("Wilt u hier 1 stamlid plaatsen?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            stamledenAantal = "1";
                            plaatsClicked(plaatsNr);
                            spelAppPaneel.doeLichtAan(plaatsNr - 10);
                        }
                    }
                break;
            case 13:
                if (dc.getHuttenLijst3().get(0).getAantalSpots() < 1) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Stamleden plaatsen..");
                        alert.setHeaderText(null);
                        alert.setGraphic(null);
                        alert.setContentText("Wilt u hier 1 stamlid plaatsen?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            stamledenAantal = "1";
                            plaatsClicked(plaatsNr);
                            spelAppPaneel.doeLichtAan(plaatsNr - 10);
                        }
                    }
                break;
            case 14:
                if (dc.getHuttenLijst4().get(0).getAantalSpots() < 1) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Stamleden plaatsen..");
                        alert.setHeaderText(null);
                        alert.setGraphic(null);
                        alert.setContentText("Wilt u hier 1 stamlid plaatsen?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            stamledenAantal = "1";
                            plaatsClicked(plaatsNr);
                            spelAppPaneel.doeLichtAan(plaatsNr - 10);
                        }
                    }
                break;
        }
    }
     
    public void volgendeBeurt()
    {
        //voor elke speler
        if (isEindeRonde == false)
        {
            int gedaan = 0;
            for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
                //als speler i zijn stamleden 0 zijn dan gedaan ++
                if (dc.getSpelerLijst().get(i).getResourceLijst().get(7).getAantal() <= 0) {
                    gedaan++;
                }
            }
            
            //als gedaan = aantal spelers, einde ronde
            if (gedaan >= dc.getSpelerLijst().size()) 
            {
                isEindeRonde = true;
            }
            
            //volgende speler
            volgendeSpeler();
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() <= 0 && isEindeRonde == false) {
               volgendeBeurt();
            }
            
        }
        else
        {
            int gedaan = 0;
            for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
                //als speler i zijn stamleden 0 zijn dan gedaan ++
                if (dc.getSpelerLijst().get(i).getGebruikteStamleden() <= 0) {
                    gedaan++;
                }
            }
            if (gedaan >= dc.getSpelerLijst().size()) 
            {
                isEindeRonde = false;
            }
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() <= 0)
            {
                volgendeSpeler();
            }
        }
        if (dc.getHuttenLijst1().size() < 1 || dc.getHuttenLijst2().size() < 1 || dc.getHuttenLijst3().size() < 1 || dc.getHuttenLijst4().size() < 1) 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Het spel is gedaan");
            alert.setHeaderText("Easy peasy lemon squeezy!");
            switch (dc.getSpelerLijst().size()) {
                case 2:
                    if (dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal()) {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(0).getNaam(), dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal()));
                        spelAppPaneel.getChildren().clear();
                    }
                    else
                    {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(1).getNaam(), dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal()));
                    }
                    alert.show();
                    break;
                case 3:
                    if (dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() &&
                        dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal()) {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(0).getNaam(), dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal()));
                    }
                    else if(dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() &&
                            dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal())
                    {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(1).getNaam(), dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal()));
                    }
                    else
                    {
                         alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(2).getNaam(), dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal()));
                    }
                    alert.show();
                    break;
                case 4:
                    if (dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() &&
                        dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal() &&
                        dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(3).getResourceLijst().get(8).getAantal()) {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(0).getNaam(), dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal()));
                    }
                    else if(dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() &&
                            dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal() &&
                            dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(3).getResourceLijst().get(8).getAantal())
                    {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(1).getNaam(), dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal()));
                    }
                    else if(dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(0).getResourceLijst().get(8).getAantal() &&
                            dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(1).getResourceLijst().get(8).getAantal() &&
                            dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal() > dc.getSpelerLijst().get(3).getResourceLijst().get(8).getAantal())
                    {
                         alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(2).getNaam(), dc.getSpelerLijst().get(2).getResourceLijst().get(8).getAantal()));
                    }
                    else
                    {
                        alert.setContentText(String.format("%s heeft het spel gewonnen met %d punten", dc.getSpelerLijst().get(3).getNaam(), dc.getSpelerLijst().get(3).getResourceLijst().get(8).getAantal()));
                    }
                    alert.show();
                    break;
            }
        }   
        formRefresh();
    }
    
    public void volgendeSpeler()
    {
        if (dc.getHuidigeSpeler() + 1 >= dc.getSpelerLijst().size()) {
            dc.setHuidigeSpeler(0);
        }
        else
        {
            dc.setHuidigeSpeler(dc.getHuidigeSpeler() + 1);
        }
        
    }
    
    public void plaatsClicked(int plaatsNummer)
    {
        try
        {
            switch (plaatsNummer) {
                //bos
                case 0:
                //leemgroeve
                case 1:
                //steengroeve
                case 2:
                //goudmijn
                case 3:
                //jachtgebied
                case 4:
                    if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() >= parseInt(stamledenAantal))
                    {
                        dc.doePlaatsOpPlekGUI(dc.getHuidigeSpeler(), plaatsNummer + 2, parseInt(stamledenAantal));
                        this.updateButtons();
                        volgendeBeurt();
                        
                    }
                    break;
                //akkerbouw
                case 5:
                //smith
                case 6:
                    if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() >= parseInt(stamledenAantal))
                    {
                        dc.doePlaatsOpPlekGUI(dc.getHuidigeSpeler(), plaatsNummer  == 5 ? plaatsNummer + 4:plaatsNummer + 2, parseInt(stamledenAantal));
                        this.updateButtons();
                        volgendeBeurt();
                        
                    }
                    break;
                //hut
                case 7:
                   if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() >= parseInt(stamledenAantal))
                    {
                        dc.doePlaatsOpPlekGUI(dc.getHuidigeSpeler(), plaatsNummer, parseInt(stamledenAantal));
                        this.updateButtons();
                        volgendeBeurt();
                    }
                    break;
                //hutkaart 1
                case 11:
                case 12:
                case 13:
                case 14:
                    dc.doePlaatsOpPlekGUI(dc.getHuidigeSpeler(), plaatsNummer, parseInt(stamledenAantal));
                    this.updateButtons();
                    volgendeBeurt();
                    break;
            }
        }catch(NumberFormatException e)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Fout");
            alert.setContentText("Vul een cijfer in aub");
            alert.show();
        }
    }
    
    public boolean isPlaatsOpPlaats(int plaatsNummer)
    {
        switch(plaatsNummer)
        {
            case 0: 
                if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpBos())
                {
                    return true;
                }
                break;
            case 1:
                if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpLeemgroeve())
                {
                    return true;
                }
                break;
            case 2:
                if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpSteengroeve())
                {
                    return true;
                }
                break;
            case 3:
                if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpGoudmijn())
                {
                    return true;
                }
                break;
            case 4:
                if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpJachtgebied())
                {
                    return true;
                }
                break;
            case 5:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpAkkerbouw())
            {
                return true;
            }
                break;
            case 6:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpSmith())
            {
                return true;
            }
                break;
            case 7:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHut())
            {
                return true;
            }
                break;
            case 11:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart1())
            {
                return true;
            }
            case 12:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart2())
            {
                return true;
            }
            case 13:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart3())
            {
                return true;
            }
            case 14:
            if(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart4())
            {
                return true;
            }
                break;
            default: 
                return false;
        }
        return false;
    }
    
    private void bosClicked(ActionEvent ae)
    {
        int plaatsNr = 0;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpBos() == true)
            {
                //het gerold getal, om te zien hoeveel resources hij krijgt
                int geroldGetal = toonGeroldGetal(plaatsNr, dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalBos(), dc.getHuidigeSpeler());
                //als de speler gereedschap heeft
                //true => de speler zou moeten gereedschap kunnen gebruiken
                //false => hij krijgt gwn zijn resources
                if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).getAantal() > 0) 
                {
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor((geroldGetal + gebruikGereedschap(dc.getHuidigeSpeler(), plaatsNr)) / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                else
                {
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalBos();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalBos());
                //gebruikte stamleden verminderen
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalBos());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalBos());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpBos(false);
                System.out.println(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots());
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            toonKeuzeStamleden(plaatsNr);
        }
    }
    
    
    private void leemGroeveClicked(ActionEvent ae)
    {
        
            int plaatsNr = 1;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpLeemgroeve() == true)
            {
                //het gerold getal, om te zien hoeveel resources hij krijgt
                int geroldGetal = toonGeroldGetal(plaatsNr, dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalLeemgroeve(), dc.getHuidigeSpeler());
                
                //als de speler gereedschap heeft
                //true => de speler zou moeten gereedschap kunnen gebruiken
                //false => hij krijgt gwn zijn resources
                if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).getAantal() > 0) 
                {
                     dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor((geroldGetal + gebruikGereedschap(dc.getHuidigeSpeler(), plaatsNr))/ dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                else
                {
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalLeemgroeve();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalLeemgroeve());
                //gebruikte stamleden verminderen
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalLeemgroeve());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalLeemgroeve());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpLeemgroeve(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            toonKeuzeStamleden(plaatsNr);
        }
    }
    
    private void steenGroeveClicked(ActionEvent ae)
    {
             int plaatsNr = 2;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpSteengroeve() == true)
            {
                //het gerold getal, om te zien hoeveel resources hij krijgt
                int geroldGetal = toonGeroldGetal(plaatsNr, dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSteengroeve(), dc.getHuidigeSpeler());
                
                //als de speler gereedschap heeft
                //true => de speler zou moeten gereedschap kunnen gebruiken
                //false => hij krijgt gwn zijn resources
                if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).getAantal() > 0) 
                {
                     dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor((geroldGetal + gebruikGereedschap(dc.getHuidigeSpeler(), plaatsNr))/ dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                else
                {
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSteengroeve();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                //getaantalbos terug op 0 zetten
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSteengroeve());
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSteengroeve());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSteengroeve());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpSteengroeve(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            toonKeuzeStamleden(plaatsNr);
        }
    }
    
    private void goudMijnClicked(ActionEvent ae)
    {
               int plaatsNr = 3;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpGoudmijn() == true)
            {
                //het gerold getal, om te zien hoeveel resources hij krijgt
                int geroldGetal = toonGeroldGetal(plaatsNr, dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalGoudmijn(), dc.getHuidigeSpeler());
                
                //als de speler gereedschap heeft
                //true => de speler zou moeten gereedschap kunnen gebruiken
                //false => hij krijgt gwn zijn resources
                if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).getAantal() > 0) 
                {
                     dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor((geroldGetal + gebruikGereedschap(dc.getHuidigeSpeler(), plaatsNr)) / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                else
                {
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalGoudmijn();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                //getaantalbos terug op 0 zetten
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalGoudmijn());
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalGoudmijn());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalGoudmijn());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpGoudmijn(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            toonKeuzeStamleden(plaatsNr);
        }
    }
    
    private void jachtGebiedClicked(ActionEvent ae)
    {
              int plaatsNr = 4;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpJachtgebied()== true)
            {
                //het gerold getal, om te zien hoeveel resources hij krijgt
                int geroldGetal = toonGeroldGetal(plaatsNr, dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalJachtgebied(), dc.getHuidigeSpeler());
                
                //als de speler gereedschap heeft
                //true => de speler zou moeten gereedschap kunnen gebruiken
                //false => hij krijgt gwn zijn resources
                if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).getAantal() > 0) 
                {
                     dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(plaatsNr).getAantal() + (int) Math.floor((geroldGetal + gebruikGereedschap(dc.getHuidigeSpeler(), plaatsNr)) / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                else
                {
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler()));
                }
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalJachtgebied();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                //getaantalbos terug op 0 zetten
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalJachtgebied());
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalJachtgebied());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalJachtgebied());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpJachtgebied(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            toonKeuzeStamleden(plaatsNr);
        }
    }
    
    private void hutClicked(ActionEvent ae)
    {
             int plaatsNr = 7;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHut()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHut();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + 1);
                //getaantalbos terug op 0 zetten
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHut());
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHut());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHut()));
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpHut(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    private void smithClicked(ActionEvent ae)
    {
              int plaatsNr = 6;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpSmith()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSmith();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(5).getAantal() + 1);
                //getaantalbos terug op 0 zetten
               dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSmith());
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSmith());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalSmith());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpSmith(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    private void akkerbouwClicked(ActionEvent ae)
    {
              int plaatsNr = 5;
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpAkkerbouw()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalAkkerbouw();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(4).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(4).getAantal() + 1);
                //getaantalbos terug op 0 zetten
                dc.getPlaatsenLijst().get(plaatsNr).setAantalSpots(dc.getPlaatsenLijst().get(plaatsNr).getAantalSpots() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalAkkerbouw());
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalAkkerbouw());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalAkkerbouw());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpAkkerbouw(false);
                
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    public void hutKaart1Clicked()
    {
              int plaatsNr = 11;
              System.out.println(dc.getHuttenLijst1().get(0).getPunten());
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart1()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart1();
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                
                if (dc.getResourcesChecked(dc.getHuidigeSpeler(), plaatsNr - 10) == true) {
                    dc.doeTrekResourcesAf(dc.getHuidigeSpeler(), plaatsNr - 10);
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst1().get(0).getPunten());
                    
                    dc.doeVerwijderKaart(plaatsNr - 10);
                    spelAppPaneel.doeLichtUit(plaatsNr-10);
                }
                else
                {
                    dc.getHuttenLijst1().get(0).setAantalSpots(1);
                    spelAppPaneel.doeLichtUit(plaatsNr - 10);
                }
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart1());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart1());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpHutkaart1(false);
                
                spelAppPaneel.refreshKaartPaneel();
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    public void hutKaart2Clicked()
    {
              int plaatsNr = 12;
              System.out.println(dc.getHuttenLijst2().get(0).getPunten());
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart2()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart2();

                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                
                if (dc.getResourcesChecked(dc.getHuidigeSpeler(), plaatsNr - 10) == true) {
                    dc.doeTrekResourcesAf(dc.getHuidigeSpeler(), plaatsNr - 10);
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst2().get(0).getPunten());
                    
                    dc.doeVerwijderKaart(plaatsNr - 10);
                    spelAppPaneel.doeLichtUit(plaatsNr-10);
                }
                else
                {
                    dc.getHuttenLijst2().get(0).setAantalSpots(1);
                    spelAppPaneel.doeLichtUit(plaatsNr - 10);
                }
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart2());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart2());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpHutkaart2(false);
                
                
                spelAppPaneel.refreshKaartPaneel();
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    public void hutKaart3Clicked()
    {
              int plaatsNr = 13;
              System.out.println(dc.getHuttenLijst3().get(0).getPunten());
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart3()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart3();
                
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                
                if (dc.getResourcesChecked(dc.getHuidigeSpeler(), plaatsNr - 10) == true) {
                    dc.doeTrekResourcesAf(dc.getHuidigeSpeler(), plaatsNr - 10);
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst3().get(0).getPunten());
                    
                    dc.doeVerwijderKaart(plaatsNr - 10);
                    spelAppPaneel.doeLichtUit(plaatsNr-10);
                }
                else
                {
                    dc.getHuttenLijst3().get(0).setAantalSpots(1);
                    spelAppPaneel.doeLichtUit(plaatsNr - 10);
                }dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart3());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart3());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpHutkaart3(false);
                
                
                spelAppPaneel.refreshKaartPaneel();
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    public void hutKaart4Clicked()
    {
              int plaatsNr = 14;
              System.out.println(dc.getHuttenLijst4().get(0).getPunten());
        //als het de einde van de ronde is
        //true => de speler kan zoveel klikken als hij wil
        //false => toont hij keuzestamleden en daarna volgende speler
        if(isEindeRonde)
        {
            //als de speler op het bos heeft geplaatst
            //true => de speler krijgt resources
            //false => er verschijnt een alert, maar hij kan wel nog klikken op een andere plaats uiteraard
            if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).isPlaatsOpHutkaart4()== true)
            {
                //voedsel aftrekken
                int voedselVermindering = dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart4();
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(6).setAantal(voedselVermindering);
                
                if (dc.getResourcesChecked(dc.getHuidigeSpeler(), plaatsNr - 10) == true) {
                    dc.doeTrekResourcesAf(dc.getHuidigeSpeler(), plaatsNr - 10);
                    dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst4().get(0).getPunten());
                    
                    dc.doeVerwijderKaart(plaatsNr - 10);
                    spelAppPaneel.doeLichtUit(plaatsNr-10);
                }
                else
                {
                    dc.getHuttenLijst4().get(0).setAantalSpots(1);
                    spelAppPaneel.doeLichtUit(plaatsNr - 10);
                }
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setGebruikteStamleden(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getGebruikteStamleden() - dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart4());
                //stamleden terug zetten
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getAantalHutkaart4());
                //setplaatsop bos false voor de speler
                dc.getSpelerLijst().get(dc.getHuidigeSpeler()).setPlaatsOpHutkaart4(false);
                
                spelAppPaneel.refreshKaartPaneel();
                formRefresh();
                updateButtons();
                volgendeBeurt();
            }
            else
            {
                //alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Je hebt niet op deze plek geplaatst..");
                alert.show();
            }
        }
        else
        {
            this.toonKeuzeStamledenSpeciaal(plaatsNr);
        }
    }
    
    private void formRefresh()
    {
        //hier moeten we de form refreshen voor de labels
        spelAppPaneel.formRefresh();
    }
    
     public void voorgrondButtons()
    {
        //dc.setHuidigeSpeler(0);
        //de coordinaten zijn (aa) ipv (0,0), dus (1,3) is button (bd)
        //buttons
        btn_bos = new Button(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(0).getAantalSpots()));
        btn_leemGroeve = new Button(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(1).getAantalSpots()));
        btn_steenGroeve = new Button(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(2).getAantalSpots()));
        btn_goudMijn = new Button(String.format("%d / 7", 7 - dc.getPlaatsenLijst().get(3).getAantalSpots()));
        btn_jachtGebied = new Button(String.format("%d / 40", 40 - dc.getPlaatsenLijst().get(4).getAantalSpots()));
        btn_akkerbouw = new Button(String.format("%d / 1", 1 - dc.getPlaatsenLijst().get(5).getAantalSpots()));
        btn_smith = new Button(String.format("%d / 1", 1 - dc.getPlaatsenLijst().get(6).getAantalSpots()));
        btn_hut = new Button(String.format("%d / 2", 2 - dc.getPlaatsenLijst().get(7).getAantalSpots()));
        
        btn_bos.setOnAction(this::bosClicked);
        btn_leemGroeve.setOnAction(this::leemGroeveClicked);
        btn_steenGroeve.setOnAction(this::steenGroeveClicked);
        btn_goudMijn.setOnAction(this::goudMijnClicked);
        btn_jachtGebied.setOnAction(this::jachtGebiedClicked);
        btn_akkerbouw.setOnAction(this::akkerbouwClicked);
        btn_smith.setOnAction(this::smithClicked);
        btn_hut.setOnAction(this::hutClicked);
        
        btn_leemGroeve.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: white; -fx-font-size: 24;");
        btn_bos.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: white; -fx-font-size: 24;");
        btn_steenGroeve.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: black; -fx-font-size: 24;");
        btn_jachtGebied.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: black; -fx-font-size: 24;-fx-alignment: BOTTOM-CENTER;");
        btn_hut.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: black; -fx-font-size: 24;-fx-alignment: BOTTOM-CENTER;");
        btn_akkerbouw.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: black; -fx-font-size: 24;-fx-alignment: BOTTOM-CENTER;");
        btn_goudMijn.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: white; -fx-font-size: 24;");
        btn_smith.setStyle("-fx-background-color: rgba(255,255,255,0);-fx-text-fill: black; -fx-font-size: 24;-fx-alignment: BOTTOM-CENTER;");
        
        //Kollommen, we hebben er 5 nodig
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(20);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(11);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(19);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(12);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(15);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(23);
        getColumnConstraints().addAll(column0,column1, column2, column3, column4, column5);
        
        //Rijen, we hebben er 4 nodig
        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(25);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(22);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(28);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25);
        getRowConstraints().addAll(row0,row1,row2, row3);
        
        // buttons groter maken
        btn_leemGroeve.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_bos.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_steenGroeve.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_jachtGebied.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_hut.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_akkerbouw.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_goudMijn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_smith.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setRowSpan(btn_bos,2);
        this.setColumnSpan(btn_jachtGebied,2);
        
        //buttons toevoegen in de gridpane
        this.add(btn_leemGroeve,0,0);
        this.add(btn_bos,0,1);
        this.add(btn_steenGroeve,2,0);
        this.add(btn_jachtGebied,2,1);
        this.add(btn_hut,2,2);
        this.add(btn_akkerbouw,2,3);
        this.add(btn_goudMijn,5,0);
        this.add(btn_smith,5,3);
    }
     
   private void buildMap()
   {
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage Achtergrond = new BackgroundImage(new Image("/images/mapSpel.png"), 
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(Achtergrond));
        //VOORGROND
        voorgrondButtons();
   }
   
   private int gebruikGereedschap(int spelerNr, int resourceNr)
   {
       int temp = 0;
       int aantalGereedschap = dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal();
       TextInputDialog tid_gereedschap = new TextInputDialog(String.format("(0-%d)", aantalGereedschap));
       tid_gereedschap.setTitle("Gebruik gereedschap");
       tid_gereedschap.setHeaderText(null);
       Optional<String> result;
       switch (resourceNr) {
            case 0:
               tid_gereedschap.setContentText("Hoeveel gereedschap wilt u gebruiken?");
               result = tid_gereedschap.showAndWait();
                if (result.isPresent()) {
                    try
                    {
                        temp = Integer.parseInt(result.get());
                        if (temp > aantalGereedschap || temp < 0) {
                            gebruikGereedschap(spelerNr, resourceNr);
                        }
                        else
                        {
                            return temp;
                        }
                    }catch(NumberFormatException e)
                    {
                        gebruikGereedschap(spelerNr, resourceNr);
                    }
                }
               break;
            case 1:
                tid_gereedschap.setContentText("Hoeveel gereedschap wilt u gebruiken?");
               result = tid_gereedschap.showAndWait();
                if (result.isPresent()) {
                    try
                    {
                        temp = Integer.parseInt(result.get());
                        if (temp > aantalGereedschap || temp < 0) {
                            gebruikGereedschap(spelerNr, resourceNr);
                        }
                        else
                        {
                            return temp;
                        }
                    }catch(NumberFormatException e)
                    {
                        gebruikGereedschap(spelerNr, resourceNr);
                    }
                }
               break;
            case 2:
                tid_gereedschap.setContentText("Hoeveel gereedschap wilt u gebruiken?");
               result = tid_gereedschap.showAndWait();
                if (result.isPresent()) {
                    try
                    {
                        temp = Integer.parseInt(result.get());
                        if (temp > aantalGereedschap || temp < 0) {
                            gebruikGereedschap(spelerNr, resourceNr);
                        }
                        else
                        {
                            return temp;
                        }
                    }catch(NumberFormatException e)
                    {
                        gebruikGereedschap(spelerNr, resourceNr);
                    }
                }
               break;
            case 3:
                 tid_gereedschap.setContentText("Hoeveel gereedschap wilt u gebruiken?");
               result = tid_gereedschap.showAndWait();
                if (result.isPresent()) {
                    try
                    {
                        temp = Integer.parseInt(result.get());
                        if (temp > aantalGereedschap || temp < 0) {
                            gebruikGereedschap(spelerNr, resourceNr);
                        }
                        else
                        {
                            return temp;
                        }
                    }catch(NumberFormatException e)
                    {
                        gebruikGereedschap(spelerNr, resourceNr);
                    }
                }
               break;
            case 4:
                 tid_gereedschap.setContentText("Hoeveel gereedschap wilt u gebruiken?");
               result = tid_gereedschap.showAndWait();
                if (result.isPresent()) {
                    try
                    {
                        temp = Integer.parseInt(result.get());
                        if (temp > aantalGereedschap || temp < 0) {
                            gebruikGereedschap(spelerNr, resourceNr);
                        }
                        else
                        {
                            return temp;
                        }
                    }catch(NumberFormatException e)
                    {
                        gebruikGereedschap(spelerNr, resourceNr);
                    }
                }
               break;
       }
       return temp;
   }
   
    
    //moeten we gui van maken
    public void voedselStraf(int spelerNr, int tekortVoedsel)
    {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Voedsel straf");
        
        if (dc.doeResourcesControleSpeler(spelerNr, tekortVoedsel))
        {
            dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(8).getAantal() - 10);
            alert.setHeaderText(null);
            alert.setContentText(String.format("%s omdat u noch voedsel noch materialen heeft werden er 10 punten afgetrokken!", dc.getSpelerLijst().get(spelerNr).getNaam()));
            ButtonType btn_verder = new ButtonType("verder", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btn_verder);
            alert.getDialogPane().lookupButton(btn_verder).setDisable(true);
            Optional<ButtonType> result = alert.showAndWait();
        }
        else
        {
            alert.setHeaderText(String.format("%s kies een materiaal om uw voedsel mee te kopen!", dc.getSpelerLijst().get(spelerNr).getNaam()));
            
            ButtonType btn_hout = new ButtonType("Hout", ButtonData.OK_DONE);
            ButtonType btn_leem = new ButtonType("Leem", ButtonData.OK_DONE);
            ButtonType btn_steen = new ButtonType("Steen", ButtonData.OK_DONE);
            ButtonType btn_goud = new ButtonType("Goud", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(btn_hout, btn_leem, btn_steen, btn_goud);
            if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() < tekortVoedsel) {
                alert.getButtonTypes().remove(btn_hout);
            }
            if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() < tekortVoedsel) {
                alert.getButtonTypes().remove(btn_leem);
            }
            if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() < tekortVoedsel) {
                alert.getButtonTypes().remove(btn_steen);
            }
            if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() < tekortVoedsel) {
                alert.getButtonTypes().remove(btn_goud);
            }
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btn_hout && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() >= tekortVoedsel) 
            {
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() - tekortVoedsel);
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(tekortVoedsel);
                
            }
            if (result.get() == btn_leem && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() >= tekortVoedsel) 
            {
                
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() - tekortVoedsel);
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(tekortVoedsel);
                
            }
            if (result.get() == btn_steen && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() >= tekortVoedsel) 
            {
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() - tekortVoedsel);
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(tekortVoedsel);
                
            }
            if (result.get() == btn_goud && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() >= tekortVoedsel) 
            {
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() - tekortVoedsel);
                dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(tekortVoedsel); 
            }
            if (!result.isPresent()) {
                result = alert.showAndWait();
            }
        }
    }

   
    //toon gerold getal plaats resource
    public int toonGeroldGetal(int plaatsNr, int aantalStamleden, int spelerNr)
    {
        int geroldGetal = dc.getGeroldGetal(aantalStamleden);
        
        System.out.printf("%n%s:%nDe gerolde dobbelstenen op de plaats %s kwamen op het totaal getal %d delen door %d geeft you %d %s.%n",
                dc.getSpelerLijst().get(spelerNr).getNaam(),
                dc.getPlaatsenLijst().get(plaatsNr).getNaam(),
                geroldGetal,
                dc.getPlaatsenLijst().get(plaatsNr).getDeler(),
                ((int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler())),
                dc.getPlaatsenLijst().get(plaatsNr).getTypeResource().getNaam());  
        return geroldGetal;
    }

    
    public void eindeRonde()
    {
        isEindeRonde = false;
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
            dc.getSpelerLijst().get(i).getResourceLijst().get(6).setAantal(dc.getSpelerLijst().get(i).getResourceLijst().get(6).getAantal() + dc.getSpelerLijst().get(i).getResourceLijst().get(4).getAantal());
        }
        
    }
}