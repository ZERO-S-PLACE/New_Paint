package org.zeros.new_paint.Views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.zeros.new_paint.Models.Layers.RasterLayer;
import org.zeros.new_paint.Models.Model;
import org.zeros.new_paint.Models.Parameters;

import java.io.IOException;

public class ViewFactory {

    private BorderPane mainWindow;
    private AnchorPane leftPanel;
    private AnchorPane topPanel;
    private AnchorPane bottomPanel;
    private AnchorPane rightPanel;
    private AnchorPane imageEditionPanel;
    private RasterLayer background;
    private AnchorPane trackingPane;





    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
        createStage(loader);
    }

    public AnchorPane getViewOfLeftPanel() {
        if (leftPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/LeftPanel.fxml"));
                loader.setController(Model.getInstance().getLeftPanelController());
                leftPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return leftPanel;
    }

    public AnchorPane getViewOfRightPanel() {

        if (rightPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/RightPanel.fxml"));
                loader.setController(Model.getInstance().getRightPanelController());
                rightPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return rightPanel;
    }

    public AnchorPane getViewOfImageEditionPanel() {

        if (imageEditionPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/ImageEditionPanel.fxml"));
                loader.setController(Model.getInstance().getImageEditionPanelController());
                imageEditionPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return imageEditionPanel;
    }

    public AnchorPane getViewOfTopPanel() {

        if (topPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/TopPanel.fxml"));
                loader.setController(Model.getInstance().getTopPanelController());
                topPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return topPanel;
    }
    public AnchorPane getViewOfBottomPanel() {

        if (bottomPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/BottomPanel.fxml"));
                loader.setController(Model.getInstance().getBottomPanelController());
                bottomPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return bottomPanel;
    }

    public RasterLayer getDrawingBackground(boolean recreate){
        if(recreate||background==null) {
            background = new RasterLayer(Parameters.getDEFAULT_WIDTH(), Parameters.getDEFAULT_HEIGHT(),
                    Parameters.getDEFAULT_X_OFFSET(), Parameters.getDEFAULT_Y_OFFSET(), "Background");
            background.canvasProperty().get().getGraphicsContext2D().setFill(Color.WHITE);
            background.canvasProperty().get().getGraphicsContext2D().fillRect(0, 0,
                    background.canvasProperty().get().getWidth(), background.canvasProperty().get().getHeight());
        }
        return background;
    }

    public AnchorPane getTrackingPane() {
        if(this.trackingPane==null){
            this.trackingPane = new AnchorPane();
        }
        return trackingPane;
    }
    public void updateTrackingPaneOverlay(){
        trackingPane.getChildren().removeAll(trackingPane.getChildren());
        Rectangle rectangle1=new Rectangle(trackingPane.getBoundsInLocal().getWidth(), background.offsetCoordinatesProperty().get().getY());
        Rectangle rectangle2=new Rectangle(trackingPane.getBoundsInLocal().getWidth(),
                trackingPane.getBoundsInLocal().getHeight()- background.offsetCoordinatesProperty().get().getY()- background.heightProperty().get());
        Rectangle rectangle3=new Rectangle(background.offsetCoordinatesProperty().get().getX(),trackingPane.getBoundsInLocal().getHeight());
        Rectangle rectangle4=new Rectangle(trackingPane.getBoundsInLocal().getWidth()- background.offsetCoordinatesProperty().get().getX()-
                background.widthProperty().get(),trackingPane.getBoundsInLocal().getHeight());
        rectangle1.setFill(Parameters.getSidesOverlayColor());
        rectangle2.setFill(Parameters.getSidesOverlayColor());
        rectangle3.setFill(Parameters.getSidesOverlayColor());
        rectangle4.setFill(Parameters.getSidesOverlayColor());

        AnchorPane.setTopAnchor(rectangle1,0.0);
        AnchorPane.setBottomAnchor(rectangle2,0.0);
        AnchorPane.setLeftAnchor(rectangle3,0.0);
        AnchorPane.setRightAnchor(rectangle4,0.0);
        trackingPane.getChildren().addAll(rectangle1,rectangle2,rectangle3,rectangle4);


    }



    private static void createStage(FXMLLoader loader) {
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(ViewFactory.class.getResource("/Icons/ProgramIcon.png"))));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Zeros Paint");
        stage.show();
    }



}
