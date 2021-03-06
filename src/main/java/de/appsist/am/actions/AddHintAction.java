/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.appsist.am.actions;

import de.appsist.am.ActionFailedException;
import de.appsist.am.PersistenceHandler;
import de.appsist.am.model.ContentModel;
import de.appsist.am.model.GuideModel;
import de.appsist.am.model.HintModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Schwantzer
 */
public class AddHintAction extends BaseAction {
    private final static Logger LOGGER = Logger.getLogger(AddHintAction.class.getName());
    
    private final HintModel hint;
    private final PersistenceHandler persistenceHandler;
    private final GuideModel guide;
    private final ContentModel content;
    
    public AddHintAction(HintModel hint, ContentModel content, GuideModel guideModel, PersistenceHandler persistenceHandler) {
        this.hint = hint;
        this.persistenceHandler = persistenceHandler;
        this.guide = guideModel;
        this.content = content;
    }

    @Override
    public void perform() {
        content.hintsProperty().add(hint);
        try {
            writeChange();
            notifyActionPerformed();
        } catch (ActionFailedException ex) {
            notifyActionFailed(ex);
        }
    }

    @Override
    public void undo() {
        content.hintsProperty().remove(hint);
        try {
            writeChange();
            notifyActionUndone();
        } catch (ActionFailedException ex) {
            notifyUndoFailed(ex);
        }
    }
    
    private void writeChange() throws ActionFailedException {
        try {
            persistenceHandler.writeContentDescriptor(guide.getBean(), content.getBean());
        } catch (IOException ex) {
           LOGGER.log(Level.SEVERE, "Failed to update content descriptor.", ex);
           throw new ActionFailedException("Failed to update content descriptor.", ex);
        }
    }

    @Override
    public String getDescription() {
        return ("Hinweis hinzufügen");
    }
   
}
