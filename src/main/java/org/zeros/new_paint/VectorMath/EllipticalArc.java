package org.zeros.new_paint.VectorMath;

import com.sun.javafx.geom.Curve;
import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.PathElement;

public class EllipticalArc {
      /*  public static CubicCurve startEndCenterElypse(Point2D startPoint, Point2D endPoint, Point2D centerPoint){
            double height=2*startPoint.distance(centerPoint);
            double width =2*endPoint.distance(centerPoint);
            Point2D[] quads =new Point2D[8];
            Point2D[] ends =new Point2D[4];

            LinearEquation tan1=new LinearEquation(startPoint,centerPoint);
            tan1=tan1.offsetLine(height/2);
            LinearEquation tan2=new LinearEquation(endPoint,centerPoint);
            tan1=tan1.offsetLine(width/2);
            LinearEquation diagonal=new LinearEquation(endPoint,startPoint);
            double offset=1.25*(Math.sqrt(2)-1)*0.5*Math.sqrt(width*width+height*height);
            diagonal=diagonal.offsetLine(offset);
            quads[0]=diagonal.intersection(tan1);
            quads[1]=diagonal.intersection(tan2);


            /*LinearEquation offsetCord = cord.offsetLine(cornerOffset);
            Point2D corner = perpendicular.intersection(offsetCord);
            LinearEquation tangent1=new LinearEquation(startPoint,corner);
            LinearEquation tangent2=new LinearEquation(endPoint,corner);



            LinearEquation offsetCord2=cord.offsetLine(offset);


            quads[0]=tangent1.intersection(offsetCord2);
            quads[1]=tangent2.intersection(offsetCord2);
            return new CubicCurve(startPoint.getX(),startPoint.getY(),quads[0].getX(),quads[0].getY(),quads[1].g);


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
*/
}

