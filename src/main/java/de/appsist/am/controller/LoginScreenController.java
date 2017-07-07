package de.appsist.am.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;

/**
 * Controller for the login window.
 * @author jens.bienias(at)im-c.de
 */
public class LoginScreenController implements SceneController {
    //@FXML private Button okButton;
    private Scene scene;
    
    @FXML
    private void initialize() throws IOException {
        System.out.println("HELLO WORLD");   
        /*JsonGuide guide = new JsonGuide("my-first-guide");
        guide.setContentId("en-US", "guide-metadata-en");
        guide.addCustomTag("isFake");
        guide.addTypedTag("SHOW_IN_CATALOG");
        guide.grantAllAccess();
        
        Milestone initialState = new Milestone("init");
        initialState.setContentId("en-US", "initial-state-content");
        guide.addStep(initialState);
        
        Action action = new Action("action-01");
        action.setContentId("en-US", "step-content");
        guide.addStep(action);
        
        Milestone endState = new Milestone("end");
        endState.setContentId("en-US", "end-state-content");
        guide.addStep(endState);
        
        System.out.println(GuideUtil.exportGuide(guide, true));
        
        Content actionContent =  new Content("step-content");
        actionContent.setLanguageId("en-US");
        actionContent.setDescription("Come on, get up and do something.");
        actionContent.setMedia(new Media("video/mp4", "jump.mp4"));
        IconizedMessage warning = new IconizedMessage("Evaluate the risks first.");
        warning.setIcon("icons/warning.png");
        actionContent.addWarning(warning);
        actionContent.addHint(new IconizedMessage("Pick a fun task!"));

        System.out.println(GuideUtil.exportContent(actionContent, true));*/
    }

   @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
