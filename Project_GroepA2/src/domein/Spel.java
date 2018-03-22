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
    SecureRandom random = new SecureRandom();
    
    //---------------------------------------------------------------------------
    //hutten lijst vullen
    public List<Plaats> vulHuttenLijst()
    {
        huttenLijst = new ArrayList<>();
           
        for (int i = 0; i < 28; i++) 
        {
            //resources bepalen
            String naam1 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            String naam2 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            String naam3 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            //nieuwe resource bepalen voor naam2 als naam1 = naam2
            while (naam1.equals(naam2)) {
                naam2 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            }
            //nieuwe resource bepalen voor naam3 als naam1 = naam3 of als naam2 = naam3
            while (naam1.equals(naam3) || naam2.equals(naam3))
            {
                naam3 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            }
                                      //hut nummer
           huttenLijst.add(new Plaats(  i,
                                        //punten hut    (van 12 tot 18)
                                        random.nextInt(6) + 12,
                                        //resource naam 1 hut
                                        naam1,
                                        //resource hoeveelheid 1 hut
                                        random.nextInt(4) + 1,
                                        //resource naam 2 hut
                                        naam2,
                                        //resource hoeveelheid 2 hut
                                        random.nextInt(4) + 1,
                                        //resource naam 3 hut
                                        naam3,
                                        //resource hoeveelheid hut
                                        random.nextInt(4) + 1));
        }
        return huttenLijst;
    }
    //---------------------------------------------------------------------------
    //hutten lijst ophalen
    public List<Plaats> getHuttenLijst()
    {
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
        resourceLijst.add(new Resource("punten",        0));
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
    //plaatsenlijst resetten
    public void resetPlaatsenLijst()
    {
        for (int index = 0; index < plaatsLijst.size(); index++) {
            plaatsLijst.remove(index);
        }
        vulPlaatsLijst();
    }
    //---------------------------------------------------------------------------
    //ronde spelerzetten resetten
    public void resetSpelerZet()
    {
        for (int index = 0; index < getAantalSpelers(); index++) {
            getSpelerLijst().get(index).setPlaatsOpBos(false);
            getSpelerLijst().get(index).setPlaatsOpLeemgroeve(false);
            getSpelerLijst().get(index).setPlaatsOpSteengroeve(false);
            getSpelerLijst().get(index).setPlaatsOpGoudmijn(false);
            getSpelerLijst().get(index).setPlaatsOpAkkerbouw(false);
            getSpelerLijst().get(index).setPlaatsOpSmith(false);
            getSpelerLijst().get(index).setPlaatsOpHut(false);
            
            getSpelerLijst().get(index).setAantalBos(0);
            getSpelerLijst().get(index).setAantalLeemgroeve(0);
            getSpelerLijst().get(index).setAantalSteengroeve(0);
            getSpelerLijst().get(index).setAantalGoudmijn(0);
            getSpelerLijst().get(index).setAantalAkkerbouw(0);
            getSpelerLijst().get(index).setAantalSmith(0);
            getSpelerLijst().get(index).setAantalHut(0);
        }
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
        int hutNummer = 0;
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
        bedieningsPaneel += String.format("%17d%n", getPlaatsenLijst().get(4).getAantalSpots());
        if (getHuttenLijst().size() >= 3) 
        {
            bedieningsPaneel += String.format("• hut kaarten: %n");
            bedieningsPaneel += String.format("• 11: hut %d geeft %d punten. ", getHuttenLijst().get(hutNummer).getHutNummer() + 1, getHuttenLijst().get(hutNummer).getPunten());
            bedieningsPaneel += String.format("benodigde materialen: %s = %d | ", getHuttenLijst().get(hutNummer).getResource1(), getHuttenLijst().get(hutNummer).getAantalResource1());
            bedieningsPaneel += String.format("%s = %d | ", getHuttenLijst().get(hutNummer).getResource2(), getHuttenLijst().get(hutNummer).getAantalResource2());
            bedieningsPaneel += String.format("%s = %d%n", getHuttenLijst().get(hutNummer).getResource3(), getHuttenLijst().get(hutNummer).getAantalResource3());
                
            bedieningsPaneel += String.format("• 12: hut %d geeft %d punten. ", getHuttenLijst().get(hutNummer + 1).getHutNummer() + 1, getHuttenLijst().get(hutNummer + 1).getPunten());
            bedieningsPaneel += String.format("benodigde materialen: %s = %d | ", getHuttenLijst().get(hutNummer + 1).getResource1(), getHuttenLijst().get(hutNummer + 1).getAantalResource1());
            bedieningsPaneel += String.format("%s = %d | ", getHuttenLijst().get(hutNummer + 1).getResource2(), getHuttenLijst().get(hutNummer + 1).getAantalResource2());
            bedieningsPaneel += String.format("%s = %d%n", getHuttenLijst().get(hutNummer + 1).getResource3(), getHuttenLijst().get(hutNummer + 1).getAantalResource3());
            
            bedieningsPaneel += String.format("• 13: hut %d geeft %d punten. ", getHuttenLijst().get(hutNummer + 2).getHutNummer() + 1, getHuttenLijst().get(hutNummer + 2).getPunten());
            bedieningsPaneel += String.format("benodigde materialen: %s = %d | ", getHuttenLijst().get(hutNummer + 2).getResource1(), getHuttenLijst().get(hutNummer + 2).getAantalResource1());
            bedieningsPaneel += String.format("%s = %d | ", getHuttenLijst().get(hutNummer + 2).getResource2(), getHuttenLijst().get(hutNummer + 2).getAantalResource2());
            bedieningsPaneel += String.format("%s = %d%n", getHuttenLijst().get(hutNummer + 2).getResource3(), getHuttenLijst().get(hutNummer + 2).getAantalResource3());
        }
        else if (getHuttenLijst().size() >= 2)
        {
            bedieningsPaneel += String.format("• hut kaarten: %n");
            bedieningsPaneel += String.format("• 12: hut %d geeft %d punten. ", getHuttenLijst().get(hutNummer).getHutNummer() + 1, getHuttenLijst().get(hutNummer).getPunten());
            bedieningsPaneel += String.format("benodigde materialen: %s = %d | ", getHuttenLijst().get(hutNummer).getResource1(), getHuttenLijst().get(hutNummer).getAantalResource1());
            bedieningsPaneel += String.format("%s = %d | ", getHuttenLijst().get(hutNummer).getResource2(), getHuttenLijst().get(hutNummer).getAantalResource2());
            bedieningsPaneel += String.format("%s = %d%n", getHuttenLijst().get(hutNummer).getResource3(), getHuttenLijst().get(hutNummer).getAantalResource3());
                
            bedieningsPaneel += String.format("• 13: hut %d geeft %d punten. ", getHuttenLijst().get(hutNummer + 1).getHutNummer() + 1, getHuttenLijst().get(hutNummer + 1).getPunten());
            bedieningsPaneel += String.format("benodigde materialen: %s = %d | ", getHuttenLijst().get(hutNummer + 1).getResource1(), getHuttenLijst().get(hutNummer + 1).getAantalResource1());
            bedieningsPaneel += String.format("%s = %d | ", getHuttenLijst().get(hutNummer + 1).getResource2(), getHuttenLijst().get(hutNummer + 1).getAantalResource2());
            bedieningsPaneel += String.format("%s = %d%n", getHuttenLijst().get(hutNummer + 1).getResource3(), getHuttenLijst().get(hutNummer + 1).getAantalResource3());
            
        }
        else if (1 < getHuttenLijst().size())
        {
            bedieningsPaneel += String.format("• hut kaarten: %n");
            bedieningsPaneel += String.format("• 13: hut %d geeft %d punten. ", getHuttenLijst().get(hutNummer).getHutNummer() + 1, getHuttenLijst().get(hutNummer).getPunten());
            bedieningsPaneel += String.format("benodigde materialen: %s = %d | ", getHuttenLijst().get(hutNummer).getResource1(), getHuttenLijst().get(hutNummer).getAantalResource1());
            bedieningsPaneel += String.format("%s = %d | ", getHuttenLijst().get(hutNummer).getResource2(), getHuttenLijst().get(hutNummer).getAantalResource2());
            bedieningsPaneel += String.format("%s = %d%n", getHuttenLijst().get(hutNummer).getResource3(), getHuttenLijst().get(hutNummer).getAantalResource3());
              
        }
        hutNummer++;
        return bedieningsPaneel;
    }
    
   public void spelerFix(int ronde)
   {
       for (int index = 0; index < getAantalSpelers(); index++) {
           if (spelerLijst.get(index).getResourceLijst().get(7).getAantal() < 0) 
           {
               spelerLijst.get(index).getResourceLijst().get(7).setAantal(0);
           }
       }
   }
   
   public String voedselMelding(int spelerNr)
   {
       String voedsel = "";
       voedsel += String.format("U heeft geen voedsel om uw dorp te voeden.%n");
       voedsel += String.format("Kies een resource om uw dorp te voeden:%n");
       for (int i = 0; i < 4; i++) {
           voedsel += String.format("%s: %d | ", getResourceLijst().get(i).getNaam(), getSpelerLijst().get(spelerNr).getResourceLijst().get(i).getAantal());
       }
       voedsel += String.format("%n");
       return voedsel;
   }
}
