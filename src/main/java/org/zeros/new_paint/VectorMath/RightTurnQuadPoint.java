package org.zeros.new_paint.VectorMath;

import javafx.geometry.Point2D;

public class RightTurnQuadPoint {
    private Point2D quadPoint;
    public static Point2D twoPointsQuadCalculation(Point2D startPoint, Point2D endPoint){
        LinearEquation line=new LinearEquation(startPoint,endPoint);
        LinearEquation perpendicular=line.perpendicularTroughPoint(startPoint.midpoint(endPoint));
        line=line.offsetLine(startPoint.distance(endPoint)/2);
        return line.intersection(perpendicular);

    }
    public static Point2D threePointsQuadCalculation(Point2D lastPoint,Point2D startPoint, Point2D endPoint){
        LinearEquation tangent1=new LinearEquation(lastPoint,startPoint);
        LinearEquation tangent2=tangent1.perpendicularTroughPoint(endPoint);
        return tangent1.intersection(tangent2);
    }


}
