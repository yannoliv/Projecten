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
    private boolean aantalSpotsHK4 = false;
    //De afbeelding
    private ImageView hutImage;
    private ImageView hutImage_2;

    //individuele kaarten
    private HBox kaart1 = new HBox(5);
    private HBox kaart2 = new HBox(5);
    private HBox kaart3 = new HBox(5);
    private HBox kaart4 = new HBox(5);
      
    public KaartPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc, MapSpel mapSpel)
    {
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        this.mapSpel = mapSpel;
        maakKaartPaneel();
        
    }
    
    public void refreshKaartPaneel()
    {
        this.getChildren().clear();
        this.getChildren().addAll(kaart1, kaart2, kaart3, kaart4);
    }
    
    private void maakKaartPaneel()
    {
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage bgImg = new BackgroundImage(new Image("/images/kaartPaneel_H.png"),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(bgImg));
        
        //HUT 1
        Label resource1 = new Label ("" + dc.getHuttenLijst1().get(0).getAantalResource1());
        Label resource2 = new Label ("" + dc.getHuttenLijst1().get(0).getAantalResource2());
        Label resource3 = new Label ("" + dc.getHuttenLijst1().get(0).getAantalResource3());
        
        HBox resources1 = new HBox ();
        resources1.getChildren().addAll(setIcon(dc.getHuttenLijst1().get(0).getResource1()), resource1);
        HBox resources2 = new HBox ();
        resources2.getChildren().addAll(setIcon(dc.getHuttenLijst1().get(0).getResource2()), resource2);
        HBox resources3 = new HBox ();
        resources3.getChildren().addAll(setIcon(dc.getHuttenLijst1().get(0).getResource3()), resource3);
        
        VBox vResources = new VBox(5);
        vResources.setStyle("-fx-padding:20px 0 0 0");
        vResources.getChildren().addAll(resources1,resources2,resources3);
        
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
        
        if(dc.getHuttenLijst1().get(0).getAantalSpots() == 1){
        kaart1.getChildren().addAll(hutImage, vResources);
        }else{
            kaart1.getChildren().addAll(hutImage_2, vResources);
        }
        
        
        //HUT 2
        resource1 = new Label ("" + dc.getHuttenLijst2().get(0).getAantalResource1());
        resource2 = new Label ("" + dc.getHuttenLijst2().get(0).getAantalResource2());
        resource3 = new Label ("" + dc.getHuttenLijst2().get(0).getAantalResource3());
        
        resources1 = new HBox ();
        resources1.getChildren().addAll(setIcon(dc.getHuttenLijst2().get(0).getResource1()), resource1);
        resources2 = new HBox ();
        resources2.getChildren().addAll(setIcon(dc.getHuttenLijst2().get(0).getResource2()), resource2);
        resources3 = new HBox ();
        resources3.getChildren().addAll(setIcon(dc.getHuttenLijst2().get(0).getResource3()), resource3);
        
        vResources = new VBox(5);
        vResources.setStyle("-fx-padding:20px 0 0 0");
        vResources.getChildren().addAll(resources1,resources2,resources3);
        
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
        
        if(dc.getHuttenLijst2().get(0).getAantalSpots() == 1){
        kaart2.getChildren().addAll(hutImage, vResources);
        }else{
            kaart2.getChildren().addAll(hutImage_2, vResources);
        }
        
        
        //HUT 3
        resource1 = new Label ("" + dc.getHuttenLijst3().get(0).getAantalResource1());
        resource2 = new Label ("" + dc.getHuttenLijst3().get(0).getAantalResource2());
        resource3 = new Label ("" + dc.getHuttenLijst3().get(0).getAantalResource3());
        
        resources1 = new HBox ();
        resources1.getChildren().addAll(setIcon(dc.getHuttenLijst3().get(0).getResource1()), resource1);
        resources2 = new HBox ();
        resources2.getChildren().addAll(setIcon(dc.getHuttenLijst3().get(0).getResource2()), resource2);
        resources3 = new HBox ();
        resources3.getChildren().addAll(setIcon(dc.getHuttenLijst3().get(0).getResource3()), resource3);
        
        vResources = new VBox(5);
        vResources.setStyle("-fx-padding:20px 0 0 0");
        vResources.getChildren().addAll(resources1,resources2,resources3);
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
        
        if(dc.getHuttenLijst3().get(0).getAantalSpots() == 1){
        kaart3.getChildren().addAll(hutImage, vResources);
        }else{
            kaart3.getChildren().addAll(hutImage_2, vResources);
        }
        
        
        //HUT 4
        resource1 = new Label ("" + dc.getHuttenLijst4().get(0).getAantalResource1());
        resource2 = new Label ("" + dc.getHuttenLijst4().get(0).getAantalResource2());
        resource3 = new Label ("" + dc.getHuttenLijst4().get(0).getAantalResource3());
        
        resources1 = new HBox ();
        resources1.getChildren().addAll(setIcon(dc.getHuttenLijst4().get(0).getResource1()), resource1);
        resources2 = new HBox ();
        resources2.getChildren().addAll(setIcon(dc.getHuttenLijst4().get(0).getResource2()), resource2);
        resources3 = new HBox ();
        resources3.getChildren().addAll(setIcon(dc.getHuttenLijst4().get(0).getResource3()), resource3);
        
        vResources = new VBox(5);
        vResources.setStyle("-fx-padding:20px 0 0 0");
        vResources.getChildren().addAll(resources1,resources2,resources3);
        
        hutImage  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
        hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
        
        if(dc.getHuttenLijst4().get(0).getAantalSpots() == 1){
        kaart4.getChildren().addAll(hutImage, vResources);
        }else{
            kaart4.getChildren().addAll(hutImage_2, vResources);
        }
        
        
        //kaarten toevoegen
        this.getChildren().addAll(kaart1, kaart2, kaart3, kaart4);
        
        kaart1.setOnMouseClicked(this::kaart1Click);
        kaart2.setOnMouseClicked(this::kaart2Click);
        kaart3.setOnMouseClicked(this::kaart3Click);
        kaart4.setOnMouseClicked(this::kaart4Click);


    }
    private Label setIcon(String getResource)
    {
        Label label = new Label("test");
        switch(getResource)
        {
            case "hout":
            label = new Label(":");
            label.setGraphic(new ImageView(new Image("/images/Hout.png", 20, 20, false, true)));
            break;
            case "leem":
            label = new Label(":");
            label.setGraphic(new ImageView(new Image("/images/Leem.png", 20, 20, false, true)));
            break;
            case "steen":
            label = new Label(":");
            label.setGraphic(new ImageView(new Image("/images/Steen.png", 20, 20, false, true)));
            break;
            case "goud":
            label = new Label(":");
            label.setGraphic(new ImageView(new Image("/images/Goud.png", 20, 20, false, true)));
            break;
        }
        return label;
    }
   

    private void kaart1Click(MouseEvent ae)
    {
        mapSpel.hutKaart1Clicked();
    }
    
    private void kaart2Click(MouseEvent ae)
    {
        mapSpel.hutKaart2Clicked();
    }
    
    private void kaart3Click(MouseEvent ae)
    {
        mapSpel.hutKaart3Clicked();
    }
    
    private void kaart4Click(MouseEvent ae)
    {
        mapSpel.hutKaart4Clicked();
    }
    
    public void doeLichtUit(int kaart)
    {
        switch (kaart)
        {
            case 1:hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
                    kaart1.getChildren().set(0, hutImage_2);
                break;
            case 2:
                hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
                    kaart2.getChildren().set(0, hutImage_2);
                break;
            case 3:
                hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
                    kaart3.getChildren().set(0, hutImage_2);
                break;
            case 4:
                hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/huts kopie.png")));
                    kaart4.getChildren().set(0, hutImage_2);
                break;
        }
    }
    
    public void doeLichtAan(int kaart)
    {
        switch (kaart)
        {
            case 1:hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
                    kaart1.getChildren().set(0, hutImage_2);
                break;
            case 2:
                hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
                    kaart2.getChildren().set(0, hutImage_2);
                break;
            case 3:
                hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
                    kaart3.getChildren().set(0, hutImage_2);
                break;
            case 4:
                hutImage_2  = new ImageView(new Image(getClass().getResourceAsStream("/images/hut kopie 2.png")));
                    kaart4.getChildren().set(0, hutImage_2);
                break;
        }
    }
}

      
