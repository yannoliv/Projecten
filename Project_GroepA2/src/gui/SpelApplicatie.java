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
                System.out.println(dc.BedieningsPaneel(spelerNr));
                System.out.printf("Keuze:%n");
                String resultaat = input.next();
                temp = Integer.parseInt(resultaat);

                switch(temp)
                {
                    case 0:
                        System.exit(0);
                        break;
                    case 1: System.out.printf("%n%n" + dc.toonSpelers());
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
            System.out.printf("%nOngeldige keuze.");
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
            do
            {
                System.out.printf("Hoeveel wilt u er plaatsen: ");
                String antw = input.next();
                aantal = Integer.parseInt(antw);
                while (aantal > dc.getSpelerLijst().get(spelerNr).getResourceLijst().get(7).getAantal() || aantal <= 0) {
                    System.out.printf("Ongeldig probeer opnieuw!%n");
                    System.out.printf("Hoeveel wilt u er plaatsen: ");
                    antw = input.next();
                    aantal = Integer.parseInt(antw);
                }
                return aantal;
            } while(aantal == 0);
        }catch(NumberFormatException e)
        {
            System.out.printf("Ongeldig probeer opnieuw!%n");
            bepaalStamleden(spelerNr);
        }
        return aantal;
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
