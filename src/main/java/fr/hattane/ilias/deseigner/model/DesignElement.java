package fr.hattane.ilias.deseigner.model;

import java.util.HashMap;
import java.util.Map;

public abstract class DesignElement {
    private long id;
    private String name;
    private ElementType type;
    private int index;
    private Dimensions dimensions;
    private Map<String, Object> properties = new HashMap<>();

    protected DesignElement(long id, String name, ElementType type, int index, Dimensions dimensions) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.index = index;
        this.dimensions = dimensions;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
