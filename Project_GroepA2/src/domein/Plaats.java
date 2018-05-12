package domein;

public class Plaats
{
   private String naam;
   private Resource typeResource;
   private int deler;
   private int aantalSpots;
   private int punten;
   private int hutNummer;
   private String resource1;
   private String resource2;
   private String resource3;
   private int aantalResource1;
   private int aantalResource2;
   private int aantalResource3;

   
   public Plaats(String naam, Resource resourceType, int deler, int aantalSpots)
   {
       setNaam(naam);
       setTypeResource(resourceType);
       setDeler(deler);
       setAantalSpots(aantalSpots);
   }
   
   public Plaats(String naam, Resource resourceType, int aantalSpots)
   {
       setNaam(naam);
       setTypeResource(resourceType);
       setAantalSpots(aantalSpots);
   }
   
   public Plaats(int hutNummer,int punten, String resourceNaam1, int aantalResource1, String resourceNaam2, int aantalResource2, String resourceNaam3, int aantalResource3, int aantalSpots)
   {
       setHutNummer(hutNummer);
       setPunten(punten);
       setResource1(resourceNaam1);
       setResource2(resourceNaam2);
       setResource3(resourceNaam3);
       setAantalResource1(aantalResource1);
       setAantalResource2(aantalResource2);
       setAantalResource3(aantalResource3);
       setAantalSpots(aantalSpots);
   }
   
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Resource getTypeResource() {
        return typeResource;
    }

    public void setTypeResource(Resource typeResource) {
        this.typeResource = typeResource;
    }

    public int getDeler() {
        return deler;
    }

    public void setDeler(int deler) {
        this.deler = deler;
    }

    public int getAantalSpots() {
        return aantalSpots;
    }

    public void setAantalSpots(int aantalSpots) {
        this.aantalSpots = aantalSpots;
    }
    
     public int getPunten() {
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }
    
    public String getResource1() {
        return resource1;
    }

    public void setResource1(String resource1) {
        this.resource1 = resource1;
    }

    public String getResource2() {
        return resource2;
    }

    public void setResource2(String resource2) {
        this.resource2 = resource2;
    }

    public String getResource3() {
        return resource3;
    }

    public void setResource3(String resource3) {
        this.resource3 = resource3;
    }

    public int getAantalResource1() {
        return aantalResource1;
    }

    public void setAantalResource1(int aantalResource1) {
        this.aantalResource1 = aantalResource1;
    }

    public int getAantalResource2() {
        return aantalResource2;
    }

    public void setAantalResource2(int aantalResource2) {
        this.aantalResource2 = aantalResource2;
    }

    public int getAantalResource3() {
        return aantalResource3;
    }

    public void setAantalResource3(int aantalResource3) {
        this.aantalResource3 = aantalResource3;
    }

    public int getHutNummer() {
        return hutNummer;
    }

    public void setHutNummer(int hutNummer) {
        this.hutNummer = hutNummer;
    }
    
}
