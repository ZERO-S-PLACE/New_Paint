package org.zeros.new_paint.Models;

import org.zeros.new_paint.Controllers.*;
import org.zeros.new_paint.Controllers.LeftPanel.ActionChoicePanelController;
import org.zeros.new_paint.Views.ViewFactory;

public class Model {

    private final ViewFactory viewFactory;
    private static Model model;
    ImageEditionPanelController imageEditionPanelController;
    ActionChoicePanelController leftPanelController;
    TopPanelController topPanelController;
    BottomPanelController bottomPanelController;



    public ImageEditionPanelController getImageEditionPanelController() {
        if(this.imageEditionPanelController==null){
            this.imageEditionPanelController = new ImageEditionPanelController();
        }
        return imageEditionPanelController;
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
    public ActionChoicePanelController getLeftPanelController() {
        if(this.leftPanelController==null){
            this.leftPanelController = new ActionChoicePanelController();
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
