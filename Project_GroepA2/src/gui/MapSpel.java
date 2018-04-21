package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class MapSpel extends GridPane 
{
    private DomeinController dc;
    private int temp;
    public MapSpel(DomeinController dc)
    {
        this.dc = dc;
        buildGUI();

    }
    //to do list
//buttons moeten per click aantalstamleden verhogen tot wnr plek vol is of speler geen stamleden heeft
//meerdere items to do in text als je scrolt
    private void buildGUI()
    {
        //ACHTERGROND
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage Achtergrond = new BackgroundImage(new Image("/images/mapSpel.png"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(Achtergrond));
        //VOORGROND
        voorgrondButtons();
    }
    
    public void voorgrondButtons()
    {
        //de coordinaten zijn (aa) ipv (0,0), dus (1,3) is button (bd)
        //buttons
        Button aa = new Button();
        Button ab = new Button();
        Button ca = new Button();
        Button cb = new Button();
        Button cc = new Button();
        Button cd = new Button();
        Button fa = new Button();
        Button fd = new Button();
        aa.setStyle("-fx-background-color: rgba(255,255,255,0);");
        ab.setStyle("-fx-background-color: rgba(255,255,255,0);");
        ca.setStyle("-fx-background-color: rgba(255,255,255,0);");
        cb.setStyle("-fx-background-color: rgba(255,255,255,0);");
        cc.setStyle("-fx-background-color: rgba(255,255,255,0);");
        cd.setStyle("-fx-background-color: rgba(255,255,255,0);");
        fa.setStyle("-fx-background-color: rgba(255,255,255,0);");
        fd.setStyle("-fx-background-color: rgba(255,255,255,0);");
        
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
        aa.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ab.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ca.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cb.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cc.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fa.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setRowSpan(ab,2);
        this.setColumnSpan(cb,2);
        
        //buttons toevoegen in de gridpane
        this.add(aa,0,0);
        this.add(ab,0,1);
        this.add(ca,2,0);
        this.add(cb,2,1);
        this.add(cc,2,2);
        this.add(cd,2,3);
        this.add(fa,5,0);
        this.add(fd,5,3);
        //zoda je de grid ziet, das temporary
        setGridLinesVisible(true); 
    }
    
    public void eindeRonde()
    {
        //elke plaats weer vrijmaken
        dc.doeResetPlaatsenLijst();
        for (int index = 0; index < dc.getSpelerLijst().size(); index++) {
            //geeft stamleden terug
            dc.getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(index).getGebruikteStamleden());
            //voedsel aftrekken per speler met akkerbouw ingerekend
            int voedselVermindering = dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() - dc.getSpelerLijst().get(index).getGebruikteStamleden();
            voedselVermindering += dc.getSpelerLijst().get(index).getResourceLijst().get(4).getAantal();
            dc.getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(voedselVermindering);
            //elke speler zijn resource die hij tijdens de ronde gegrind heeft geven
            //op het bos
            if (dc.getSpelerLijst().get(index).isPlaatsOpBos() == true){
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = toonGeroldGetal(0, dc.getSpelerLijst().get(index).getAantalBos(), index);
                if (dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (gereedschapBoodschap(index)) { 
                      aantalGereedschap = aantalGebruikGereedschap(index);
                        dc.getSpelerLijst().get(index).getResourceLijst().get(0).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(0).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / dc.getPlaatsenLijst().get(0).getDeler()));
                    }
                }
                else
                {
                    dc.getSpelerLijst().get(index).getResourceLijst().get(0).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(0).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(0).getDeler()));
                }
            }
            //op de leemgroeve
            if (dc.getSpelerLijst().get(index).isPlaatsOpLeemgroeve()== true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = toonGeroldGetal(1, dc.getSpelerLijst().get(index).getAantalLeemgroeve(), index);
                if (dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (gereedschapBoodschap(index)) { 
                    aantalGereedschap = aantalGebruikGereedschap(index);
                        dc.getSpelerLijst().get(index).getResourceLijst().get(1).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(1).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / dc.getPlaatsenLijst().get(1).getDeler()));
                    }
                }
                else
                {
                    dc.getSpelerLijst().get(index).getResourceLijst().get(1).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(1).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(1).getDeler()));
                }
            }
            //op de steengroeve
            if (dc.getSpelerLijst().get(index).isPlaatsOpSteengroeve()== true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = toonGeroldGetal(2, dc.getSpelerLijst().get(index).getAantalSteengroeve(), index);
                if (dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (gereedschapBoodschap(index)) { 
                        aantalGereedschap = aantalGebruikGereedschap(index);
                        dc.getSpelerLijst().get(index).getResourceLijst().get(2).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(2).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / dc.getPlaatsenLijst().get(2).getDeler()));
                    }
                }
                else
                {
                    dc.getSpelerLijst().get(index).getResourceLijst().get(2).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(2).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(2).getDeler()));
                }
            }
            //op de goudmijn
            if (dc.getSpelerLijst().get(index).isPlaatsOpGoudmijn()== true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = toonGeroldGetal(3, dc.getSpelerLijst().get(index).getAantalGoudmijn(), index);
                if (dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    aantalGereedschap = aantalGebruikGereedschap(index);
                    if (gereedschapBoodschap(index)) { 
                        dc.getSpelerLijst().get(index).getResourceLijst().get(3).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(3).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / dc.getPlaatsenLijst().get(3).getDeler()));
                    }
                }
                else
                {
                    dc.getSpelerLijst().get(index).getResourceLijst().get(3).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(3).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(3).getDeler()));
                }
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpJachtgebied() == true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = toonGeroldGetal(4, dc.getSpelerLijst().get(index).getAantalJachtgebied(), index);
                if (dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (gereedschapBoodschap(index)) {
                        aantalGereedschap = aantalGebruikGereedschap(index);
                        dc.getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / dc.getPlaatsenLijst().get(4).getDeler()));
                    }
                }
                else
                {
                    dc.getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() + (int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(4).getDeler()));
                }
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpAkkerbouw()== true) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(4).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(4).getAantal() + dc.getSpelerLijst().get(index).getAantalAkkerbouw());
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpSmith()== true) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(5).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() + dc.getSpelerLijst().get(index).getAantalSmith());
            }
            if (dc.getSpelerLijst().get(index).isPlaatsOpHut()== true) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(dc.getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() + dc.getSpelerLijst().get(index).getAantalHut());
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
            
           //zet gebruikte stamleden terug op 0
            dc.getSpelerLijst().get(index).setGebruikteStamleden(0);
            if (dc.getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() <= 0) {
                dc.getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(0);
                voedselStraf(index, 0);
            }
        }
        dc.doeResetSpelerZet();
    }
    
    public void bedieningsPaneel(int spelerNr)
    {
        boolean isOpGezet = false;
        switch(temp)
        {
            //terug keren
            case 0:                        
                break;
            //bos
            case 2:
                if (dc.getPlaatsenLijst().get(0).getAantalSpots() < 1)
                {
                    //toon label bos is vol
                }
                else if (dc.getSpelerLijst().get(spelerNr).isPlaatsOpBos())
                {
                    //toon label hier heb je al stamleden gezet
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                }
            break;
            //leemgroeve
            case 3:
                if (dc.getPlaatsenLijst().get(1).getAantalSpots() < 1)
                {
                   //toon label leemgroeve is vol
                }
                else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpLeemgroeve())
                {
                   //toon label hier heb je al stamleden gezet
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                }
            break;
            //steengroeve
            case 4: 
                if (dc.getPlaatsenLijst().get(2).getAantalSpots() < 1)
                {
                    //toon label steengroeve is vol
                }
                else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpSteengroeve())
                {
                    //toon label hier heb je al stamleden gezet
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                }
            break;
            //goudmijn
            case 5: 
                if (dc.getPlaatsenLijst().get(3).getAantalSpots() < 1)
                {
                    //toon label goudmijn is vol
                }
                else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpGoudmijn())
                {
                   //toon label hier heb je al stamleden gezet
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                }
            break;
            //jachtgebied
            case 6: 
                if (dc.getPlaatsenLijst().get(4).getAantalSpots() < 1)
                {
                    //toon label jachtgebied is vol 
                }
                else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpJachtgebied())
                {
                    //toon label hier heb je al stamleden gezet
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                }
            break;
            //hut
            case 7:
                if (dc.getPlaatsenLijst().get(7).getAantalSpots() < 2)
                {
                    //toon label hut is vol
                }
                else if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() < 2)
                {
                    //toon label onvoldoende stamleden
                }
                else if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() == 10)
                {
                    //toon label max stamleden bereikt
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, 2);
                }
            break;
            //smith
            case 8: 
                if (dc.getPlaatsenLijst().get(6).getAantalSpots() == 0)
                {
                    //toon label smith is vol
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, 1);
                }
            break;
            //akkerbouw
            case 9: 
                if (dc.getPlaatsenLijst().get(5).getAantalSpots() == 0)
                {
                    //toon label akkerbouw is vol
                }
                else
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, 1);
                }
            break;
            //stop spel
            case 10:
                System.exit(0);
                break;
            case 11:
                for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
                {
                    if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart1())
                    {
                        isOpGezet = true;
                    }
                }
                if (isOpGezet == false)// 
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, 1);
                }
                else
                {
                    //toon label hut 1 is vol
                }
                break;
            case 12:
                for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
                {
                    if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart2())
                    {
                        isOpGezet = true;
                    }
                }
                if (isOpGezet == false)// 
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, 1);
                }
                else
                {
                    //toon label hut 2 is vol
                }
            break;
            case 13:
                for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
                {
                    if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart3())
                    {
                        isOpGezet = true;
                    }
                }
                if (isOpGezet == false)// 
                {
                    dc.doePlaatsOpPlek(spelerNr, temp, 1);
                }
                else
                {
                    //toon label hut 3 is vol
                }
            break;
            default:
                break;
        }
}
    
    
    public void voedselStraf(int spelerNr, int check)
    {
        if (check == 0) {
            //toon label dat speler geen voedsel heeft
        }
        if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() <= 0
            && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() <= 0
            && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() <= 0 
            && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() <= 0) {
            //toon label dat speler 10 strafpunten krijgt
            dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(8).getAantal() - 10);
        }
        else
        {
            //toon venster voor resource te kiezen
            //als ge op button klikt controleert hij of speler resources heeft dan pas 
            //controleer zo: if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() <= 0) 
        }
    }
    
    public int bepaalStamleden(int spelerNr, int keuzeNr)
    {
        int ingegevenAantalStamleden = 0;
        //per buttonclick aantal stamleden verhogen tot aantal stamleden bereikt is
        if (ingegevenAantalStamleden > dc.getPlaatsenLijst().get(keuzeNr - 2).getAantalSpots())
        {
            //toon label te weinig plaats
        }
        return ingegevenAantalStamleden;
    }
    
    public boolean gereedschapBoodschap(int spelerNr)
    {
        boolean a = false;
        //toon label gereedschap bij confirmation button
        return a;
    }
    
    public int aantalGebruikGereedschap(int spelerNr)
    {
        int getal = 0;
        //doen via buttons
        return getal;
    }
    //toon gerold getal plaats resource
    public int toonGeroldGetal(int plaatsNr, int aantalStamleden, int spelerNr)
    {
        int geroldGetal = dc.getGeroldGetal(aantalStamleden);
        
        //toon totaal gerold getal
        return geroldGetal;
    }
    
    
}