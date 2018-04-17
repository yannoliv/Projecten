package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AantalSpelersControlePaneel extends GridPane {
    
    private HoofdPaneel hoofdpaneel;
    private DomeinController dc;
    
    public boolean aantalSpelersOK;
    public int aantalSpelers;
    
    private Label lbl_aantalSpelers;
    private TextField txt_aantalSpelers;
    private Label lbl_foutMelding;
    
    
    public AantalSpelersControlePaneel(HoofdPaneel hoofdpaneel, DomeinController dc)
    {
        //de scene
        this.hoofdpaneel = hoofdpaneel;
        this.dc = dc;
        setPrefSize(400,400);
        
        //Het label
        lbl_aantalSpelers = new Label();
        lbl_aantalSpelers.setText("Aantal spelers:");
        lbl_aantalSpelers.setLabelFor(txt_aantalSpelers);
        GridPane.setConstraints(lbl_aantalSpelers, 0, 0);
        
        //textbox
        txt_aantalSpelers = new TextField();
        GridPane.setRowIndex(txt_aantalSpelers, 0);
        GridPane.setColumnIndex(txt_aantalSpelers, 1);
        
        
        //het label
        lbl_foutMelding = new Label();
        lbl_foutMelding.setText("Incorrect");
        lbl_foutMelding.setVisible(false);
        GridPane.setRowIndex(lbl_foutMelding, 1);
        GridPane.setColumnIndex(lbl_foutMelding, 1);
        
        //de button
        Button btn_gaVerder = new Button("Ga verder");
        GridPane.setRowIndex(btn_gaVerder, 1);
        GridPane.setColumnIndex(btn_gaVerder, 0);
        btn_gaVerder.setOnAction(this::controleerAantalSpelers);
        
        
        //het paneel geven
        this.getChildren().addAll(lbl_aantalSpelers, txt_aantalSpelers, lbl_foutMelding, btn_gaVerder);
    }
    
    private void controleerAantalSpelers(ActionEvent event)
    {
        String antw = txt_aantalSpelers.getText();
        if (dc.doeAantalSpelersControle(antw))
        {
            aantalSpelersOK = true;
            aantalSpelers = Integer.parseInt(txt_aantalSpelers.getText());
        }
        else
        {
            lbl_foutMelding.setVisible(true);
        }
    }
    
    public int getAantalSpelers()
    {
        return aantalSpelers;
    }
    
}