
package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class Scorebord extends Pane 
{
    
    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;
    
    //VBox vbox = new VBox(8); //spacing 8
    private Label lbl_spelerBord;
    
    public Scorebord(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {
        
        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;
        
        
        
        //lbl_spelerBord = new Label();
        //vbox.getChildren().add(new Label("eee"));
            
        VBox vbox = new VBox();
        //ListView list = new ListView();
        //VBox.setVgrow(list, Priority.ALWAYS);
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
        "Single", "Double", "Suite", "Family App");
        list.setItems(items);
        vbox.getChildren().addAll(new Label("Names:"), list);
        
        vbox.setPrefWidth(400);
    }
    
}
