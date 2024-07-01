package org.zeros.new_paint.Controllers;

import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.zeros.new_paint.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftPanelController implements Initializable {
    public Button drawStraightLineButton;
    public Button drawPolyLineButton;
    public Button drawCurveButton;
    public Button drawOvalButton;
    public Button DrawRectangleButton;
    public Button drawPolygonButton;
    public Button moveElementButton;
    public Button freeHandLineButton;
    public ColorPicker colorPicker;
    public Button vectorPolyLineButton;
    public Button vectorCurveButton;
    public Button vectorClosedPolyLineButton;
    public Button vectorClosedCurveButton;
    public Button drawRectangleButton;
    public Button rectangleSelectionButton;
    public Button polylineSelectionButton;
    public Button magicSelectionButton;
    public Button fillButton;
    public Button moveElementButton11;
    public Button eraserButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void resetChoice() {
        Model.getInstance().getViewFactory().getTrackingPane().fireEvent(new KeyEvent(
                KeyEvent.KEY_PRESSED,
                "",
                "",
                KeyCode.ESCAPE,
                false,
                false,
                false,
                false));
    }


    public void disableButtons(boolean setDisabled){
        drawStraightLineButton.setDisable(setDisabled);
       drawPolyLineButton.setDisable(setDisabled);
        drawCurveButton.setDisable(setDisabled);
        drawOvalButton.setDisable(setDisabled);
        DrawRectangleButton.setDisable(setDisabled);
        drawPolygonButton.setDisable(setDisabled);
       moveElementButton.setDisable(setDisabled);
       freeHandLineButton.setDisable(setDisabled);
        colorPicker.setDisable(setDisabled);
        vectorCurveButton.setDisable(setDisabled);
        vectorClosedPolyLineButton.setDisable(setDisabled);
        vectorClosedCurveButton.setDisable(setDisabled);
        drawRectangleButton.setDisable(setDisabled);
        rectangleSelectionButton.setDisable(setDisabled);
        polylineSelectionButton.setDisable(setDisabled);
        magicSelectionButton.setDisable(setDisabled);
        fillButton.setDisable(setDisabled);
        moveElementButton11.setDisable(setDisabled);
        eraserButton.setDisable(setDisabled);
    }
}
