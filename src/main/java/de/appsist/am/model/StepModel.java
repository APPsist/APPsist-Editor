package de.appsist.am.model;

import de.appsist.ape.Step;
import de.appsist.ape.annotations.SceneAnnotation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class StepModel {
    private final Step step;
    private final ObjectProperty<SceneAnnotation> sceneProperty;
    
    public StepModel(Step step) {
        this.step = step;
        sceneProperty = new SimpleObjectProperty<SceneAnnotation>(step.getContent().getScene()) {
            @Override
            public void set(SceneAnnotation scene) {
                super.set(scene);
                step.getContent().setScene(scene);
            }
        };
    }
    
    public Step getBean() {
        return step;
    }
    
    public ObjectProperty<SceneAnnotation> sceneProperty() {
        return sceneProperty;
    }
    
    public void setScene(SceneAnnotation scene) {
        sceneProperty.set(scene);
    }
    
    public SceneAnnotation getScene() {
        return sceneProperty.get();
    }
}
