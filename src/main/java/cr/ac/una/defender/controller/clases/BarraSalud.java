/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Haymara
 */
public class BarraSalud extends Pane {

    Rectangle outerHealthRect;
    Rectangle innerHealthRect;

    public BarraSalud() {

        double height = 10;

        double outerWidth = 60;
        double innerWidth = 40;

        double x=0.0;
        double y=0.0;

        outerHealthRect = new Rectangle( x, y, outerWidth, height);
        outerHealthRect.setStroke(Color.BLACK);
        outerHealthRect.setStrokeWidth(2);
        outerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        outerHealthRect.setFill(Color.RED);

        innerHealthRect = new Rectangle( x, y, innerWidth, height);
        innerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        innerHealthRect.setFill(Color.LIMEGREEN);

        getChildren().addAll( outerHealthRect, innerHealthRect);

    }

    public void setValue( double value) {
        innerHealthRect.setWidth( outerHealthRect.getWidth() * value);
    }

}
