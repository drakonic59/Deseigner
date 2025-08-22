package fr.hattane.ilias.deseigner.view;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class MenuPanel extends JPanel {
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
