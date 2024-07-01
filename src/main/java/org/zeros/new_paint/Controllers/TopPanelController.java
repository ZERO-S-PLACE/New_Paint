package org.zeros.new_paint.Controllers;

import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TopPanelController implements Initializable {
    public SplitPane pickersContainer;
    public HBox linePropertyPickerContainer;
    public ColorPicker lineColorPicker;
    public Slider lineWeightSlider;
    public TextField lineWeightTextField;
    public Slider lineTransparencySlider;
    public TextField lineTransparencyTextField;
    public HBox fillPropertyPickerContainer;
    public ColorPicker fillColorPicker;
    public Slider fillTransparencySlider;
    public TextField fillTransparencyTextField;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
