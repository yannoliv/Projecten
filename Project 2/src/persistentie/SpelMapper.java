package persistentie;

import domein.DomeinController;
import domein.Speler;
import java.util.List;

public class SpelMapper
{
    //wordt aangeroepen door repository
    public List<Speler> maakSpelersAan(int aantalSpelers, List<Speler> spelers, DomeinController dc)
    {
        for (int i = 0; i < aantalSpelers; i++) {
            spelers.add(dc.maakSpelerAan(spelers));
        }
        return spelers;
    }
}
    
    