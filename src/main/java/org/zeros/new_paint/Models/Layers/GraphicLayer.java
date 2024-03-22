package org.zeros.new_paint.Models.Layers;

import eu.hansolo.tilesfx.tools.Point;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class GraphicLayer extends Layer {
    private DoubleProperty width;
    private DoubleProperty height;
    private ObjectProperty<Point> offsetCoordinates;
    private ObjectProperty<Point> midpointCoordinates;


    public GraphicLayer(int widthPixels, int heightPixels, double xOffset, double yOffset, String name) {
        super(name);
        setLayerType(LayerType.GRAPHIC);
        this.width = new SimpleDoubleProperty(this,"layer width",widthPixels);
        this.height = new SimpleDoubleProperty(this,"layer height",heightPixels);
        this.offsetCoordinates = new SimpleObjectProperty<>(this,"offset",new Point(xOffset,yOffset));
        this.midpointCoordinates = new SimpleObjectProperty<>(this,"midpoint",new Point(
                offsetCoordinates.get().getX()+(int)(width.get()/2),
                offsetCoordinates.get().getY()+(int)(height.get()/2)));


    }
    public DoubleProperty widthProperty() {
        return width;
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public ObjectProperty<Point> offsetCoordinatesProperty() {
        return offsetCoordinates;
    }


    public ObjectProperty<Point> midpointCoordinatesProperty() {
        return midpointCoordinates;
    }


}
