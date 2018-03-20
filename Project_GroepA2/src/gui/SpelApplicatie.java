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
    
    
    public DomeinController getDc() {
        return dc;
    }

    public void setDc(DomeinController dc) {
        this.dc = dc;
    }

}
