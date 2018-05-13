package domein;

import Persistentie.SpelerMapper;
import gui.KaartPaneel;
import java.util.ArrayList;
import java.util.List;


public class DomeinController 
{

    private Spel spel;
    private SpelerMapper spelerMapper;
    private KaartPaneel kaartPaneel;
    
    
    public DomeinController()
    {
        spel = new Spel();
        spelerMapper = new SpelerMapper();
    }
    
    public boolean doeAantalSpelersControle(String aantal)
    {
        return spel.aantalSpelersControle(aantal);
    }

    public boolean doeNaamControle(int spelerNr, String keuze)
    {
        return spel.naamControle(spelerNr, keuze);
    }

    public void vulLijsten(boolean demo)
    {
        if (demo == false) {
            spel.geefSpelersResources();
            spel.vulPlaatsLijst();
            spel.vulHuttenLijst1(7);
            spel.vulHuttenLijst2(7);
            spel.vulHuttenLijst3(7);
            spel.vulHuttenLijst4(7);
        }
        else
        {
            spel.geefSpelersResourcesDemo();
            spel.vulPlaatsLijst();
            spel.vulHuttenLijst1(2);
            spel.vulHuttenLijst2(2);
            spel.vulHuttenLijst3(2);
            spel.vulHuttenLijst4(1);
        }
        
    }
    
    public void vuldbLijsten(int[] lijst)
    {
        spel.vulResourceLijst();
        spel.vulPlaatsLijst();
        spel.vulHuttenLijst1(lijst[0]);
        spel.vulHuttenLijst2(lijst[1]);
        spel.vulHuttenLijst3(lijst[2]);
        spel.vulHuttenLijst4(lijst[3]);
    }
    
    public void vulSpelerLijst(int aantal)
    {
        spel.vulSpelerLijst(aantal);
    }
    public void geefSpelersResources()
    {
        spel.geefSpelersResources();
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

    public void doeTrekResourcesAf(int spelerNr, int lijstNr)
   {
    spel.trekResourcesAf(spelerNr, lijstNr);
    }
    
    public void doeVerwijderKaart(int lijstNr)
    {
        spel.verwijderKaart(lijstNr);
    }

    public void doeResetSpelerZet(int spelerNr)
    {
        spel.resetSpelerZet(spelerNr);
    }

    public boolean getResourcesChecked(int spelerNr, int lijstNr)
    {
        return spel.checkResources(spelerNr, lijstNr);
    }

    public void doePlaatsOpPlek(int spelerNr, int keuzeNr, int aantalStamleden)
    {
        spel.plaatsOpPlek(spelerNr, keuzeNr, aantalStamleden);
    }

    public void doePlaatsOpPlekGUI(int spelerNr, int keuzeNr, int aantalStamleden)
    {
        spel.plaatsOpPlekGUI(spelerNr, keuzeNr, aantalStamleden);
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

  public List<Plaats> getHuttenLijst1()
  {
    return spel.getHuttenLijst1();
  }

  public List<Plaats> getHuttenLijst2()
  {
    return spel.getHuttenLijst2();
  }

  public List<Plaats> getHuttenLijst3()
  {
    return spel.getHuttenLijst3();
  }

  public List<Plaats> getHuttenLijst4()
  {
    return spel.getHuttenLijst4();
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
  public SpelerMapper getSpelerMapper()
{
    return this.spelerMapper;
}

  public void setSpelerLijst(List<Speler> spelerLijst)
  {
      spel.setSpelerLijst(spelerLijst);
  }

  public void setAantalSpelers(int aantalSpelers)
  {
      spel.setAantalSpelers(aantalSpelers);
  }
  
  public void setKaartPaneel(KaartPaneel kaartpaneel)
  {
      this.kaartPaneel = kaartpaneel;
  }
  
  public KaartPaneel getKaartpaneel()
  {
      return this.kaartPaneel;
  }
  
  public void doeLichtAan(int kaart)
  {
      getKaartpaneel().doeLichtUit(kaart);
  }
      
}
