package org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;

import javafx.geometry.Point2D;
import javafx.scene.shape.QuadCurveTo;
import org.zeros.new_paint.Models.Layers.VectorLayer;
import org.zeros.new_paint.VectorMath.RightTurnQuadPoint;

public class EllipticalCurve extends PolyLineSegment {


    public EllipticalCurve(VectorLayer layer) {
        super(layer);

    }

    @Override
    public void createNextPathElement() {
        updatePoints();

        calculateQuadPoint();
        nextElement = new QuadCurveTo(quadPoint.getX(), quadPoint.getY(), currentPoint.getX(), currentPoint.getY());

        layer.pathProperty().get().getElements().add(nextElement);
    }


    @Override
    public void updateShape(Point2D currentPoint) {
        this.currentPoint = currentPoint;
        calculateQuadPoint();
        ((QuadCurveTo) nextElement).setControlX(quadPoint.getX());
        ((QuadCurveTo) nextElement).setControlY(quadPoint.getY());
        ((QuadCurveTo) nextElement).setX(currentPoint.getX());
        ((QuadCurveTo) nextElement).setY(currentPoint.getY());
    }

    private void calculateQuadPoint() {
        if (startPoint.equals(currentPoint)) {
            quadPoint = startPoint;
        } else {
            if (layer.getPoints().size() < 2 && lastQuadPoint == null) {
                quadPoint = RightTurnQuadPoint.twoPointsQuadCalculation(startPoint, currentPoint);
            } else {
                if (lastQuadPoint == null) {
                    quadPoint = RightTurnQuadPoint.threePointsQuadCalculation(layer.getPoints().get(layer.getPoints().size() - 2), startPoint, currentPoint);
                } else {
                    quadPoint = RightTurnQuadPoint.threePointsQuadCalculation(lastQuadPoint, startPoint, currentPoint);
                }
            }
        }
    }


}




