package fr.hattane.ilias.deseigner.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

public class ModelSerializer {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void write(ProjectModel model, Writer writer) {
        gson.toJson(model, writer);
    }

    public ProjectModel read(Reader reader) {
        return gson.fromJson(reader, ProjectModel.class);
    }
}
