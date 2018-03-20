package domein;

import java.util.*;



public class Spel 
{

    private int aantalSpelers = -1;
    private List<Speler> spelerLijst;
    private List<Resource> resourceLijst;
    
    //---------------------------------------------------------------------------
    //speler lijst vullen
    public List<Speler> vulSpelerLijst(int aantal)
    {
       spelerLijst = new ArrayList<>();
       for (int i = 0; i < aantal; i++) {
           spelerLijst.add(new Speler(i));
       }
       return spelerLijst;
    }
    //---------------------------------------------------------------------------
    //resource lijst vullen
    public List<Resource> vulResourceLijst()
    {
        resourceLijst = new ArrayList<>();
        resourceLijst.add(new Resource("hout", 0));
        resourceLijst.add(new Resource("leem", 0));
        resourceLijst.add(new Resource("steen", 0));
        resourceLijst.add(new Resource("goud", 0));
        resourceLijst.add(new Resource("akkerbouw", 0));
        resourceLijst.add(new Resource("gereedschap", 0));
        resourceLijst.add(new Resource("voedsel", 12));
        resourceLijst.add(new Resource("stamleden", 5));
        return resourceLijst;
    }
    //---------------------------------------------------------------------------
    //Spelers namen geven
    public void setSpelerNaam(String naam, Speler speler)
    {
        speler.setNaam(naam);
    }
    //---------------------------------------------------------------------------
    //Spelers resources geven
    public void setSpelerResources()
    {
        for (int index = 0; index < getAantalSpelers(); index++) {
            spelerLijst.get(index).setResourcesLijst(resourceLijst);
        }
    }
    //---------------------------------------------------------------------------
    //spelers lijst ophalen
    public List<Speler> getSpelerLijst() {
        return spelerLijst;
    }
    
    //---------------------------------------------------------------------------
    //speler lijst aanmaken & vullen
    public void setSpelers() {
        this.spelerLijst = vulSpelerLijst(getAantalSpelers());
    }    
    //---------------------------------------------------------------------------
    //Aantal spelers bepalen
    public void maakAantalSpelers(int aantal)
    {
        setAantalSpelers(aantal);
    }
    
    public int getAantalSpelers() {
        return aantalSpelers;
    }

    public void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
    }
    //---------------------------------------------------------------------------
    
    
}
