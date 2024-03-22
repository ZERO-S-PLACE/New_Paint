package org.zeros.new_paint.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import org.zeros.new_paint.Models.*;
import org.zeros.new_paint.Models.Layers.Directory;
import org.zeros.new_paint.Models.Layers.Layer;
import org.zeros.new_paint.Models.Layers.LayerType;
import org.zeros.new_paint.Models.Layers.RasterLayer;
import org.zeros.new_paint.Views.LayerTreeCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RightPanelController implements Initializable {
    public TreeView <Layer> layersTree;
    public Button addLayerButton;
    public Button addDirectoryButton;
    TreeItem<Layer> root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Directory rootDirectory =new Directory("New_Project");
        root = new TreeItem<>(rootDirectory);
        layersTree.setRoot(root);
        layersTree.setCellFactory(param ->new LayerTreeCellFactory());
        layersTree.getSelectionModel().select(root);
        addLayerButton.setOnAction(e->createEmptyLayer(false));
        addDirectoryButton.setOnAction(e->createEmptyLayer(true));
        layersTree.addEventHandler(MouseEvent.MOUSE_CLICKED,this::stopDrawing);

    }

    public Layer getSelectedLayer(){
        System.out.println(layersTree.getSelectionModel().getSelectedItem().getValue().nameProperty().get());
        return layersTree.getSelectionModel().getSelectedItem().getValue();
    }

    public void addLayer(String layerName){
       TreeItem<Layer> item= new TreeItem<>(Model.getInstance().getImageEditionPanelController().getLayerByName(layerName));
        addToNearestDirectory(item);


    }
    public void createEmptyLayer(boolean isDirectory){
        Layer layer;
        if(isDirectory) {
            int number = countLayersContainingPhrase(root,"New_Directory")+1;
            layer = new Directory("New_Directory"+number);
        }else {
            int number = countLayersContainingPhrase(root,"Drawing")+1;
            layer = new RasterLayer(Parameters.getDEFAULT_WIDTH(), Parameters.getDEFAULT_HEIGHT(),
                    Parameters.getDEFAULT_X_OFFSET(), Parameters.getDEFAULT_Y_OFFSET(), "Drawing"+number);
            Model.getInstance().getImageEditionPanelController().addLayer(layer);
        }
        TreeItem<Layer> item= new TreeItem<>();
        item.setValue(layer);
        addToNearestDirectory(item);

    }



    private void addToNearestDirectory(TreeItem<Layer> item) {
        if( layersTree.getSelectionModel().getSelectedItem().getValue().getLayerType().equals(LayerType.DIRECTORY))
        {layersTree.getSelectionModel().getSelectedItem().getChildren().add(item);
            layersTree.getSelectionModel().select(item);

        }else{
            layersTree.getSelectionModel().getSelectedItem().getParent().getChildren().add(item);
            layersTree.getSelectionModel().select(item);
        }
    }

    public void removeLayer(Layer layer){
        selectTreeItemByValue(root,layer);
        layersTree.getSelectionModel().getSelectedItem().getParent().getChildren().remove(layersTree.getSelectionModel().getSelectedItem());
    }

    private void selectTreeItemByValue(TreeItem<Layer> itemRoot, Layer layer) {
        if (itemRoot.getValue().equals(layer)) {
            itemRoot.setExpanded(true);
            layersTree.getSelectionModel().select(itemRoot);

        } else {

            for (TreeItem<Layer> child : itemRoot.getChildren()) {
                selectTreeItemByValue(child, layer);
            }
        }

        }

        public int countLayersContainingPhrase(TreeItem<Layer> itemRoot,String phrase){
        if(itemRoot==null)
        {
            itemRoot=root;
        }
        int count=0;
            if (itemRoot.getValue().nameProperty().get().contains(phrase)){
                count++;

            }

            for (TreeItem<Layer> child : itemRoot.getChildren()) {
                   count=count+countLayersContainingPhrase(child, phrase);
            }

        return count;
        }
    private void stopDrawing(MouseEvent event){
        Model.getInstance().getLeftPanelController().resetChoice();
    }
    public void disableButtons(boolean setDisabled){
        addLayerButton.setDisable(setDisabled);
        addDirectoryButton.setDisable(setDisabled);
    }

    public void  switchChildrenVisibility(Layer layer){
        selectTreeItemByValue(root,layer);
        TreeItem<Layer> currentItem=layersTree.getSelectionModel().getSelectedItem();
        boolean visible =currentItem.getValue().visibleProperty().getValue();
       // currentItem.setExpanded(true);

        for(TreeItem<Layer> item:layersTree.getSelectionModel().getSelectedItem().getChildren()){

            item.getValue().visibleProperty().setValue(visible);
            if(!item.getChildren().isEmpty()) {
                switchChildrenVisibility(item.getValue());
            }
        }
       // currentItem.setExpanded(false);


    }
}
