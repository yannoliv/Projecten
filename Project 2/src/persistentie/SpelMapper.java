package persistentie;
import domein.*;
import java.util.*;

public class SpelMapper
{
   public List<Speler> vulSpelerLijst(int aantal)
   {
       List<Speler> spelers = new ArrayList<>();
       for (int i = 0; i < aantal; i++) {
           spelers.add(new Speler(i));
       }
       return spelers;
   }
}
    
    