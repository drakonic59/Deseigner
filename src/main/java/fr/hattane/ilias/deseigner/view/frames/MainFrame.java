package fr.hattane.ilias.deseigner.view.frames;

import java.awt.CardLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

import fr.hattane.ilias.deseigner.model.ProjectModel;
import fr.hattane.ilias.deseigner.model.serializers.ModelSerializer;

import fr.hattane.ilias.deseigner.view.panels.MenuPanel;
import fr.hattane.ilias.deseigner.view.panels.ProjectEditorPanel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -4811040262377922922L;
	
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final ProjectEditorPanel editorPanel = new ProjectEditorPanel();

    public MainFrame() {
        super("Deseigner");
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(896, 414);
        setLocationRelativeTo(null);
        initMenuBar();
        initPanels();
    }

    private void initMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem openItem = new JMenuItem("Ouvrir");
        JMenuItem saveItem = new JMenuItem("Sauvegarder");
        JMenuItem exportItem = new JMenuItem("Exporter HTML");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exportItem);
        bar.add(fileMenu);
        bar.add(Box.createHorizontalGlue());
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(e -> dispose());
        bar.add(closeButton);
        setJMenuBar(bar);

        openItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (FileReader reader = new FileReader(chooser.getSelectedFile())) {
                    ProjectModel model = new ModelSerializer().read(reader);
                    startEditor(model);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        saveItem.addActionListener(e -> {
            if (editorPanel.getProject() == null) return;
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (FileWriter writer = new FileWriter(chooser.getSelectedFile())) {
                    new ModelSerializer().write(editorPanel.getProject(), writer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initPanels() {
        MenuPanel menuPanel = new MenuPanel(this::startEditor);
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(editorPanel, "editor");
        add(mainPanel);
        cardLayout.show(mainPanel, "menu");
    }

    private void startEditor(ProjectModel model) {
        editorPanel.setProject(model);
        cardLayout.show(mainPanel, "editor");
    }
    
}
