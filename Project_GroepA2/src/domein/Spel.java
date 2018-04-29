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
    private int spelerBeurt;
    SecureRandom random = new SecureRandom();
    
    public boolean aantalSpelersControle(String aantal)
    {
        boolean a = false;
        try
        {
            int aantalSpelers = Integer.parseInt(aantal);
            if (aantalSpelers >= 2 && aantalSpelers <= 4) {
                //zet het aantal spelers
                setAantalSpelers(aantalSpelers);
                //roept de spelerLijst op om te vullen
                this.spelerLijst = vulSpelerLijst(aantalSpelers);
                a = true;
            }
        }catch(NumberFormatException e)
        {
            
        }
       return a;
    }
    
    public boolean naamControle(int spelerNr, String naam)
    {
        boolean a = false;
        switch(spelerNr)
        {
            case 0:
                getSpelerLijst().get(0).setNaam(naam);
                a = true;
                break;
            case 1:
                if (!naam.toLowerCase().equals(getSpelerLijst().get(0).getNaam().toLowerCase())) {
                    getSpelerLijst().get(1).setNaam(naam);
                    a = true;
                }
                break;
            case 2:
                if (!naam.toLowerCase().equals(getSpelerLijst().get(0).getNaam().toLowerCase()) &&
                    !naam.toLowerCase().equals(getSpelerLijst().get(1).getNaam().toLowerCase())) {
                    getSpelerLijst().get(2).setNaam(naam);
                    a = true;
                }
                break;
            case 3:
                if (!naam.toLowerCase().equals(getSpelerLijst().get(0).getNaam().toLowerCase()) &&
                    !naam.toLowerCase().equals(getSpelerLijst().get(1).getNaam().toLowerCase()) &&
                    !naam.toLowerCase().equals(getSpelerLijst().get(2).getNaam().toLowerCase()))  {
                    getSpelerLijst().get(3).setNaam(naam);
                    a = true;
                }
                break;
        }
        return a;
    }
    
    public List<Speler> vulSpelerLijst(int aantal)
    {
       spelerLijst = new ArrayList<>();
       for (int i = 0; i < aantal; i++) {
           spelerLijst.add(new Speler(i));
       }
       return spelerLijst;
    }
    
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
    
    public List<Plaats> vulPlaatsLijst()
    {
        plaatsLijst = new ArrayList<>();
        //                         naam             resource                 deler  beschikbare plaatsen
        plaatsLijst.add(new Plaats("Bos",           getResourceLijst().get(0), 3,        7));
        plaatsLijst.add(new Plaats("Leemgroeve",    getResourceLijst().get(1), 4,        7));
        plaatsLijst.add(new Plaats("Steengroeve",   getResourceLijst().get(2), 5,        7));
        plaatsLijst.add(new Plaats("Goudmijn",      getResourceLijst().get(3), 6,        7));
        plaatsLijst.add(new Plaats("Jachtgebied",   getResourceLijst().get(6), 2,        40));
        plaatsLijst.add(new Plaats("Akkerbouw",     getResourceLijst().get(4),           1));
        plaatsLijst.add(new Plaats("Smith",         getResourceLijst().get(5),           1));
        plaatsLijst.add(new Plaats("Hut",           getResourceLijst().get(7),           2));
        return plaatsLijst;
    }
    
    public List<Plaats> vulHuttenLijst()
    {
        huttenLijst = new ArrayList<>();
           
        for (int i = 0; i < 28; i++) 
        {
            //resources bepalen
            String Rnaam1 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            String Rnaam2 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            String Rnaam3 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            //nieuwe resource bepalen voor naam2 als naam1 = naam2
            while (Rnaam1.equals(Rnaam2)) {
                Rnaam2 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            }
            //nieuwe resource bepalen voor naam3 als naam1 = naam3 of als naam2 = naam3
            while (Rnaam1.equals(Rnaam3) || Rnaam2.equals(Rnaam3))
            {
                Rnaam3 = getPlaatsenLijst().get(random.nextInt(4)).getTypeResource().getNaam();
            }
                                      //hut nummer
           huttenLijst.add(new Plaats(  i,
                                        //punten hut    (van 12 tot 18)
                                        random.nextInt(6) + 12,
                                        //resource naam 1 hut
                                        Rnaam1,
                                        //resource hoeveelheid 1 hut
                                        random.nextInt(4) + 1,
                                        //resource naam 2 hut
                                        Rnaam2,
                                        //resource hoeveelheid 2 hut
                                        random.nextInt(4) + 1,
                                        //resource naam 3 hut
                                        Rnaam3,
                                        //resource hoeveelheid hut
                                        random.nextInt(4) + 1));
        }
        return huttenLijst;
    }
    
    public void geefSpelersResources()
    {
        for (int i = 0; i < getAantalSpelers(); i++) {
            spelerLijst.get(i).setResourceLijst(vulResourceLijst());
        }
        
    }
    
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
    
    public String bedieningsPaneel(int spelerNr)
    {
        int hutNummer = 0;
        String bedieningsPaneel = "";
        bedieningsPaneel += String.format("%n%nSpeler %d is nu aan de beurt.%n", getSpelerLijst().get(spelerNr).getSpelerNummer() + 1);
        bedieningsPaneel += String.format("• 0: niets doen    ");
        bedieningsPaneel += String.format("• 1: Toon spelers    ");
        bedieningsPaneel += String.format("• 2: %s     ", getPlaatsenLijst().get(0).getNaam()); //bos
        bedieningsPaneel += String.format("• 3: %s     ", getPlaatsenLijst().get(1).getNaam()); //leemgroeve
        bedieningsPaneel += String.format("• 4: %s     ", getPlaatsenLijst().get(2).getNaam()); //steengroeve
        bedieningsPaneel += String.format("• 5: %s     ", getPlaatsenLijst().get(3).getNaam()); //goudmijn
        bedieningsPaneel += String.format("• 6: %s     ", getPlaatsenLijst().get(4).getNaam()); //Jachtgebied
        bedieningsPaneel += String.format("• 7: %s     ", getPlaatsenLijst().get(7).getNaam()); //hut
        bedieningsPaneel += String.format("• 8: %s     ", getPlaatsenLijst().get(6).getNaam()); //smith
        bedieningsPaneel += String.format("• 9: %s     ", getPlaatsenLijst().get(5).getNaam()); //akkerbouw
        bedieningsPaneel += String.format("• 10: Stop spel");
        bedieningsPaneel += String.format("%n");
        bedieningsPaneel += String.format("• vrije plaatsen: %24d", getPlaatsenLijst().get(0).getAantalSpots());//bos
        bedieningsPaneel += String.format("%16d", getPlaatsenLijst().get(1).getAantalSpots());//leemgroeve
        bedieningsPaneel += String.format("%21d", getPlaatsenLijst().get(2).getAantalSpots());
        bedieningsPaneel += String.format("%19d", getPlaatsenLijst().get(3).getAantalSpots());
        bedieningsPaneel += String.format("%19d", getPlaatsenLijst().get(4).getAantalSpots());
        bedieningsPaneel += String.format("%18d", getPlaatsenLijst().get(7).getAantalSpots());
        bedieningsPaneel += String.format("%14d", getPlaatsenLijst().get(6).getAantalSpots());
        bedieningsPaneel += String.format("%17d%n", getPlaatsenLijst().get(5).getAantalSpots());
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
    
     public String eindeRondeBericht()
    {
        String output = "";
        output += String.format("%n%n");
        output += String.format("%n               -----------------------------------------------");
        output += String.format("%n               -                Ronde is klaar!              -");
        output += String.format("%n               -           De uiteindelijke score is         -");
        output += String.format("%n               -----------------------------------------------%n%n%n%n");
        output += String.format("%n%n%n%n<scroll naar boven voor eind resultaat van vorige ronde>%n" +"%n%n<Nieuwe ronde is gestart>%n%n");
        return output;
    }
    
    public void trekResourcesAf(int spelerNr, int hutNummer)
    {
        int resourceNr1 = resourceNummerOphalen(spelerNr, 0);
        int resourceNr2 = resourceNummerOphalen(spelerNr, 1);
        int resourceNr3 = resourceNummerOphalen(spelerNr, 2);
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr1).getAantal() >= getHuttenLijst().get(hutNummer).getAantalResource1())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr1).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr1).getAantal() - getHuttenLijst().get(hutNummer).getAantalResource1());
        }
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr2).getAantal() >= getHuttenLijst().get(hutNummer).getAantalResource2())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr2).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr2).getAantal() - getHuttenLijst().get(hutNummer).getAantalResource2());
        }
        if(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr3).getAantal() >= getHuttenLijst().get(hutNummer).getAantalResource3())
        {
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr3).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNr3).getAantal() - getHuttenLijst().get(hutNummer).getAantalResource3());
        }
    }
    
    public boolean checkResources(int spelerNr, int hutNummer)
    {
        boolean a = false;
        if (getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummerOphalen(spelerNr,hutNummer)).getAantal() >= getHuttenLijst().get(hutNummer).getAantalResource1() &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummerOphalen(spelerNr,hutNummer)).getAantal() >= getHuttenLijst().get(hutNummer).getAantalResource2() &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummerOphalen(spelerNr,hutNummer)).getAantal() >= getHuttenLijst().get(hutNummer).getAantalResource3()) {
            a = true;
        }
        return a;
    }
    
    public boolean checkResourcesStraf(int spelerNr)
    {
        boolean a = false;
        if (getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() <= 0 &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() <= 0 &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() <= 0 &&
            getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() <= 0) {
            a = true;
        }
        return a;
    }
    
     public int resourceNummerOphalen(int spelerNr, int hutNummer)
    {
        int getal;
        for (int resourceNummer = 0; resourceNummer < 4; resourceNummer++) {
            String resourceNamen = getSpelerLijst().get(spelerNr).getResourceLijst().get(resourceNummer).getNaam();
            if (getHuttenLijst().get(hutNummer).getResource1().toLowerCase().equals(resourceNamen)) {
                return resourceNummer;
            }
        }
        return getal = 0;
    }
     
     
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
    
    public void resetSpelerZet(int spelerNr)
    {
        getSpelerLijst().get(spelerNr).setPlaatsOpBos(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpLeemgroeve(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpSteengroeve(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpGoudmijn(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpAkkerbouw(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpJachtgebied(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpSmith(false);
        getSpelerLijst().get(spelerNr).setPlaatsOpHut(false);

        getSpelerLijst().get(spelerNr).setAantalBos(0);
        getSpelerLijst().get(spelerNr).setAantalLeemgroeve(0);
        getSpelerLijst().get(spelerNr).setAantalSteengroeve(0);
        getSpelerLijst().get(spelerNr).setAantalGoudmijn(0);
        getSpelerLijst().get(spelerNr).setAantalJachtgebied(0);
        getSpelerLijst().get(spelerNr).setAantalAkkerbouw(0);
        getSpelerLijst().get(spelerNr).setAantalSmith(0);
        getSpelerLijst().get(spelerNr).setAantalHut(0);
    }
    
    public void resetPlaatsenLijst()
    {
        for (int index = 0; index < plaatsLijst.size(); index++) {
            plaatsLijst.remove(index);
        }
        vulPlaatsLijst();
    }
    
    public int getAantalSpelers() {
        return aantalSpelers;
    }
    public List<Speler> getSpelerLijst() {
        return spelerLijst;
    }
    public List<Resource> getResourceLijst(){
        return resourceLijst;
    }
    public List<Plaats> getPlaatsenLijst(){
        return plaatsLijst;
    }
    public List<Plaats> getHuttenLijst(){
        return huttenLijst;
    }
    public void setSpelersResourceLijst(){
        for (int index = 0; index < getAantalSpelers(); index++) {
            spelerLijst.get(index).setResourceLijst(resourceLijst);
        }
    }
    public void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
    }
    
    public int getSpelerBeurt()
    {
        return spelerBeurt;
    }
    
    public void setSpelerBeurt(int spelerBeurt)
    {
        this.spelerBeurt = spelerBeurt;
    }
}
