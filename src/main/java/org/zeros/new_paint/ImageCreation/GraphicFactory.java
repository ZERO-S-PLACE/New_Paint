package org.zeros.new_paint.ImageCreation;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class GraphicFactory {
    protected Pane trackingPane;
    protected boolean orthogonalDrawingFlag =false;
    protected Point2D offsetPoint;
    protected Point2D startPoint;
    protected boolean mousePressed = false;
    protected Point2D currentPoint;
    protected EventHandler<KeyEvent> escKeyHandler = this::onEscPressed;
    protected EventHandler<MouseEvent> drawingMousePressedHandler = this::drawingMouseListener;
    protected EventHandler<KeyEvent> orthogonalKeyHandler = this::onOrthogonalShiftHold;

    protected abstract void drawingMouseListener(MouseEvent event);

    protected abstract void updateCurrentPosition(MouseEvent event);

    private void onEscPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE||event.getCode()==KeyCode.ENTER) {
            removeHandlersAndFinish();
        }
    }

    protected abstract void removeHandlersAndFinish();

    private void onOrthogonalShiftHold(KeyEvent event) {
        if (event.getCode() == KeyCode.SHIFT) {
            orthogonalDrawingFlag = event.getEventType().equals(KeyEvent.KEY_PRESSED);
        }
    }

    protected void addBasicKeyEventsHandlers(){
        trackingPane.getScene().addEventHandler(KeyEvent.KEY_PRESSED, escKeyHandler);
        trackingPane.getScene().addEventHandler(KeyEvent.KEY_PRESSED, orthogonalKeyHandler);
        trackingPane.getScene().addEventHandler(KeyEvent.KEY_RELEASED, orthogonalKeyHandler);
    }
    protected void removeBasicKeyHandlers(){

        trackingPane.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, escKeyHandler);
        trackingPane.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, orthogonalKeyHandler);
        trackingPane.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, orthogonalKeyHandler);
    }

}
