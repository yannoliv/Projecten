package domein;

import gui.SpelApplicatie;
import java.util.List;
import persistentie.SpelMapper;



public class DomeinController
{
    
    SpelerRepository spelerRepository;
    SpelMapper spelMapper;
    SpelApplicatie spel;
    
    public DomeinController()
    {
        spel = new SpelApplicatie(this);   //hier komt de methode da alles start
        
    }
    
    public void maakSpelersAan(int aantal, DomeinController dc)
    {
        spelerRepository = new SpelerRepository(aantal, dc);
        System.out.printf("%nNieuw spel is gestart...%n"); 
    }
    
    
    public String toonSpelers()
    {
        return spelerRepository.getSpelerLijst().toString();
    }
    
    public void geefSpelersNamen(String naam, Speler speler)
    {
        spelerRepository.setSpelerNaam(naam, speler);
    }
    
    public SpelerRepository getSpelerRepository() {
        return spelerRepository;
    }

    public void setSpelerRepository(SpelerRepository spelerRepository) {
        this.spelerRepository = spelerRepository;
    }

    public SpelApplicatie getSpel() {
        return spel;
    }

    public void setSpel(SpelApplicatie spel) {
        this.spel = spel;
    }
    
    
    
    
    
    
    
    
}