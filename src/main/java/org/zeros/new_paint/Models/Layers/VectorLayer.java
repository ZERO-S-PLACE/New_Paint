package org.zeros.new_paint.Models.Layers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.Path;
import org.zeros.new_paint.Models.Parameters;
import org.zeros.new_paint.Models.ShapeType;

public class VectorLayer extends GraphicLayer {
    private Color lineColor=Color.BLACK;
    private Color fillColor=Color.BLACK;
    private  double lineWidth=1;
    private final ObjectProperty<AnchorPane> drawingPane;
    private final BooleanProperty isClosed;
    private final ObservableList<Point2D> connectedPoints;
    private final ObservableList<Point2D> quadPoints;
    private final ShapeType shapeType;
    private final ObjectProperty<Path> path;

    public VectorLayer(double xOffset, double yOffset, String name, ShapeType shapeType) {
        super(0,0, xOffset, yOffset,name);

        this.connectedPoints = FXCollections.observableArrayList();
        this.quadPoints = FXCollections.observableArrayList();
        this.path = new SimpleObjectProperty<>(this,"path",new Path());
        setLayerType(LayerType.VECTOR);
        this.shapeType=shapeType;
        this.drawingPane = new SimpleObjectProperty<>(this,"drawingPane ",new AnchorPane());
        drawingPane.get().setPrefHeight(3*Parameters.getDEFAULT_HEIGHT());
        drawingPane.get().setPrefWidth(3*Parameters.getDEFAULT_WIDTH());
        drawingPane.get().visibleProperty().bindBidirectional(visibleProperty());
        drawingPane.get().getChildren().add(path.get());
        isClosed= new SimpleBooleanProperty(this,"",false);
    }
    public ObjectProperty<AnchorPane> drawingPaneProperty() {
        return drawingPane;
    }
    public ObjectProperty<Path> pathProperty() {
        return path;
    }
    public BooleanProperty closedProperty(){
        return isClosed;
    }
    public ObservableList<Point2D> getPoints() {
        return connectedPoints;
    }
    public void addPoint(Point2D point) {
        connectedPoints.add(point);
    }
    public Point2D lastPoint() {
        return connectedPoints.getLast();
    }
    public Point2D firstPoint() {
        return connectedPoints.getFirst();
    }
    public void removePoint(Point2D point) {
        connectedPoints.remove(point);
    }
    public void movePoint(Point2D oldPoint,Point2D newPoint) {
        int i =connectedPoints.indexOf(oldPoint);
        connectedPoints.remove(i);
        connectedPoints.add(i,newPoint);
    }

    public ObservableList<Point2D> getQuadPoints() {
        return quadPoints;
    }
    public void addQuadPoint(Point2D point) {
        quadPoints.add(point);
    }
    public Point2D lastQuadPoint() {
        return quadPoints.getLast();
    }
    public Point2D firstQuadPoint() {
        return quadPoints.getFirst();
    }
    public void removeQuadPoint(Point2D point) {
        quadPoints.remove(point);
    }
    public void moveQuadPoint(Point2D oldPoint,Point2D newPoint) {
        int i =quadPoints.indexOf(oldPoint);
        quadPoints.remove(i);
        quadPoints.add(i,newPoint);
    }


    public ShapeType getShapeType() {
        return shapeType;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        path.get().setStroke(lineColor);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        if(isClosed.get()) {
            path.get().setFill(fillColor);
        }else {
            path.get().setFill(new Color(0,0,0,0));
        }
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
        path.get().setStrokeWidth(lineWidth);
    }
}
