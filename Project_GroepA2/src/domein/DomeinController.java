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
        System.out.printf("%n" + spel.getHuttenLijst().get(0).getResource1() + " " + spel.getHuttenLijst().get(0).getResource2() + " " + spel.getHuttenLijst().get(0).getResource3() + "%n" 
        + spel.getHuttenLijst().get(0).getAantalResource1() + " " + spel.getHuttenLijst().get(0).getAantalResource2() + " " + spel.getHuttenLijst().get(0).getAantalResource3());
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
            getSpelerLijst().get(index).getResourceLijst().get(7).setAantal(getSpelerLijst().get(index).getGebruikteStamleden());
            //voedsel aftrekken per speler
            getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() - (getSpelerLijst().get(index).getGebruikteStamleden() - getSpelerLijst().get(index).getResourceLijst().get(4).getAantal()));
            //verhoog voedsel met akkerbouw van de speler
            //zet gebruikte stamleden terug op 0
            getSpelerLijst().get(index).setGebruikteStamleden(0);
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
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() //setStamlid( huidig - gebruikt)
                        - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                //verhoogt het aantal hout van de speler door zijn voorig aantal op te halen en te verhogen met het gekregen hout
                getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() 
                        + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(0).getDeler()));
                //verlaag de aantal mogelijke posities bij bos
                getPlaatsenLijst().get(0).setAantalSpots(getPlaatsenLijst().get(0).getAantalSpots() 
                        - aantalStamleden);    
                
                break;
            case 3:
                //leem
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(1).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(1).getDeler()));
                getPlaatsenLijst().get(1).setAantalSpots(getPlaatsenLijst().get(1).getAantalSpots() - aantalStamleden);
                break;
            case 4:
                //steen
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(2).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(2).getDeler()));
                getPlaatsenLijst().get(2).setAantalSpots(getPlaatsenLijst().get(2).getAantalSpots() - aantalStamleden);
                break;
            case 5:
                //goud
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(3).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(3).getDeler()));
                getPlaatsenLijst().get(3).setAantalSpots(getPlaatsenLijst().get(3).getAantalSpots() - aantalStamleden);
                break;
            case 6:
                //voedsel
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(6).getAantal() + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(6).getDeler()));
                getPlaatsenLijst().get(6).setAantalSpots(getPlaatsenLijst().get(6).getAantalSpots() - aantalStamleden);
                break;
            case 7:
                //stamleden
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() + 2);
                getPlaatsenLijst().get(7).setAantalSpots(getPlaatsenLijst().get(7).getAantalSpots() - aantalStamleden);
                break;
            case 8:
                //gereedschap
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(5).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal() + 1);
                getPlaatsenLijst().get(5).setAantalSpots(getPlaatsenLijst().get(5).getAantalSpots() - aantalStamleden);
                break;
            case 9:
                //akkerbouw
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(4).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(4).getAantal() + 1);
                getPlaatsenLijst().get(4).setAantalSpots(getPlaatsenLijst().get(4).getAantalSpots() - aantalStamleden);
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