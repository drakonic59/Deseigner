package fr.hattane.ilias.deseigner;

import fr.hattane.ilias.deseigner.view.frames.MainFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
