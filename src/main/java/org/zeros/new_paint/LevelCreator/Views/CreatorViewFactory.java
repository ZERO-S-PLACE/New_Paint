package org.zeros.new_paint.LevelCreator.Views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.zeros.new_paint.LevelCreator.Models.CreatorModel;

import java.io.IOException;

public class CreatorViewFactory {

    private BorderPane mainWindow;
    private AnchorPane leftPanel;
    private BorderPane topPanel;
    private BorderPane bottomPanel;
    private AnchorPane imageEditionPanel;

    private AnchorPane trackingPane;





    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LevelCreator/MainWindow.fxml"));
        createStage(loader);
    }

    public AnchorPane getViewOfLeftPanel() {
        if (leftPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/LevelCreator/LeftPanels/ActionChoicePanel.fxml"));
                loader.setController(CreatorModel.getInstance().getLeftPanelController());
                leftPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return leftPanel;
    }


    public AnchorPane getViewOfImageEditionPanel() {

        if (imageEditionPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/LevelCreator/ImageEditionPanel.fxml"));
                loader.setController(CreatorModel.getInstance().getImageEditionPanelController());
                imageEditionPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return imageEditionPanel;
    }

    public BorderPane getViewOfTopPanel() {

        if (topPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/LevelCreator/TopPanel.fxml"));
                loader.setController(CreatorModel.getInstance().getTopPanelController());
                topPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return topPanel;
    }
    public BorderPane getViewOfBottomPanel() {

        if (bottomPanel == null) {
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/LevelCreator/BottomPanel.fxml"));
                loader.setController(CreatorModel.getInstance().getBottomPanelController());
                bottomPanel =loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return bottomPanel;
    }

    public AnchorPane getTrackingPane() {
        if(this.trackingPane==null){
            this.trackingPane = new AnchorPane();
        }
        return trackingPane;
    }
    public void updateTrackingPaneOverlay(){
        trackingPane.getChildren().removeAll(trackingPane.getChildren());
       /* Rectangle rectangle1=new Rectangle(trackingPane.getBoundsInLocal().getWidth(), background.offsetCoordinatesProperty().get().getY());
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
        trackingPane.getChildren().addAll(rectangle1,rectangle2,rectangle3,rectangle4);*/


    }



    private static void createStage(FXMLLoader loader) {
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(CreatorViewFactory.class.getResource("/Icons/ProgramIcon.png"))));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Zeros Paint");
        stage.show();
    }



}
