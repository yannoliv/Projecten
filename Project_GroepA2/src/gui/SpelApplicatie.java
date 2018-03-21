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
    
    public void setSpelersNamen() 
    {
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) 
        {
            System.out.printf("Naam speler %d: ", i +1);
            dc.geefSpelersNamen(input.nextLine(), dc.getSpelerLijst().get(i)); // naam,lijst(index)

            switch(i)
            {
                case 1: 
                    while (
                            dc.getSpelerLijst().get(0).getNaam().equals(dc.getSpelerLijst().get(1).getNaam())
                            )
                    {
                        System.out.printf("Naam speler %d: ", i +1);
                        dc.geefSpelersNamen(input.nextLine(), dc.getSpelerLijst().get(i));
                    }
                break;
                case 2:
                    while (
                            dc.getSpelerLijst().get(0).getNaam().equals(dc.getSpelerLijst().get(2).getNaam()) ||
                            dc.getSpelerLijst().get(1).getNaam().equals(dc.getSpelerLijst().get(2).getNaam())
                            )
                    {
                        System.out.printf("Naam speler %d: ", i +1);
                        dc.geefSpelersNamen(input.nextLine(), dc.getSpelerLijst().get(i));
                    }
                break;
                case 3:
                    while (
                            dc.getSpelerLijst().get(0).getNaam().equals(dc.getSpelerLijst().get(3).getNaam()) ||
                            dc.getSpelerLijst().get(1).getNaam().equals(dc.getSpelerLijst().get(3).getNaam()) ||
                            dc.getSpelerLijst().get(2).getNaam().equals(dc.getSpelerLijst().get(3).getNaam())
                            )
                    {
                        System.out.printf("Naam speler %d: ", i +1);
                        dc.geefSpelersNamen(input.nextLine(), dc.getSpelerLijst().get(i));
                    }
                break;
            }
        }
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
                System.out.println(dc.getBedieningsPaneel(spelerNr));
                System.out.printf("Keuze:%n");
                resultaat = input.next();
                temp = Integer.parseInt(resultaat);

                switch(temp)
                {
                    //terug keren
                    case 0:
                        bedieningsPaneel(spelerNr);
                        break;
                    //toon spelers
                    case 1: System.out.printf("%n%n" + dc.toonSpelers());
                    break;
                    //bos
                    case 2:
                        if (dc.getPlaatsenLijst().get(0).getAantalSpots() == 0)
                        {
                            System.out.printf("Het bos is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 2, bepaalStamleden(spelerNr));
                    break;
                    //leemgroeve
                    case 3:
                        if (dc.getPlaatsenLijst().get(1).getAantalSpots() == 0)
                        {
                            System.out.printf("De leemgroeve is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 3, bepaalStamleden(spelerNr));
                    break;
                    //steengroeve
                    case 4: 
                        if (dc.getPlaatsenLijst().get(2).getAantalSpots() == 0)
                        {
                            System.out.printf("De steengroeve is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 4, bepaalStamleden(spelerNr));
                    break;
                    //goudmijn
                    case 5: 
                        if (dc.getPlaatsenLijst().get(3).getAantalSpots() == 0)
                        {
                            System.out.printf("De goudmijn is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 5, bepaalStamleden(spelerNr));
                    break;
                    //jachtgebied
                    case 6: 
                        if (dc.getPlaatsenLijst().get(6).getAantalSpots() == 0)
                        {
                            System.out.printf("Het jachtgebied is volzet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 6, bepaalStamleden(spelerNr));
                    break;
                    //hut
                    case 7:
                        if (dc.getPlaatsenLijst().get(7).getAantalSpots() == 0)
                        {
                            System.out.printf("De hut is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        else if (dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() <= 1)
                        {
                            System.out.printf("Onvoldoende stamleden.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 7, bevestiging(spelerNr, temp));
                    break;
                    //smith
                    case 8: 
                        if (dc.getPlaatsenLijst().get(5).getAantalSpots() == 0)
                        {
                            System.out.printf("De smith is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 8, bevestiging(spelerNr, temp));
                    break;
                    //akkerbouw
                    case 9: 
                        if (dc.getPlaatsenLijst().get(4).getAantalSpots() == 0)
                        {
                            System.out.printf("De akkerbouw is bezet.");
                            bedieningsPaneel(spelerNr);
                        }
                        dc.plaatsOpPlek(spelerNr, 9, bevestiging(spelerNr, temp));
                    break;
                    //stop spel
                    case 10:
                        System.exit(0);
                        break;
                    default: 
                        System.out.printf("%nOngeldige keuze.");
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
    
   //Einde ronde
    public void eindeRonde()
    {
        String output = "";
        output += String.format("%n%n");
        output += String.format("%n               -----------------------------------------------");
        output += String.format("%n               -                Ronde is klaar!              -");
        output += String.format("%n               -           De uiteindelijke score is         -");
        output += String.format("%n               -----------------------------------------------%n%n%n%n");
        dc.eindeRonde();
        System.out.printf(output + dc.toonSpelers() + "%n%n%n%n" + "<scroll naar boven voor eind resultaat van vorige ronde>%n" +"%n%n<Nieuwe ronde is gestart>%n%n");
    }
    
    public int bepaalStamleden(int spelerNr)
    {
        int aantal = 0;
        System.out.printf("<Huidig aantal stamleden: %d >%n", dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal());
        try
        {
            System.out.printf("Hoeveel wilt u er plaatsen (0: terug): ");
            String antw = input.next();
            aantal = Integer.parseInt(antw);
            if (aantal == 0) {
                bedieningsPaneel(spelerNr);
            }
            while (aantal > dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() || aantal <= 0 || //aantal > beschikbare stamleden || aantal <=0
                    dc.getPlaatsenLijst().get(0).getAantalSpots() <  aantal) //aantal spots besch > aantal stamleden
            {
                System.out.printf("Ongeldig probeer opnieuw!%n");
                System.out.printf("Hoeveel wilt u er plaatsen  (0: terug): ");
                antw = input.next();
                aantal = Integer.parseInt(antw);
                if (aantal == 0)
                {
                    bedieningsPaneel(spelerNr);
                }
            }
            return aantal;
        }catch(NumberFormatException e)
        {
            System.out.printf("Ongeldig probeer opnieuw!%n");
            bepaalStamleden(spelerNr);
        }
        return aantal;
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
                        antwoord = input.next();
                    }      
                    if ("nee".equals(antwoord))
                    {
                        bedieningsPaneel(spelerNr);
                    }
                    else
                    {
                        bevestiging = 2;
                    }
                }
                break;
            case 8:
                {
                    System.out.printf("<Smith neemt vast 1 stamlid>%nHier plaatsen (ja/nee): ");
                    String antwoord = input.next().toLowerCase();
                    while (!"ja".equals(antwoord) && !"nee".equals(antwoord)) {
                        System.out.printf("Ongeldig antwoord!%nja of nee: ");
                        antwoord = input.next();
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
                        antwoord = input.next();
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
            default:
                break;
        }
        return bevestiging;
    }
    
    public void toonScoreBord()
    {
        System.out.printf("%n" + dc.toonSpelers());
    }
    
    public DomeinController getDc() {
        return dc;
    }

    public void setDc(DomeinController dc) {
        this.dc = dc;
    }

}
