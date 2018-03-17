package domein;

import java.util.List;
import persistentie.SpelMapper;



public class SpelerRepository 
{
    
    private SpelMapper spelMapper;
    private List<Speler> spelers;
    
    public SpelerRepository()
    {
        spelMapper = new SpelMapper();
    }
    
    public SpelerRepository(int aantalSpelers, DomeinController dc)// wordt aangeroepen door domeincontroller
    {
        spelMapper = new SpelMapper();
        spelers = spelMapper.maakSpelersAan(aantalSpelers, spelers, dc);
    }
    
    public List<Speler> getSpelers()
    {
        return spelers;
    }

    public SpelMapper getSpelMapper() {
        return spelMapper;
    }

    
    
}