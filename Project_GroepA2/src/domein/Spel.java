package domein;

import java.security.SecureRandom;
import java.util.*;



public class Spel 
{

    private int aantalSpelers = 0;
    private List<Speler> spelerLijst;
    private List<Resource> resourceLijst;
    private List<Plaats> plaatsLijst;
    private List<Plaats> huttenLijst;
    //---------------------------------------------------------------------------
    //hutten lijst vullen
    public List<Plaats> vulHuttenLijst()
    {
        SecureRandom random = new SecureRandom();
        huttenLijst = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            huttenLijst.add(new Plaats());
        }
        return huttenLijst;
    }
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
        //                              naam        aantal
        resourceLijst.add(new Resource("hout",          0));
        resourceLijst.add(new Resource("leem",          0));
        resourceLijst.add(new Resource("steen",         0));
        resourceLijst.add(new Resource("goud",          0));
        resourceLijst.add(new Resource("akkerbouw",     0));
        resourceLijst.add(new Resource("gereedschap",   0));
        resourceLijst.add(new Resource("voedsel",       12));
        resourceLijst.add(new Resource("stamleden",     5));
        return resourceLijst;
    }
    //---------------------------------------------------------------------------
    //plaatsen lijst vullen
    public List<Plaats> vulPlaatsLijst()
    {
        plaatsLijst = new ArrayList<>();
        //                         naam             resource                 deler  beschikbare plaatsen
        plaatsLijst.add(new Plaats("Bos",           getResourceLijst().get(0), 3,        7));
        plaatsLijst.add(new Plaats("Leemgroeve",    getResourceLijst().get(1), 4,        7));
        plaatsLijst.add(new Plaats("Steengroeve",   getResourceLijst().get(2), 5,        7));
        plaatsLijst.add(new Plaats("Goudmijn",      getResourceLijst().get(3), 6,        7));
        plaatsLijst.add(new Plaats("Akkerbouw",     getResourceLijst().get(4),           1));
        plaatsLijst.add(new Plaats("Smith",         getResourceLijst().get(5),           1));
        plaatsLijst.add(new Plaats("Jachtgebied",   getResourceLijst().get(6), 2,        40));
        plaatsLijst.add(new Plaats("Hut",           getResourceLijst().get(7),           2));
        return plaatsLijst;
    }
    //---------------------------------------------------------------------------
    //huttenlijst ophalen
    public List<Plaats> getHuttenLijst()
    {
        return huttenLijst;
    }
    //---------------------------------------------------------------------------
    //plaatsenlijst resetten
    public void resetPlaatsenLijst()
    {
        for (int index = 0; index < plaatsLijst.size(); index++) {
            plaatsLijst.remove(index);
        }
        vulPlaatsLijst();
    }
    //---------------------------------------------------------------------------
    //elke speler resources geven
    public void setSpelersResourceLijst()
    {
        for (int index = 0; index < getAantalSpelers(); index++) {
            spelerLijst.get(index).setResourceLijst(resourceLijst);
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
            spelerLijst.get(i).setResourceLijst(vulResourceLijst());
        }
        
    }
    //---------------------------------------------------------------------------
    //spelers lijst ophalen
    public List<Speler> getSpelerLijst() {
        return spelerLijst;
    }
    //---------------------------------------------------------------------------
    //plaatsen lijst ophalen
    public List<Plaats> getPlaatsenLijst()
    {
        return plaatsLijst;
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
    //Gerold getal bepalen
    public int dobbelStenen(int aantalStamleden)
    {
        int getal = 0;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < aantalStamleden; i++) {
            int dobbelSteen = random.nextInt(6) + 1;
            getal += dobbelSteen;
        }
        return getal;
    }
    //---------------------------------------------------------------------------
    //Bedieningspaneel afprinten
    public String bedieningsPaneel(int spelerNr)
    {
        String bedieningsPaneel = "";
        bedieningsPaneel += String.format("%n%nSpeler %d is nu aan de beurt.%n", getSpelerLijst().get(spelerNr).getSpelerNummer() + 1);
        bedieningsPaneel += String.format("• 0: terug    ");
        bedieningsPaneel += String.format("• 1: Toon spelers    ");
        bedieningsPaneel += String.format("• 2: %s     ", getPlaatsenLijst().get(0).getNaam()); //bos
        bedieningsPaneel += String.format("• 3: %s     ", getPlaatsenLijst().get(1).getNaam()); //leemgroeve
        bedieningsPaneel += String.format("• 4: %s     ", getPlaatsenLijst().get(2).getNaam()); //steengroeve
        bedieningsPaneel += String.format("• 5: %s     ", getPlaatsenLijst().get(3).getNaam()); //goudmijn
        bedieningsPaneel += String.format("• 6: %s     ", getPlaatsenLijst().get(6).getNaam()); //Jachtgebied
        bedieningsPaneel += String.format("• 7: %s     ", getPlaatsenLijst().get(7).getNaam()); //hut
        bedieningsPaneel += String.format("• 8: %s     ", getPlaatsenLijst().get(5).getNaam()); //smith
        bedieningsPaneel += String.format("• 9: %s     ", getPlaatsenLijst().get(4).getNaam()); //akkerbouw
        bedieningsPaneel += String.format("• 10: Stop spel");
        bedieningsPaneel += String.format("%n");
        bedieningsPaneel += String.format("• vrije plaatsen: %24d", getPlaatsenLijst().get(0).getAantalSpots());
        bedieningsPaneel += String.format("%16d", getPlaatsenLijst().get(1).getAantalSpots());
        bedieningsPaneel += String.format("%21d", getPlaatsenLijst().get(2).getAantalSpots());
        bedieningsPaneel += String.format("%19d", getPlaatsenLijst().get(3).getAantalSpots());
        bedieningsPaneel += String.format("%19d", getPlaatsenLijst().get(6).getAantalSpots());
        bedieningsPaneel += String.format("%18d", getPlaatsenLijst().get(7).getAantalSpots());
        bedieningsPaneel += String.format("%14d", getPlaatsenLijst().get(5).getAantalSpots());
        bedieningsPaneel += String.format("%17d", getPlaatsenLijst().get(4).getAantalSpots());
        return bedieningsPaneel;
    }
    
}
