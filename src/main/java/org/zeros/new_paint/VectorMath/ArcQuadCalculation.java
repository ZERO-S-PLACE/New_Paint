package org.zeros.new_paint.VectorMath;

import javafx.geometry.Point2D;
public class ArcQuadCalculation {


    public static Point2D[] startEndRadiusPointCalculation(Point2D startPoint, Point2D endpoint, Point2D radiusPoint){
        Point2D[] quads =new Point2D[2];

        double radius=0.0;
        double halfOfCord=Math.abs(startPoint.distance(endpoint)/2);
        if(radiusPoint!=null) {
        radius=radiusPoint.distance(startPoint.midpoint(endpoint));
        }else {
            radius=halfOfCord;
        }

        if(radius<halfOfCord*2/Math.sqrt(2)){
            radius=halfOfCord*2/Math.sqrt(2);
        }


        double sinA = halfOfCord/radius;
        double cosA = Math.sqrt(1-sinA*sinA);


        LinearEquation cord=new LinearEquation(startPoint,endpoint);
        LinearEquation perpendicular=cord.perpendicularTroughPoint(startPoint.midpoint(endpoint));

        double cornerOffset = radius / cosA - radius * cosA;
        double offset=1.25*radius*(1-cosA);

        if(radiusPoint != null){
        if(radiusPoint.getY()<cord.getValue(radiusPoint.getX())){
            cornerOffset=-cornerOffset;
            offset=-offset;
        }
        }


            LinearEquation offsetCord = cord.offsetLine(cornerOffset);
            Point2D corner = perpendicular.intersection(offsetCord);
        LinearEquation tangent1=new LinearEquation(startPoint,corner);
        LinearEquation tangent2=new LinearEquation(endpoint,corner);



        LinearEquation offsetCord2=cord.offsetLine(offset);


        quads[0]=tangent1.intersection(offsetCord2);
        quads[1]=tangent2.intersection(offsetCord2);
        return quads;


    }




    public static Point2D[] threePointCalculation(Point2D startPoint,Point2D middlePoint,Point2D endpoint){
        Point2D[] quads =new Point2D[2];
        return quads;

    }


    public static double[] solveQuadraticEquation(double a, double b, double c){

        double delta =b*b-4*a*c;
        double[] y =new double[2];
        if(delta>=0){
        y[0]=(-b-Math.sqrt(delta))/2;
        y[1]=(-b+Math.sqrt(delta))/2;
        }
        return y;

    }
    private static Point2D rotatePoint(Point2D point, Point2D center, double alphaRadians){


        double rotatedX = center.getX() + (point.getX() - center.getX()) * Math.cos(alphaRadians) - (point.getY() - center.getY()) * Math.sin(alphaRadians);
        double rotatedY = center.getY() + (point.getX() - center.getX()) * Math.sin(alphaRadians) + (point.getY() - center.getY()) * Math.cos(alphaRadians);
        return new Point2D(rotatedX,rotatedY);
    }



}
