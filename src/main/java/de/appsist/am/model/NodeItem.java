package de.appsist.am.model;

import de.appsist.ape.Node;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon.schwantzer(at)im-c.de
 */
public interface NodeItem {
    public enum Type {
        STEP,
        CHAPTER
    }
    
    public Node getNode();
    public StringProperty labelProperty();
    public Type getType();
}
