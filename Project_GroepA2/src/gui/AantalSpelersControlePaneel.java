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
    
    private Label lbl_aantalSpelers;
    private TextField txt_aantalSpelers;
    private Label lbl_foutMelding;
    
    
    public AantalSpelersControlePaneel(HoofdPaneel hoofdpaneel)
    {
        this.hoofdpaneel = hoofdpaneel;
        setPrefSize(400,400);
        
        //Alle items in de scene
        lbl_aantalSpelers = new Label();
        lbl_aantalSpelers.setText("Aantal spelers:");
        lbl_aantalSpelers.setLabelFor(txt_aantalSpelers);
        GridPane.setRowIndex(lbl_aantalSpelers, 0);
        GridPane.setColumnIndex(lbl_aantalSpelers, 0);
        
        txt_aantalSpelers = new TextField();
        GridPane.setRowIndex(txt_aantalSpelers, 0);
        GridPane.setColumnIndex(txt_aantalSpelers, 1);
        
        lbl_foutMelding = new Label();
        lbl_foutMelding.setText("Incorrect");
        lbl_foutMelding.setVisible(false);
        GridPane.setRowIndex(lbl_foutMelding, 1);
        GridPane.setColumnIndex(lbl_foutMelding, 1);
        
        Button controleerAantalSpelers = new Button("Confirm");
        GridPane.setRowIndex(controleerAantalSpelers, 1);
        GridPane.setColumnIndex(controleerAantalSpelers, 0);
        controleerAantalSpelers.setOnAction(this::controleerAantalSpelers);
        
        getChildren().addAll(lbl_aantalSpelers, txt_aantalSpelers, lbl_foutMelding, controleerAantalSpelers);
    }
    
    private void controleerAantalSpelers(ActionEvent event)
    {
        String antw = txt_aantalSpelers.getText();
        if (dc.doeAantalSpelersControle(antw) == true)
        {
            hoofdpaneel.toonControleNamenSpelers();
        }
        else
        {
            lbl_foutMelding.setVisible(true);
        }
    }
    
}