package de.appsist.am;

import de.appsist.am.controller.*;
import de.appsist.am.model.GuideManagerModel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main application.
 * @author simon.schwantzer(at)im-c.de
 */
public class MainApp extends Application {
    public static final String LANG = "de_DE";
    public static Stage mainStage;
    public static String stylesheetOption;
    public static String themeOption;
    public static final String GLASSROOM_TITLE = "GLASSROOM Manager";
    public static final String APPSIST_TITLE = "APPSIST Editor";
    public static final String GLASSROOM_STRING = "Glassroom";
    public static final String APPSIST_STRING = "Appsist";
    public static final String STYLESHEET_STRING = "stylesheet";
    public static final String THEME_STRING = "theme";
    public static final String STYLESHEET_GLASSROOM_STRING = "style_glassroom.css";
    public static final String STYLESHEET_APPSIST_STRING = "style_appsist.css";
    public static final String ICON_GLASSROOM_STRING = "logo_glassroom.png";
    public static final String ICON_APPSIST_STRING = "logo_appsist.png";
    public static final String PROP_FILE_PATH = "/config/client.properties";
    
    /**
     * Enum for available scenes.
     */
    public static enum AppScene {
        MAIN,
        SETTINGS,
        OPEN_GUIDE,
        SYNCHRONIZE,
        EDIT_GUIDE,
        LOGIN_SCREEN
    }
    
    public static final AppConfiguration CONFIG;
    
    private final PersistenceHandler persistenceHandler;
    @FXML public static Map<AppScene, Scene> scenes;
    private final Session session;
    
    static {
        CONFIG = new AppConfiguration();
    }
    
    public MainApp() {
        scenes = new HashMap<>();
        session = new Session();
        persistenceHandler = new PersistenceHandler();
        mainStage = new Stage();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
    // read properties file to decide which theme (Appsist or Glassroom)
        Properties props = new Properties(); 
        URL urlPropertiesFile = getClass().getResource(PROP_FILE_PATH);
        try { 
            InputStream in = urlPropertiesFile.openStream();
            props.load(in);
            urlPropertiesFile.openStream().close();
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stylesheetOption = props.getProperty(STYLESHEET_STRING);
        themeOption = props.getProperty(THEME_STRING);
        
        if(themeOption.equalsIgnoreCase(GLASSROOM_STRING)){
            stage.setTitle(GLASSROOM_TITLE);
            stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/logos/"+ICON_GLASSROOM_STRING)));
        }
        else if(themeOption.equalsIgnoreCase(APPSIST_STRING)){
            stage.setTitle(APPSIST_TITLE);    
            stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/logos/"+ICON_APPSIST_STRING)));
        }
        
        MainController mainController = new MainController(scenes, persistenceHandler, session);
        initScene(AppScene.MAIN, "/fxml/MainWindow.fxml", mainController);
        initScene(AppScene.SETTINGS, "/fxml/SettingsWindow.fxml", new SettingsController(scenes, persistenceHandler, session));
        initScene(AppScene.OPEN_GUIDE, "/fxml/OpenGuideWindow.fxml", new OpenGuideController(session));
        initScene(AppScene.LOGIN_SCREEN, "/fxml/LoginScreen.fxml", new LoginScreenController());
        initScene(AppScene.SYNCHRONIZE, "/fxml/SynchronizeWindow.fxml", new SynchronizeController(persistenceHandler, session));
        initScene(AppScene.EDIT_GUIDE, "/fxml/EditGuideWindow.fxml", new EditGuideController(persistenceHandler, session));

        stage.setScene(scenes.get(AppScene.MAIN));
        stage.show();
        mainStage=stage;
        
        String repoDirPath = CONFIG.getRepoDir();
        if (repoDirPath != null) {
            File repoDir = new File(repoDirPath);
            if (repoDir.exists()) {
                GuideManagerModel gmm = new GuideManagerModel(persistenceHandler.initializeRepository(repoDir), LANG);
                session.setGuideManager(gmm);
            } else {
                mainController.showPreferences();
            }
        } else {
            mainController.showPreferences();
        }
    }
    
    private void initScene(AppScene appScene, String fxmlPath, SceneController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        
        if(scene.getRoot().getStylesheets().size()>0)
            scene.getRoot().getStylesheets().clear();
        String url = getClass().getResource("/themes/"+stylesheetOption).toExternalForm();
        scene.getRoot().getStylesheets().add(url);
        scenes.put(appScene, scene);
        controller.setScene(scene);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}