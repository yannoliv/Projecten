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
    public void bediening(int spelerNr)
    {
        
        try
        {
            int temp;
            do
            {
                System.out.printf("%n%n-Speler %d is aan de beurt.%n" + "--------------------------------------------------------------------------------------------------------------------------------------------------------------%n", dc.getSpelerLijst().get(spelerNr).getSpelerNummer() + 1);
                System.out.printf("- • 0: Stop spel • 1: toonSpelers • 2: Bos • 3: Leemgroeve • 4: Steengroeve • 5: Goudmijn • 6: Jachtgebied • 7: Hut • 8: Smith • 9: Akkerbouw     -%n");
                System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
                System.out.printf("Keuze: ");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);

                switch(temp)
                {
                    case 0:
                        System.exit(0);
                        break;
                    case 1: dc.toonSpelers();
                    break;
                    case 2: dc.plaatsOpPlek(spelerNr, 2, bepaalStamleden(spelerNr));
                    break;
                    case 3: dc.plaatsOpPlek(spelerNr, 3, bepaalStamleden(spelerNr));
                    break;
                    case 4: dc.plaatsOpPlek(spelerNr, 4, bepaalStamleden(spelerNr));
                    break;
                    case 5: dc.plaatsOpPlek(spelerNr, 5, bepaalStamleden(spelerNr));
                    break;
                    case 6: dc.plaatsOpPlek(spelerNr, 6, bepaalStamleden(spelerNr));
                    break;
                    case 7: dc.plaatsOpPlek(spelerNr, 7, bepaalStamleden(spelerNr));
                    break;
                    case 8: dc.plaatsOpPlek(spelerNr, 8, bepaalStamleden(spelerNr));
                    break;
                    case 9: dc.plaatsOpPlek(spelerNr, 9, bepaalStamleden(spelerNr));
                    break;
                    default: 
                        System.out.printf("%nOngeldige keuze.");
                        bediening(spelerNr);
                        break;
                }

            }while(temp == 1);
        }catch(NumberFormatException e) 
        {
            System.out.printf("%n                   ----------------------------");
            System.out.printf("%n                   -!!!Keuze moet 0-9 zijn.!!!-");
            System.out.printf("%n                   ----------------------------%n");
        }
    }
    
   //Einde ronde
    private void eindeRonde()
    {
        System.out.printf("%n               -----------------------------------------------");
        System.out.printf("%n               -                Ronde is klaar!              -");
        System.out.printf("%n               -           De uiteindelijke score is         -");
        System.out.printf("%n               -----------------------------------------------");
        dc.eindeRonde();
        dc.toonSpelers();
        System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n");
        System.out.printf("%n%n<Nieuwe ronde is gestart>%n%n");
    }
    
    public int bepaalStamleden(int spelerNr)
    {
        int aantal = 0;
        System.out.printf("<Huidig aantal stamleden: %d >%n", dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal());
            try
            {
                do
                {
                    System.out.printf("Hoeveel wilt u er plaatsen: ");
                    String antw = input.next();
                    aantal = Integer.parseInt(antw);
                    return aantal;
                } while(aantal == 0);
            }catch(NumberFormatException e)
            {
                bepaalStamleden(spelerNr);
            }
            return aantal;
        }
    
    public DomeinController getDc() {
        return dc;
    }

    public void setDc(DomeinController dc) {
        this.dc = dc;
    }

}
