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
        for (int index = 0; index < getAantalSpelers(); index++) {
            while (spel.getSpelerLijst().get(index).getResourceLijst().get(7).getAantal() != 0) {
                spelApp.bediening(index);
            }
        }
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
    
    //---------------------------------------------------------------------------
    //speler plaatsen op bos
    public void plaatsOpPlek(int spelerNr, int keuzeNr, int aantalStamleden)
    {
        double geroldGetal;
        double deler;
        double uitkomst;
        switch (keuzeNr) {
            case 2:
                //hout
                geroldGetal = (double) spel.dobbelStenen(aantalStamleden);
                deler = (double) getPlaatsenLijst().get(0).getDeler();
                uitkomst = geroldGetal / deler;
                //haalspelerlijst op, neem de speler met spelerNr uit de spelerlijst, haal van die speler de resourceLijst op, neem van de 0de resource (hout), zet de resource hout zijn aantal = het totale dobbeslenen getal gedeeld door de deler van de plaats bos
                getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal((int) Math.floor(uitkomst));
                break;
            case 3:
                //leem
                geroldGetal = (double) spel.dobbelStenen(aantalStamleden);
                deler = (double) getPlaatsenLijst().get(1).getDeler();
                uitkomst = geroldGetal / deler;
                getSpelerLijst().get(spelerNr).getResourceLijst().get(1).setAantal((int) Math.floor(uitkomst));
                break;
            case 4:
                //steengerold
                geroldGetal = (double) spel.dobbelStenen(aantalStamleden);
                deler = (double) getPlaatsenLijst().get(2).getDeler();
                uitkomst = geroldGetal / deler;
                getSpelerLijst().get(spelerNr).getResourceLijst().get(2).setAantal((int) Math.floor(uitkomst));
                break;
            case 5:
                //goud
                geroldGetal = (double) spel.dobbelStenen(aantalStamleden);
                deler = (double) getPlaatsenLijst().get(3).getDeler();
                uitkomst = geroldGetal / deler;
                getSpelerLijst().get(spelerNr).getResourceLijst().get(3).setAantal((int) Math.floor(uitkomst));
                break;
            case 6:
                //voedsel
                geroldGetal = (double) spel.dobbelStenen(aantalStamleden);
                deler = (double) getPlaatsenLijst().get(6).getDeler();
                uitkomst = geroldGetal / deler;
                getSpelerLijst().get(spelerNr).getResourceLijst().get(6).setAantal((int) Math.floor(uitkomst));
                break;
            case 7:
                //stamleden
                getSpelerLijst().get(spelerNr).getResourceLijst().get(7).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() + 1);
                break;
            case 8:
                //gereedschap
                getSpelerLijst().get(spelerNr).getResourceLijst().get(5).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal() + 1);
                break;
            case 9:
                //akkerbouw
                getSpelerLijst().get(spelerNr).getResourceLijst().get(4).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(4).getAantal() + 1);
                ;
                break;
        }
        
    }
}