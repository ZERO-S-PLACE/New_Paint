package org.zeros.new_paint.Models;

import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.zeros.new_paint.Controllers.*;
import org.zeros.new_paint.Views.ViewFactory;

import javax.crypto.spec.OAEPParameterSpec;

public class Model {

    private final ViewFactory viewFactory;
    private static Model model;
    ImageEditionPanelController imageEditionPanelController;
    RightPanelController rightPanelController;
    LeftPanelController leftPanelController;
    TopPanelController topPanelController;
    BottomPanelController bottomPanelController;



    public ImageEditionPanelController getImageEditionPanelController() {
        if(this.imageEditionPanelController==null){
            this.imageEditionPanelController = new ImageEditionPanelController();
        }
        return imageEditionPanelController;
    }



    public RightPanelController getRightPanelController() {
        if(this.rightPanelController==null){
            this.rightPanelController = new RightPanelController();
        }
        return rightPanelController;
    }


    public TopPanelController getTopPanelController() {
        if(this.topPanelController==null){
            this.topPanelController = new TopPanelController();
        }
        return topPanelController;
    }
    public BottomPanelController getBottomPanelController() {
        if(this.bottomPanelController==null){
            this.bottomPanelController = new BottomPanelController();
        }
        return bottomPanelController;
    }
    public LeftPanelController getLeftPanelController() {
        if(this.leftPanelController==null){
            this.leftPanelController = new LeftPanelController();
        }
        return leftPanelController;
    }




    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    private Model() {
        this.viewFactory = new ViewFactory();

    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

}
