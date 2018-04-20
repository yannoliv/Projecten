package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class keuzeStage extends VBox{
    private DomeinController dc;
    private Stage stage;
    private ComboBox cbo_aantalSpelers = new ComboBox();
    private Label lbl_foutMelding = new Label();
    
    private Button btn_confirm = new Button();
    
    private int spelerNr = 0;
    private Label lbl_naamSpeler = new Label();
    private TextField txt_naamSpeler = new TextField();
    
    public keuzeStage(DomeinController dc, Stage stage) {
        this.dc = dc;
        this.stage = stage;
        vraagAantalSpelers();
    }

    public void confirm(ActionEvent ae){
        if (cbo_aantalSpelers.getValue().equals(cbo_aantalSpelers.getItems().get(0))) {
            getChildren().removeAll(cbo_aantalSpelers, btn_confirm);
            getChildren().addAll(cbo_aantalSpelers, lbl_foutMelding,btn_confirm);
        }
        else
        {
            String temp = (String) cbo_aantalSpelers.getValue();
            char keuze = temp.charAt(0);
            String res = "" + keuze;
            dc.doeAantalSpelersControle(res);
            getChildren().removeAll(cbo_aantalSpelers, lbl_foutMelding,btn_confirm);
            vraagNamenSpelers();
        }
    }
    
    public void confirmNaam(ActionEvent ae)
    {
        if (dc.doeNaamControle(spelerNr, txt_naamSpeler.getText())) {
            getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding,btn_confirm);
            spelerNr += 1;
            if (spelerNr < dc.getSpelerLijst().size())
            {
                txt_naamSpeler.clear();
                vraagNamenSpelers();
            }
            else
            {
                //ga verder
            }
        }
        else
        {
            txt_naamSpeler.clear();
            getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, btn_confirm);
            getChildren().addAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding, btn_confirm);
        }
    }
    
    public void vraagAantalSpelers()
    {
        ObservableList<String> options = FXCollections.observableArrayList("Aantal spelers", "2 spelers", "3 spelers", "4 spelers");
        cbo_aantalSpelers.setItems(options);
        cbo_aantalSpelers.getSelectionModel().selectFirst();
        lbl_foutMelding.setText("kies het aantal spelers!");
        btn_confirm.setText("Confirm");
        btn_confirm.setOnAction(this::confirm);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(cbo_aantalSpelers, new Insets(100,0,25,0));
        cbo_aantalSpelers.setMinWidth(250);
        btn_confirm.setMinWidth(250);
        cbo_aantalSpelers.setMinHeight(50);
        btn_confirm.setMinHeight(50);
        getChildren().addAll(cbo_aantalSpelers, btn_confirm);
    }
    
    
    public void vraagNamenSpelers()
    {
        stage.setTitle("Speler " + String.format("%d", spelerNr + 1));
        lbl_naamSpeler.setText("Naam speler " + String.format("%d", spelerNr + 1));
        lbl_foutMelding.setText("ongeldige naam");
        btn_confirm.setText("Confirm");
        btn_confirm.setOnAction(this::confirmNaam);
        lbl_naamSpeler.setAlignment(Pos.TOP_CENTER);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(txt_naamSpeler, new Insets(100,0,25,0));
        txt_naamSpeler.setMaxWidth(250);
        btn_confirm.setMinWidth(250);
        txt_naamSpeler.setMinHeight(50);
        btn_confirm.setMinHeight(50);
        getChildren().addAll(lbl_naamSpeler, txt_naamSpeler, btn_confirm);
    }
}