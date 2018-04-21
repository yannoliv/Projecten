package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class MapSpel extends GridPane 
{
    public MapSpel()
    {

        buildGUI();

    }

    private void buildGUI()
    {
        //ACHTERGROND
        BackgroundSize achtergrondLengteBreedte = new BackgroundSize( 1, 1, true, true, false, false);
        BackgroundImage Achtergrond = new BackgroundImage(new Image("/images/mapSpel.png"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, achtergrondLengteBreedte);
        this.setBackground(new Background(Achtergrond));
        //VOORGROND
        voorgrondButtons();
    }
    
    public void voorgrondButtons()
    {
        //de coordinaten zijn (aa) ipv (0,0), dus (1,3) is button (bd)
        //buttons
        Button aa = new Button();
        Button ab = new Button();
        Button ca = new Button();
        Button cb = new Button();
        Button cc = new Button();
        Button cd = new Button();
        Button fa = new Button();
        Button fd = new Button();
        
        
        //Kollommen, we hebben er 5 nodig
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(20);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(11);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(19);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(12);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(15);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(23);
        getColumnConstraints().addAll(column0,column1, column2, column3, column4, column5);
        
        //Rijen, we hebben er 4 nodig
        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(25);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(22);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(28);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25);
        getRowConstraints().addAll(row0,row1,row2, row3);
        
        // buttons groter maken
        aa.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ab.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ca.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cb.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cc.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fa.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setRowSpan(ab,2);
        this.setColumnSpan(cb,2);
        
        //buttons toevoegen in de gridpane
        this.add(aa,0,0);
        this.add(ab,0,1);
        this.add(ca,2,0);
        this.add(cb,2,1);
        this.add(cc,2,2);
        this.add(cd,2,3);
        this.add(fa,5,0);
        this.add(fd,5,3);
        
        //zoda je de grid ziet, das temporary
        setGridLinesVisible(true); 
    }
}