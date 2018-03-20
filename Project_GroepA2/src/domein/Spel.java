package domein;

import java.util.*;



public class Spel 
{

    private int aantalSpelers = 0;
    private List<Speler> spelerLijst;
    private List<Resource> resourceLijst;
    private List<Plaats> plaatsLijst;
    
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
    //plaatsen lijst vullen
    public List<Plaats> vulPlaatsLijst()
    {
        plaatsLijst = new ArrayList<>();
        plaatsLijst.add(new Plaats("Bos", getResourceLijst().get(0), 3, 7));
        plaatsLijst.add(new Plaats("Leemgroeve", getResourceLijst().get(1), 4, 7));
        plaatsLijst.add(new Plaats("Steengroeve", getResourceLijst().get(2), 5, 7));
        plaatsLijst.add(new Plaats("Goudmijn", getResourceLijst().get(3), 6, 7));
        plaatsLijst.add(new Plaats("Akkerbouw", getResourceLijst().get(4), 1));
        plaatsLijst.add(new Plaats("Smith", getResourceLijst().get(5), 1));
        plaatsLijst.add(new Plaats("Jachtgebied", getResourceLijst().get(6), 2, 40));
        plaatsLijst.add(new Plaats("Hut", getResourceLijst().get(7), 2));
        return plaatsLijst;
    }
    //---------------------------------------------------------------------------
    //elke speler resources geven
    public void setSpelersResourceLijst()
    {
        for (int index = 0; index < getAantalSpelers(); index++) {
            spelerLijst.get(index).setResourcesLijst(resourceLijst);
        }
    }
    //---------------------------------------------------------------------------
    //Spelers namen geven
    public void setSpelerNaam(String naam, Speler speler)
    {
        speler.setNaam(naam);
    }
    //---------------------------------------------------------------------------
    //Resourcelijst laten opvullen
    public void setSpelerResources()
    {
        for (int i = 0; i < getAantalSpelers(); i++) {
            spelerLijst.get(i).setResourcesLijst(vulResourceLijst());
        }
        
    }
    //---------------------------------------------------------------------------
    //spelers lijst ophalen
    public List<Speler> getSpelerLijst() {
        return spelerLijst;
    }
    //---------------------------------------------------------------------------
    //resource lijst ophalen
    public List<Resource> getResourceLijst()
    {
        return resourceLijst;
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
