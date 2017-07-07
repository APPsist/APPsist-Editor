package de.appsist.am.actions;

import de.appsist.am.ActionFailedException;
import de.appsist.am.PersistenceHandler;
import de.appsist.am.model.GuideModel;
import de.appsist.am.model.StepModel;
import de.appsist.ape.annotations.ToolAnnotation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon.schwantzer(at)im-c.de
 */
public class AddToolAction extends BaseAction {
    private final static Logger LOGGER = Logger.getLogger(AddToolAction.class.getName());
    
    private final ToolAnnotation toolAnnotation;
    private final PersistenceHandler persistenceHandler;
    private final GuideModel guide;
    private final StepModel step;
    
    public AddToolAction(ToolAnnotation toolAnnotation, StepModel step, GuideModel guideModel, PersistenceHandler persistenceHandler) {
        this.toolAnnotation = toolAnnotation;
        this.persistenceHandler = persistenceHandler;
        this.guide = guideModel;
        this.step = step;
    }

    @Override
    public void perform() {
        step.getBean().getContent().addTool(toolAnnotation);
        try {
            writeChange();
            notifyActionPerformed();
        } catch (ActionFailedException ex) {
            notifyActionFailed(ex);
        }
    }

    @Override
    public void undo() {
        step.getBean().getContent().getTools().remove(toolAnnotation);
        try {
            writeChange();
            notifyActionUndone();
        } catch (ActionFailedException ex) {
            notifyUndoFailed(ex);
        }
    }
    
    private void writeChange() throws ActionFailedException {
        try {
            persistenceHandler.writeGuide(guide.getBean());
        } catch (IOException ex) {
           LOGGER.log(Level.SEVERE, "Failed to update guide descriptor.", ex);
           throw new ActionFailedException("Failed to update guide descriptor.", ex);
        }
    }

    @Override
    public String getDescription() {
        return ("Werkzeug hinzufügen");
    }
   
}
