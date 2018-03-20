package domein;

public class Plaats
{
   private String naam;
   private Resource typeResource;
   private int deler;
   private int aantalSpots;

   
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
   
}
