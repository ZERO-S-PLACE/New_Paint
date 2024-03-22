package org.zeros.new_paint.ImageCreation.VectorShapes;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.zeros.new_paint.Models.ShapeType;

public class VectorCurveFactory extends VectorShapeFactory {
    public VectorCurveFactory() {
        super();
    }
    @Override
    protected void setShapeType() {
        this.shapeType=ShapeType.CURVE;
    }

    @Override
    protected void drawingMouseListener(MouseEvent event) {

    }

    @Override
    protected void removeHandlersAndFinish() {

    }

    @Override
    public void startDrawingShape(MouseEvent event) {

    }

    @Override
    protected void onSwitchKeyPressed(KeyEvent event) {

    }
}
