package org.zeros.new_paint.VectorMath;

import javafx.geometry.Point2D;

import static java.lang.Float.NaN;

public class LinearEquation {
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public boolean isVertical() {
        return vertical;
    }

    private boolean vertical=false;

    private double a;
    private double b;

    public LinearEquation(double a, double b) {
        this.a = a;
        this.b = b;
        if(Float.isNaN((float) b)){
            this.vertical=true;
        }
    }
    public double getValue(double x){
        if(!isVertical()) {
            return a * x + b;
        }else {
                return NaN;
        }

    }
    public double getArgument(double y){
        if(a!=0) {
            return (y-b)/a;
        }else {
            return NaN;
        }

    }

    public LinearEquation(Point2D c, Point2D d) {
        if(c.getX()!= d.getX()) {
            this.a = (c.getY() - d.getY()) / (c.getX() - d.getX());
            this.b = c.getY() - a * c.getX();
        }
        else {
            this.a =c.getX();
            this.b =Double.NaN;
            vertical=true;
        }
    }

    public LinearEquation perpendicularTroughPoint(Point2D e){
        if(a!=0&&!isVertical()) {
            return new LinearEquation((-1) / a, e.getY() + e.getX() / a);
        }else if(a==0){
            return new LinearEquation(e,e);
        }
        else {
            return new LinearEquation(0,e.getY());
        }
    }
    public Point2D intersection(LinearEquation equation){
        if (a!=equation.getA()&&!equation.isVertical()&&!this.isVertical()) {
            double x = (equation.getB() - b) / (a - equation.getA());
            double y = a * x + b;
            return new Point2D(x, y);
        }
        else if(equation.isVertical()){
            return new Point2D(equation.getA(),b);
        }else if(this.isVertical()){
            return new Point2D(a,equation.getB());
        }
        return null;

    }
    public LinearEquation offsetLine(double distance){
        if(isVertical()){
            return new LinearEquation(a-distance,b);
        }
        if(a==0){

            return new LinearEquation(0,b-distance);
        }

        return new LinearEquation(a,b-(distance/Math.cos(Math.atan(a))));
    }

    public Point2D mirrorPoint(Point2D point){
        LinearEquation perpendicular =this.perpendicularTroughPoint(point);
        Point2D intersection = this.intersection(perpendicular);
        double offset=point.distance(intersection);
        if(point.getY()>intersection.getY()){
            offset=-offset;
        }
        LinearEquation second=this.offsetLine(offset);
        return second.intersection(perpendicular);


    }

}
