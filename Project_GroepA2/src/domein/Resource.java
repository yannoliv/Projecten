package domein;

public class Resource
{
   private String naam;
   private int aantal;

   public Resource(String naam, int aantal)
   {
       setNaam(naam);
       setAantal(aantal);
   }
   
   public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    
   public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}