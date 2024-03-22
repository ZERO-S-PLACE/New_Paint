package org.zeros.new_paint.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import org.zeros.new_paint.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public ScrollPane mainImageContainer;
    public SplitPane rightContainer;
    public AnchorPane topPane;
    public AnchorPane leftPane;
    public AnchorPane bottomPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainImageContainer.setContent(Model.getInstance().getViewFactory().getViewOfImageEditionPanel());

        topPane.getChildren().add(Model.getInstance().getViewFactory().getViewOfTopPanel());
        AnchorPane.setRightAnchor(Model.getInstance().getViewFactory().getViewOfTopPanel(),0.0);
        AnchorPane.setLeftAnchor(Model.getInstance().getViewFactory().getViewOfTopPanel(), 0.0);

        leftPane.getChildren().add(Model.getInstance().getViewFactory().getViewOfLeftPanel());
        AnchorPane.setBottomAnchor(Model.getInstance().getViewFactory().getViewOfLeftPanel(),0.0);
        AnchorPane.setTopAnchor(Model.getInstance().getViewFactory().getViewOfLeftPanel(), 0.0);

        rightContainer.getItems().add(Model.getInstance().getViewFactory().getViewOfRightPanel());
        AnchorPane.setBottomAnchor(Model.getInstance().getViewFactory().getViewOfRightPanel(),0.0);
        AnchorPane.setTopAnchor(Model.getInstance().getViewFactory().getViewOfRightPanel(), 0.0);

        bottomPane.getChildren().add(Model.getInstance().getViewFactory().getViewOfBottomPanel());
        AnchorPane.setRightAnchor(Model.getInstance().getViewFactory().getViewOfBottomPanel(),0.0);
        AnchorPane.setLeftAnchor(Model.getInstance().getViewFactory().getViewOfBottomPanel(), 0.0);

        mainImageContainer.fitToHeightProperty().set(true);
        mainImageContainer.fitToWidthProperty().set(true);

        




    }
}
