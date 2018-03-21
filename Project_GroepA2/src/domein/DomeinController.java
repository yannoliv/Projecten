package domein;

import gui.SpelApplicatie;
import java.util.List;
import java.security.SecureRandom;
import java.util.HashSet;
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
    //Bedieningspaneel afprinten
    public String BedieningsPaneel(int spelerNr)
    {
        String bedieningsPaneel = "";
        bedieningsPaneel += String.format("%n%nSpeler %d is nu aan de beurt.%n", getSpelerLijst().get(spelerNr).getSpelerNummer() + 1);
        bedieningsPaneel += String.format("• 0: Stop spel    ");
        bedieningsPaneel += String.format("• 1: Toon spelers    ");
        bedieningsPaneel += String.format("• 2: %s     ", getPlaatsenLijst().get(0).getNaam());//Crashed hier
        bedieningsPaneel += String.format("• 3: %s     ", getPlaatsenLijst().get(1).getNaam());
        bedieningsPaneel += String.format("• 4: %s     ", getPlaatsenLijst().get(2).getNaam());
        bedieningsPaneel += String.format("• 5: %s     ", getPlaatsenLijst().get(3).getNaam());
        bedieningsPaneel += String.format("• 6: %s     ", getPlaatsenLijst().get(6).getNaam());
        bedieningsPaneel += String.format("• 7: %s     ", getPlaatsenLijst().get(7).getNaam());
        bedieningsPaneel += String.format("• 8: %s     ", getPlaatsenLijst().get(5).getNaam());
        bedieningsPaneel += String.format("• 9: %s     ", getPlaatsenLijst().get(4).getNaam());
        bedieningsPaneel += String.format("%n");
        bedieningsPaneel += String.format("• vrije plaatsen: %28d", getPlaatsenLijst().get(0).getAantalSpots());
        bedieningsPaneel += String.format("%17d", getPlaatsenLijst().get(1).getAantalSpots());
        bedieningsPaneel += String.format("%18d", getPlaatsenLijst().get(2).getAantalSpots());
        bedieningsPaneel += String.format("%18d", getPlaatsenLijst().get(3).getAantalSpots());
        bedieningsPaneel += String.format("%23d", getPlaatsenLijst().get(6).getAantalSpots());
        bedieningsPaneel += String.format("%17d", getPlaatsenLijst().get(7).getAantalSpots());
        bedieningsPaneel += String.format("%13d", getPlaatsenLijst().get(5).getAantalSpots());
        bedieningsPaneel += String.format("%18d", getPlaatsenLijst().get(4).getAantalSpots());
        return bedieningsPaneel;
    }
    //---------------------------------------------------------------------------
    //Begint het spel zelf
    public void beginSpel()
    {
        spel.vulPlaatsLijst();
        spelApp.toonScoreBord();
        boolean eindeSpel = true; //dit moet zijn wanneer het einde van het spel bereikt is
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
                        spelApp.bediening(j);
                    }
                }
            }
            spelApp.eindeRonde();
        }
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
            getSpelerLijst().get(index).getResourceLijst().get(6).setAantal(getSpelerLijst().get(index).getResourceLijst().get(6).getAantal() - getSpelerLijst().get(index).getGebruikteStamleden());
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
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                //verhoogt het aantal hout van de speler door zijn voorig aantal op te halen en te verhogen met het gekregen hout
                getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(0).getDeler()));
                //verlaag de aantal mogelijke posities bij bos
                getPlaatsenLijst().get(0).setAantalSpots(getPlaatsenLijst().get(0).getAantalSpots() - aantalStamleden);                
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
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
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
                getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(4).getAantal() + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(6).getDeler()));
                getPlaatsenLijst().get(6).setAantalSpots(getPlaatsenLijst().get(6).getAantalSpots() - aantalStamleden);
                break;
            case 7:
                //stamleden
                getSpelerLijst().get(spelerNr).setGebruikteStamleden(getSpelerLijst().get(spelerNr).getGebruikteStamleden() + aantalStamleden);
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() - getSpelerLijst().get(spelerNr).getGebruikteStamleden());
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() + 1);
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
}