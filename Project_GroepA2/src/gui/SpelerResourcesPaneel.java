package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SpelerResourcesPaneel extends VBox 
{

    private DomeinController dc;
    private SpelApplicatiePaneel spelApplicatiePaneel;

    HBox hbox = new HBox(5); //spacing 5
    private Label lbl_hout = new Label("Hout: ");
    private Label lbl_leem = new Label("Leem: ");
    private Label lbl_steen = new Label("Steen: ");
    private Label lbl_goud = new Label("Goud: ");

    /*
        resourceLijst.add(new Resource("hout",          0));
        resourceLijst.add(new Resource("leem",          0));
        resourceLijst.add(new Resource("steen",         0));
        resourceLijst.add(new Resource("goud",          0));
        resourceLijst.add(new Resource("akkerbouw",     0));
        resourceLijst.add(new Resource("gereedschap",   0));
        resourceLijst.add(new Resource("voedsel",       12));
        resourceLijst.add(new Resource("stamleden",     5));
        resourceLijst.add(new Resource("punten",        0));
    */

    public SpelerResourcesPaneel(SpelApplicatiePaneel spelApplicatiePaneel, DomeinController dc)
    {

        this.dc = dc;
        this.spelApplicatiePaneel = spelApplicatiePaneel;



        hbox.getChildren().addAll(lbl_hout,lbl_leem, lbl_steen,lbl_goud);

        this.getChildren().add(hbox);

    }

}