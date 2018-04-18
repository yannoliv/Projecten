//
//package gui;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class NaamSpelersControlePaneel extends StackPane {
//    
//    private HoofdPaneel hoofdpaneel;
//    
//    public NaamSpelersControlePaneel(HoofdPaneel hoofdpaneel)
//    {
//        this.hoofdpaneel = hoofdpaneel;
//        setPrefSize(400,400);
//        Label aantalSpelers_lbl = new Label("Aantal spelers:");
//        String aantalSpelersTekst = "2-4";
//        TextField aantalSpelers_txt = new TextField(aantalSpelersTekst); 
//        Button controleerAantalSpelers_btn = new Button("Oke");
//        controleerAantalSpelers_btn.setOnAction(this::controleerAantalSpelers_btn);
//        
//        getChildren().add(aantalSpelers_lbl, aantalSpelers_txt, aantalSpelersControle_btn);
//    }
//    
//    private void controleerAantalSpelers_btn(ActionEvent event)
//    {
//        hoofdpaneel.toonControleerAantalSpelers_btn();
//    }
//}
