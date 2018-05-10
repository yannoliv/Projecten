package domein;

import java.util.*;

public class Speler 
{
    //atributen
    private String naam;
    private final String[] kleurLijst = {"rood","blauw","geel","groen"};
    private String kleur;
    public int spelerNummer = 0;
    private List<Resource> resourceLijst;
    private int gebruikteStamleden;
    
    private boolean plaatsOpBos = false;
    private int aantalBos = 0;
    private boolean plaatsOpLeemgroeve = false;
    private int aantalLeemgroeve = 0;
    private boolean plaatsOpSteengroeve = false;
    private int aantalSteengroeve = 0;
    private boolean plaatsOpGoudmijn = false;
    private int aantalGoudmijn = 0;
    private boolean plaatsOpJachtgebied = false;
    private int aantalJachtgebied = 0;
    private boolean plaatsOpAkkerbouw = false;
    private int aantalAkkerbouw = 0;
    private boolean plaatsOpSmith = false;
    private int aantalSmith = 0;
    private boolean plaatsOpHut = false;
    private int aantalHut = 0;
    private boolean plaatsOpHutkaart1 = false;
    private boolean plaatsOpHutkaart2 = false;
    private boolean plaatsOpHutkaart3 = false;
    private int totaalScore = 0;
    private int highscoreNr = 0;
    
    //constructor
    public Speler(int spelerNummer){
        setSpelerNummer(spelerNummer); 
        //deze if dient voor scorebord
        if (spelerNummer < 4)
        {
            setKleur(kleurLijst[spelerNummer]);
        }
        setGebruikteStamleden(gebruikteStamleden);
    }
    
    //---------------------------------------------------------------------------
    //speler scoreboard afprinten
   public String toString(Speler speler){
       String resultaat = "";
       resultaat += String.format("Speler %d |  ", speler.spelerNummer + 1);
       resultaat += String.format("Naam: %s    ", speler.getNaam());
       resultaat += String.format("Kleur: %s    ", speler.getKleur());
          for (int index = 0; index < getResourceLijst().size(); index++) {
           resultaat += String.format(speler.getResourceLijst().get(index).getNaam() + ": " + speler.getResourceLijst().get(index).getAantal() + "     ");
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
    public List<Resource> getResourceLijst() {
        return resourceLijst;
    }

    public void setResourceLijst(List<Resource> resourceLijst) {
        this.resourceLijst = resourceLijst;
    }
    
    
    public int getGebruikteStamleden() {
        return gebruikteStamleden;
    }

    public void setGebruikteStamleden(int gebruikteStamleden) {
        this.gebruikteStamleden = gebruikteStamleden;
    }

    public boolean isPlaatsOpBos() {
        return plaatsOpBos;
    }

    public void setPlaatsOpBos(boolean plaatsOpBos) {
        this.plaatsOpBos = plaatsOpBos;
    }

    public boolean isPlaatsOpLeemgroeve() {
        return plaatsOpLeemgroeve;
    }

    public void setPlaatsOpLeemgroeve(boolean plaatsOpLeemgroeve) {
        this.plaatsOpLeemgroeve = plaatsOpLeemgroeve;
    }

    public boolean isPlaatsOpSteengroeve() {
        return plaatsOpSteengroeve;
    }

    public void setPlaatsOpSteengroeve(boolean plaatsOpSteengroeve) {
        this.plaatsOpSteengroeve = plaatsOpSteengroeve;
    }

    public boolean isPlaatsOpGoudmijn() {
        return plaatsOpGoudmijn;
    }

    public void setPlaatsOpGoudmijn(boolean plaatsOpGoudmijn) {
        this.plaatsOpGoudmijn = plaatsOpGoudmijn;
    }

    public boolean isPlaatsOpJachtgebied() {
        return plaatsOpJachtgebied;
    }

    public void setPlaatsOpJachtgebied(boolean plaatsOpJachtgebied) {
        this.plaatsOpJachtgebied = plaatsOpJachtgebied;
    }

    public boolean isPlaatsOpAkkerbouw() {
        return plaatsOpAkkerbouw;
    }

    public void setPlaatsOpAkkerbouw(boolean plaatsOpAkkerbouw) {
        this.plaatsOpAkkerbouw = plaatsOpAkkerbouw;
    }

    public boolean isPlaatsOpSmith() {
        return plaatsOpSmith;
    }

    public void setPlaatsOpSmith(boolean plaatsOpSmith) {
        this.plaatsOpSmith = plaatsOpSmith;
    }

    public boolean isPlaatsOpHut() {
        return plaatsOpHut;
    }

    public void setPlaatsOpHut(boolean plaatsOpHut) {
        this.plaatsOpHut = plaatsOpHut;
    }

    public boolean isPlaatsOpHutkaart1() {
        return plaatsOpHutkaart1;
    }

    public void setPlaatsOpHutkaart1(boolean plaatsOpHutkaart1) {
        this.plaatsOpHutkaart1 = plaatsOpHutkaart1;
    }

    public boolean isPlaatsOpHutkaart2() {
        return plaatsOpHutkaart2;
    }

    public void setPlaatsOpHutkaart2(boolean plaatsOpHutkaart2) {
        this.plaatsOpHutkaart2 = plaatsOpHutkaart2;
    }

    public boolean isPlaatsOpHutkaart3() {
        return plaatsOpHutkaart3;
    }

    public void setPlaatsOpHutkaart3(boolean plaatsOpHutkaart3) {
        this.plaatsOpHutkaart3 = plaatsOpHutkaart3;
    }

    public int getAantalBos() {
        return aantalBos;
    }

    public void setAantalBos(int aantalBos) {
        this.aantalBos = aantalBos;
    }

    public int getAantalLeemgroeve() {
        return aantalLeemgroeve;
    }

    public void setAantalLeemgroeve(int aantalLeemgroeve) {
        this.aantalLeemgroeve = aantalLeemgroeve;
    }

    public int getAantalSteengroeve() {
        return aantalSteengroeve;
    }

    public void setAantalSteengroeve(int aantalSteengroeve) {
        this.aantalSteengroeve = aantalSteengroeve;
    }

    public int getAantalGoudmijn() {
        return aantalGoudmijn;
    }

    public void setAantalGoudmijn(int aantalGoudmijn) {
        this.aantalGoudmijn = aantalGoudmijn;
    }

    public int getAantalJachtgebied() {
        return aantalJachtgebied;
    }

    public void setAantalJachtgebied(int aantalJachtgebied) {
        this.aantalJachtgebied = aantalJachtgebied;
    }

    public int getAantalAkkerbouw() {
        return aantalAkkerbouw;
    }

    public void setAantalAkkerbouw(int aantalAkkerbouw) {
        this.aantalAkkerbouw = aantalAkkerbouw;
    }

    public int getAantalSmith() {
        return aantalSmith;
    }

    public void setAantalSmith(int aantalSmith) {
        this.aantalSmith = aantalSmith;
    }

    public int getAantalHut() {
        return aantalHut;
    }

    public void setAantalHut(int aantalHut) {
        this.aantalHut = aantalHut;
    }

    public int getTotaalScore() {
        return totaalScore;
    }

    public void setTotaalScore(int totaalScore) {
        this.totaalScore = totaalScore;
    }

    public int getHighscoreNr() {
        return highscoreNr;
    }

    public void setHighscoreNr(int highscoreNr) {
        this.highscoreNr = highscoreNr;
    }
    
    
}
