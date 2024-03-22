package org.zeros.new_paint.ImageCreation.VectorShapes;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.zeros.new_paint.ImageCreation.GraphicFactory;
import org.zeros.new_paint.ImageCreation.SettingsValues;
import org.zeros.new_paint.Models.*;
import org.zeros.new_paint.Models.Layers.VectorLayer;

public abstract class VectorShapeFactory extends GraphicFactory {

    protected int selectedDrawingMethod = 0;
    protected int numberOfDrawingMethods;

    protected VectorLayer layer;
    protected ShapeType shapeType;
    protected EventHandler<KeyEvent> switchMethodKeyHandler = this::onSwitchKeyPressed;


    public VectorShapeFactory() {
        this.trackingPane = Model.getInstance().getViewFactory().getTrackingPane();
        setShapeType();

        generateLayer();
        addBasicKeyEventsHandlers();
        setColors();
        offsetPoint = new Point2D(layer.offsetCoordinatesProperty().get().getX(), layer.offsetCoordinatesProperty().get().getY());
    }

    protected void setColors() {
        layer.setLineColor(SettingsValues.lineColorProperty().get());
        layer.setFillColor(SettingsValues.fillColorProperty().get());
        layer.setLineWidth(SettingsValues.lineWidthProperty().get());
    }

    protected abstract void setShapeType();


    @Override
    protected void updateCurrentPosition(MouseEvent event) {
        if (!orthogonalDrawingFlag) {
            currentPoint = new Point2D(event.getX(), event.getY());
        } else {
            if (Math.abs(event.getX() - layer.lastPoint().getX()) >
                    Math.abs(event.getY() - layer.lastPoint().getY())) {
                currentPoint = new Point2D(event.getX(), layer.lastPoint().getY());
            } else {
                currentPoint = new Point2D(layer.lastPoint().getX(), event.getY());
            }
        }
    }


    protected void generateLayer() {
        int shapeNumber = Model.getInstance().getRightPanelController()
                .countLayersContainingPhrase(null, String.valueOf(shapeType));

        layer = new VectorLayer(Parameters.getDEFAULT_X_OFFSET(), Parameters.getDEFAULT_Y_OFFSET(),
                String.valueOf(shapeType) + shapeNumber, shapeType);
        Model.getInstance().getImageEditionPanelController().addLayer(layer);
        Model.getInstance().getRightPanelController().addLayer(layer.nameProperty().get());
    }


    abstract protected void startDrawingShape(MouseEvent event);

    public void enableDrawing() {
        trackingPane.addEventHandler(MouseEvent.MOUSE_PRESSED, drawingMousePressedHandler);
    }

    protected abstract void onSwitchKeyPressed(KeyEvent event);


    @Override
    protected void addBasicKeyEventsHandlers() {
        super.addBasicKeyEventsHandlers();
        trackingPane.getScene().addEventHandler(KeyEvent.KEY_PRESSED, switchMethodKeyHandler);

    }

    @Override
    protected void removeBasicKeyHandlers() {
        super.removeBasicKeyHandlers();
        trackingPane.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, switchMethodKeyHandler);
        trackingPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, drawingMousePressedHandler);
    }
}


