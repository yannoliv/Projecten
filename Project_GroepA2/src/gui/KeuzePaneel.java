package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KeuzePaneel extends VBox{
    private DomeinController dc;
    private Stage stage;
    private MainMenu mainMenu;
    
    private ComboBox cbo_aantalSpelers = new ComboBox();
    private Label lbl_foutMelding = new Label();
    private int temp = 0;
    private Button btn_confirm = new Button();
    private Button btn_back = new Button();
    
    private int spelerNr = 0;
    private Label lbl_naamSpeler = new Label();
    private TextField txt_naamSpeler = new TextField();
    
    public KeuzePaneel(DomeinController dc, Stage stage, MainMenu mainMenu) {
        this.dc = dc;
        this.stage = stage;
        this.mainMenu = mainMenu;
        vraagAantalSpelers();
    }
    
    private void vraagAantalSpelers()
    {
        stage.setTitle("Aantal spelers");
        ObservableList<String> options = FXCollections.observableArrayList("2 spelers", "3 spelers", "4 spelers");
        cbo_aantalSpelers.setItems(options);
        cbo_aantalSpelers.setPromptText("Aantal spelers");
        lbl_foutMelding.setText("kies het aantal spelers!");
        btn_confirm.setText("Confirm");
        btn_confirm.setOnAction(this::confirm);
        cbo_aantalSpelers.setOnKeyPressed(this::confirmEnter);
        btn_back.setText("Main Menu");
        btn_back.setOnAction(this::mainMenu);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(cbo_aantalSpelers, new Insets(100,0,25,0));
        VBox.setMargin(btn_confirm, new Insets(0,0,25,0));
        cbo_aantalSpelers.setMinWidth(250);
        btn_confirm.setMinWidth(250);
        cbo_aantalSpelers.setMinHeight(50);
        btn_confirm.setMinHeight(50);
        btn_back.setMinWidth(250);
        btn_back.setMinHeight(50);
        getChildren().addAll(cbo_aantalSpelers, btn_confirm, btn_back);
    }
    
    
    private void vraagNamenSpelers()
    {
        stage.setTitle("Naamgeving van de spelers");
        lbl_naamSpeler.setText("Naam speler " + String.format("%d", spelerNr + 1));
        lbl_foutMelding.setText("ongeldige naam");
        btn_confirm.setText("Confirm");
        btn_confirm.setOnAction(this::confirmNaam);
        btn_back.setText("Terug");
        txt_naamSpeler.setOnKeyPressed(this::confirmNaamEnter);
        lbl_naamSpeler.setAlignment(Pos.TOP_CENTER);
        this.setAlignment(Pos.CENTER);
        VBox.setMargin(txt_naamSpeler, new Insets(100,0,25,0));
        VBox.setMargin(btn_confirm, new Insets(0,0,25,0));
        txt_naamSpeler.setMaxWidth(250);
        btn_confirm.setMinWidth(250);
        txt_naamSpeler.setMinHeight(50);
        btn_confirm.setMinHeight(50);
        btn_back.setMinWidth(250);
        btn_back.setMinHeight(50);
        btn_back.setOnAction(this::back);
        this.getChildren().addAll(lbl_naamSpeler, txt_naamSpeler, btn_confirm, btn_back);
    }
    
    public void confirmNaamEnter(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.confirmNaam();
        }
    }
    
    private void confirmNaam()
    {
        if (txt_naamSpeler.getText() == null || txt_naamSpeler.getText().trim().isEmpty()) {
            txt_naamSpeler.clear();
            getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, btn_confirm, btn_back);
            getChildren().addAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding, btn_confirm, btn_back);
        }
        else
        {
            if (dc.doeNaamControle(spelerNr, txt_naamSpeler.getText())) {
                getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding,btn_confirm, btn_back);
                spelerNr += 1;
                if (spelerNr < dc.getSpelerLijst().size())
                {
                    txt_naamSpeler.clear();
                    vraagNamenSpelers();
                }
                else
                {
                    this.getChildren().clear();
                    stage.hide();

                    dc.vulLijsten();
                    Stage spelStage = new Stage();
                    SpelApplicatiePaneel spelAppPaneel = new SpelApplicatiePaneel(dc, spelStage);
                    Scene scenery = new Scene(spelAppPaneel);
                    String cssURL = this.getClass().getResource("/gui/StylesheetSpel.css").toExternalForm();
                    scenery.getStylesheets().add(cssURL);
                    spelStage.setScene(scenery);

                }
            }
            else
            {
                txt_naamSpeler.clear();
                getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, btn_confirm, btn_back);
                getChildren().addAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding, btn_confirm, btn_back);
            }
        }
    }
    
    private void confirmNaam(ActionEvent ae)
    {
        if (dc.doeNaamControle(spelerNr, txt_naamSpeler.getText()) || "".equals(txt_naamSpeler.getText())) {
            getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding,btn_confirm, btn_back);
            spelerNr += 1;
            if (spelerNr < dc.getSpelerLijst().size())
            {
                txt_naamSpeler.clear();
                vraagNamenSpelers();
            }
            else
            {
                this.getChildren().clear();
                stage.hide();
                
                dc.vulLijsten();
                Stage spelStage = new Stage();
                SpelApplicatiePaneel spelAppPaneel = new SpelApplicatiePaneel(dc, spelStage);
                Scene scenery = new Scene(spelAppPaneel);
                String cssURL = this.getClass().getResource("/gui/StylesheetSpel.css").toExternalForm();
                scenery.getStylesheets().add(cssURL);
                spelStage.setScene(scenery);
                
            }
        }
        else
        {
            txt_naamSpeler.clear();
            getChildren().removeAll(lbl_naamSpeler, txt_naamSpeler, btn_confirm, btn_back);
            getChildren().addAll(lbl_naamSpeler, txt_naamSpeler, lbl_foutMelding, btn_confirm, btn_back);
        }
    }
    
    public void confirmEnter(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.confirm();
        }
    }
    
    private void confirm(ActionEvent ae)
    {
        String temp = (String) cbo_aantalSpelers.getValue();
        char keuze = temp.charAt(0);
        String res = "" + keuze;
        dc.doeAantalSpelersControle(res);
        getChildren().removeAll(cbo_aantalSpelers, lbl_foutMelding,btn_confirm, btn_back);
        vraagNamenSpelers();
    }
    
    private void confirm()
    {
        String temp = (String) cbo_aantalSpelers.getValue();
        char keuze = temp.charAt(0);
        String res = "" + keuze;
        dc.doeAantalSpelersControle(res);
        getChildren().removeAll(cbo_aantalSpelers, lbl_foutMelding,btn_confirm, btn_back);
        vraagNamenSpelers();
    }
    
    
    private void mainMenu(ActionEvent ae)
    {
        this.getChildren().clear();
        mainMenu.maakMainMenu();
    }
    
    private void back(ActionEvent ae)
    {
        if (spelerNr < 1) {
            this.getChildren().clear();
            dc.doeResetLijsten();
            spelerNr = 0;
            vraagAantalSpelers();
        }
        else
        {
            this.getChildren().clear();
            spelerNr -= 1;
            vraagNamenSpelers();
        }
    }
}