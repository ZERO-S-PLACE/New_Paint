package org.zeros.new_paint.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.zeros.new_paint.Models.Layers.*;
import org.zeros.new_paint.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ImageEditionPanelController implements Initializable {
    private static ObservableMap<String, Layer> layerMap;
    public AnchorPane layersContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        layersContainer.getChildren().addAll(Model.getInstance().getViewFactory().getTrackingPane());
        AnchorPane.setBottomAnchor(Model.getInstance().getViewFactory().getTrackingPane(), 0.0);
        AnchorPane.setLeftAnchor(Model.getInstance().getViewFactory().getTrackingPane(), 0.0);
        AnchorPane.setRightAnchor(Model.getInstance().getViewFactory().getTrackingPane(), 0.0);
        AnchorPane.setTopAnchor(Model.getInstance().getViewFactory().getTrackingPane(), 0.0);
        BackgroundFill backgroundFill = new BackgroundFill(Color.TRANSPARENT, null, null);
        Background background = new Background(backgroundFill);
        Model.getInstance().getViewFactory().getTrackingPane().setBackground(background);
        layerMap = FXCollections.observableHashMap();
        addDefaultLayer();

    }


    private void addDefaultLayer() {
        RasterLayer backgroundLayer = Model.getInstance().getViewFactory().getDrawingBackground(true);
        layerMap.put(backgroundLayer.nameProperty().get(), backgroundLayer);
        layersContainer.getChildren().addFirst(((RasterLayer) (layerMap.get("Background"))).canvasProperty().get());
        AnchorPane.setLeftAnchor(backgroundLayer.canvasProperty().get(), backgroundLayer.offsetCoordinatesProperty().get().getX());
        AnchorPane.setTopAnchor(backgroundLayer.canvasProperty().get(), backgroundLayer.offsetCoordinatesProperty().get().getY());

    }


    public Layer getLayerByName(String name) {
        return layerMap.get(name);
    }

    public void addLayer(Layer layer) {
        Model.getInstance().getViewFactory().updateTrackingPaneOverlay();

        layerMap.put(layer.nameProperty().get(), layer);
        if(layer.getLayerType().equals(LayerType.GRAPHIC)) {
            layersContainer.getChildren().add(layersContainer.getChildren().size() - 1,
                    ((RasterLayer) layerMap.get(layer.nameProperty().get())).canvasProperty().get());
            AnchorPane.setLeftAnchor(((RasterLayer)layer).canvasProperty().get(), ((RasterLayer)layer).offsetCoordinatesProperty().get().getX());
            AnchorPane.setTopAnchor(((RasterLayer)layer).canvasProperty().get(), ((RasterLayer)layer).offsetCoordinatesProperty().get().getY());
        }else {
            layersContainer.getChildren().add(layersContainer.getChildren().size() - 1,
                    ((VectorLayer) (layerMap.get(layer.nameProperty().get()))).drawingPaneProperty().get());
            AnchorPane.setLeftAnchor(((VectorLayer)layer).drawingPaneProperty().get(), 0.0);
            AnchorPane.setTopAnchor(((VectorLayer)layer).drawingPaneProperty().get(), 0.0);
        }
    }

    public void removeLayer(Layer layer) {

        if (layer.getLayerType().equals(LayerType.GRAPHIC)) {
            layersContainer.getChildren().remove(
                    ((RasterLayer) (layerMap.get(layer.nameProperty().get()))).canvasProperty().get());
        }else {
            layersContainer.getChildren().remove(
                    ((VectorLayer) (layerMap.get(layer.nameProperty().get()))).drawingPaneProperty().get());
        }


        layerMap.remove(layer.nameProperty().get(), layer);
    }



}
