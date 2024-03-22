package org.zeros.new_paint.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.zeros.new_paint.Models.*;
import org.zeros.new_paint.Models.Layers.Layer;
import org.zeros.new_paint.Models.Layers.LayerType;
import org.zeros.new_paint.Models.Layers.RasterLayer;
import org.zeros.new_paint.Models.Layers.VectorLayer;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LayerCellController implements Initializable {
    public FontAwesomeIconView layerTypeSymbol;
    public TextField nameTextField;
    public Label nameLabel;
    public Button lockButton;
    public FontAwesomeIconView lockIcon;
    public Button visibilityButton;
    public FontAwesomeIconView visibilityIcon;
    public Button deleteButton;
    private Layer layer;
    private String layerName;
    EventHandler<KeyEvent> enterKeyHandler = this::onEnterPressed;

    public LayerCellController(Layer layer) {
        this.layer = layer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(layer.nameProperty().get().isEmpty()){
           getLayerName();
        }
        else {
            nameLabel.setText(layer.nameProperty().get());
        }

        if(layer.nameProperty().get().contains("Background")||layer.nameProperty().get().contains("PROJECT"))
        {deleteButton.setVisible(false);
            lockButton.setVisible(false);
        visibilityButton.setVisible(false);}

        nameLabel.addEventHandler(MouseEvent.MOUSE_CLICKED,this::nameEditOnClick);
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::deleteCurrentLayer);
        visibilityButton.addEventHandler(MouseEvent.MOUSE_CLICKED,this::changeVisibilityOnClick);
        layer.visibleProperty().addListener((observable, oldValue, newValue) -> switchVisibility(newValue));

        setLayerSymbol();

    }

    public void   getLayerName(){
        nameLabel.setVisible(false);
        nameTextField.setVisible(true);
        nameTextField.addEventHandler(KeyEvent.KEY_PRESSED,enterKeyHandler);
        Model.getInstance().getLeftPanelController().disableButtons(true);
        Model.getInstance().getRightPanelController().disableButtons(true);
    }


    private void onEnterPressed(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            layerName=nameTextField.getText();
            nameTextField.removeEventHandler(KeyEvent.KEY_PRESSED,enterKeyHandler);
            nameTextField.setVisible(false);
            nameLabel.setVisible(true);
            nameLabel.setText(layerName);
            layer.nameProperty().setValue(layerName);
            if(!layer.getLayerType().equals(LayerType.DIRECTORY)) {
                createNewDrawingLayer();
            }
            Model.getInstance().getLeftPanelController().disableButtons(false);
            Model.getInstance().getRightPanelController().disableButtons(false);
        }
    }

    private void createNewDrawingLayer() {
        Model.getInstance().getRightPanelController().removeLayer(layer);
        layer = new RasterLayer(Parameters.getDEFAULT_WIDTH(), Parameters.getDEFAULT_HEIGHT(),
                Parameters.getDEFAULT_X_OFFSET(), Parameters.getDEFAULT_Y_OFFSET(), layerName);
        Model.getInstance().getImageEditionPanelController().addLayer(layer);
        Model.getInstance().getRightPanelController().addLayer(layerName);
    }

    private void nameEditOnClick(MouseEvent event){
        if(event.getClickCount()==2){
            getLayerName();

        }
    }

    public void deleteCurrentLayer(MouseEvent event){
        if(event.getClickCount()==2){

            if(!layer.getLayerType().equals(LayerType.DIRECTORY)) {
                Model.getInstance().getImageEditionPanelController().removeLayer( layer);
            }
            Model.getInstance().getRightPanelController().removeLayer(layer);
        }
    }
    private void setLayerSymbol(){
        switch (layer.getLayerType()){
            case DIRECTORY -> layerTypeSymbol.setGlyphName("ARCHIVE");
            case GRAPHIC -> layerTypeSymbol.setGlyphName("PAINT_BRUSH");
            case VECTOR -> {
                switch (((VectorLayer)layer).getShapeType()){
                    case OVAL ->layerTypeSymbol.setGlyphName("CIRCLE_THIN");
                    case RECTANGLE ->layerTypeSymbol.setGlyphName("SQUARE_O");
                    case POLY_LINE ->layerTypeSymbol.setGlyphName("OPENID");
                    case CURVE ->layerTypeSymbol.setGlyphName("OPENCART");
                    case POLYGON ->layerTypeSymbol.setGlyphName("CONNECTDEVELOP");
                }
        }}
    }
    private void changeVisibilityOnClick(MouseEvent event){
        layer.visibleProperty().set(!layer.visibleProperty().get());
        if (Objects.requireNonNull(layer.getLayerType()) == LayerType.DIRECTORY) {
            Model.getInstance().getRightPanelController().switchChildrenVisibility(layer);
        }

    }

    public void switchVisibility(boolean newVal){
        if(newVal) {
            visibilityIcon.setGlyphName("EYE");
        }else {
            visibilityIcon.setGlyphName("EYE_SLASH");
        }
    }



}
