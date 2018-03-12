package domein;

import java.security.SecureRandom;

public class Speler 
{
    //atributen
    private String naam;
    private int goud;
    private int hout;
    private int steen;
    private int leem;
    private int voedsel;
    private int akkerbouw;
    private int gereedschap;
    private int punten;
    private int stamlid;
    private final String[] kleurLijst = {"rood","blauw","geel","groen"};
    private String kleur;
    public int spelerNummer= 0;
    private int maxStamleden;
    private int aantalStamleden = 0;
    private int geroldGetal = 0;
    boolean gebruikteHout = false;
    boolean gebruikteLeem = false;
    boolean gebruikteSteen = false;
    boolean gebruikteGoud = false;
    boolean gebruikteVoedsel = false;
    boolean gebruikteHut = false;
    boolean heeftGereedschap = false;

    public boolean getHeeftGereedschap() {
        return heeftGereedschap;
    }

    public void setHeeftGereedschap(boolean heeftGereedschap) {
        this.heeftGereedschap = heeftGereedschap;
    }
  

   
    SecureRandom random = new SecureRandom();
    
    //constructor
    public Speler(int spelerNummer)
    {
        setSpelerNummer(spelerNummer);
        setHout(0);
        setLeem(0);
        setSteen(0);
        setGoud(0);
        setAkkerbouw(0);
        setGereedschap(0);
        setVoedsel(12);
        setStamlid(5);
        setMaxStamleden(5);
        setPunten(0);        
        setKleur(kleurLijst[spelerNummer]); // speler 1 -1 wordt 0 dus rood
        setAantalStamleden(0);
    }

   
   
    
    //getters en setters
    
    //Naam
    public String getNaam() {
        return naam;
    }

    public final void setNaam(String naam)
    {
        this.naam = naam;
    }
    
    
    //Kleur
    public String getKleur() {
        return kleur;
    }

    private void setKleur(String kleur) {
        this.kleur = kleur;
    }
    
    
    //Spelernummer
    public int getSpelerNummer() {
        return spelerNummer;
    }

    private void setSpelerNummer(int spelerNummer) {
        this.spelerNummer = spelerNummer;
    }
    
    
    //Voedsel
    public int getVoedsel() {
        return voedsel;
    }

    
    public void setVoedsel(int voedsel)//setvoedsel is de setter
    {
        this.voedsel = voedsel;
    }
    
    public void harvestVoedsel(int aantalStamleden) 
    {
        this.voedsel += dobbelsteen()/2;
    }
    
    
    //Stamlid
    public int getStamlid() {
        return stamlid;
    }

    public void setStamlid(int stamlid) {
        this.stamlid = stamlid;
    }
    
    
    //Hout
    public int getHout() {
        return hout;
    }

    public void setHout(int aantalStamleden) 
    {
        this.hout += dobbelsteen()/3;
    }
    
    
    //Leem
    public int getLeem() {
        return leem;
    }

    public void setLeem(int aantalStamleden) 
    {
        this.leem += dobbelsteen()/4;
    }
    
    
    //Steen
    public int getSteen() {
        return steen;
    }

    public void setSteen(int aantalStamleden) {
        this.steen += dobbelsteen()/5;
    }
    
    
    //Goud
    public int getGoud() {
        return goud;
    }
    
    
    public void setGoud(int aantalStamleden) 
    {
        this.goud += dobbelsteen()/6;
    }
    
    
    //Akkerbouw
    public int getAkkerbouw() {
        return akkerbouw;
    }

    public void setAkkerbouw(int akkerbouw) 
    {
        this.akkerbouw = akkerbouw;
    }

    
    //Gereedschap
    public int getGereedschap() {
        return gereedschap;
    }

    public void setGereedschap(int gereedschap) 
    {
        this.gereedschap = gereedschap;
    }

    
    //Punten
    public int getPunten() {
        return punten;
    }

    private void setPunten(int punten) {
        this.punten = punten;
    }

    
    //Gerold getal
    public int getGeroldGetal()
    {
        return geroldGetal;
    }

    public int getMaxStamleden() {
        return maxStamleden;
    }

    public void setMaxStamleden(int maxStamleden) {
        this.maxStamleden = maxStamleden;
    }
    
    
    //Geplaatste plekken
      public boolean getGebruikteHout() {
        return gebruikteHout;
    }

    public void setGebruikteHout(boolean gebruikteHout) {
        this.gebruikteHout = gebruikteHout;
    }

    public boolean getGebruikteLeem() {
        return gebruikteLeem;
    }

    public void setGebruikteLeem(boolean gebruikteLeem) {
        this.gebruikteLeem = gebruikteLeem;
    }

    public boolean getGebruikteSteen() {
        return gebruikteSteen;
    }

    public void setGebruikteSteen(boolean gebruikteSteen) {
        this.gebruikteSteen = gebruikteSteen;
    }

    public boolean getGebruikteGoud() {
        return gebruikteGoud;
    }

    public void setGebruikteGoud(boolean gebruikteGoud) {
        this.gebruikteGoud = gebruikteGoud;
    }

    public boolean getGebruikteVoedsel() {
        return gebruikteVoedsel;
    }

    public void setGebruikteVoedsel(boolean gebruikteVoedsel) {
        this.gebruikteVoedsel = gebruikteVoedsel;
    }
    
    public boolean getGebruikteHut() {
        return gebruikteHut;
    }

    public void setGebruikteHut(boolean gebruikteHut) {
        this.gebruikteHut = gebruikteHut;
    }
    
    //aantal gebruikte stamleden bijhouden bij elke speler
     public int getAantalStamleden() {
        return aantalStamleden;
    }

    public void setAantalStamleden(int aantalStamleden) {
        this.aantalStamleden = aantalStamleden;
    }
    
     public void toonStamleden(Speler speler)
    {
        System.out.printf("%n%s heeft nu nog %d %s.",speler.getNaam(),speler.getStamlid(), speler.getStamlid() == 1 ? "stamlid":"stamleden");
    }
    
     private int dobbelsteen()
     {
        for (int i = 0; i < aantalStamleden; i++) 
        {
            geroldGetal += (int) Math.floor(((random.nextInt(6) + 1)));
        }
        return geroldGetal;
     }
}

//