package fr.hattane.ilias.deseigner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.hattane.ilias.deseigner.model.elements.DesignElement;
import fr.hattane.ilias.deseigner.model.utils.Dimensions;

public class ProjectModel {
	
	public static long ids = 0;
	
    private long id;
    private String name;
    private String description;
    
    private Dimensions dimensions;
    private float scale = 1.0f;
    
    private Map<String, Object> properties = new HashMap<>();
    private List<DesignElement> elements = new ArrayList<>();

    public ProjectModel(String name, String description, Dimensions dimensions) {
        this.id = ++ids;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<DesignElement> getElements() {
        return elements;
    }

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
