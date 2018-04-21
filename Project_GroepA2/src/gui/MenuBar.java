package gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {
    
    MenuBar menuBar = new MenuBar();
    
    
    public MenuBar()
    {    
       javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();
        Menu spelMenu = new Menu("Spel");
        MenuItem overMenuItem = new MenuItem("Over");
        MenuItem afsluitenMenuItem = new MenuItem("Afsluiten");
        afsluitenMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        overMenuItem.setOnAction(this::aboutClicked);
        afsluitenMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Platform.exit();
            }
        });

        spelMenu.getItems().addAll(overMenuItem, new SeparatorMenuItem(), afsluitenMenuItem);
        menuBar.getMenus().add(spelMenu);
    }
    public void aboutClicked (ActionEvent e) {
                
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Over");
        alert.setHeaderText(null);
        alert.setContentText("Program version 1.0\nMade by King Werner Yannick Rayen");
        alert.showAndWait();
    }
}
