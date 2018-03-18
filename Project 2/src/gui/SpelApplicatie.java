package gui;


import java.util.Scanner;
import domein.DomeinController;
import domein.Speler;
import domein.SpelerRepository;
import java.util.List;

public final class SpelApplicatie
{
    Scanner input = new Scanner(System.in);
    DomeinController dc;
    SpelerRepository spelerRepository;
    private int aantalSpelers = -1;
    
    
    public SpelApplicatie(DomeinController dc) 
    {
        setDc(dc);
        BepaalAantalSpelers(); //geeft aantal terug
        dc.maakSpelersAan(aantalSpelers, dc);     
        dc.toonSpelers();
        
        //toon speler scoreboard
    }
    
    public void BepaalAantalSpelers()
    {
        //aantal spelers bepalen
        while (getAantalSpelers() < 2 || getAantalSpelers() > 4)
        {
            String resultaat;
            try{
                System.out.printf("%nAantal spelers:(2-4): ");
                resultaat = input.nextLine();
                int aantal = Integer.parseInt(resultaat);
                setAantalSpelers(aantal);
                if (getAantalSpelers() < 2 || getAantalSpelers() > 4)
                    System.out.printf("Ongeldige keuze.%n");
            }catch(NumberFormatException e) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        }
    }

    public Speler geefSpelersNamen(List<Speler> spelers) //wordt aangeroepen door domeincontroller
    {
        int nummer = spelers.size();
        Speler speler = new Speler(nummer);
        
        System.out.printf("Naam speler %d: ", nummer);
        String naam = input.next();
        
        switch(nummer)
        {
            case -1: System.out.println("probleem"); break;//ZZZ, gwn troubleshooten
            case 0: 
                dc.geefSpelersNamen(naam, speler);
                return speler;
            case 1: 
                while(naam.equals(spelers.get(0).getNaam()))
                {
                    System.out.printf("Kan niet dezelfde naam als andere spelers zijn!%n");
                    System.out.printf("naam speler %d: ", nummer + 1);
                    naam = input.next();
                }
                dc.geefSpelersNamen(naam, speler);
                return speler;
            case 2: 
                while(naam.equals(spelers.get(0).getNaam()) || naam.equals(spelers.get(1).getNaam()))
                {
                    System.out.printf("Kan niet dezelfde naam als andere spelers zijn!%n");
                    System.out.printf("naam speler %d: ", nummer + 1);
                    naam = input.next();
                }
                dc.geefSpelersNamen(naam, speler);
                return speler;
            case 3: 
                while(naam.equals(spelers.get(0).getNaam()) || naam.equals(spelers.get(1).getNaam()) || naam.equals(spelers.get(2).getNaam()))
                {
                    System.out.printf("Kan niet dezelfde naam als andere spelers zijn!%n");
                    System.out.printf("naam speler %d: ", nummer + 1);
                    naam = input.next();
                }
                dc.geefSpelersNamen(naam, speler);
                return speler;
        }
        return speler;
    }
  
    public DomeinController getDc() {
        return dc;
    }

    public void setDc(DomeinController dc) {
        this.dc = dc;
    }  
    
    
    public int getAantalSpelers() {
        return aantalSpelers;
    }

    public void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
    }
    
    
    
    
    
    
    /*
    public int aantalSpelers;
    Speler spelers[];
    Scanner input = new Scanner(System.in);
    static SpelApplicatie spel = new SpelApplicatie();
    boolean c = false;
    boolean loop = true;
    */
    
    
    /*
    //Startup methode zodat we startupklasse kunnen gebruiken
    public  void start() throws InterruptedException
    {
        spel.spelStarten();
        
        
        System.out.println("");
        spel.toonSpelers();
        
        while (loop) {
        spel.omDeBeurt();
        }
        
        spel.eindeSpel();
    }
    
    //het spel opstarten
    public void spelStarten() // in orde
    {
        //aantal spelers bepalen
        System.out.printf("Aantal spelers(2-4): ");
        String resultaat = input.nextLine();
        int aantal;
        try {
            aantal = Integer.parseInt(resultaat);
            while(aantal > 4 || aantal < 2)
            {
                System.out.printf("Ongeldige keuze.%nAantal spelers:(2-4): ");
                resultaat = input.nextLine();
                try{
                    aantal = Integer.parseInt(resultaat);
                }catch(NumberFormatException e) {}
            }
            
            setAantalSpelers(aantal);
            System.out.printf("%n<spel gestart voor %d spelers>%n%n", aantalSpelers);
            spelerRepository = new SpelerRepository(aantalSpelers);
        }catch(NumberFormatException e) 
        {
            System.out.printf("Ongeldige keuze.%n");
            spel.spelStarten();
        } 
    }
    
    //Elke speler afprinten
    public void toonSpelers()
    {
        System.out.printf("%n%n%n");
        System.out.printf("Scoreboard:%n");
        //spelers informatie tonen
        for (int i = 0; i < spelerRepository.getSpelers().size(); i) 
        {
            toString(spelerRepository.getSpelers().get(i));
        }
        
    }

    //maakspelers aan
    
    
    //maak speler aan
    
    
    
    //Bedieningspaneel
    public void bediening(Speler speler)
    {
        
        try
        {
            int temp;
            do
            {
                System.out.printf("%n%n-Speler %s is aan de beurt.%n"  "--------------------------------------------------------------------------------------------------------------------------------------------------------------%n", speler.getNaam());
                System.out.printf("- • 0: Stop spel • 1: toonSpelers • 2: Bos • 3: Leemgroeve • 4: Steengroeve • 5: Goud rivier • 6: Yachtgebied • 7: Hut • 8: Tool makelaar • 9: Akkerbouw     -%n");
                System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
                System.out.printf("Keuze: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);

                switch(temp)
                {
                    case 0:
                        spel.eindeSpel();
                        System.exit(0);
                        break;
                    case 1: toonSpelers();
                    break;
                    case 2: plaatsBos(speler);
                    break;
                    case 3: plaatsLeem(speler);
                    break;
                    case 4: plaatsSteen(speler);
                    break;
                    case 5: plaatsGoud(speler);
                    break;
                    case 6: plaatsJachtveld(speler);
                    break;
                    case 7: plaatsHut(speler);
                    break;
                    case 8: plaatsGereedschap(speler);
                    break;
                    case 9: plaatsAkkerbouw(speler);
                    break;
                    default: 
                        System.out.printf("%nOngeldige keuze.");
                        spel.bediening(speler);
                        break;
                }

            }while(temp == 1);
        }catch(NumberFormatException e) 
        {
            System.out.printf("%n                   ----------------------------");
            System.out.printf("%n                   -!!!Keuze moet 0-9 zijn.!!!-");
            System.out.printf("%n                   ----------------------------%n");
            spel.bediening(speler);
        }
        
        System.out.printf("%n<Volgende speler>%n%n");
    }
    
    //Elke speler om de beurt laten spelen
    public void omDeBeurt()
    {
        
        //per ronde per speler een apart bedieningspaneel
        
        for (int i = 0; i < aantalSpelers; i)         
        {
            if(spelers[i].getMaxStamleden()<spelers[i].getStamlid())
            {
                spelers[i].setMaxStamleden(spelers[i].getStamlid());//set aantalMaxstamleden gewoon (doet hij maar 1 keer)
            }
            
            while(spelers[i].getStamlid() != 0)
            // extend de for eigenlijk, blijft gaan tot de speler geen stamleden heeft
            {
                for (int j = 0; j < aantalSpelers; j) 
                {
                    if (spelers[j].getStamlid() > 0)    
                    {
                        spel.bediening(spelers[j]);
                    }
                }
            }
            
            eindeRonde();
        }
        
        //elke speler kijken ofdat ze gereedschap hebben
        
    }
   //Einde ronde
    public void eindeRonde()
    {
        System.out.printf("%n               -----------------------------------------------");
        System.out.printf("%n               -                Ronde is klaar!              -");
        System.out.printf("%n               -           De uiteindelijke score is         -");
        System.out.printf("%n               -----------------------------------------------");
        
         //per ronde stamleden terug geven
        for (int i = 0; i < aantalSpelers; i) 
        {
            //per ronde resources geven
            spel.krijgVoedsel(spelers[i]);
            spel.krijgBaby(spelers[i]);
            spel.krijgBos(spelers[i]);
            spel.krijgLeem(spelers[i]);
            spel.krijgSteen(spelers[i]);
            spel.krijgGoud(spelers[i]);
            //per ronde voedsel aftrekken
            spelers[i].setVoedsel(spelers[i].getVoedsel() - spelers[i].getMaxStamleden() spelers[i].getAkkerbouw());
        }
        
        spel.toonSpelers();
        System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n");
        System.out.printf("%n%n<Nieuwe ronde is gestart>%n%n");
    }
    
    //Eindigen spel
    public void eindeSpel()
    {
        System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n");
        System.out.printf("%n               -----------------------------------------------");
        System.out.printf("%n               -                 Spel is klaar!              -");
        System.out.printf("%n               -           De uiteindelijke score is         -");
        System.out.printf("%n               -----------------------------------------------");
        
        spel.toonSpelers();
    }
    
    //Plaatsen van hout & krijgen van hout
   public void plaatsBos(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -        Bos       -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            int temp;
            try{
                System.out.print("Aantal stamleden: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);
                speler.setAantalStamleden(temp);
                if (speler.getAantalStamleden() == 0) 
                {
                    bediening(speler);
                }
                else if(speler.getAantalStamleden() <= speler.getStamlid() && speler.getStamlid() > 0)
                {
                    speler.setStamlid(speler.getStamlid()-speler.getAantalStamleden()); //verwijdert het aantal stamleden
                    speler.setGebruikteHout(true);  
                    speler.toonStamleden(speler);
                    b=false;
                }
                else
                {
                    System.out.printf("%n                   ------------------------------");
                    System.out.printf("%n                   -!!!Niet genoeg stamleden.!!!-");
                    System.out.printf("%n                   ------------------------------%n");
                    System.out.printf("Aantal beschikbare stamleden: %d%n",speler.getStamlid());
                }
            }catch(NumberFormatException i) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        }
    }
    
    public void krijgBos(Speler speler)
    {
        if (speler.getGebruikteHout() == true)
        {
            speler.setHout(speler.getAantalStamleden());
            speler.setStamlid(speler.getStamlid()  speler.getAantalStamleden());
            System.out.printf("%n%n%s bij het bos:",speler.getNaam());
            System.out.printf("%n       Het gerolde getal van %s is %d", speler.getNaam(),speler.getGeroldGetal());
            System.out.printf("%n       Speler %d krijgt %d hout", speler.getSpelerNummer()  1, speler.getHout());
            
        }
        else
        {
            
        }
    }
    
    //Plaatsen van leem & krijgen van leem
    public void plaatsLeem(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -     Leemgroeve   -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            int temp;
            try{
                System.out.print("Aantal stamleden: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);
                speler.setAantalStamleden(temp);
                if (speler.getAantalStamleden() == 0) 
                {
                    bediening(speler);
                }
                else if(speler.getAantalStamleden() <= speler.getStamlid() && speler.getStamlid() > 0)
                {
                    speler.setStamlid(speler.getStamlid()-speler.getAantalStamleden()); //verwijdert het aantal stamleden
                    speler.setGebruikteLeem(true);  
                    speler.toonStamleden(speler);
                    b=false;
                }
                else
                {
                    System.out.printf("%n                   ------------------------------");
                    System.out.printf("%n                   -!!!Niet genoeg stamleden.!!!-");
                    System.out.printf("%n                   ------------------------------%n");
                    System.out.printf("Aantal beschikbare stamleden: %d%n",speler.getStamlid());
                }
            }catch(NumberFormatException i) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        }       
    }
    
    public void krijgLeem(Speler speler)
    {
        if (speler.getGebruikteLeem() == true)
        {
            speler.setLeem(speler.getAantalStamleden());
            speler.setStamlid(speler.getStamlid()  speler.getAantalStamleden());
            System.out.printf("%n%n%s bij de leemgroeve:", speler.getNaam());
            System.out.printf("%n       Het gerolde getal van %s is %d", speler.getNaam(),speler.getGeroldGetal());
            System.out.printf("%n       Speler %d krijgt %d leem", speler.getSpelerNummer()  1, speler.getLeem());
        }
        else
        {
            
        }
    }
    
    //plaatsen van steen & krijgen van steen
    public void plaatsSteen(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -    Steengroeve   -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            int temp;
            try{
                System.out.print("Aantal stamleden: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);
                speler.setAantalStamleden(temp);
                if (speler.getAantalStamleden() == 0) 
                {
                    bediening(speler);
                }
                else if(speler.getAantalStamleden() <= speler.getStamlid() && speler.getStamlid() > 0)
                {
                    speler.setStamlid(speler.getStamlid()-speler.getAantalStamleden()); //verwijdert het aantal stamleden
                    speler.setGebruikteSteen(true);  
                    speler.toonStamleden(speler);
                    b=false;
                }
                else
                {
                    System.out.printf("%n                   ------------------------------");
                    System.out.printf("%n                   -!!!Niet genoeg stamleden.!!!-");
                    System.out.printf("%n                   ------------------------------%n");
                    System.out.printf("Aantal beschikbare stamleden: %d%n",speler.getStamlid());
                }
            }catch(NumberFormatException i) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        }
    }
    
    public void krijgSteen(Speler speler)
    {
       if (speler.getGebruikteSteen() == true)
        {
            speler.setSteen(speler.getAantalStamleden());
            speler.setStamlid(speler.getStamlid()  speler.getAantalStamleden());
            System.out.printf("%n%n%s bij de steengroeve:",speler.getNaam());
            System.out.printf("%n       Het gerolde getal van %s is %d", speler.getNaam(),speler.getGeroldGetal());
            System.out.printf("%n       Speler %d krijgt %d steen", speler.getSpelerNummer()  1, speler.getSteen());
        }
        else
        {
            
        }
    }
    
    //Plaatsen van goud & krijgen van goud
    public void plaatsGoud(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -    Goud rivier   -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            int temp;
            try{
                System.out.print("Aantal stamleden: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);
                speler.setAantalStamleden(temp);
                if (speler.getAantalStamleden() == 0) 
                {
                    bediening(speler);
                }
                else if(speler.getAantalStamleden() <= speler.getStamlid() && speler.getStamlid() > 0)
                {
                    speler.setStamlid(speler.getStamlid()-speler.getAantalStamleden()); //verwijdert het aantal stamleden
                    speler.setGebruikteGoud(true);  
                    speler.toonStamleden(speler);
                    b=false;
                }
                else
                {
                    System.out.printf("%n                   ------------------------------");
                    System.out.printf("%n                   -!!!Niet genoeg stamleden.!!!-");
                    System.out.printf("%n                   ------------------------------%n");
                    System.out.printf("Aantal beschikbare stamleden: %d%n",speler.getStamlid());
                }
            }catch(NumberFormatException i) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        }
    }
    
    public void krijgGoud(Speler speler)
    {
        if (speler.getGebruikteGoud() == true)
        {
            speler.setGoud(speler.getAantalStamleden());
            speler.setStamlid(speler.getStamlid()  speler.getAantalStamleden());
            System.out.printf("%n%n%s bij de goud rivier:", speler.getNaam());
            System.out.printf("%n       Het gerolde getal van %s is %d", speler.getNaam(),speler.getGeroldGetal());
            System.out.printf("%n       Speler %d krijgt %d goud", speler.getSpelerNummer()  1, speler.getGoud());
        }
        else
        {
            
        }
    }
    
    //plaatsen van voedsel & krijgen van voedsel
     public void plaatsJachtveld(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -    Jachtgebied   -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            int temp;
            try{
                System.out.print("Aantal stamleden: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);
                speler.setAantalStamleden(temp);
                if (speler.getAantalStamleden() == 0) 
                {
                    bediening(speler);
                }
                else if(speler.getAantalStamleden() <= speler.getStamlid() && speler.getStamlid() > 0)
                {
                    speler.setStamlid(speler.getStamlid()-speler.getAantalStamleden()); //verwijdert het aantal stamleden
                    speler.setGebruikteVoedsel(true);  
                    speler.toonStamleden(speler);
                    b=false;
                }
                else
                {
                    System.out.printf("%n                   ------------------------------");
                    System.out.printf("%n                   -!!!Niet genoeg stamleden.!!!-");
                    System.out.printf("%n                   ------------------------------%n");
                    System.out.printf("Aantal beschikbare stamleden: %d%n",speler.getStamlid());
                }
            }catch(NumberFormatException i) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        }
       
    }
    
    public void krijgVoedsel(Speler speler)
    {
        if (speler.getGebruikteVoedsel() == true)
        {
            speler.harvestVoedsel(speler.getAantalStamleden());
            speler.setStamlid(speler.getStamlid()  speler.getAantalStamleden());
            System.out.printf("%n%n%s bij het jacht gebied:", speler.getNaam());
            System.out.printf("%n       Het gerolde getal van %s is %d", speler.getNaam(),speler.getGeroldGetal());
            System.out.printf("%n       Speler %d krijgt %d voedsel", speler.getSpelerNummer()  1, speler.getVoedsel()-12);
        }
        else
        {
            
        }
    }
    
    //Plaatsen van hut & krijgen van extra stamlid methodes
    public void plaatsHut(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -        Hut       -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            if(speler.getStamlid() >= 2)
            {
                System.out.print("Ben je zeker dat je hier 2 stamleden wilt plaatsen? ja/nee: ");
                String antwoord = input.next();
                if ("ja".equals(antwoord.toLowerCase()))
                {
                    speler.setStamlid(speler.getStamlid() - 2);
                    System.out.printf("U hebt 2 stamleden op de hut geplaats");  
                    speler.toonStamleden(speler);
                    speler.setGebruikteHut(true);
                    b = false;  
                }
                else if("nee".equals(antwoord.toLowerCase()))
                {
                    b = false;
                    spel.bediening(speler);
                }
            }
            else
            {
                System.out.printf("%n                   ------------------------------");
                System.out.printf("%n                   -!!!Niet genoeg stamleden.!!!-");
                System.out.printf("%n                   ------------------------------%n");
                System.out.printf("%nAantal beschikbare stamleden: %d%nJe hebt er 2 nodig voor op de hut te plaatsen.%n",speler.getStamlid());
                b = false;
                spel.bediening(speler);
            }
            
            
        }
    }
    
    public void krijgBaby(Speler speler)
    {
        if (speler.getGebruikteHut() == true)
        {
            speler.setStamlid(speler.getStamlid()  speler.getAantalStamleden());
            System.out.printf("%n%s kreeg een extra stamlid", speler.getNaam());
        }
        else
        {
            
        }            
    }
    
    //Plaatsen van akkerbouw & krijgen van akkerbouw
    public void plaatsAkkerbouw(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -     Akkerbouw    -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            System.out.print("Ben je zeker dat je hier een stamlid wilt plaatsen? ja/nee: ");
            String antwoord = input.next();
            if ("ja".equals(antwoord.toLowerCase()))
            {
                b = false;
                speler.setStamlid(speler.getStamlid() - 1);
                speler.setAkkerbouw(1);
                System.out.printf("%s heeft een stamlid geplaats op de akkerbouw",speler.getNaam());  
                speler.toonStamleden(speler);  
            }
            else if("nee".equals(antwoord.toLowerCase()))
            {
                b = false;
                spel.bediening(speler);
            }
        }
    }
    
    //plaatsen van gereedschap & krijgen van gereedschap
    public void plaatsGereedschap(Speler speler)
    {
        System.out.printf("%n          --------------------");
        System.out.printf("%n          -    Gereedschap   -");
        System.out.printf("%n          --------------------%n%n");
        boolean b = true;
        while(b)
        {
            System.out.print("Ben je zeker dat je hier een stamlid wilt plaatsen? ja/nee: ");
            String antwoord = input.next();
            if ("ja".equals(antwoord.toLowerCase()))
            {
                speler.setStamlid(speler.getStamlid() - 1);
                speler.setHeeftGereedschap(true);
                speler.setGereedschap(1);
                System.out.printf("%s heeft een stamlid geplaats op de tool makelaar",speler.getNaam());  
                speler.toonStamleden(speler);
                b = false;
            }
            else if("nee".equals(antwoord.toLowerCase()))
            {
                b = false;
                spel.bediening(speler);
            }
        }
    }
    
    //Elke speler in een tabel afdrukken in de console
    public String toString(Speler speler)
    {
        String resultaat = "";
        if (speler.getSpelerNummer() == 0) 
        {
            for (int i = 0; i <= 177; i) 
            {
                resultaat = String.format("-");            
            }
            resultaat = String.format("%n");
        }
        resultaat = String.format("| Naam: "  speler.getNaam()  "\t\t\t");
        resultaat = String.format("Kleur: "  speler.getKleur()  "\t");
        resultaat = String.format("Hout: "  speler.getHout()  "     ");
        resultaat = String.format("Leem: "  speler.getLeem()  "     ");
        resultaat = String.format("Steen: "  speler.getSteen()  "     ");
        resultaat = String.format("Goud: "  speler.getGoud()  "\t");
        resultaat = String.format("Akkerbouw: "  speler.getAkkerbouw()  "     ");
        resultaat = String.format("Gereedschap: "  speler.getGereedschap()  "     ");
        resultaat = String.format("Voedsel: "  speler.getVoedsel()  "     ");
        resultaat = String.format("Stamleden: "  speler.getStamlid()  "     ");
        resultaat = String.format("Punten:  "  speler.getPunten())  "  |";
        resultaat = String.format("%n");
        for (int i = 0; i <= 177; i) {
            resultaat = String.format("-");            
        }
        return resultaat;
    }
    
    //Extra getters en setters voor huidige klasse
    public int getAantalSpelers() {
        return aantalSpelers;
    }

    private void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
    }

    public Speler[] getSpelers() {
        return spelers;
    }

    public void setSpelers(Speler[] spelers) {
        this.spelers = spelers;
    }
       */

    

}