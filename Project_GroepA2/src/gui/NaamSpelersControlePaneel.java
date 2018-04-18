
package gui;

import domein.DomeinController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class NaamSpelersControlePaneel extends VBox {
    private HoofdPaneel hoofdpaneel;
    private DomeinController dc;
    private int spelerNr;
    private Label lbl_spelerNummer;
    private Label lbl_foutMelding = new Label("Voer een geldige naam in");
    private Label lbl_naamSpeler = new Label("Naam:");
    private TextField txt_naamSpeler;
    private Button btn_confirm = new Button("Confirm");
            
    public NaamSpelersControlePaneel(HoofdPaneel hoofdpaneel, DomeinController dc, int spelerNr)
    {
        this.hoofdpaneel = hoofdpaneel;
        this.dc = dc;
        this.spelerNr = spelerNr;
        setPrefSize(225,250);
        
        lbl_spelerNummer  = new Label("Naam speler " + String.format("%d", spelerNr));
        txt_naamSpeler = new TextField();
        txt_naamSpeler.setPromptText("voer hier uw naam in");
        hoofdpaneel.standaardTextfieldClear(txt_naamSpeler);
        
        VBox.setMargin(lbl_spelerNummer, new Insets(25,75,10,30));
        VBox.setMargin(lbl_naamSpeler, new Insets(60,10,3,25));
        VBox.setMargin(txt_naamSpeler, new Insets(0,30,3,25));
        VBox.setMargin(lbl_foutMelding, new Insets(0,30,3,25));
        lbl_foutMelding.setVisible(false);
        VBox.setMargin(btn_confirm, new Insets(0,30,10,50));
        btn_confirm.setOnAction(this::confirmNaam);
        txt_naamSpeler.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    confirmNaam();
                }
            }
        });
        getChildren().addAll(lbl_spelerNummer, lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding, btn_confirm);
    }
    
    private void confirmNaam()
    {
        if (txt_naamSpeler.getText().isEmpty()) {
            lbl_foutMelding.setVisible(true);
        }
        else
        {
            if (dc.doeNaamControle(spelerNr, txt_naamSpeler.getText())) {
                hoofdpaneel.naamConfirmed();
            }
            else
            {
                lbl_foutMelding.setText("naam is reeds genomen");
                lbl_foutMelding.setVisible(true);
            }            
        }
    }
    
    private void confirmNaam(ActionEvent event)
    {
        if (txt_naamSpeler.getText().isEmpty()) {
            lbl_foutMelding.setVisible(true);
        }
        else
        {
            if (dc.doeNaamControle(spelerNr, txt_naamSpeler.getText())) {
                hoofdpaneel.naamConfirmed();
            }
            else
            {
                lbl_foutMelding.setText("naam is reeds genomen");
                lbl_foutMelding.setVisible(true);
            }            
        }
    }
    
}
