
package gui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class MapSpel extends Pane 
{
    public MapSpel()
    {
        
        buildGUI();
        
    }
    
    private void buildGUI()
    {
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        //new BackgroundSize(USE_PREF_SIZE, USE_PREF_SIZE, true, true, true, true)
        
        BackgroundImage Achtergrond = new BackgroundImage(new Image("/images/mapSpel.png"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        //png_spelMap = new ImageView(new Image(getClass().getResourceAsStream("/images/mapSpel.png")));
        
        this.setBackground(new Background(Achtergrond));
    }
}
