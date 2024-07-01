package org.zeros.new_paint.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.zeros.new_paint.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ActionChoicePanelController implements Initializable {

    public Button movingObjectButton;
    public Button obstacleButton;
    public Button targetAreaButton;
    public Button movingObjectToAddButton;
    public Button obstacleToAddButton;
    public Button moveElementButton11;
    public FontAwesomeIconView moveButton;
    public Button rotateButton;
    public Button inputAreaButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonsGraphic();
    }

    private void setButtonsGraphic() {
       // movingObjectButton.setGraphic(getGraphic("BallMoving"));


    }
private ImageView getGraphic(String name){
/*
    Image image = new Image(getClass().getResource("path/to/your/image.png").toExternalForm());

    // Create an ImageView with the desired size
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(50);  // Set the desired width
    imageView.setFitHeight(50); // Set the desired height
    imageView.setPreserveRatio(true); // Preserve the aspect ratio*/
    return null;

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

    }
}
