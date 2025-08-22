package fr.hattane.ilias.deseigner.model.elements;

import java.util.HashMap;
import java.util.Map;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.utils.Dimensions;

public abstract class DesignElement {
	
	public static long ids = 0;
	
    private long id;
    private String name;
    private ElementTypes type;
    private int index;
    private Dimensions dimensions;
    private Map<String, Object> properties = new HashMap<>();

    protected DesignElement(String name, ElementTypes type, int index, Dimensions dimensions) {
        this.id = ++ids;
        this.name = name;
        this.type = type;
        this.index = index;
        this.dimensions = dimensions;
        this.properties = new HashMap<>();
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

    public ElementTypes getType() {
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
