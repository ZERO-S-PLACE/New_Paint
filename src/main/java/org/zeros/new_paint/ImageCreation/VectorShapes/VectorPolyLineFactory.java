package org.zeros.new_paint.ImageCreation.VectorShapes;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine.*;
import org.zeros.new_paint.Models.Parameters;
import org.zeros.new_paint.Models.ShapeType;

public class VectorPolyLineFactory extends VectorShapeFactory {
    private PolyLineSegment nextSegment;

    private final EventHandler<MouseEvent> mouseMovedHandler = this::onMouseMoved;
    private final EventHandler<KeyEvent> closePolyLineKeyHandler = this::onClosePolyLineKeyPressed;


    public VectorPolyLineFactory() {
        super();
        trackingPane.getScene().addEventHandler(KeyEvent.KEY_PRESSED, closePolyLineKeyHandler);
        numberOfDrawingMethods = 5;
    }


    @Override
    protected void setShapeType() {
        this.shapeType = ShapeType.POLY_LINE;
    }

    @Override
    protected void drawingMouseListener(MouseEvent event) {
        if (!mousePressed) {
            mousePressed = true;
            startDrawingShape(event);
        } else {
            continueDrawingShape();
        }
    }

    @Override
    public void startDrawingShape(MouseEvent event) {
        startPoint = new Point2D(event.getX(), event.getY());
        currentPoint = startPoint;
        layer.addPoint(startPoint);
        layer.pathProperty().get().getElements().addFirst(new MoveTo(startPoint.getX(), startPoint.getY()));
        trackingPane.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);
        updateDrawingMethod();
        nextSegment.createNextPathElement();

    }

    private void continueDrawingShape() {
        nextSegment.save();
        startPoint = layer.lastPoint();
        nextSegment.createNextPathElement();
    }

    private void onMouseMoved(MouseEvent event) {
        updateCurrentPosition(event);
        nextSegment.updateShape(currentPoint);


    }

    @Override
    protected void onSwitchKeyPressed(KeyEvent event) {
        if (event.getCode().equals(Parameters.getSWITCH_KEY_CODE())) {
            selectedDrawingMethod = (selectedDrawingMethod + 1) % numberOfDrawingMethods;
            nextSegment.dismiss();
            updateDrawingMethod();
            nextSegment.createNextPathElement();
        }

    }

    private void updateDrawingMethod() {
        switch (selectedDrawingMethod) {
            case 0 -> nextSegment = new StraightLine(layer);
            case 1 -> nextSegment = new EllipticalCurve(layer);
            case 2 -> nextSegment=new Arc(layer);
            case 3 ->nextSegment=new CurveWithControlPoint(layer);
            case 4 ->nextSegment=new CurveTroughPoint(layer);

        }
    }

    @Override
    protected void addBasicKeyEventsHandlers() {
        super.addBasicKeyEventsHandlers();

    }

    @Override
    protected void removeBasicKeyHandlers() {
        super.removeBasicKeyHandlers();
        trackingPane.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMovedHandler);
        trackingPane.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, closePolyLineKeyHandler);
    }

    protected void onClosePolyLineKeyPressed(KeyEvent event) {
        if (event.getCode() == Parameters.getCLOSE_KEY_CODE()) {
            layer.closedProperty().set(!layer.closedProperty().get());
            setColors();
        }
    }

    @Override
    protected void removeHandlersAndFinish() {
        nextSegment.dismiss();

        if (layer.closedProperty().get()) {
            layer.pathProperty().get().getElements().add(new LineTo(layer.firstPoint().getX(), layer.firstPoint().getY()));
        }
        removeBasicKeyHandlers();

    }


}
