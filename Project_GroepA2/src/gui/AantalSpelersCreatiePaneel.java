package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AantalSpelersCreatiePaneel extends VBox {
    
    private HoofdPaneel hoofdpaneel;
    private DomeinController dc;
    
    private final ComboBox cbo_aantalSpelers = new ComboBox();
    private Button btn_confirm = new Button("Confirm");
    private Label lbl_foutMelding = new Label("Kies iets anders");
    
    public AantalSpelersCreatiePaneel(HoofdPaneel hoofdpaneel, DomeinController dc)
    {
        this.hoofdpaneel = hoofdpaneel;
        this.dc = dc;
        setPrefSize(225,250);
        ObservableList<String> options = FXCollections.observableArrayList("Aantal spelers","2","3","4");
        cbo_aantalSpelers.setItems(options);
        cbo_aantalSpelers.getSelectionModel().select(0);
        VBox.setMargin(cbo_aantalSpelers, new Insets(70, 50, 3, 50));
        lbl_foutMelding.setVisible(false);
        VBox.setMargin(lbl_foutMelding, new Insets(0,0,10,50));
        VBox.setMargin(btn_confirm, new Insets(0,0,0,75));
        btn_confirm.setOnAction(this::controleerNamenSpelers);
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
            hoofdpaneel.toonControleNamenSpelers(dc.getSpelerLijst().size());
        }
    }
    
}

