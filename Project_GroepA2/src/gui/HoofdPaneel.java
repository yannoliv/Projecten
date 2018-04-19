package gui;

import domein.DomeinController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class HoofdPaneel extends VBox
{
    private DomeinController dc;
    private int tempSpelerNr = 0;
    
    private AantalSpelersControlePaneel aantalSpelers;
    private NaamSpelersControlePaneel naamSpeler;
    private SpelApplicatiePaneel spelApplicatiePaneel;
    
    
    public HoofdPaneel(DomeinController dc){
        this.dc = dc;
        naamSpeler = new NaamSpelersControlePaneel(this, dc, tempSpelerNr);
        setSpacing(10);
        aantalSpelers = new AantalSpelersControlePaneel(this, dc);
        this.setPrefSize(500, 500);
        getChildren().add(aantalSpelers);
    }
    
    public void toonControleNamenSpelers(int aantal){
        getChildren().remove(aantalSpelers);
        getChildren().add(naamSpeler);
    }
    
    public void naamConfirmed()
    {
        getChildren().remove(naamSpeler);
        tempSpelerNr += 1;
        if (tempSpelerNr < dc.getSpelerLijst().size()) {
            getChildren().add(naamSpeler = new NaamSpelersControlePaneel(this, dc, tempSpelerNr));
        }
        else
        {
            System.out.println(dc.getToonSpelers());
            spelApplicatiePaneel = new SpelApplicatiePaneel(this,dc);
            getChildren().add(spelApplicatiePaneel);
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
}
    
    
    