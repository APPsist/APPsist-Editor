package de.appsist.am;

import de.appsist.am.model.ContentModel;
import de.appsist.am.model.GuideManagerModel;
import de.appsist.am.model.GuideModel;
import de.appsist.am.model.NodeItem;
import de.appsist.am.model.StepModel;
import de.appsist.am.model.Tool;
import de.appsist.am.model.VRMethod;
import de.appsist.am.model.VRScene;
import de.appsist.ape.Chapter;
//import de.appsist.age.guide.json.JsonContent;
//import de.appsist.age.guide.json.JsonGuide;
import java.util.Stack;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/**
 * Model for a user session.
 * @author simon.schwantzer(at)im-c.de
 */
public class Session {
    public static class GuideStackItem {
        private final GuideModel guide;
        private final NodeItem selectedItem;
        
        /*public GuideStackItem(GuideModel guide, JsonGuide jsonguide, NodeItem selectedItem) {
            this.guide = guide;
            this.selectedItem = selectedItem;
        }*/

        public GuideStackItem(GuideModel guide, NodeItem selectedItem) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        public GuideModel getGuideModel() {
            return guide;
        }
        
        public NodeItem getSelectedItem() {
            return selectedItem;
        }
    }
    
    private final ObjectProperty<GuideManagerModel> guideManagerProperty;
    private final ObjectProperty<GuideModel> guideProperty;
//    private final ObjectProperty<JsonGuide> jsonGuideProperty;
//   private final ObjectProperty<JsonContent> jsonContentProperty;
    private final ObjectProperty<StepModel> stepProperty;
    private final ObjectProperty<ContentModel> contentProperty;
    private final ObjectProperty<Chapter> chapterProperty;
    private final ObjectProperty<VRScene> vrSceneProperty;
    
    private final Stack<GuideStackItem> guideStack;
    private ObservableList<Tool> tools;
    private ObservableList<VRMethod> vrMethods;
    
    public Session() {
        guideProperty = new SimpleObjectProperty<>();
//        jsonGuideProperty = new SimpleObjectProperty<>();
//        jsonContentProperty = new SimpleObjectProperty<>();
        guideManagerProperty = new SimpleObjectProperty<>();
        stepProperty = new SimpleObjectProperty<>();
        contentProperty = new SimpleObjectProperty<>();
        chapterProperty = new SimpleObjectProperty<>();
        guideStack = new Stack<>();
        vrSceneProperty = new SimpleObjectProperty<>();
    }

    public void setGuide(GuideModel guide) {
        guideProperty.set(guide);
    }
    
   /* public void setJsonGuide (JsonGuide jsonguide){
        jsonGuideProperty.set(jsonguide);
    }
    
    public void setJsonContent (JsonContent content){
        jsonContentProperty.set(content);
    }*/

    public GuideModel getGuide() {
        return guideProperty.get();
    }
    
    /*public JsonGuide getJsonGuide(){
        return jsonGuideProperty.get();
    }
    
    public JsonContent getJsonContent(){
        return jsonContentProperty.get();
    }*/
    
    public ObjectProperty<GuideModel> guideProperty() {
        return guideProperty;
    }
    
    /*public ObjectProperty<JsonGuide> jsonGuideProperty(){
        return jsonGuideProperty;
    }*/
    
    public void setVRScene(VRScene vrScene) {
         vrSceneProperty.set(vrScene);
    }
    
    public VRScene getVRScene() {
        return vrSceneProperty.get();
    }
    
    public ObjectProperty<VRScene> vrSceneProperty() {
        return vrSceneProperty;
    }
    
    public void setGuideManager(GuideManagerModel guideManagerModel) {
        guideManagerProperty.set(guideManagerModel);
    }
    
    public GuideManagerModel getGuideManager() {
        return guideManagerProperty.get();
    }
    
    public ObjectProperty<GuideManagerModel> guideManagerProperty() {
        return guideManagerProperty;
    }
    
    public Chapter getChapter() {
        return chapterProperty.get();
    }
    
    public void setChapter(Chapter chapter) {
        chapterProperty.set(chapter);
    }
    
    public ObjectProperty<Chapter> chapterProperty() {
        return chapterProperty;
    }
    
    public StepModel getStep() {
        return stepProperty.get();
    }
    
    public void setStep(StepModel step) {
        stepProperty.set(step);
    }
    
    public ObjectProperty<StepModel> stepProperty() {
        return stepProperty;
    }
    
    public ContentModel getContent() {
        return contentProperty.get();
    }
    
    public void setContent(ContentModel content) {
        contentProperty.set(content);
    }
    
    public ObjectProperty<ContentModel> contentProperty() {
        return contentProperty;
    }
    
    public Stack<GuideStackItem> getGuideStack() {
        return guideStack;
    }
    
    public void setTools(ObservableList<Tool> tools) {
        this.tools = tools;
    }
    
    public ObservableList<Tool> getTools() {
        return tools;
    }
    
    public void setVRMethods(ObservableList<VRMethod> methods) {
        this.vrMethods = methods;
    }
    
    public ObservableList<VRMethod> getVRMethods() {
        return vrMethods;
    }
}
