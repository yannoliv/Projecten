package gui;

import domein.DomeinController;
import java.util.Scanner;

public class SpelApplicatie 
{
    //Attributen
    DomeinController dc;
    Scanner input = new Scanner(System.in);
    //Constructor
    public SpelApplicatie(DomeinController dc)
    {
        setDc(dc);
    }
    
    public void BepaalAantalSpelers()
    {
        //aantal spelers bepalen
        
        while (dc.getAantalSpelers() < 2 || dc.getAantalSpelers() > 4)
        {
            String resultaat;
            try{
                System.out.printf("%nAantal spelers:(2-4): ");
                resultaat = input.nextLine();
                int aantal = Integer.parseInt(resultaat);
                if (aantal < 2 || aantal > 4)
                    System.out.printf("Ongeldige keuze.%n");
                dc.setAantalSpelers(aantal);
            }catch(NumberFormatException e) 
            {
                System.out.printf("Ongeldige keuze.%n");
            }
        } //deze return dienen voor geen rode lijntjes :p
    }
    
    public String vraagSpelerNamen(int spelerNr)
    {
        System.out.printf("Naam speler %d: ", spelerNr + 1);
        return input.next();
    }
    
    
    //Bedieningspaneel
    public void bedieningsPaneel(int spelerNr)
    {
        String resultaat;
        int temp;
        try
        {
            do
            {
                boolean isOpGezet = false;
                System.out.println(dc.getBedieningsPaneel(spelerNr));
                System.out.printf("Keuze:%n");
                resultaat = input.next();
                temp = Integer.parseInt(resultaat);
                switch(temp)
                {
                    //terug keren
                    case 0:                        
                        break;
                    //toon spelers
                    case 1: System.out.printf("%n%n" + dc.toonSpelers());
                    break;
                    //bos
                    case 2:
                        if (dc.getPlaatsenLijst().get(0).getAantalSpots() < 1)
                        {
                            System.out.printf("Het bos is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpBos())
                        {
                            System.out.printf("Je hebt al spelers gezet op deze plaats.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                        }
                    break;
                    //leemgroeve
                    case 3:
                        if (dc.getPlaatsenLijst().get(1).getAantalSpots() < 1)
                        {
                            System.out.printf("De leemgroeve is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpLeemgroeve())
                        {
                            System.out.printf("Je hebt al spelers gezet op deze plaats.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                        }
                    break;
                    //steengroeve
                    case 4: 
                        if (dc.getPlaatsenLijst().get(2).getAantalSpots() < 1)
                        {
                            System.out.printf("De steengroeve is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpSteengroeve())
                        {
                            System.out.printf("Je hebt al spelers gezet op deze plaats.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                        }
                    break;
                    //goudmijn
                    case 5: 
                        if (dc.getPlaatsenLijst().get(3).getAantalSpots() < 1)
                        {
                            System.out.printf("De goudmijn is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpGoudmijn())
                        {
                            System.out.printf("Je hebt al spelers gezet op deze plaats.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                        }
                    break;
                    //jachtgebied
                    case 6: 
                        if (dc.getPlaatsenLijst().get(4).getAantalSpots() < 1)
                        {
                            System.out.printf("Het jachtgebied is volzet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if(dc.getSpelerLijst().get(spelerNr).isPlaatsOpJachtgebied())
                        {
                            System.out.printf("Je hebt al spelers gezet op deze plaats.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bepaalStamleden(spelerNr, temp));
                        }
                    break;
                    //hut
                    case 7:
                        if (dc.getPlaatsenLijst().get(7).getAantalSpots() < 2)
                        {
                            System.out.printf("De hut is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() < 2)
                        {
                            System.out.printf("Onvoldoende stamleden.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() == 10)
                        {
                            System.out.printf("Maximum stamleden bereikt.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bevestiging(spelerNr, temp));
                        }
                    break;
                    //smith
                    case 8: 
                        if (dc.getPlaatsenLijst().get(6).getAantalSpots() == 0)
                        {
                            System.out.printf("De smith is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bevestiging(spelerNr, temp));
                        }
                    break;
                    //akkerbouw
                    case 9: 
                        if (dc.getPlaatsenLijst().get(5).getAantalSpots() == 0)
                        {
                            System.out.printf("De akkerbouw is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        else
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bevestiging(spelerNr, temp));
                        }
                    break;
                    //stop spel
                    case 10:
                        System.exit(0);
                        break;
                    case 11:
                        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
                        {
                            if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart1())
                            {
                                isOpGezet = true;
                            }
                        }
                        if (isOpGezet == false)// 
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bevestiging(spelerNr, temp));
                        }
                        else
                        {
                            System.out.printf("Hut 1 is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                        break;
                    case 12:
                        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
                        {
                            if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart2())
                            {
                                isOpGezet = true;
                            }
                        }
                        if (isOpGezet == false)// 
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bevestiging(spelerNr, temp));
                        }
                        else
                        {
                            System.out.printf("Hut 2 is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                    break;
                    case 13:
                        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
                        {
                            if (dc.getSpelerLijst().get(i).isPlaatsOpHutkaart3())
                            {
                                isOpGezet = true;
                            }
                        }
                        if (isOpGezet == false)// 
                        {
                            dc.plaatsOpPlek(spelerNr, temp, bevestiging(spelerNr, temp));
                        }
                        else
                        {
                            System.out.printf("Hut 3 is bezet.");
                            resultaat = null;
                            temp = 0;
                            bedieningsPaneel(spelerNr);
                        }
                    break;
                    default: 
                        System.out.printf("%nOngeldige keuze.");
                        resultaat = null;
                        temp = 0;
                        bedieningsPaneel(spelerNr);
                        break;
                }

            }while(temp == 1);
        }catch(NumberFormatException e) 
        {
            System.out.printf("%nOngeldige keuze.");
            resultaat = null;
            temp = 0;
            bedieningsPaneel(spelerNr);
        }
    }
        
    public void voedselStraf(int spelerNr, int check)
    {
        if (check == 0) {
            System.out.printf("U heeft geen voedsel om uw dorp te voeden.%n");
        }
        if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() <= 0
            && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() <= 0
            && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() <= 0 
            && dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() <= 0) {
            System.out.printf("U krijgt 10 strafpunten omdat uw dorp honger moet leiden...");
            dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(8).setAantal(dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(8).getAantal() - 10);
        }
        else
        {
            System.out.printf("Kies een resource om uw dorp te voeden (type naam van de resource):%n");
            for (int i = 0; i < 4; i++) {
                System.out.printf("%s: %d | ", dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(i).getNaam(), dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(i).getAantal());
            }
            System.out.printf("%n");
            String antwoord = input.next().toLowerCase();
            switch (antwoord)
            {
                case "hout":
                    if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() <= 0) {
                        System.out.printf("U heeft geen hout om af te geven...%n");
                        voedselStraf(spelerNr, 1);
                    }
                    else
                    {
                        dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(0);
                    }
                    break;
                case "leem":
                     if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(1).getAantal() <= 0) {
                        System.out.printf("U heeft geen leem om af te geven...%n");
                        voedselStraf(spelerNr, 1);
                    }
                    break;
                case "steen":
                     if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(2).getAantal() <= 0) {
                        System.out.printf("U heeft geen steen om af te geven...%n");
                        voedselStraf(spelerNr, 1);
                    }
                    break;
                case "goud":
                     if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(3).getAantal() <= 0) {
                        System.out.printf("U heeft geen goud om af te geven...%n");
                        voedselStraf(spelerNr, 1);
                    }
                    break;
                default:
                    System.out.printf("Gelieve een resource te kiezen...%n");
                    voedselStraf(spelerNr, 1);
                    break;
            }
        }
    }
    
    
    public int bepaalStamleden(int spelerNr, int keuzeNr)
    {
        int ingegevenAantalStamleden = 0;
        boolean OK = true;
        System.out.printf("<Huidig aantal stamleden: %d >%n", dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal());
        while(OK)
        {
            try
            {
                System.out.printf("Hoeveel wilt u er plaatsen (0: terug): ");
                String antw = input.next();
                ingegevenAantalStamleden = Integer.parseInt(antw);
                if (ingegevenAantalStamleden == 0) 
                {
                    OK = false;
                    bedieningsPaneel(spelerNr);
                }
                if(ingegevenAantalStamleden > dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() || ingegevenAantalStamleden < 0)
                {
                    System.out.printf("Ongeldig probeer opnieuw!%n");
                    bepaalStamleden(spelerNr, keuzeNr);
                }
                if (ingegevenAantalStamleden > dc.getPlaatsenLijst().get(keuzeNr - 2).getAantalSpots())
                {
                    System.out.printf("Te weinig plaats voor %d %s%n", ingegevenAantalStamleden, ingegevenAantalStamleden > 1 ? "stamleden":"stamlid");
                }
                else if(ingegevenAantalStamleden <= dc.getPlaatsenLijst().get(keuzeNr - 2).getAantalSpots())
                {
                    System.out.println("Stamleden worden geplaatst...");
                    OK = false;
                }
            }catch(NumberFormatException e)
            {
                System.out.printf("Ongeldig probeer opnieuw!%n");
            }
        }
        return ingegevenAantalStamleden;
    }
    
    public int bevestiging(int spelerNr, int keuzeNr)
    {
        int bevestiging = 0;
        switch (keuzeNr) 
        {
            case 7:
                {
                    System.out.printf("<Hut neemt vast 2 stamleden>%nHier plaatsen (ja/nee):");
                    String antwoord = input.next().toLowerCase();
                    while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) 
                    {
                        System.out.printf("Ongeldig antwoord!%nja of nee: ");
                        antwoord = input.next().toLowerCase();
                    }      
                    if ("nee".equals(antwoord))
                    {
                        bedieningsPaneel(spelerNr);
                    }
                    else
                    {
                        bevestiging = 1;
                    }
                }
                break;
            case 8:
                {
                    System.out.printf("<Smith neemt vast 1 stamlid>%nHier plaatsen (ja/nee): ");
                    String antwoord = input.next().toLowerCase();
                    while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
                        System.out.printf("Ongeldig antwoord!%nja of nee: ");
                        antwoord = input.next().toLowerCase();
                    }       if ("ja".equals(antwoord))
                    {
                        bevestiging = 1;
                    }
                    else
                    {
                        bedieningsPaneel(spelerNr);
                    }       break;
                }
            case 9:
                {
                    System.out.printf("<Akkerbouw neemt vast 1 stamlid>%nHier plaatsen (ja/nee): ");
                    String antwoord = input.next().toLowerCase();
                    while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
                        System.out.printf("Ongeldig antwoord!%nja of nee: ");
                        antwoord = input.next().toLowerCase();
                    }
                    if ("ja".equals(antwoord))
                    {
                        bevestiging = 1;
                    }
                    else
                    {
                        bedieningsPaneel(spelerNr);
                    }       break;
                }
            case 11:
            {
                System.out.printf("<Hut kaart 1 neemt vast 1 stamlid>%nHier plaatsen (ja/nee): ");
                String antwoord = input.next().toLowerCase();
                while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
                    System.out.printf("Ongeldig antwoord!%nja of nee: ");
                    antwoord = input.next().toLowerCase();
                }
                if ("ja".equals(antwoord))
                {
                    bevestiging = 1;
                }
                else
                {
                    antwoord = "";
                    bedieningsPaneel(spelerNr);
                }  break;
            }
            case 12:
            {
                System.out.printf("<Hut kaart 2 neemt vast 1 stamlid>%nHier plaatsen (ja/nee): ");
                String antwoord = input.next().toLowerCase();
                while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
                    System.out.printf("Ongeldig antwoord!%nja of nee: ");
                    antwoord = input.next().toLowerCase();
                }
                if ("ja".equals(antwoord))
                {
                    bevestiging = 1;
                }
                else
                {
                    antwoord = "";
                    bedieningsPaneel(spelerNr);
                }  break;
            }
            case 13:
            {
                System.out.printf("<Hut kaart 3 neemt vast 1 stamlid>%nHier plaatsen (ja/nee): ");
                String antwoord = input.next().toLowerCase();
                while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
                    System.out.printf("Ongeldig antwoord!%nja of nee: ");
                    antwoord = input.next().toLowerCase();
                }
                if ("ja".equals(antwoord))
                {
                    bevestiging = 1;
                }
                else
                {
                    antwoord = "";
                    bedieningsPaneel(spelerNr);
                }  break;
            }
            
            default:
                break;
        }
        return bevestiging;
    }
    
    public boolean gereedschapBoodschap(int spelerNr)
    {
        boolean a = false;
        System.out.printf("%n%s u heeft %d gereedschap.%n wilt u dit gebruiken (ja/nee):", dc.getSpelerLijst().get(spelerNr).getNaam(), dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal());
        String antwoord = input.next().toLowerCase();
        while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
            System.out.printf("Ongeldig antwoord!%nja of nee: ");
            antwoord = input.next().toLowerCase();
        }
        if ("ja".equals(antwoord))
        {
            a = true;
        }
        return a;
    }
    
    public void toonEindeRondeBericht(String bericht)
    {
        System.out.printf(bericht);
    }
    
    public int aantalGebruikGereedschap(int spelerNr)
    {
        int getal = 0;
        String antwoord;
        try
        {
            System.out.printf("Hoeveel ervan wilt u gebruiken (0: geen): ");
            antwoord = input.next();
            getal = Integer.parseInt(antwoord);
            if (getal > dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal())
            {
                System.out.printf("Te weinig gereedschap daarvoor...%n");
                antwoord = null;
                getal = 0;
                aantalGebruikGereedschap(spelerNr);
            }
            if (getal < 0) 
            {
                System.out.printf("%nOngeldig aantal.%n");
                antwoord = null;
                getal = 0;
                aantalGebruikGereedschap(spelerNr);
            }
            if (getal <= dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(5).getAantal())
            {
                return getal;
            }            
        }
        catch(NumberFormatException e)
        {
            System.out.printf("%nOngeldig aantal.%n");
            antwoord = null;
            getal = 0;
            aantalGebruikGereedschap(spelerNr);
        }
        return getal;
    }
    //toon gerold getal plaats resource
    public int toonGeroldGetal(int plaatsNr, int aantalStamleden, int spelerNr)
    {
        int geroldGetal = dc.getGeroldGetal(aantalStamleden);
        
        System.out.printf("%n%s:%nDe gerolde dobbelstenen op de plaats %s kwamen op het totaal getal %d delen door %d geeft you %d %s.%n",
                dc.getSpelerLijst().get(spelerNr).getNaam(),
                dc.getPlaatsenLijst().get(plaatsNr).getNaam(),
                geroldGetal,
                dc.getPlaatsenLijst().get(plaatsNr).getDeler(),
                ((int) Math.floor(geroldGetal / dc.getPlaatsenLijst().get(plaatsNr).getDeler())),
                dc.getPlaatsenLijst().get(plaatsNr).getTypeResource().getNaam());  
        return geroldGetal;
    }
    
    public void toonScoreBord()
    {
        System.out.printf("%n%n%n%n" + dc.toonSpelers() + "%n%n");
    }
    
    public DomeinController getDc() {
        return dc;
    }

    public void setDc(DomeinController dc) {
        this.dc = dc;
    }

}
