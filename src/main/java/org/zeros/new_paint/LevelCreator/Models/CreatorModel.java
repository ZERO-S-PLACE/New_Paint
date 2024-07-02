package org.zeros.new_paint.LevelCreator.Models;

import org.zeros.new_paint.LevelCreator.Controllers.*;
import org.zeros.new_paint.LevelCreator.Controllers.LeftPanel.ActionChoicePanelController;
import org.zeros.new_paint.LevelCreator.Views.CreatorViewFactory;

public class CreatorModel {

    private final CreatorViewFactory viewFactory;
    private static CreatorModel model;
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




    public CreatorViewFactory getViewFactory() {
        return viewFactory;
    }

    private CreatorModel() {
        this.viewFactory = new CreatorViewFactory();

    }

    public static synchronized CreatorModel getInstance() {
        if (model == null) {
            model = new CreatorModel();
        }
        return model;
    }

}
