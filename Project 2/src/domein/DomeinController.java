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
        String resultaat = "";
        for (int i = 0; i < spelerRepository.getSpelerLijst().size(); i++) 
        {
            resultaat += spelerRepository.getSpeler(i).toString(spelerRepository.getSpeler(i));
        }
        return resultaat;
    }
    
    public Speler getSpeler(int index)
    {
        return spelerRepository.getSpeler(index);
    }
    
    public List<Speler> getSpelerLijst()
    {
        return spelerRepository.getSpelerLijst();
    }
    
    public void geefSpelersNamen(String naam, int aantal)
    {
        spelerRepository.geefSpelerNaam(naam, aantal);
    }
}