package org.zeros.new_paint.ImageCreation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class SettingsValues {

    private static final ObjectProperty<Color> bucketFillColor=new SimpleObjectProperty<>(Color.BLACK);

    private static final ObjectProperty<Color> lineColor=new SimpleObjectProperty<>(Color.BLACK);
    private static final ObjectProperty<Color> fillColor=new SimpleObjectProperty<>(Color.WHITE);
    private static final DoubleProperty lineWidth=new SimpleDoubleProperty(1);
    private static double lineTransparency=0;

    private static double fillTransparency=0;



    public static ObjectProperty<Color> lineColorProperty() {
        return lineColor;
    }


    public static ObjectProperty<Color> fillColorProperty() {
        return fillColor;
    }



    public static ObjectProperty<Color> bucketFillColorProperty(){

        return bucketFillColor;
    }

    public static DoubleProperty lineWidthProperty() {
        return lineWidth;
    }
    public static double getLineTransparency() {
        return lineTransparency;
    }
    public static void setLineTransparency(double lineTransparency) {
        SettingsValues.lineTransparency=lineTransparency;
        SettingsValues.lineColor.set(new Color(lineColor.get().getRed(),lineColor.get().getGreen(),lineColor.get().getBlue()
                ,1-SettingsValues.lineTransparency/100));
    }

    public static double getFillTransparency() {
        return fillTransparency;
    }
    public static void setFillTransparency(double fillTransparency) {
        SettingsValues.fillTransparency=fillTransparency;
        SettingsValues.fillColor.set(new Color(fillColor.get().getRed(),fillColor.get().getGreen(),fillColor.get().getBlue()
                ,1-SettingsValues.fillTransparency/100));
    }

}
