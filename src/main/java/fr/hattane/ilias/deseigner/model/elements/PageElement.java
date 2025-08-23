package fr.hattane.ilias.deseigner.model.elements;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.elements.types.RectangleElement;

public class PageElement extends RectangleElement {

    public PageElement(String name, int width, int height) {
        super(name, 0, 0, width, height, ElementTypes.PAGE);
    }
}
