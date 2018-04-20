package gui;

import domein.DomeinController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NaamSpelersControlePaneel extends VBox 
{
    //private NaamSpelersControlePaneel naamSpeler;
    private SpelApplicatiePaneel spelApplicatiePaneel;
    private AantalSpelersControlePaneel aantalSpelerPaneel;
    private NaamSpelersControlePaneel naamSpelerPaneel;
    private DomeinController dc;
    
    private int tempSpelerNr = 0;
    
    private Label lbl_spelerNummer;
    private Label lbl_foutMelding = new Label("Voer een geldige naam in");
    private Label lbl_naamSpeler = new Label("Naam:");
    private TextField txt_naamSpeler;
    private Button btn_confirm = new Button("Confirm");
            
    public NaamSpelersControlePaneel(DomeinController dc, int spelerNr, AantalSpelersControlePaneel aantalSpelerPaneel)
    {
        this.dc = dc;
        this.aantalSpelerPaneel = aantalSpelerPaneel;
        
        setPrefSize(300,500);
        
        lbl_spelerNummer  = new Label("Naam speler " + String.format("%d", spelerNr));
        txt_naamSpeler = new TextField();
        txt_naamSpeler.setPromptText("voer hier uw naam in");
        this.standaardTextfieldClear(txt_naamSpeler);
        
        VBox.setMargin(lbl_spelerNummer, new Insets(25,75,10,30));
        VBox.setMargin(lbl_naamSpeler, new Insets(60,10,3,25));
        VBox.setMargin(txt_naamSpeler, new Insets(0,30,3,25));
        VBox.setMargin(lbl_foutMelding, new Insets(0,30,3,25));
        lbl_foutMelding.setVisible(false);
        VBox.setMargin(btn_confirm, new Insets(0,30,10,50));
        btn_confirm.setOnAction(this::buttonPushed);
        txt_naamSpeler.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    //buttonPushed();
                }
            }
        });
        getChildren().addAll(lbl_spelerNummer, lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding, btn_confirm);
    }

    private void buttonPushed(ActionEvent event) 
    {
        if (txt_naamSpeler.getText().isEmpty()) {
            lbl_foutMelding.setVisible(true);
        }
        else
        {
            if (dc.doeNaamControle(tempSpelerNr, txt_naamSpeler.getText())) 
            {
                this.naamConfirmed();
            }
            else
            {
                lbl_foutMelding.setText("naam is reeds genomen");
                lbl_foutMelding.setVisible(true);
            }            
        }
        
    }
    public void standaardTextfieldClear(TextField textfield)
    {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        textfield.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                this.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
    }
    
    public void naamConfirmed()
    {
        tempSpelerNr += 1;
        if (tempSpelerNr < dc.getSpelerLijst().size()) 
        {
            dc.doeNaamControle(tempSpelerNr, txt_naamSpeler.getText());
            this.resetForm();
        }
        else
        {
            System.out.println(dc.getToonSpelers());
            SpelApplicatiePaneel spelAppPaneel = new SpelApplicatiePaneel(dc);
            Scene scene = new Scene(spelAppPaneel, 500, 500);
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        
        
        
        
    }
    
    public void resetForm()
    {
        lbl_foutMelding.setVisible(false);
        txt_naamSpeler.clear();
        
    }
    
    
}

    /*
    private void confirmNaam(ActionEvent event)
    {
        if (txt_naamSpeler.getText().isEmpty()) {
            lbl_foutMelding.setVisible(true);
        }
        else
        {
            if (dc.doeNaamControle(spelerNr, txt_naamSpeler.getText())) {
                this.naamConfirmed();
            }
            else
            {
                lbl_foutMelding.setText("naam is reeds genomen");
                lbl_foutMelding.setVisible(true);
            }            
        }
    }
    */
