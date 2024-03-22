package org.zeros.new_paint.ImageCreation.RasterShapes;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.zeros.new_paint.ImageCreation.GraphicFactory;
import org.zeros.new_paint.ImageCreation.SettingsValues;
import org.zeros.new_paint.Models.*;
import org.zeros.new_paint.Models.Layers.RasterLayer;

public class RasterLineFactory extends GraphicFactory {

    private RasterLayer layer;
    private Line line;
    private LineType lineType;
    private final EventHandler<MouseEvent> mouseMovedHandler = this::onMouseMoved;
    private final EventHandler<MouseEvent> drawingMouseDraggedHandler = this::freehandMouseDragged;
    private final EventHandler<MouseEvent> freehandMouseReleasedHandler = this::freehandMouseReleased;

    protected final ChangeListener<Color> strokeColorListener=((observable, oldValue, newValue) -> {
        if(lineType!=LineType.FREEHAND) {
            line.setStroke(newValue);
        }
        layer.canvasProperty().get().getGraphicsContext2D().setStroke(newValue);
    });

    protected final InvalidationListener lineWidthListener=(e -> {
        if(lineType!=LineType.FREEHAND) {
            line.setStrokeWidth(SettingsValues.lineWidthProperty().get());
        }
        layer.canvasProperty().get().getGraphicsContext2D().setLineWidth(SettingsValues.lineWidthProperty().get());
    });


    public RasterLineFactory(LineType lineType) {
            layer=(RasterLayer) Model.getInstance().getRightPanelController().getSelectedLayer();
            this.lineType = lineType;
            this.trackingPane = Model.getInstance().getViewFactory().getTrackingPane();
            offsetPoint=new Point2D(layer.offsetCoordinatesProperty().get().getX(),layer.offsetCoordinatesProperty().get().getY());
            addBasicKeyEventsHandlers();

    }

    private void setVisualProperties() {

        if(lineType!=LineType.FREEHAND) {
            line.setStroke(SettingsValues.lineColorProperty().get());
            line.setStrokeWidth(SettingsValues.lineWidthProperty().get());
        }

        layer.canvasProperty().get().getGraphicsContext2D().setLineWidth(SettingsValues.lineWidthProperty().get());
        SettingsValues.lineColorProperty().addListener(strokeColorListener);
        SettingsValues.lineWidthProperty().addListener(lineWidthListener);
    }

    public void enableLineDrawing() {

        trackingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, drawingMousePressedHandler);
        if(lineType== LineType.FREEHAND){
            trackingPane.addEventHandler(MouseEvent.MOUSE_RELEASED, freehandMouseReleasedHandler);
        }
    }



    @Override
    protected void drawingMouseListener(MouseEvent event) {
        if (!mousePressed) {
            startDrawingLine(event);
        } else {
            if(lineType== LineType.SINGLE_LINE) {
                drawAnotherStraightLine();
            }else {
                continuePolyLine();
            }
        }
    }



    private void startDrawingLine(MouseEvent event) {
        mousePressed = true;
        startPoint=new Point2D(event.getX(),event.getY());
        currentPoint=startPoint;

        if(lineType!= LineType.FREEHAND) {
            line = new Line(startPoint.getX(),startPoint.getY(),currentPoint.getX(),currentPoint.getY());
            trackingPane.getChildren().add(line);
            trackingPane.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);

        }else {
            trackingPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, drawingMouseDraggedHandler);
        }
        setVisualProperties();



    }

    private void drawAnotherStraightLine() {
        mousePressed = false;
        trackingPane.getChildren().remove(line);
        layer.canvasProperty().get().getGraphicsContext2D().strokeLine(startPoint.getX()-offsetPoint.getX(),
                startPoint.getY()-offsetPoint.getY(),currentPoint.getX()-offsetPoint.getX()
                ,currentPoint.getY()-offsetPoint.getY());
        trackingPane.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);
    }
    private void continuePolyLine() {

        layer.canvasProperty().get().getGraphicsContext2D().strokeLine(startPoint.getX()-offsetPoint.getX(),
                startPoint.getY()-offsetPoint.getY(),currentPoint.getX()-offsetPoint.getX()
                ,currentPoint.getY()-offsetPoint.getY());
        startPoint=currentPoint;
        if(lineType!=LineType.FREEHAND) {
            line.setStartX(startPoint.getX());
            line.setStartY(startPoint.getY());
        }

    }

    private void onMouseMoved(MouseEvent event) {
        updateCurrentPosition(event);
        line.setEndX(currentPoint.getX());
        line.setEndY(currentPoint.getY());

    }

    @Override
    protected void updateCurrentPosition(MouseEvent event) {
        if(!orthogonalDrawingFlag){
            currentPoint=new Point2D(event.getX(),event.getY());
        }else {
            if(Math.abs(currentPoint.getX()-startPoint.getX())>
                    Math.abs(currentPoint.getY()-startPoint.getY()))
            {
                currentPoint=new Point2D(event.getX(),startPoint.getY());
            }
            else {
                currentPoint=new Point2D(startPoint.getX(),event.getY());
            }
        }
    }
    private void freehandMouseDragged(MouseEvent event) {
            updateCurrentPosition(event);
            continuePolyLine();
    }
    private void freehandMouseReleased(MouseEvent event){
        mousePressed = false;
        trackingPane.removeEventHandler(MouseEvent.MOUSE_DRAGGED, drawingMouseDraggedHandler);
    }


    @Override
    protected void removeHandlersAndFinish() {
        removeBasicKeyHandlers();
        trackingPane.getChildren().remove(line);
        SettingsValues.lineColorProperty().removeListener(strokeColorListener);
        SettingsValues.lineWidthProperty().removeListener( lineWidthListener);
        trackingPane.removeEventHandler(MouseEvent.MOUSE_RELEASED, freehandMouseReleasedHandler);
        trackingPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, drawingMousePressedHandler);
        trackingPane.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);
        trackingPane.removeEventHandler(MouseEvent.MOUSE_DRAGGED, drawingMouseDraggedHandler);
    }
}

