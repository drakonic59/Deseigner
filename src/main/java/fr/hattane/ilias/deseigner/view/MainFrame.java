package fr.hattane.ilias.deseigner.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

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
        JMenuItem saveItem = new JMenuItem("Sauvegarder");
        JMenuItem exportItem = new JMenuItem("Exporter HTML");
        fileMenu.add(saveItem);
        fileMenu.add(exportItem);
        bar.add(fileMenu);
        bar.add(Box.createHorizontalGlue());
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(e -> dispose());
        bar.add(closeButton);
        setJMenuBar(bar);
    }

    private void initPanels() {
        MenuPanel menuPanel = new MenuPanel(this::showEditor);
        ProjectEditorPanel editorPanel = new ProjectEditorPanel();
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(editorPanel, "editor");
        add(mainPanel);
        cardLayout.show(mainPanel, "menu");
    }

    private void showEditor() {
        cardLayout.show(mainPanel, "editor");
    }
}
