package org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;

import javafx.geometry.Point2D;
import javafx.scene.shape.QuadCurveTo;
import org.zeros.new_paint.Models.Layers.VectorLayer;

public class CurveWithControlPoint extends PolyLineSegment{
    private boolean endPointPicking = false;
    private Point2D endPoint;

    public CurveWithControlPoint(VectorLayer layer) {
        super(layer);
    }

    @Override
    public void createNextPathElement() {
        endPointPicking =!endPointPicking;
        if(endPointPicking) {
            updatePoints();
            nextElement = new QuadCurveTo(quadPoint.getX(), quadPoint.getY(),
                    currentPoint.getX(), currentPoint.getY());
            layer.pathProperty().get().getElements().add(nextElement);
        }else {
            endPoint=currentPoint;
        }
    }

    @Override
    public void updatePoints() {
        this.startPoint=layer.lastPoint();
        this.currentPoint=startPoint;
        this.quadPoint=currentPoint;
    }


    @Override
    public void updateShape(Point2D currentPoint) {
        this.currentPoint = currentPoint;

        ((QuadCurveTo) nextElement).setControlX(currentPoint.getX());
        ((QuadCurveTo) nextElement).setControlY(currentPoint.getY());

        if(endPointPicking) {
            ((QuadCurveTo) nextElement).setX(currentPoint.getX());
            ((QuadCurveTo) nextElement).setY(currentPoint.getY());
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        endPoint=null;
    }

    @Override
    public void save() {
        if (!endPointPicking) {
            layer.addPoint(currentPoint);
            layer.addQuadPoint(quadPoint);
            layer.addPoint(endPoint);
        }
    }
}
