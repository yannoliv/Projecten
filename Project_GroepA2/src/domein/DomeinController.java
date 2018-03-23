package domein;

import gui.SpelApplicatie;
import java.util.List;


public class DomeinController 
{

    private SpelApplicatie spelApp;
    private Spel spel;
    
    public DomeinController()
    {
        spelApp = new SpelApplicatie(this); //setDc
        spel = new Spel(); //geen constructor
        spelApp.BepaalAantalSpelers(); //bepaald aantal spelers en set
        spel.setSpelers();
        spelApp.setSpelersNamen();
        spel.setSpelerResources();
        beginSpel();
    }
    //---------------------------------------------------------------------------
    //spelers aanmaken
    public void maakSpelersAan(int aantal)
    {
        spel.maakAantalSpelers(aantal);
    }
    //---------------------------------------------------------------------------
    //spelers namen geven
    public void geefSpelersNamen(String naam, Speler speler)
    {
        spel.setSpelerNaam(naam, speler);
    }
    //---------------------------------------------------------------------------
    //aantal speler ophalen
    public int getAantalSpelers()
    {
        return spel.getAantalSpelers();
    }
    //---------------------------------------------------------------------------
    //aantal speler opslaan
    public void setAantalSpelers(int aantal)
    {
        spel.setAantalSpelers(aantal);
    }
    //---------------------------------------------------------------------------
    //spelerlijst ophalen
    public List<Speler> getSpelerLijst()
    {
        return spel.getSpelerLijst();
    }
    //---------------------------------------------------------------------------
    //plaatsenlijst ophalen
    public List<Plaats> getPlaatsenLijst()
    {
        return spel.getPlaatsenLijst();
    }
    //---------------------------------------------------------------------------
    //huttenlijst ophalen
    public List<Plaats> getHuttenLijst()
    {
        return spel.getHuttenLijst();
    }
    
    
    //---------------------------------------------------------------------------
    //per speler zijn scoreboard ophalen
    public String toonSpelers()
    {
        String resultaat = "";
        for (int i = 0; i < getSpelerLijst().size(); i++) 
        {
            resultaat += getSpelerLijst().get(i).toString(getSpelerLijst().get(i));
            resultaat += String.format("%n");
        }
        return resultaat;
    }
    //---------------------------------------------------------------------------
    //Begint het spel zelf
    public void beginSpel()
    {
        spel.vulPlaatsLijst();
        spel.vulHuttenLijst();
        spelApp.toonScoreBord();
        boolean eindeSpel = true;
        while(eindeSpel)
        {
            omDeBeurt();
        }
    }
    //---------------------------------------------------------------------------
    //Elke speler om de beurt laten spelen
    public void omDeBeurt()
    {
        //per ronde per speler een apart bedieningspaneel
        for (int i = 0; i < getSpelerLijst().size(); i++)         
        {
            while(getSpelerLijst().get(i).getResourceLijst().get(7).getAantal() != 0)
            {
                for (int j = 0; j < getSpelerLijst().size(); j++) 
                {
                    if (getSpelerLijst().get(j).getResourceLijst().get(7).getAantal() > 0)    
                    {
                        spelApp.bedieningsPaneel(j);
                        spel.spelerFix(j);
                    }
                }
            }
        }
        //tonen dat de ronde gedaan is
        spelApp.toonEindeRondeBericht(spel.eindeRondeBericht());
        eindeRonde();
        spelApp.toonScoreBord();
    }
    //---------------------------------------------------------------------------
    //get gerold getal
    public int getGeroldGetal(int aantalStamleden)
    {
        return spel.dobbelStenen(aantalStamleden);
    }
    //---------------------------------------------------------------------------
    // einde van de ronde, resources geven
    public void eindeRonde()
    {
        //elke plaats weer vrijmaken
        spel.resetPlaatsenLijst();
        for (int index = 0; index < spel.getAantalSpelers(); index++) {
            //geeft stamleden terug
            getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() + getSpelerLijst().get(index).getGebruikteStamleden());
            //voedsel aftrekken per speler met akkerbouw ingerekend
            int voedselVermindering = getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() - getSpelerLijst().get(index).getGebruikteStamleden();
            voedselVermindering += getSpelerLijst().get(index).getResourceLijst().get(4).getAantal();
            getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(voedselVermindering);
            //elke speler zijn resource die hij tijdens de ronde gegrind heeft geven
            //op het bos
            if (getSpelerLijst().get(index).isPlaatsOpBos() == true){
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = spelApp.toonGeroldGetal(0, getSpelerLijst().get(index).getAantalBos(), index);
                if (getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (spelApp.gereedschapBoodschap(index)) { 
                      aantalGereedschap = spelApp.aantalGebruikGereedschap(index);
                        getSpelerLijst().get(index).getResourceLijst().get(0).setAantal(getSpelerLijst().get(index).getResourceLijst().get(0).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / getPlaatsenLijst().get(0).getDeler()));
                    }
                }
                else
                {
                    getSpelerLijst().get(index).getResourceLijst().get(0).setAantal(getSpelerLijst().get(index).getResourceLijst().get(0).getAantal() + (int) Math.floor(geroldGetal / getPlaatsenLijst().get(0).getDeler()));
                }
            }
            //op de leemgroeve
            if (getSpelerLijst().get(index).isPlaatsOpLeemgroeve()== true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = spelApp.toonGeroldGetal(1, getSpelerLijst().get(index).getAantalLeemgroeve(), index);
                if (getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (spelApp.gereedschapBoodschap(index)) { 
                    aantalGereedschap = spelApp.aantalGebruikGereedschap(index);
                        getSpelerLijst().get(index).getResourceLijst().get(1).setAantal(getSpelerLijst().get(index).getResourceLijst().get(1).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / getPlaatsenLijst().get(1).getDeler()));
                    }
                }
                else
                {
                    getSpelerLijst().get(index).getResourceLijst().get(1).setAantal(getSpelerLijst().get(index).getResourceLijst().get(1).getAantal() + (int) Math.floor(geroldGetal / getPlaatsenLijst().get(1).getDeler()));
                }
            }
            //op de steengroeve
            if (getSpelerLijst().get(index).isPlaatsOpSteengroeve()== true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = spelApp.toonGeroldGetal(2, getSpelerLijst().get(index).getAantalSteengroeve(), index);
                if (getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (spelApp.gereedschapBoodschap(index)) { 
                        aantalGereedschap = spelApp.aantalGebruikGereedschap(index);
                        getSpelerLijst().get(index).getResourceLijst().get(2).setAantal(getSpelerLijst().get(index).getResourceLijst().get(2).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / getPlaatsenLijst().get(2).getDeler()));
                    }
                }
                else
                {
                    getSpelerLijst().get(index).getResourceLijst().get(2).setAantal(getSpelerLijst().get(index).getResourceLijst().get(2).getAantal() + (int) Math.floor(geroldGetal / getPlaatsenLijst().get(2).getDeler()));
                }
            }
            //op de goudmijn
            if (getSpelerLijst().get(index).isPlaatsOpGoudmijn()== true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = spelApp.toonGeroldGetal(3, getSpelerLijst().get(index).getAantalGoudmijn(), index);
                if (getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    aantalGereedschap = spelApp.aantalGebruikGereedschap(index);
                    if (spelApp.gereedschapBoodschap(index)) { 
                        getSpelerLijst().get(index).getResourceLijst().get(3).setAantal(getSpelerLijst().get(index).getResourceLijst().get(3).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / getPlaatsenLijst().get(3).getDeler()));
                    }
                }
                else
                {
                    getSpelerLijst().get(index).getResourceLijst().get(3).setAantal(getSpelerLijst().get(index).getResourceLijst().get(3).getAantal() + (int) Math.floor(geroldGetal / getPlaatsenLijst().get(3).getDeler()));
                }
            }
            if (getSpelerLijst().get(index).isPlaatsOpJachtgebied() == true) {
                int geroldGetal;
                int aantalGereedschap;
                geroldGetal = spelApp.toonGeroldGetal(4, getSpelerLijst().get(index).getAantalJachtgebied(), index);
                if (getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() >= 1) 
                {
                    if (spelApp.gereedschapBoodschap(index)) {
                        aantalGereedschap = spelApp.aantalGebruikGereedschap(index);
                        getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() + (int) Math.floor((geroldGetal + aantalGereedschap) / getPlaatsenLijst().get(4).getDeler()));
                    }
                }
                else
                {
                    getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() + (int) Math.floor(geroldGetal / getPlaatsenLijst().get(4).getDeler()));
                }
            }
            if (getSpelerLijst().get(index).isPlaatsOpAkkerbouw()== true) {
                getSpelerLijst().get(index).getResourceLijst().get(4).setAantal(getSpelerLijst().get(index).getResourceLijst().get(4).getAantal() + getSpelerLijst().get(index).getAantalAkkerbouw());
            }
            if (getSpelerLijst().get(index).isPlaatsOpSmith()== true) {
                getSpelerLijst().get(index).getResourceLijst().get(5).setAantal(getSpelerLijst().get(index).getResourceLijst().get(5).getAantal() + getSpelerLijst().get(index).getAantalSmith());
            }
            if (getSpelerLijst().get(index).isPlaatsOpHut()== true) {
                getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() + getSpelerLijst().get(index).getAantalHut());
            }
            if (getSpelerLijst().get(index).isPlaatsOpHutkaart1() == true){
                if (checkResources(index,0)) {
                    //resources aftrekken
                    trekResourcesAf(index, 0);
                    //speler.setPunten(punten);
                    getSpelerLijst().get(index).getResourceLijst().get(8).setAantal(getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + spel.getHuttenLijst().get(0).getPunten());
                    //hut verwijderen
                    spel.getHuttenLijst().remove(0);
                }                
            }
            if (getSpelerLijst().get(index).isPlaatsOpHutkaart2() == true){
                if (checkResources(index, 1)) 
                {
                     //resources aftrekken
                    trekResourcesAf(index, 1);
                    //speler.setPunten(punten);
                    getSpelerLijst().get(index).getResourceLijst().get(8).setAantal(getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + spel.getHuttenLijst().get(1).getPunten());
                    spel.getHuttenLijst().remove(1);
                }
            }
            if (getSpelerLijst().get(index).isPlaatsOpHutkaart3() == true){
                if (checkResources(index,2)) {
                    //resources aftrekken
                    trekResourcesAf(index, 2);
                    //speler.setPunten(punten);
                    getSpelerLijst().get(index).getResourceLijst().get(8).setAantal(getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + spel.getHuttenLijst().get(2).getPunten());
                    spel.getHuttenLijst().remove(2);
                }
            }
            
           //zet gebruikte stamleden terug op 0
            getSpelerLijst().get(index).setGebruikteStamleden(0);
            if (getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() <= 0) {
                getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(0);
                spelApp.voedselStraf(index, 0);
            }
        }
        spel.resetSpelerZet();
    }
    //---------------------------------------------------------------------------
    //resources controleren
    public boolean checkResources(int spelerNr, int hutNummer)
    {
        boolean a = false;
        if (getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummerOphalen(spelerNr,hutNummer)).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource1() &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummerOphalen(spelerNr,hutNummer)).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource2() &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummerOphalen(spelerNr,hutNummer)).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource3()) {
            a = true;
        }
        return a;
    }
    //---------------------------------------------------------------------------
    //resource omzetting
    public int resourceNummerOphalen(int spelerNr, int hutNummer)
    {
        int getal;
        for (int resourceNummer = 0; resourceNummer < 4; resourceNummer++) {
            String resourceNamen = getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummer).getNaam();
            if (spel.getHuttenLijst().get(hutNummer).getResource1().toLowerCase().equals(resourceNamen)) {
                return resourceNummer;
            }
        }
        return getal = 0;
    }
    //---------------------------------------------------------------------------
    //resource verminderen door aankoop
    public void trekResourcesAf(int spelerNr, int hutNummer)
    {
        int resourceNr1 = resourceNummerOphalen(spelerNr, 0);
        int resourceNr2 = resourceNummerOphalen(spelerNr, 1);
        int resourceNr3 = resourceNummerOphalen(spelerNr, 2);
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr1).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource1())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr1).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr1).getAantal() - spel.getHuttenLijst().get(hutNummer).getAantalResource1());
        }
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr2).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource2())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr2).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr2).getAantal() - spel.getHuttenLijst().get(hutNummer).getAantalResource2());
        }
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr3).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource3())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr3).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr3).getAantal() - spel.getHuttenLijst().get(hutNummer).getAantalResource3());
        }
    }
    //---------------------------------------------------------------------------
    //speler plaatsen op bos
    public void plaatsOpPlek(int spelerNr, int keuzeNr, int aantalStamleden)
    {        
        switch (keuzeNr) {
            case 2:
                //hout
                //verhoogt gebruikte stamleden
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                //verlaagt de speler zijn stamleden met de gebruikte stamleden
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden); //setStamlid( huidig - gebruikt)
                //zet de boolean bos voor de speler op true
                getSpelerLijst().get(spelerNr).setPlaatsOpBos(true);
                //verhoogt het aantal dat speler op bos heeft geplaatst
                getSpelerLijst().get(spelerNr).setAantalBos(getSpelerLijst().get(spelerNr).getAantalBos() + aantalStamleden);
                //verlaag de aantal mogelijke posities bij bos
                getPlaatsenLijst().get(0).setAantalSpots(getPlaatsenLijst().get(0).getAantalSpots() - aantalStamleden);
                break;
            case 3:
                //leem
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpLeemgroeve(true);
                getSpelerLijst().get(spelerNr).setAantalLeemgroeve(getSpelerLijst().get(spelerNr).getAantalLeemgroeve()+ aantalStamleden);
                getPlaatsenLijst().get(1).setAantalSpots(getPlaatsenLijst().get(1).getAantalSpots() - aantalStamleden);
                break;
            case 4:
                //steen
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpSteengroeve(true);
                getSpelerLijst().get(spelerNr).setAantalSteengroeve(getSpelerLijst().get(spelerNr).getAantalSteengroeve()+ aantalStamleden);
                getPlaatsenLijst().get(2).setAantalSpots(getPlaatsenLijst().get(2).getAantalSpots() - aantalStamleden);
                break;
            case 5:
                //goud
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpGoudmijn(true);
                getSpelerLijst().get(spelerNr).setAantalGoudmijn(getSpelerLijst().get(spelerNr).getAantalGoudmijn()+ aantalStamleden);
                getPlaatsenLijst().get(3).setAantalSpots(getPlaatsenLijst().get(3).getAantalSpots() - aantalStamleden);
                break;
            case 6:
                //voedsel
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpJachtgebied(true);
                getSpelerLijst().get(spelerNr).setAantalJachtgebied(getSpelerLijst().get(spelerNr).getAantalJachtgebied()+ aantalStamleden);
                getPlaatsenLijst().get(4).setAantalSpots(getPlaatsenLijst().get(4).getAantalSpots() - aantalStamleden);
                break;
            case 7:
                //stamleden
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpHut(true);
                getSpelerLijst().get(spelerNr).setAantalHut(getSpelerLijst().get(spelerNr).getAantalHut()+ aantalStamleden);
                getPlaatsenLijst().get(7).setAantalSpots(getPlaatsenLijst().get(7).getAantalSpots() - aantalStamleden);
                break;
            case 8:
                //gereedschap
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpSmith(true);
                getSpelerLijst().get(spelerNr).setAantalSmith(getSpelerLijst().get(spelerNr).getAantalSmith()+ aantalStamleden);
                getPlaatsenLijst().get(6).setAantalSpots(getPlaatsenLijst().get(6).getAantalSpots() - aantalStamleden);
                break;
            case 9:
                //akkerbouw
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpAkkerbouw(true);
                getSpelerLijst().get(spelerNr).setAantalAkkerbouw(getSpelerLijst().get(spelerNr).getAantalAkkerbouw()+ aantalStamleden);
                getPlaatsenLijst().get(5).setAantalSpots(getPlaatsenLijst().get(5).getAantalSpots() -aantalStamleden);
                break;
            case 11:
                //hut kaart 1
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpHutkaart1(true);
                break;
            case 12:
                //hut kaart 2
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpHutkaart2(true);
                break;
            case 13:
                //hut kaart 3
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - aantalStamleden);
                getSpelerLijst().get(spelerNr).setPlaatsOpHutkaart3(true);
                break;
        }
    }
    //---------------------------------------------------------------------------
        //bedieningspaneel ophalen
        public String getBedieningsPaneel(int spelerNr)
        {
            return spel.bedieningsPaneel(spelerNr);
        }
        
}


//recyclage later
//verhoogt het aantal hout van de speler door zijn voorig aantal op te halen en te verhogen met het gekregen hout
//                getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() 
//                        + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(0).getDeler()));