package domein;

import gui.SpelApplicatie;
import java.util.List;
import java.security.SecureRandom;


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
        System.out.println(toonSpelers());
        
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
            
            eindeRonde();
        }
    }
    //---------------------------------------------------------------------------
    // einde van de ronde, resources geven
    public void eindeRonde()
    {
        //per ronde stamleden terug geven
        for (int i = 0; i < getSpelerLijst().size(); i++) 
        {
            //per ronde resources geven
            getSpelerLijst().get(i).getResourceLijst().get(0);
            getSpelerLijst().get(i).getResourceLijst().get(0);
            getSpelerLijst().get(i).getResourceLijst().get(0);
            getSpelerLijst().get(i).getResourceLijst().get(0);
            getSpelerLijst().get(i).getResourceLijst().get(0);
            getSpelerLijst().get(i).getResourceLijst().get(0);
        }
    }
    //Gerold getal bepalen
    public int dobbelStenen(int aantalStamleden)
    {
        int getal;
        int dobbelSteen = 0;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < aantalStamleden; i++) {
            dobbelSteen += random.nextInt(6) + 1;
        }
        
        getal = dobbelSteen * aantalStamleden;
        return getal;
    }
    //---------------------------------------------------------------------------
    //speler plaatsen op bos
    public void plaatsOpPlek(int spelerNr, int keuzeNr, int aantalStamleden)
    {
        switch (keuzeNr) {
            case 2:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 3:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(1).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 4:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(2).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 5:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(3).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 6:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 7:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 8:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(5).setAantal(dobbelStenen(aantalStamleden));
                break;
            case 9:
                getSpelerLijst().get(spelerNr).getResourceLijst().get(4).setAantal(dobbelStenen(aantalStamleden));
                break;
        }
        
    }
}