package de.appsist.am.controller;

import de.appsist.am.MainApp;
import de.appsist.am.PersistenceHandler;
import de.appsist.am.Session;
import de.appsist.am.model.VRScene;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Controller for the theme setting window.
 *
 * @author MAbouelenein
 */
public class ThemeSettingsController implements SceneController, Initializable {

    private final Session session;
    private final PersistenceHandler persistenceHandler;
    private Scene scene;
    private VRScene vrScene;
    private boolean removeScene;
    private Properties props;
      
    @FXML private Button saveButton;
    @FXML private RadioButton selectGlassroomRadioButton;
    @FXML private RadioButton selectAppsistRadioButton;
    
    
    public ThemeSettingsController(PersistenceHandler persistenceHandler, Session session) {
       this.session = session;
       this.persistenceHandler = persistenceHandler;
       this.vrScene = session.getVRScene();
       this.removeScene = false;
       this.selectGlassroomRadioButton = new RadioButton();
       this.selectAppsistRadioButton = new RadioButton();

    }
     
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        props = new Properties(); 
        URL urlPropertiesFile = getClass().getResource(MainApp.PROP_FILE_PATH);
        try { 
            InputStream in = urlPropertiesFile.openStream();
            props.load(in);
            urlPropertiesFile.openStream().close();
        } catch (IOException ex) {
            Logger.getLogger(ThemeSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(props.getProperty(MainApp.THEME_STRING).equals(MainApp.APPSIST_STRING))
        {
            selectGlassroomRadioButton.setSelected(false);
            selectAppsistRadioButton.setSelected(true);
        }
        else if(props.getProperty(MainApp.THEME_STRING).equals(MainApp.GLASSROOM_STRING))
        {
            selectGlassroomRadioButton.setSelected(true);
            selectAppsistRadioButton.setSelected(false);
        }
    }
    
    
    @FXML
    private void start(ActionEvent event) {
    }
    
    @FXML
    private void loadScene(ActionEvent event) {
        
    }

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @FXML
    private void selectGlassroomAction() {
    }

    @FXML
    private void selectAppsistAction() {
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
    
    @FXML
    private void save(ActionEvent event) {
        Stage stage = (Stage) scene.getWindow();
        if(selectGlassroomRadioButton.isSelected())
        {
            MainApp.themeOption = MainApp.GLASSROOM_STRING;
            MainApp.stylesheetOption = MainApp.STYLESHEET_GLASSROOM_STRING;
            MainApp.mainStage.setTitle("Glassroom Manager");
            if(MainApp.mainStage.getIcons().size()>0)
                MainApp.mainStage.getIcons().remove(0);
            MainApp.mainStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/logos/"+MainApp.ICON_GLASSROOM_STRING))); 
            String url = getClass().getResource("/themes/"+MainApp.STYLESHEET_GLASSROOM_STRING).toExternalForm();
            for (MainApp.AppScene theme : MainApp.AppScene.values())
            {
                if(MainApp.scenes.get(theme).getRoot().getStylesheets().size()>0)
                    MainApp.scenes.get(theme).getRoot().getStylesheets().remove(0);
                MainApp.scenes.get(theme).getRoot().getStylesheets().add(url);
            }
        }
        if(selectAppsistRadioButton.isSelected())
        {
            MainApp.themeOption =  MainApp.APPSIST_STRING;
            MainApp.stylesheetOption = MainApp.STYLESHEET_APPSIST_STRING;
            MainApp.mainStage.setTitle("Appsist Manager");
            if(MainApp.mainStage.getIcons().size()>0)
                MainApp.mainStage.getIcons().remove(0);
            MainApp.mainStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/logos/"+MainApp.ICON_APPSIST_STRING)));
            
            String url = getClass().getResource("/themes/"+MainApp.STYLESHEET_APPSIST_STRING).toExternalForm();
            for (MainApp.AppScene theme : MainApp.AppScene.values())
            {
                if(MainApp.scenes.get(theme).getRoot().getStylesheets().size()>0)
                    MainApp.scenes.get(theme).getRoot().getStylesheets().remove(0);
                MainApp.scenes.get(theme).getRoot().getStylesheets().add(url);
            }
        }  
        
        // write in properties file new theme
        props.replace(MainApp.THEME_STRING, MainApp.themeOption);
        props.replace(MainApp.STYLESHEET_STRING, MainApp.stylesheetOption);
        try {
            URL urlPropertiesFile = getClass().getResource(MainApp.PROP_FILE_PATH);
            String absolutePath = urlPropertiesFile.getPath();
            FileOutputStream out = new FileOutputStream(absolutePath);
            props.store(out, "Properties");
            urlPropertiesFile.openStream().close();
        } catch (IOException ex) {
            Logger.getLogger(ThemeSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.close();
    }
}