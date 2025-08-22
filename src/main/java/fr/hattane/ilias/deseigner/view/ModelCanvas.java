package fr.hattane.ilias.deseigner.view;

import fr.hattane.ilias.deseigner.model.ElementType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Zone de dessin principale affichant la grille et les éléments.
 */
public class ModelCanvas extends JPanel {
    private final JPopupMenu contextMenu = new JPopupMenu();
    private final List<ElementView> elements = new ArrayList<>();
    private final PropertyPanel propertyPanel;
    private Point lastClick = new Point();
    private ElementView target;

    public ModelCanvas(PropertyPanel panel) {
        this.propertyPanel = panel;
        setBackground(Color.WHITE);
        setLayout(null);
        initContextMenu();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) showMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) showMenu(e);
            }
        });
    }

    private final JMenuItem editItem = new JMenuItem("Modifier l'élément");

    private void initContextMenu() {
        JMenu addMenu = new JMenu("Ajouter");
        JMenuItem rectItem = new JMenuItem("Rectangle");
        rectItem.addActionListener(e -> addElement(ElementType.RECTANGLE));
        addMenu.add(rectItem);
        contextMenu.add(addMenu);

        editItem.addActionListener(e -> propertyPanel.setElement(target));
        contextMenu.add(editItem);
    }

    private void addElement(ElementType type) {
        ElementView el = new ElementView(lastClick.x, lastClick.y, 100, 60);
        el.setType(type);
        elements.add(el);
        add(el);
        repaint();
    }

    private ElementView findElement(Point p) {
        for (ElementView el : elements) {
            if (el.getBounds().contains(p)) return el;
        }
        return null;
    }

    private void showMenu(MouseEvent e) {
        lastClick = e.getPoint();
        target = findElement(lastClick);
        editItem.setEnabled(target != null);
        contextMenu.show(this, e.getX(), e.getY());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int step = 20;
        g.setColor(new Color(230, 230, 230));
        for (int x = 0; x < getWidth(); x += step) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += step) {
            g.drawLine(0, y, getWidth(), y);
        }
    }
}
