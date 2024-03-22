package org.zeros.new_paint.VectorMath;

import javafx.geometry.Point2D;

public class CurveTroughPointQuadCalculation {


    public static Point2D[] curveTroughFourPoints(Point2D lastPoint, Point2D startPoint, Point2D middlePoint, Point2D endPoint) {
        Point2D[] quads =new Point2D[2];
        LinearEquation tangent1=new LinearEquation(lastPoint,startPoint);
        LinearEquation cord=new LinearEquation(endPoint,startPoint);
        Point2D cordMidpoint=startPoint.midpoint(endPoint);

        LinearEquation middleLine=new LinearEquation(middlePoint,cordMidpoint);
        if(middleLine.getA()==tangent1.getA()){
            LinearEquation offsetLine=cord.offsetLine(1.25*cordMidpoint.distance(startPoint));
            quads[0]=offsetLine.intersection(tangent1);
            quads[1]=middleLine.mirrorPoint(quads[0]);
            return quads;
        }
        Point2D corner=tangent1.intersection(middleLine);
        System.out.println("corner"+corner);
        LinearEquation tangent1perp=tangent1.perpendicularTroughPoint(corner);
        tangent1perp=tangent1perp.offsetLine(startPoint.distance(corner)/2);
        quads[0]=tangent1.intersection(tangent1perp);

        LinearEquation middleTan=new LinearEquation(quads[0],middlePoint);
        LinearEquation middleTanPerp=middleTan.perpendicularTroughPoint(middlePoint);
        middleTanPerp=middleTanPerp.offsetLine(quads[0].distance(middlePoint));


        quads[1]=middleTanPerp.intersection(middleTan);





        return quads;
    }
}

