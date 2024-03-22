module org.zeros.new_paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;

    opens org.zeros.new_paint to javafx.fxml;
    exports org.zeros.new_paint;
    exports org.zeros.new_paint.Controllers;
    exports org.zeros.new_paint.Views;
    exports org.zeros.new_paint.ImageCreation;
    exports org.zeros.new_paint.Models;
    exports org.zeros.new_paint.Models.Layers;
    exports org.zeros.new_paint.VectorMath;
    exports org.zeros.new_paint.ImageCreation.VectorShapes;
    exports org.zeros.new_paint.ImageCreation.VectorShapes.VectorPolyLine;
    exports org.zeros.new_paint.ImageCreation.RasterShapes;
}