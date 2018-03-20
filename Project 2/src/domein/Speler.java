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
       resultaat += String.format("Speler %d \t", speler.spelerNummer);
       resultaat += String.format("Naam: %s \t\t", speler.getNaam());
       resultaat += String.format("Kleur: %s \t\t", speler.getKleur());
       resultaat += String.format("Hout: %d \t", speler.getResourcesLijst().get(0));
       resultaat += String.format("Leem: %d \t", speler.getResourcesLijst().get(1));
       resultaat += String.format("Steen: %d \t", speler.getResourcesLijst().get(2));
       resultaat += String.format("Goud: %d \t", speler.getResourcesLijst().get(3));
       resultaat += String.format("Akkerbouw: %d \t", speler.getResourcesLijst().get(4));
       resultaat += String.format("Gereedschap: %d \t", speler.getResourcesLijst().get(5));
       resultaat += String.format("Voedsel: %d \t", speler.getResourcesLijst().get(6));
       resultaat += String.format("Stamleden: %d \t", speler.getResourcesLijst().get(7));
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