package fr.hattane.ilias.deseigner.view;

import javax.swing.*;
import java.awt.*;

public class ProjectEditorPanel extends JPanel {
    private final PropertyPanel propertyPanel = new PropertyPanel();
    private final ModelCanvas canvas = new ModelCanvas(propertyPanel);

    public ProjectEditorPanel() {
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(propertyPanel, BorderLayout.EAST);
    }
}
