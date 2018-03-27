package domein;

import java.util.List;


public class DomeinController 
{

    private Spel spel;
    
    public DomeinController()
    {
        spel = new Spel();
    }
//------------------------------------------------------------------   
//New
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
        
        public void doeResetSpelerZet()
        {
            spel.resetSpelerZet();
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
}


//recyclage later
//verhoogt het aantal hout van de speler door zijn voorig aantal op te halen en te verhogen met het gekregen hout
//                getSpelerLijst().get(spelerNr).getResourceLijst().get(0).setAantal(getSpelerLijst().get(spelerNr).getResourceLijst().get(0).getAantal() 
//                        + (int) Math.floor(((double) spel.dobbelStenen(aantalStamleden))/(double) getPlaatsenLijst().get(0).getDeler()));