package fr.hattane.ilias.deseigner.view.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.hattane.ilias.deseigner.model.ProjectModel;
import fr.hattane.ilias.deseigner.view.panels.editor.ModelCanvas;
import fr.hattane.ilias.deseigner.view.panels.editor.PropertyPanel;

public class ProjectEditorPanel extends JPanel {
	
	private static final long serialVersionUID = 975617998984467670L;
	
    private final PropertyPanel propertyPanel = new PropertyPanel();
    private final JScrollPane propertyScroll = new JScrollPane(propertyPanel);
    private ModelCanvas canvas;
    private ProjectModel project;

    public ProjectEditorPanel() {
        setLayout(new BorderLayout());
        propertyScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        propertyScroll.setVisible(false);
        add(propertyScroll, BorderLayout.EAST);
    }

    public void setProject(ProjectModel project) {
        this.project = project;
        if (canvas != null) {
            remove(canvas);
        }
        canvas = new ModelCanvas(propertyPanel, project);
        add(canvas, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public ProjectModel getProject() {
        return project;
    }

}
