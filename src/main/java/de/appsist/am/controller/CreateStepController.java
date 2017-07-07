/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.appsist.am.controller;

import de.appsist.am.PersistenceHandler;
import de.appsist.am.Session;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;

/**
 *
 * @author Bienias
 */
public class CreateStepController implements SceneController {
    
    private Scene scene;
    private final PersistenceHandler persistenceHandler;
    private final Session session;

    public CreateStepController(PersistenceHandler persistenceHandler, Session session) {
        this.persistenceHandler = persistenceHandler;
        this.session = session;
    }
    
    @FXML
    private void initialize() throws IOException {
        //System.out.println("Initialized Create Step Window");
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
