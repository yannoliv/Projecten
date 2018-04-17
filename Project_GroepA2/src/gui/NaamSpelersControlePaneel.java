
package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NaamSpelersControlePaneel extends StackPane {
    
    private HoofdPaneel hoofdpaneel;
    
    private Label lbl_naamSpeler_1;
    private TextField txt_naamSpeler_1;
    private Label lbl_naamSpeler_2;
    private TextField txt_naamSpeler_2;
    private Label lbl_naamSpeler_3;
    private TextField txt_naamSpeler_3;
    private Label lbl_naamSpeler_4;
    private TextField txt_naamSpeler_4;
    
    public NaamSpelersControlePaneel(HoofdPaneel hoofdpaneel, int aantalSpelers)
    {
        //het paneel
        this.hoofdpaneel = hoofdpaneel;
        setPrefSize(400,800);
        
        lbl_naamSpeler_1 = new Label("Naam speler 1");
        txt_naamSpeler_1 = new TextField("");
        
        lbl_naamSpeler_2 = new Label("Naam speler 2");
        txt_naamSpeler_2 = new TextField("");
            
        lbl_naamSpeler_3 = new Label("Naam speler 3");
        txt_naamSpeler_3 = new TextField("");
            
        lbl_naamSpeler_4 = new Label("Naam speler 4");
        txt_naamSpeler_4 = new TextField("");
        
        
        //de button
        Button btn_controleerAantalSpelers = new Button("Ga verder");
        btn_controleerAantalSpelers.setOnAction(this::controleerNamenSpelers);
        
        switch(aantalSpelers)
        {
            case 2:
                getChildren().addAll(lbl_naamSpeler_1, txt_naamSpeler_1, btn_controleerAantalSpelers);
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    
    private void controleerNamenSpelers(ActionEvent event)
    {
        
    }
}
