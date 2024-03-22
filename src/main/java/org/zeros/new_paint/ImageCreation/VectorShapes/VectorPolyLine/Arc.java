package org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;

import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurveTo;
import org.zeros.new_paint.Models.Layers.VectorLayer;
import org.zeros.new_paint.VectorMath.ArcQuadCalculation;

public class Arc extends PolyLineSegment {

    private boolean endPointPicking = false;
    private Point2D endPoint;
    private Point2D[] quads= new Point2D[2];



    public Arc(VectorLayer layer) {
        super(layer);
    }

    @Override
    public void createNextPathElement() {
        endPointPicking =!endPointPicking;
        if(endPointPicking) {
            updatePoints();
            calculateQuadPoints();
            nextElement = new CubicCurveTo(quads[0].getX(), quads[0].getY(),quads[1].getX(),quads[1].getY(),
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
    }


    @Override
    public void updateShape(Point2D currentPoint) {
        this.currentPoint = currentPoint;
        calculateQuadPoints();
        ((CubicCurveTo) nextElement).setControlX1(quads[0].getX());
        ((CubicCurveTo) nextElement).setControlY1(quads[0].getY());
        ((CubicCurveTo) nextElement).setControlX2(quads[1].getX());
        ((CubicCurveTo) nextElement).setControlY2(quads[1].getY());
        if(endPointPicking) {
            ((CubicCurveTo) nextElement).setX(currentPoint.getX());
            ((CubicCurveTo) nextElement).setY(currentPoint.getY());
        }
    }

    private void calculateQuadPoints() {
        if (startPoint.equals(currentPoint)) {
            quads[0] = startPoint;
             quads[1]=startPoint;
        } else {
            if(endPointPicking){

                quads = ArcQuadCalculation.startEndRadiusPointCalculation(startPoint,currentPoint,null);
            }else {
                quads=ArcQuadCalculation.startEndRadiusPointCalculation(startPoint,endPoint,currentPoint);
            }

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
            layer.addQuadPoint(quads[0]);
            layer.addQuadPoint(quads[1]);
            layer.addPoint(null);
            layer.addPoint(endPoint);
        }
    }
}
