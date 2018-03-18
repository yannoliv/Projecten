package domein;

import java.util.List;
import persistentie.SpelMapper;



public class SpelerRepository 
{
    
    SpelMapper spelMapper;
    private final List<Speler> spelerLijst;

    
    public SpelerRepository(int aantal, DomeinController dc)// wordt aangeroepen door domeincontroller
    {
        spelMapper = new SpelMapper();
        spelerLijst = spelMapper.vulSpelerLijst(aantal);
    }
    
    public Speler getSpeler(int index)
    {
        return spelerLijst.get(index);
    }
    
    public void geefSpelerNaam(String naam, int index)
    {
        getSpeler(index).setNaam(naam);
    }
    
    public List<Speler> getSpelerLijst() {
        return spelerLijst;
    }
    
}