package org.zeros.new_paint.Controllers;

import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import org.zeros.new_paint.ImageCreation.SettingsValues;
import org.zeros.new_paint.Models.Parameters;

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

        configureColorPickers();
        configureLineWeightFlow();
        configureLineTransparencyFlow();
        configureFillTransparencyFlow();

    }

    private void configureLineWeightFlow() {

        lineWeightSlider.setMin(0);
        lineWeightSlider.setMax(Math.log(5000));
        lineWeightSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            SettingsValues.lineWidthProperty().set(Math.pow(Math.E,((Double) newValue)));
            lineWeightTextField.setText(decimalFormat.format(SettingsValues.lineWidthProperty().get()));
        }));
        lineWeightTextField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {

                try {
                    double newVal = Double.parseDouble(lineWeightTextField.getText());
                    if (newVal <= 5000 && newVal > 0) {
                        SettingsValues.lineWidthProperty().set(newVal);
                    } else {
                        lineWeightTextField.setText(decimalFormat.format(SettingsValues.lineWidthProperty().get()));
                    }
                } catch (Exception e) {
                    lineWeightTextField.setText(decimalFormat.format(SettingsValues.lineWidthProperty().get()));
                }
                lineWeightSlider.setValue(Math.log(SettingsValues.lineWidthProperty().getValue()));
            }
        });
        lineWeightTextField.setText(String.valueOf(Parameters.getDEFAULT_LINE_WEIGHT()));
    }

    private void configureLineTransparencyFlow(){

        lineTransparencySlider.setMin(0);
        lineTransparencySlider.setMax(100);
        lineTransparencySlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            SettingsValues.setLineTransparency((Double) newValue);
            lineTransparencyTextField.setText(decimalFormat.format(SettingsValues.getLineTransparency()));
        }));

        lineTransparencyTextField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {

                try {
                    double newVal = Double.parseDouble(lineTransparencyTextField.getText());
                    if (newVal <= 100 && newVal > 0) {
                        SettingsValues.setLineTransparency(newVal);
                    } else {
                        lineTransparencyTextField.setText(decimalFormat.format(SettingsValues.getLineTransparency()));
                    }
                } catch (Exception e) {
                    lineTransparencyTextField.setText(decimalFormat.format(SettingsValues.getLineTransparency()));
                }
                lineTransparencySlider.setValue(SettingsValues.getLineTransparency());
            }
        });

        lineTransparencyTextField.setText("0.0");


    }

    private void configureFillTransparencyFlow(){

        fillTransparencySlider.setMin(0);
        fillTransparencySlider.setMax(100);
        fillTransparencySlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            SettingsValues.setFillTransparency((Double) newValue);
            fillTransparencyTextField.setText(decimalFormat.format(SettingsValues.getFillTransparency()));
        }));
        fillTransparencyTextField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {

                try {
                    double newVal = Double.parseDouble(fillTransparencyTextField.getText());
                    if (newVal <= 100 && newVal > 0) {
                        SettingsValues.setFillTransparency(newVal);
                    } else {
                        fillTransparencyTextField.setText(decimalFormat.format(SettingsValues.getFillTransparency()));
                    }
                } catch (Exception e) {
                    fillTransparencyTextField.setText(decimalFormat.format(SettingsValues.getFillTransparency()));
                }
                fillTransparencySlider.setValue(SettingsValues.getFillTransparency());
            }
        });
        fillTransparencyTextField.setText("0.0");


    }




    private void configureColorPickers() {
        SettingsValues.fillColorProperty().set(fillColorPicker.getValue());
        fillColorPicker.addEventHandler(EventType.ROOT, e-> SettingsValues.bucketFillColorProperty().set(
                fillColorPicker.getValue()));
        SettingsValues.lineColorProperty().set(lineColorPicker.getValue());
        lineColorPicker.addEventHandler(EventType.ROOT, e-> SettingsValues.lineColorProperty().set(
                lineColorPicker.getValue()));
    }
}
