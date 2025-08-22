package fr.hattane.ilias.deseigner.model.elements;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.utils.Dimensions;

public class PageElement extends DesignElement {
	
    public PageElement(String name, int index, Dimensions dimensions) {
        super(name, ElementTypes.PAGE, index, dimensions);
    }
    
}
