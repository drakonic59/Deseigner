package fr.hattane.ilias.deseigner.view.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import fr.hattane.ilias.deseigner.view.panels.editor.ModelCanvas;
import fr.hattane.ilias.deseigner.view.panels.editor.PropertyPanel;

public class ProjectEditorPanel extends JPanel {
	
	private static final long serialVersionUID = 975617998984467670L;
	
	private final PropertyPanel propertyPanel = new PropertyPanel();
    private final ModelCanvas canvas = new ModelCanvas(propertyPanel);

    public ProjectEditorPanel() {
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(propertyPanel, BorderLayout.EAST);
    }
    
}
