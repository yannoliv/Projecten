package domein;

import gui.SpelApplicatie;
import java.util.ArrayList;
import java.util.List;
import persistentie.SpelMapper;



public class DomeinController
{
    
    SpelerRepository spelerRepository;
    SpelMapper spelMapper;
    SpelApplicatie spel;
    
    public DomeinController()
    {
        
        spelerRepository = new SpelerRepository();
    }
    
    public void maakRepository(int aantal, DomeinController dc)
    {
        spelerRepository = new SpelerRepository(aantal, dc);
    }
    
    public List<Speler> geefSpelers()
    {
        return spelerRepository.getSpelers();
    }
    
    
    
    public void setSpelerNaam(String naam, Speler speler) // kan zijn dat dit ergens anders moet
    {
        speler.setNaam(naam);
    }
    
    public Speler maakSpelerAan(List<Speler> spelers)//wordt aangeroepen door spelmapper
    {
        Speler speler;
        speler = spel.maakSpelerAan(spelers);
        return speler;
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