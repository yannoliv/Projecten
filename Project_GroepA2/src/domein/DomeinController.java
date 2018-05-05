package domein;

import java.util.List;


public class DomeinController 
{

    private Spel spel;
    
    public DomeinController()
    {
        spel = new Spel();
    }
    
    public boolean doeAantalSpelersControle(String aantal)
    {
        return spel.aantalSpelersControle(aantal);
    }

    public boolean doeNaamControle(int spelerNr, String keuze)
    {
        return spel.naamControle(spelerNr, keuze);
    }

    public void vulLijsten()
    {
        spel.geefSpelersResources();
        spel.vulPlaatsLijst();
        spel.vulHuttenLijst();
    }

    public void doeSpelerFix(int ronde)
    {
        spel.spelerFix(ronde);
    }

    public String getEindeRondeBericht()
    {
        return spel.eindeRondeBericht();
    }

    public void doeResetPlaatsenLijst()
    {
        spel.resetPlaatsenLijst();
    }

    public void doeTrekResourcesAf(int spelerNr, int hutNummer)
   {
    spel.trekResourcesAf(spelerNr, hutNummer);
    }
        
        public void doeResetSpelerZet(int spelerNr)
        {
            spel.resetSpelerZet(spelerNr);
        }
        
        public boolean getResourcesChecked(int spelerNr, int hutNummer)
        {
            return spel.checkResources(spelerNr, hutNummer);
        }
        
        public void doePlaatsOpPlek(int spelerNr, int keuzeNr, int aantalStamleden)
        {
            spel.plaatsOpPlek(spelerNr, keuzeNr, aantalStamleden);
        }
        
        public String getBedieningsPaneel(int spelerNr)
        {
            return spel.bedieningsPaneel(spelerNr);
        }
        
        public int getGeroldGetal(int aantalStamleden)
        {
            return spel.dobbelStenen(aantalStamleden);
        }
         
       public String getToonSpelers()
       {
           return spel.toonSpelers();
       }
        
      public List<Plaats> getHuttenLijst()
      {
        return spel.getHuttenLijst();
      }
       
      public List<Speler> getSpelerLijst()
      {
        return spel.getSpelerLijst();
      }
      
      public List<Plaats> getPlaatsenLijst()
      {
        return spel.getPlaatsenLijst();
      }
      
      public int getHuidigeSpeler()
      {
          return spel.getSpelerBeurt();
      }
      
      public void setHuidigeSpeler(int spelerBeurt)
      {
          spel.setSpelerBeurt(spelerBeurt);
      }
      
      public boolean doeResourcesControleSpeler(int spelerNr, int tekortVoedsel)
      {
          return spel.checkResourcesStraf(spelerNr, tekortVoedsel);
      }
      
      public void doeResetLijsten()
      {
          spel.resetLijsten();
      }
}
