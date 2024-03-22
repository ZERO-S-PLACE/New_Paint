package org.zeros.new_paint.Controllers;

import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.zeros.new_paint.ImageCreation.SettingsValues;
import org.zeros.new_paint.ImageCreation.RasterShapes.RasterLineFactory;
import org.zeros.new_paint.ImageCreation.VectorShapes.*;
import org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLineFactory;
import org.zeros.new_paint.ImageCreation.VectorShapes.VectorShapeFactory;
import org.zeros.new_paint.Models.Layers.LayerType;
import org.zeros.new_paint.Models.LineType;
import org.zeros.new_paint.Models.Model;
import org.zeros.new_paint.Models.ShapeType;

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
        drawStraightLineButton.setOnAction(e-> setOnRasterLineDrawing(LineType.SINGLE_LINE));
        drawPolyLineButton.setOnAction(e-> setOnRasterLineDrawing(LineType.POLY_LINE));
        freeHandLineButton.setOnAction(e-> setOnRasterLineDrawing(LineType.FREEHAND));
        vectorPolyLineButton.setOnAction(e-> setOnVectorDrawing(ShapeType.POLY_LINE));


        SettingsValues.bucketFillColorProperty().set(colorPicker.getValue());
        colorPicker.addEventHandler(EventType.ROOT,e-> SettingsValues.bucketFillColorProperty().set(colorPicker.getValue()));
    }

    private void setOnRasterLineDrawing(LineType lineType) {

        resetChoice();
        if(Model.getInstance().getRightPanelController().getSelectedLayer().getLayerType().equals(LayerType.GRAPHIC)) {
            RasterLineFactory shapeFactory = new RasterLineFactory(lineType);
            shapeFactory.enableLineDrawing();
        }else {
            Model.getInstance().getRightPanelController().createEmptyLayer(false);
        }
    }
    private void setOnVectorDrawing(ShapeType shapeType) {

        resetChoice();
        VectorShapeFactory factory =null;
        switch (shapeType){
            case POLY_LINE -> factory=new VectorPolyLineFactory();
            case RECTANGLE -> factory=new VectorRectangleFactory();
            case CURVE -> factory=new VectorCurveFactory();
            case OVAL -> factory=new VectorOvalFactory();
            case POLYGON -> factory=new VectorPolygonFactory();

        }
        factory.enableDrawing();


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

    public Color currentColor(){
        return colorPicker.getValue();
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
