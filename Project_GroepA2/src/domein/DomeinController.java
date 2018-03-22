package domein;

import gui.SpelApplicatie;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        spelApp.eindeRonde();
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
            if (getSpelerLijst().get(index).isPlaatsOpBos() == true){
                getSpelerLijst().get(index).getResourceLijst().get(0).setAantal(getSpelerLijst().get(index).getResourceLijst().get(0).getAantal() + getSpelerLijst().get(index).getAantalBos());
            }
            if (getSpelerLijst().get(index).isPlaatsOpLeemgroeve()== true) {
                getSpelerLijst().get(index).getResourceLijst().get(1).setAantal(getSpelerLijst().get(index).getResourceLijst().get(1).getAantal() + getSpelerLijst().get(index).getAantalLeemgroeve());
            }
            if (getSpelerLijst().get(index).isPlaatsOpSteengroeve()== true) {
                getSpelerLijst().get(index).getResourceLijst().get(2).setAantal(getSpelerLijst().get(index).getResourceLijst().get(2).getAantal() + getSpelerLijst().get(index).getAantalSteengroeve());
            }
            if (getSpelerLijst().get(index).isPlaatsOpGoudmijn()== true) {
                getSpelerLijst().get(index).getResourceLijst().get(3).setAantal(getSpelerLijst().get(index).getResourceLijst().get(3).getAantal() + getSpelerLijst().get(index).getAantalGoudmijn());
            }
            if (getSpelerLijst().get(index).isPlaatsOpJachtgebied() == true) {
                getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() + getSpelerLijst().get(index).getAantalJachtgebied());
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
                if (checkResources(index,1)) //controleer ofdat de speler de nodige resources heeft voor hutnummer 1
                {   
                    //resources aftrekken
                    trekResourcesAf(index);
                    //speler.setPunten(punten);
                    getSpelerLijst().get(index).getResourceLijst().get(spelerResourcesControleren(0,index)).setAantal(getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + spel.getHuttenLijst().get(0).getPunten());
                    //hut verwijderen
                    spel.getHuttenLijst().remove(0);
                }
            }
            else if (getSpelerLijst().get(index).isPlaatsOpHutkaart2() == true){
                 if (checkResources(index,2)) //controleer ofdat de speler de nodige resources heeft voor hutnummer 2
                {
                    //resources aftrekken
                    trekResourcesAf(index);
                    //speler.setPunten(punten);
                    getSpelerLijst().get(index).getResourceLijst().get(spelerResourcesControleren(0,index)).setAantal(getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + spel.getHuttenLijst().get(0).getPunten());
                    spel.getHuttenLijst().remove(1);
                 }
            }
            else if (getSpelerLijst().get(index).isPlaatsOpHutkaart3() == true){
                 if (checkResources(index,3)) //controleer ofdat de speler de nodige resources heeft voor hutnummer 3
                {
                    //resources aftrekken
                    trekResourcesAf(index);
                    //speler.setPunten(punten);
                    getSpelerLijst().get(index).getResourceLijst().get(spelerResourcesControleren(0,index)).setAantal(getSpelerLijst().get(index).getResourceLijst().get(8).getAantal() + spel.getHuttenLijst().get(0).getPunten());
                    spel.getHuttenLijst().remove(2);
                 }
                 
            }
            
            
           //zet gebruikte stamleden terug op 0
            getSpelerLijst().get(index).setGebruikteStamleden(0);
            if (getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() <= 0) {
                spel.voedselMelding(index);
            }
        }
        spel.resetSpelerZet();
    }
    //---------------------------------------------------------------------------
    //resources controleren
    public boolean checkResources(int spelerNr, int hutNummer)
    {
        boolean a = false;
        if (getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(spelerNr,hutNummer)).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource1() &&
                    getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(spelerNr,hutNummer)).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource2() &&
                    getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(spelerNr,hutNummer)).getAantal() >= spel.getHuttenLijst().get(hutNummer).getAantalResource3()) {
            a = true;
        }
        return a;
    }
    //---------------------------------------------------------------------------
    //resource omzetting
    public int spelerResourcesControleren(int spelerNr, int hutNummer)
    {
        int getal;
        for (int index = 0; index < spel.getResourceLijst().size(); index++) {
            String spelerResources = getSpelerLijst().get(spelerNr).getResourceLijst().get(index).getNaam();
            if (spel.getHuttenLijst().get(hutNummer).getResource1().equals(spelerResources)) {
                return index;
            }
        }
        return getal = 0;
    }
    //---------------------------------------------------------------------------
    //resource verminderen door aankoop
    public void trekResourcesAf(int spelerNr)
    {
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(0,spelerNr)).getAantal() >= spel.getHuttenLijst().get(0).getAantalResource1())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(0,spelerNr)).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(0,spelerNr)).getAantal() - spel.getHuttenLijst().get(0).getAantalResource1());
        }
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(0,spelerNr)).getAantal() >= spel.getHuttenLijst().get(0).getAantalResource1())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(1,spelerNr)).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(1,spelerNr)).getAantal() - spel.getHuttenLijst().get(0).getAantalResource2());
        }
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(0,spelerNr)).getAantal() >= spel.getHuttenLijst().get(0).getAantalResource1())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(2,spelerNr)).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(spelerResourcesControleren(2,spelerNr)).getAantal() - spel.getHuttenLijst().get(0).getAantalResource3());
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
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden()); //setStamlid( huidig - gebruikt)
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
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpLeemgroeve(true);
                getSpelerLijst().get(spelerNr).setAantalLeemgroeve(getSpelerLijst().get(spelerNr).getAantalLeemgroeve()+ aantalStamleden);
                getPlaatsenLijst().get(1).setAantalSpots(getPlaatsenLijst().get(1).getAantalSpots() - aantalStamleden);
                break;
            case 4:
                //steen
                int test = getSpelerLijst().get(spelerNr).getGebruikteStamleden();
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                test = getSpelerLijst().get(spelerNr).getGebruikteStamleden();;
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpSteengroeve(true);
                getSpelerLijst().get(spelerNr).setAantalSteengroeve(getSpelerLijst().get(spelerNr).getAantalSteengroeve()+ aantalStamleden);
                getPlaatsenLijst().get(2).setAantalSpots(getPlaatsenLijst().get(2).getAantalSpots() - aantalStamleden);
                break;
            case 5:
                //goud
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpGoudmijn(true);
                getSpelerLijst().get(spelerNr).setAantalGoudmijn(getSpelerLijst().get(spelerNr).getAantalGoudmijn()+ aantalStamleden);
                getPlaatsenLijst().get(3).setAantalSpots(getPlaatsenLijst().get(3).getAantalSpots() - aantalStamleden);
                break;
            case 6:
                //voedsel
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpJachtgebied(true);
                getSpelerLijst().get(spelerNr).setAantalJachtgebied(getSpelerLijst().get(spelerNr).getAantalJachtgebied()+ aantalStamleden);
                getPlaatsenLijst().get(6).setAantalSpots(getPlaatsenLijst().get(6).getAantalSpots() - aantalStamleden);
                break;
            case 7:
                //stamleden
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpHut(true);
                getSpelerLijst().get(spelerNr).setAantalHut(getSpelerLijst().get(spelerNr).getAantalHut()+ aantalStamleden);
                getPlaatsenLijst().get(7).setAantalSpots(getPlaatsenLijst().get(7).getAantalSpots() - aantalStamleden);
                break;
            case 8:
                //gereedschap
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpSmith(true);
                getSpelerLijst().get(spelerNr).setAantalSmith(getSpelerLijst().get(spelerNr).getAantalSmith()+ aantalStamleden);
                getPlaatsenLijst().get(5).setAantalSpots(getPlaatsenLijst().get(5).getAantalSpots() - aantalStamleden);
                break;
            case 9:
                //akkerbouw
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpAkkerbouw(true);
                getSpelerLijst().get(spelerNr).setAantalAkkerbouw(getSpelerLijst().get(spelerNr).getAantalAkkerbouw()+ aantalStamleden);
                getPlaatsenLijst().get(4).setAantalSpots(getPlaatsenLijst().get(4).getAantalSpots() -aantalStamleden);
                break;
            case 11:
                //hut kaart 1
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpHutkaart1(true);
                break;
            case 12:
                //hut kaart 1
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setPlaatsOpHutkaart2(true);
                break;
            case 13:
                //hut kaart 1
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
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