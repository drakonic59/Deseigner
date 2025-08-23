package fr.hattane.ilias.deseigner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.hattane.ilias.deseigner.model.elements.DesignElement;

public class ProjectModel {

    private static long ids = 0;

    private long id;
    private String name;
    private String description;

    private float scale = 1.0f;

    private final Map<String, Object> properties = new HashMap<>();
    private final List<DesignElement> elements = new ArrayList<>();

    public ProjectModel(String name, String description) {
        this.id = ++ids;
        this.name = name;
        this.description = description;
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
