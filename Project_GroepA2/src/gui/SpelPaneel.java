package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpelPaneel extends VBox{
    private DomeinController dc;
    private Stage stage;
    
    private GridPane gridPane;
    private ComboBox cbo_aantalSpelers = new ComboBox();
    private Label lbl_foutMelding = new Label();
    private Button btn_confirm = new Button();
    
    public SpelPaneel(DomeinController dc, Stage stage) {
        this.dc = dc;
        this.stage = stage;
        vraagAantalSpelers();
    }

    public void vraagAantalSpelers()
    {
        stage.setTitle("Groep A2");
        stage.show();
        gridPane = new GridPane();
        gridPane.getColumnConstraints().add(new ColumnConstraints(10));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(10));
        gridPane.getRowConstraints().add(new RowConstraints(20));
        gridPane.getRowConstraints().add(new RowConstraints(80));
        gridPane.getRowConstraints().add(new RowConstraints(120));
        ObservableList<String> options = FXCollections.observableArrayList("Aantal spelers", "2 spelers", "3 spelers", "4 spelers");
        cbo_aantalSpelers.setItems(options);
        cbo_aantalSpelers.getSelectionModel().selectFirst();
        
        lbl_foutMelding.setText("kies het aantal spelers!");
        lbl_foutMelding.setStyle("-fx-foreground-color: rgb(255,0,0)");
        
        btn_confirm.setText("Confirm");
        btn_confirm.setOnAction(this::confirm);
        
        GridPane.setConstraints(cbo_aantalSpelers, 1, 1);        
        GridPane.setConstraints(lbl_foutMelding, 1, 1);
        GridPane.setConstraints(btn_confirm, 1, 1);

        GridPane.setValignment(cbo_aantalSpelers, VPos.CENTER);
        GridPane.setHalignment(cbo_aantalSpelers, HPos.CENTER);
        
        GridPane.setValignment(btn_confirm, VPos.BOTTOM);
        GridPane.setHalignment(btn_confirm, HPos.CENTER);
        this.getChildren().add(gridPane);
        gridPane.getChildren().addAll(cbo_aantalSpelers, lbl_foutMelding,btn_confirm);
    }
    
    public void verwijderVraagAantalSpelers()
    {
        this.getChildren().remove(gridPane);        
    }
    
    public void confirm(ActionEvent ae){
        if (cbo_aantalSpelers.getValue().equals(cbo_aantalSpelers.getItems().get(0))) {
            System.out.println("not correct");
        }
        else
        {
            verwijderVraagAantalSpelers();
        }
    }
}
