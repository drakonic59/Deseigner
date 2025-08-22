package fr.hattane.ilias.deseigner.view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = -6046308218369357949L;
	
	public MenuPanel(Runnable startEditor) {
        setLayout(new GridLayout(3, 1, 10, 10));
        JButton newProject = new JButton("Créer un nouveau projet");
        JButton editProject = new JButton("Modifier un projet");
        JButton continueProject = new JButton("Continuer le dernier projet");
        newProject.addActionListener(e -> startEditor.run());
        editProject.addActionListener(e -> startEditor.run());
        continueProject.addActionListener(e -> startEditor.run());
        add(newProject);
        add(editProject);
        add(continueProject);
    }
	
}
