package org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;

import javafx.geometry.Point2D;
import javafx.scene.shape.PathElement;
import org.zeros.new_paint.Models.Layers.VectorLayer;

public abstract class PolyLineSegment {
    protected Point2D quadPoint=null;
    protected   Point2D lastQuadPoint=null;

    protected final VectorLayer layer;
    protected PathElement nextElement;
    protected Point2D startPoint;
    protected Point2D currentPoint;

    public abstract void createNextPathElement();
    public abstract void updateShape(Point2D currentPoint);



    public PolyLineSegment(VectorLayer layer) {
        this.layer = layer;

    }
    public void updatePoints() {
        this.startPoint=layer.lastPoint();
        this.currentPoint=startPoint;
        if(!layer.getQuadPoints().isEmpty()) {
            lastQuadPoint = layer.getQuadPoints().getLast();

        }
    }


    public void dismiss() {
        layer.pathProperty().get().getElements().removeLast();
    }


    public void save() {
        layer.addPoint(currentPoint);
        layer.addQuadPoint(quadPoint);
    }
}
