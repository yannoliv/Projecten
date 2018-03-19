package domein;

import gui.SpelApplicatie;
import java.util.*;

public class DomeinController
{
    
    SpelerRepository spelerRepository;
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
        String resultaat = "";
        for (int i = 0; i < spelerRepository.getSpelerLijst().size(); i++) 
        {
            resultaat += spelerRepository.neemSpeler(i).toString(spelerRepository.neemSpeler(i));
        }
        return resultaat;   
    }
    
    public List<Speler> getSpelerLijst()
    {
        return spelerRepository.getSpelerLijst();
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
    
    
    
}