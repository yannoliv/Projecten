package domein;

import java.security.SecureRandom;
import java.util.*;

public class Speler 
{
    //atributen
    private String naam;
    private final String[] kleurLijst = {"rood","blauw","geel","groen"};
    private String kleur;
    public int spelerNummer = 0;
    private List<Resource> resourcesLijst;

    
    SecureRandom random = new SecureRandom();
    
    //constructor
    public Speler(int spelerNummer)
    {
        setSpelerNummer(spelerNummer);    
        setKleur(kleurLijst[spelerNummer]); // speler 1 -1 wordt 0 dus rood
    }
    
    //---------------------------------------------------------------------------
    //speler scoreboard afprinten
   public String toString(Speler speler)
   {
       String resultaat = "";
       resultaat += String.format("Speler %d  ", speler.spelerNummer + 1);
       resultaat += String.format("Naam: %s    ", speler.getNaam());
       resultaat += String.format("Kleur: %s    ", speler.getKleur());
          for (int index = 0; index < getResourcesLijst().size(); index++) {
           resultaat += String.format(speler.getResourcesLijst().get(index).getNaam() + ": " + speler.getResourcesLijst().get(index).getAantal() + "     ");
        } 
       return resultaat;
   }
    
    //getters en setters
    //---------------------------------------------------------------------------
    //Naam
    public String getNaam() {
        return naam;
    }

    public final void setNaam(String naam)
    {
        this.naam = naam;
    }
    //---------------------------------------------------------------------------
    //Kleur
    public String getKleur() {
        return kleur;
    }
 
    private void setKleur(String kleur) {
        this.kleur = kleur;
    }
    //---------------------------------------------------------------------------    
    //Spelernummer
    public int getSpelerNummer() {
        return spelerNummer;
    }

    private void setSpelerNummer(int spelerNummer) {
        this.spelerNummer = spelerNummer;
    }
    //---------------------------------------------------------------------------
    //Resourcelijst
    public List<Resource> getResourcesLijst() {
        return resourcesLijst;
    }

    public void setResourcesLijst(List<Resource> resourcesLijst) {
        this.resourcesLijst = resourcesLijst;
    }
    
}

//