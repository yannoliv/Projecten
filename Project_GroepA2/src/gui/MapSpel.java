package gui;

import domein.DomeinController;
import static java.lang.Integer.parseInt;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    private int buttonNr;
    private int plaatsAantalStamleden;
    private String stamledenAantal;
    private boolean a = false;
    private boolean OK = true;
    private int spelerAanBeurt = 0;
    
    //alert
    Alert alert = new Alert(AlertType.INFORMATION);
    
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
    
    private void beginSpel()
    {
        buildMap();
        dc.setSpelerBeurt(spelerAanBeurt);
        //omDeBeurt();
        
    }
    
    private void toonKeuzeStamleden()
    {
        TextInputDialog inputAantalStamleden = new TextInputDialog("u heeft "+ dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() + String.format("%s",dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 1 ? "stamleden":"stamlid"));
        inputAantalStamleden.setTitle("U heeft " + dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() + String.format("%s",dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 1 ? "stamleden":"stamlid"));
        inputAantalStamleden.setHeaderText("Kies het aantal stamleden");
        inputAantalStamleden.setContentText(null);
        Optional<String> result = inputAantalStamleden.showAndWait();
        if (result.isPresent()) {
            stamledenAantal = result.get();
        }
    }
    
    private void bosClicked(ActionEvent ae)
    {
        toonKeuzeStamleden();
        
        if (dc.getPlaatsenLijst().get(0).getAantalSpots() < 1)
        {
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol...");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
        else if (dc.getSpelerLijst().get(spelerAanBeurt).isPlaatsOpBos())
        {
            
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Je hebt reeds geplaatst op bos...");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
        else
        {
            try
            {
                //als dit niet in orde is, wordt het onderste ook niet uitgevoerd
                dc.doePlaatsOpPlek(spelerAanBeurt, 2, parseInt(stamledenAantal));
                
                //dit eronder is enkel voor te testen
                dc.getSpelerLijst().get(spelerAanBeurt).getResourceLijst().get(0).setAantal(dc.getGeroldGetal(parseInt(stamledenAantal)));
                System.out.println("hout: "+dc.getSpelerLijst().get(spelerAanBeurt).getResourceLijst().get(0).getAantal());
                System.out.println("speler: " + (spelerAanBeurt ));

                volgendeBeurt();
            }catch(NumberFormatException e)
            {
                alert.setTitle("Er is een fout opgetreden");
                alert.setHeaderText("Fout");
                alert.setContentText("Vul een cijfer in aub");
                alert.show();
            }
        }
        
        
        
    }
    
    private void volgendeBeurt()
    {
        if (spelerAanBeurt +1 < dc.getSpelerLijst().size())
        {
            this.spelerAanBeurt ++;
        }
        else
        {
            spelerAanBeurt = 0;
        }
        formRefresh();
    }
    
    
    private void leemGroeveClicked(ActionEvent ae)
    {
        buttonNr = 3;
        if (dc.getPlaatsenLijst().get(1).getAantalSpots() > 0 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 0) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void steenGroeveClicked(ActionEvent ae)
    {
        buttonNr = 4;
        if (dc.getPlaatsenLijst().get(2).getAantalSpots() > 0 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 0) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void goudMijnClicked(ActionEvent ae)
    {
        buttonNr = 5;
        if (dc.getPlaatsenLijst().get(3).getAantalSpots() > 0 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 0) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void jachtGebiedcClicked(ActionEvent ae)
    {
        buttonNr = 6;
        if (dc.getPlaatsenLijst().get(4).getAantalSpots() > 0 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 0) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void hutClicked(ActionEvent ae)
    {
        buttonNr = 7;
        if (dc.getPlaatsenLijst().get(7).getAantalSpots() == 2 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() >= 2) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void smithClicked(ActionEvent ae)
    {
        buttonNr = 8;
        if (dc.getPlaatsenLijst().get(6).getAantalSpots() > 0 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 0) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void akkerbouwClicked(ActionEvent ae)
    {
        buttonNr = 9;
        if (dc.getPlaatsenLijst().get(5).getAantalSpots() > 0 && dc.getSpelerLijst().get(dc.getSpelerBeurt()).getResourceLijst().get(7).getAantal() > 0) {
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Er is een fout opgetreden");
            alert.setHeaderText("Deze plek staat vol");
            alert.setContentText("Kies een andere plek");
            alert.show();
        }
    }
    
    private void formRefresh()
    {
        //hier moeten we de form refreshen
        spelAppPaneel.formRefresh();
    }
    
     public void voorgrondButtons()
    {
        //de coordinaten zijn (aa) ipv (0,0), dus (1,3) is button (bd)
        //buttons
        btn_leemGroeve = new Button();
        btn_bos = new Button();
        btn_steenGroeve = new Button();
        btn_jachtGebied = new Button();
        btn_hut = new Button();
        btn_akkerbouw = new Button();
        btn_goudMijn = new Button();
        btn_smith = new Button();
        
        btn_bos.setOnAction(this::bosClicked);
        btn_leemGroeve.setOnAction(this::leemGroeveClicked);
        btn_steenGroeve.setOnAction(this::steenGroeveClicked);
        btn_goudMijn.setOnAction(this::goudMijnClicked);
        btn_akkerbouw.setOnAction(this::akkerbouwClicked);
        btn_smith.setOnAction(this::smithClicked);
        btn_hut.setOnAction(this::hutClicked);
        
        btn_leemGroeve.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_bos.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_steenGroeve.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_jachtGebied.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_hut.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_akkerbouw.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_goudMijn.setStyle("-fx-background-color: rgba(255,255,255,0);");
        btn_smith.setStyle("-fx-background-color: rgba(255,255,255,0);");
        
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
        //zoda je de grid ziet, das temporary
        setGridLinesVisible(true);
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
}
