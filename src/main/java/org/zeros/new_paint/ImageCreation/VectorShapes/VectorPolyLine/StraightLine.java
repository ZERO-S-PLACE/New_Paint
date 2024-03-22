package org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;

import javafx.geometry.Point2D;
import javafx.scene.shape.LineTo;
import org.zeros.new_paint.Models.Layers.VectorLayer;

public class StraightLine extends PolyLineSegment {

    public StraightLine(VectorLayer layer) {
        super(layer);
    }

    @Override
    public void createNextPathElement() {
        updatePoints();
        nextElement = new LineTo(currentPoint.getX(), currentPoint.getY());
        layer.pathProperty().get().getElements().add(nextElement);
    }

    @Override
    public void updateShape(Point2D currentPoint) {
        this.currentPoint = currentPoint;
        ((LineTo) nextElement).setX(currentPoint.getX());
        ((LineTo) nextElement).setY(currentPoint.getY());
    }


}
