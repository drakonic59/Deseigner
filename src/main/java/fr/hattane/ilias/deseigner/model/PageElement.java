package fr.hattane.ilias.deseigner.model;

public class PageElement extends DesignElement {
    public PageElement(long id, String name, int index, Dimensions dimensions) {
        super(id, name, ElementType.PAGE, index, dimensions);
    }
}
