package org.zeros.new_paint.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeCell;
import org.zeros.new_paint.Controllers.LayerCellController;
import org.zeros.new_paint.Models.Layers.Layer;

public  class LayerTreeCellFactory extends TreeCell<Layer> {
    @Override
        protected void updateItem(Layer layer, boolean empty) {
        super.updateItem(layer,empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LayerCell.fxml"));
            LayerCellController controller = new LayerCellController(layer);
            loader.setController(controller);
            setText(null);

            try {
                setGraphic(loader.load());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        }

}
