package org.zeros.new_paint.Models.Layers;

import javafx.beans.property.*;
import javafx.scene.canvas.Canvas;

public class RasterLayer extends GraphicLayer {
    private final ObjectProperty<Canvas>canvas;

    public RasterLayer(int widthPixels, int heightPixels, double xOffset, double yOffset, String name) {
        super( widthPixels,  heightPixels, xOffset, yOffset,name);
        setLayerType(LayerType.GRAPHIC);
        this.canvas = new SimpleObjectProperty<>(this,"canvas",new Canvas(widthProperty().get(),heightProperty().get()));
        canvas.get().visibleProperty().bindBidirectional(visibleProperty());

    }

    public ObjectProperty<Canvas> canvasProperty() {
        return canvas;
    }

}
