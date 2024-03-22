package org.zeros.new_paint.Models.Layers;

import javafx.beans.property.*;

public abstract class Layer {
    private LayerType layerType;
    private final BooleanProperty isVisible;
    private final BooleanProperty isLocked;
    private final StringProperty name;
    public Layer(String name) {
        this.name = new SimpleStringProperty(this,"layer name",name);
        this.isVisible=new SimpleBooleanProperty(this,"visibility",true);
        this.isLocked=new SimpleBooleanProperty(this,"locking",false);
    }


    public LayerType getLayerType() {
        return layerType;
    }

    public BooleanProperty visibleProperty() {
        return isVisible;
    }
    public StringProperty nameProperty() {
        return name;
    }

    public BooleanProperty lockedProperty() {
        return isLocked;
    }

    protected void setLayerType(LayerType layerType) {
        this.layerType=layerType;
    }
}


