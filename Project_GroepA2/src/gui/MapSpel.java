package gui;

import domein.DomeinController;
import static java.lang.Integer.parseInt;
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
    private boolean a = false;
    private boolean OK = true;
    
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
        TextInputDialog inputAantalStamleden = new TextInputDialog("u heeft "+ dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() + String.format(" %s",dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() > 1 ? "stamleden":"stamlid"));
        inputAantalStamleden.setTitle("Kies het aantal stamleden");
        inputAantalStamleden.setHeaderText(null);
        inputAantalStamleden.setContentText(String.format("(0 - %d)",dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() <= 7 ? dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal(): 7));
        Optional<String> result = inputAantalStamleden.showAndWait();
        if (result.isPresent()) {
            stamledenAantal = result.get();
            plaatsClicked(plaatsNr);
        }
    }
    
    private void toonKeuzeStamledenSpeciaal(int plaatsNr)
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
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Stamleden plaatsen..");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setContentText(String.format("Wilt u hier %d %s plaatsen?", plaatsNr == 7 ? 2:1, plaatsNr == 7 ? "stamleden":"stamlid"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                if (plaatsNr == 7)
                {stamledenAantal = "2";}
                else
                {stamledenAantal = "1";}
                plaatsClicked(plaatsNr);
            }
        }
    }
     
    public void volgendeBeurt()
    {
        int gedaan = 0;
        
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
            if (dc.getSpelerLijst().get(i).getResourceLijst().get(7).getAantal() <= 0) {
                gedaan++;
            }
        }
        
        if (gedaan >= dc.getSpelerLijst().size()) {
            eindeRonde();
        }
        if (dc.getHuidigeSpeler() + 1 >= dc.getSpelerLijst().size()) {
            dc.setHuidigeSpeler(0);
        }
        else
        {
            dc.setHuidigeSpeler(dc.getHuidigeSpeler() + 1);
        }
        if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() <= 0) {
           volgendeBeurt();
        }
        
        formRefresh();
        
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
                    if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() < 1) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else if (isPlaatsOpPlaats(plaatsNummer))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Je hebt reeds geplaatst op deze plek...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() >= parseInt(stamledenAantal))
                    {
                        if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() >= parseInt(stamledenAantal)) {
                            dc.doePlaatsOpPlek(dc.getHuidigeSpeler(), plaatsNummer + 2, parseInt(stamledenAantal));
                            this.updateButtons();
                            volgendeBeurt();
                        }
                        else
                        {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Er is een fout opgetreden");
                            alert.setHeaderText("Je niet genoeg stamleden beschikbaar");
                            alert.setContentText("Kies een andere plek");
                            alert.show();
                        }
                    }
                    break;
                //akkerbouw
                case 5:
                //smith
                case 6:
                    if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() == 0) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else if (isPlaatsOpPlaats(plaatsNummer))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Je hebt reeds geplaatst op deze plek...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() >= parseInt(stamledenAantal))
                    {
                        if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() >= parseInt(stamledenAantal)) {
                            dc.doePlaatsOpPlek(dc.getHuidigeSpeler(), plaatsNummer  == 5 ? plaatsNummer + 4:plaatsNummer + 2, parseInt(stamledenAantal));
                            this.updateButtons();
                            volgendeBeurt();
                        }
                        else
                        {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Er is een fout opgetreden");
                            alert.setHeaderText("Je niet genoeg stamleden beschikbaar");
                            alert.setContentText("Kies een andere plek");
                            alert.show();
                        }
                    }
                    break;
                //hut
                case 7:
                    if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() == 0) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Deze plek staat vol...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else if (isPlaatsOpPlaats(plaatsNummer))
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Er is een fout opgetreden");
                        alert.setHeaderText("Je hebt reeds geplaatst op deze plek...");
                        alert.setContentText("Kies een andere plek");
                        alert.show();
                    }
                    else if (dc.getPlaatsenLijst().get(plaatsNummer).getAantalSpots() >= parseInt(stamledenAantal))
                    {
                        if (dc.getSpelerLijst().get(dc.getHuidigeSpeler()).getResourceLijst().get(7).getAantal() >= parseInt(stamledenAantal)) {
                            dc.doePlaatsOpPlek(dc.getHuidigeSpeler(), plaatsNummer, parseInt(stamledenAantal));
                            this.updateButtons();
                            volgendeBeurt();
                        }
                        else
                        {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Er is een fout opgetreden");
                            alert.setHeaderText("Je niet genoeg stamleden beschikbaar");
                            alert.setContentText("Kies een andere plek");
                            alert.show();
                        }
                    }
                    break;
                
                default:
                    throw new AssertionError();
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
            default: 
                return false;
        }
        return false;
    }
    
    private void bosClicked(ActionEvent ae)
    {
        toonKeuzeStamleden(0);
    }
    
    
    private void leemGroeveClicked(ActionEvent ae)
    {
        toonKeuzeStamleden(1);
    }
    
    private void steenGroeveClicked(ActionEvent ae)
    {
        toonKeuzeStamleden(2);
    }
    
    private void goudMijnClicked(ActionEvent ae)
    {
         toonKeuzeStamleden(3);
    }
    
    private void jachtGebiedClicked(ActionEvent ae)
    {
        toonKeuzeStamleden(4);
    }
    
    private void hutClicked(ActionEvent ae)
    {
        toonKeuzeStamledenSpeciaal(7);
    }
    
    private void smithClicked(ActionEvent ae)
    {
        toonKeuzeStamledenSpeciaal(6);
    }
    
    private void akkerbouwClicked(ActionEvent ae)
    {
        toonKeuzeStamledenSpeciaal(5);
    }
    
    private void formRefresh()
    {
        //hier moeten we de form refreshen voor de labels
        spelAppPaneel.formRefresh();
        
    }
    
     public void voorgrondButtons()
    {
        dc.setHuidigeSpeler(0);
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
   
   private void gebruikGereedschap(int spelerNr, int resourceNr)
   {
       spelAppPaneel.getRow0().setPercentHeight(15);
       spelAppPaneel.add(this, 0, 0);
       switch (resourceNr) {
            case 0:
                new GereedschapPaneel(dc, resourceNr);
               break;
            case 1:
               break;
            case 2:
               break;
            case 3:
               break;
       }
   }
   
    public void eindeRonde()
    {
        //elke plaats weer vrijmaken
        dc.doeResetPlaatsenLijst();
        this.updateButtons();
        for (int index = 0; index < dc.getSpelerLijst().size(); index++) {
            //voedsel aftrekken per speler met akkerbouw ingerekend
            int voedselVermindering = dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(index).getGebruikteStamleden();
            voedselVermindering += dc.getSpelerLijst().get(index).getResourceLijst().get(4).getAantal();
            dc.getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(voedselVermindering);
            
            //elke speler zijn resource die hij tijdens de ronde gegrind heeft geven
            //op het bos
            if (dc.getSpelerLijst().get(index).isPlaatsOpBos() == true){
                int geroldGetal;
                geroldGetal = toonGeroldGetal(0, dc.getSpelerLijst().get(index).getAantalBos(), index);
                if (dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() > 0) {
                    gebruikGereedschap(index, 0);
                }
                else
                {
                    dc.getSpelerLijst().get(index).getResourceLijst().get(0).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(0).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(0).getDeler()));
                }
            }
            //op de leemgroeve
            if (dc.getSpelerLijst().get(index).isPlaatsOpLeemgroeve()== true) {
                int geroldGetal;
                geroldGetal = toonGeroldGetal(1, dc.getSpelerLijst().get(index).getAantalLeemgroeve(), index);
                dc.getSpelerLijst().get(index).getResourceLijst().get(1).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(1).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(1).getDeler()));
                
            }
            //op de steengroeve
            if (dc.getSpelerLijst().get(index).isPlaatsOpSteengroeve()== true) {
                int geroldGetal;
                geroldGetal = toonGeroldGetal(2, dc.getSpelerLijst().get(index).getAantalSteengroeve(), index);
                dc.getSpelerLijst().get(index).getResourceLijst().get(2).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(2).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(2).getDeler()));
                
            }
            //op de goudmijn
            if (dc.getSpelerLijst().get(index).isPlaatsOpGoudmijn()== true) {
                int geroldGetal;
                geroldGetal = toonGeroldGetal(3, dc.getSpelerLijst().get(index).getAantalGoudmijn(), index);
                dc.getSpelerLijst().get(index).getResourceLijst().get(3).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(3).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(3).getDeler()));
                
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpJachtgebied() == true) {
                int geroldGetal;
                geroldGetal = toonGeroldGetal(4, dc.getSpelerLijst().get(index).getAantalJachtgebied(), index);
                dc.getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(4).getDeler()));
                
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpAkkerbouw()== true) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(4).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(4).getAantal() + dc.getSpelerLijst().get(index).getAantalAkkerbouw());
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpSmith()== true) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(5).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() + dc.getSpelerLijst().get(index).getAantalSmith());
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpHut()== true) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(index).getAantalHut() - 1);
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpHutkaart1() == true){
                if (dc.getResourcesChecked(index,0)) {
                    //resources aftrekken
                    dc.doeTrekResourcesAf(index, 0);
                    //speler.setPunten(punten);
                    dc.getSpelerLijst().get(index).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst().get(0).getPunten());
                    //hut verwijderen
                    dc.getHuttenLijst().remove(0);
                }                
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpHutkaart2() == true){
                if (dc.getResourcesChecked(index, 1)) 
                {
                     //resources aftrekken
                    dc.doeTrekResourcesAf(index, 1);
                    //speler.setPunten(punten);
                    dc.getSpelerLijst().get(index).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst().get(1).getPunten());
                    dc.getHuttenLijst().remove(1);
                }
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpHutkaart3() == true){
                if (dc.getResourcesChecked(index,2)) {
                    //resources aftrekken
                    dc.doeTrekResourcesAf(index, 2);
                    //speler.setPunten(punten);
                    dc.getSpelerLijst().get(index).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + dc.getHuttenLijst().get(2).getPunten());
                    dc.getHuttenLijst().remove(2);
                }
            }
            int a = 0;
            formRefresh();
            if (dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() <= 0 && a == 0) {
                a++;
                voedselStraf(index, Math.abs(dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal()));
            }
            //geeft stamleden terug
            dc.getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(index).getGebruikteStamleden());
             //zet gebruikte stamleden terug op 0
            dc.getSpelerLijst().get(index).setGebruikteStamleden(0);
            dc.doeResetSpelerZet(index);
            formRefresh();
        }
        volgendeBeurt();
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
   /* public void eindeSpel(int spelerPunten)
    {
       
    }*/
}

