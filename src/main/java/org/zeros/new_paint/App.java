package org.zeros.new_paint;

import javafx.application.Application;
import javafx.stage.Stage;
import org.zeros.new_paint.Models.Model;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Model.getInstance().getViewFactory().showMainWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}