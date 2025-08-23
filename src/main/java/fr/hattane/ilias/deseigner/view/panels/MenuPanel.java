package fr.hattane.ilias.deseigner.view.panels;

import fr.hattane.ilias.deseigner.model.ProjectModel;
import fr.hattane.ilias.deseigner.model.serializers.ModelSerializer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class MenuPanel extends JPanel {

    private static final long serialVersionUID = -6046308218369357949L;

    public MenuPanel(Consumer<ProjectModel> startEditor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("Deseigner");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton newProject = new JButton("Créer un nouveau projet");
        JButton editProject = new JButton("Modifier un projet");
        JButton continueProject = new JButton("Continuer le dernier projet");

        newProject.setAlignmentX(Component.CENTER_ALIGNMENT);
        editProject.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueProject.setAlignmentX(Component.CENTER_ALIGNMENT);

        newProject.addActionListener(e -> {
            JTextField name = new JTextField();
            JTextField desc = new JTextField();
            Object[] message = {"Nom", name, "Description", desc};
            int option = JOptionPane.showConfirmDialog(this, message, "Nouveau projet", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                ProjectModel model = new ProjectModel(name.getText(), desc.getText());
                startEditor.accept(model);
            }
        });

        editProject.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (FileReader reader = new FileReader(chooser.getSelectedFile())) {
                    ProjectModel model = new ModelSerializer().read(reader);
                    startEditor.accept(model);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        continueProject.addActionListener(e -> startEditor.accept(new ProjectModel("Projet", "")));

        add(title);
        add(Box.createVerticalStrut(30));
        add(newProject);
        add(Box.createVerticalStrut(10));
        add(editProject);
        add(Box.createVerticalStrut(10));
        add(continueProject);
    }

}
