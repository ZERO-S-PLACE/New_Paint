package org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;

import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import org.zeros.new_paint.Models.Layers.VectorLayer;
import org.zeros.new_paint.VectorMath.ArcQuadCalculation;
import org.zeros.new_paint.VectorMath.CurveTroughPointQuadCalculation;
import org.zeros.new_paint.VectorMath.RightTurnQuadPoint;

public class CurveTroughPoint extends PolyLineSegment{
    private boolean middlePointPicking = false;
    private Point2D middlePoint;
    private Point2D lastPoint;
    private Point2D[] quads= new Point2D[2];
    PathElement nextElementSecondPart;
    public CurveTroughPoint(VectorLayer layer) {
        super(layer);
    }

    @Override
    public void createNextPathElement() {
        middlePointPicking =!middlePointPicking;
        if(middlePointPicking) {
            updatePoints();
            calculateQuadPoints();
            nextElement = new QuadCurveTo(quads[0].getX(), quads[0].getY(),
                    currentPoint.getX(), currentPoint.getY());
            layer.pathProperty().get().getElements().add(nextElement);
        }else {
            middlePoint =currentPoint;
            calculateQuadPoints();
            nextElementSecondPart = new QuadCurveTo(quads[1].getX(), quads[1].getY(),
                    currentPoint.getX(), currentPoint.getY());
            layer.pathProperty().get().getElements().add(nextElementSecondPart);


            layer.drawingPaneProperty().get().getChildren().add(new Rectangle(middlePoint.getX(),middlePoint.getY(),5,5));

        }
    }

    @Override
    public void updatePoints() {
        this.startPoint=layer.lastPoint();
        this.currentPoint=startPoint;
        this.lastQuadPoint=layer.lastQuadPoint();
        if(layer.getPoints().size()>=2){
            lastPoint=layer.getPoints().get(layer.getPoints().size()-2);
        }
    }


    @Override
    public void updateShape(Point2D currentPoint) {
        this.currentPoint = currentPoint;
        calculateQuadPoints();
        ((QuadCurveTo) nextElement).setControlX(quads[0].getX());
        ((QuadCurveTo) nextElement).setControlY(quads[0].getY());
        if(middlePointPicking){
            ((QuadCurveTo) nextElement).setX(currentPoint.getX());
            ((QuadCurveTo) nextElement).setY(currentPoint.getY());
        }else {
            ((QuadCurveTo) nextElementSecondPart).setControlX(quads[1].getX());
            ((QuadCurveTo) nextElementSecondPart).setControlY(quads[1].getY());
            ((QuadCurveTo) nextElementSecondPart).setX(currentPoint.getX());
            ((QuadCurveTo) nextElementSecondPart).setY(currentPoint.getY());
        }


    }

    private void calculateQuadPoints() {
        if (startPoint.equals(currentPoint)) {
            quads[0] = startPoint;
            quads[1]=startPoint;
        } else {
            if(middlePointPicking){
                if(lastQuadPoint==null) {
                    quads[0] = RightTurnQuadPoint.threePointsQuadCalculation(lastPoint, startPoint, currentPoint);
                }
                else {
                    quads[0] = RightTurnQuadPoint.threePointsQuadCalculation(lastQuadPoint, startPoint, currentPoint);
                }
                quads[1] = currentPoint;
            }else {
                if(lastQuadPoint==null) {
                    quads = CurveTroughPointQuadCalculation.curveTroughFourPoints(lastPoint, startPoint,middlePoint, currentPoint);
                }
                else {
                    quads = CurveTroughPointQuadCalculation.curveTroughFourPoints(lastQuadPoint, startPoint,middlePoint, currentPoint);

                }
            }

        }

    }

    @Override
    public void dismiss() {
        super.dismiss();
        middlePoint =null;
    }

    @Override
    public void save() {
        if (!middlePointPicking) {
            layer.addPoint(currentPoint);
            layer.addQuadPoint(quads[0]);
            layer.addQuadPoint(quads[1]);
            layer.addPoint(middlePoint);
            layer.addPoint(currentPoint);
        }
    }
}
