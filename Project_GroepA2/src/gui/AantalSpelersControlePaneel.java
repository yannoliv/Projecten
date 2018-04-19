
package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AantalSpelersControlePaneel extends VBox {
    
    //private AantalSpelersControlePaneel aantalSpelersPaneel;
    private NaamSpelersControlePaneel naamSpeler;
    private DomeinController dc;
    
    private int tempSpelerNr = 0;
    
    private final ComboBox cbo_aantalSpelers = new ComboBox();
    private Button btn_confirm = new Button("Confirm");
    private Label lbl_foutMelding = new Label("Kies iets anders");
    
    public AantalSpelersControlePaneel(DomeinController dc)
    {
        this.dc = dc;
        //this.setMaxSize(500, 500);
        ObservableList<String> options = FXCollections.observableArrayList("Aantal spelers","2","3","4");
        cbo_aantalSpelers.setItems(options);
        cbo_aantalSpelers.getSelectionModel().select(0);
        VBox.setMargin(cbo_aantalSpelers, new Insets(70, 50, 3, 50));
        lbl_foutMelding.setVisible(false);
        VBox.setMargin(lbl_foutMelding, new Insets(0,0,10,50));
        VBox.setMargin(btn_confirm, new Insets(0,0,0,75));
        btn_confirm.setOnAction(this::controleerNamenSpelers);
        cbo_aantalSpelers.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    //controleerNamenSpelers();
                }
            }
        });
        getChildren().addAll(cbo_aantalSpelers, lbl_foutMelding,btn_confirm);
    }
    
  
    
    private void controleerNamenSpelers(ActionEvent event)
    {
        if (cbo_aantalSpelers.getValue() == cbo_aantalSpelers.getItems().get(0)) {
            lbl_foutMelding.setVisible(true);
        }
        else
        {
            dc.doeAantalSpelersControle((String) cbo_aantalSpelers.getValue());
            dc.vulLijsten();
            
            NaamSpelersControlePaneel naamSpelerPaneel = new NaamSpelersControlePaneel(dc,tempSpelerNr,this);
            Scene scene = new Scene(naamSpelerPaneel, 500, 500);
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            //(dc.getSpelerLijst().size());
        }
    }
    
        public void naamConfirmed()
    {
        tempSpelerNr += 1;
        if (tempSpelerNr < dc.getSpelerLijst().size()) {
            getChildren().add(naamSpeler = new NaamSpelersControlePaneel(dc, tempSpelerNr, this));
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
    
}
