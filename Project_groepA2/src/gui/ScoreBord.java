package gui;

import domein.DomeinController;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ScoreBord extends VBox
{
    private DomeinController dc;
    private Stage stage;
    private MainMenu mainMenu;
    
    public ScoreBord(DomeinController dc, Stage stage, MainMenu mainMenu) {
        this.dc = dc;
        this.stage = stage;
        this.mainMenu = mainMenu;
       
}
}