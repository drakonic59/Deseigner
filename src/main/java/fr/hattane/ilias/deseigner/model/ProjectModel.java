package fr.hattane.ilias.deseigner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectModel {
    private long id;
    private String name;
    private String description;
    private Dimensions dimensions;
    private Map<String, Object> properties = new HashMap<>();
    private List<DesignElement> elements = new ArrayList<>();

    public ProjectModel(long id, String name, String description, Dimensions dimensions) {
        this.id = id;
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
}
