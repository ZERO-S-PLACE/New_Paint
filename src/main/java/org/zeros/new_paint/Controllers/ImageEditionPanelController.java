package org.zeros.new_paint.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import org.zeros.new_paint.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ImageEditionPanelController implements Initializable {

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

    }
}




