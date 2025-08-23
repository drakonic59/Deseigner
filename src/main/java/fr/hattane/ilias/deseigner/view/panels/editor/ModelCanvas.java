package fr.hattane.ilias.deseigner.view.panels.editor;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.ProjectModel;
import fr.hattane.ilias.deseigner.model.elements.DesignElement;
import fr.hattane.ilias.deseigner.model.elements.types.RectangleElement;
import fr.hattane.ilias.deseigner.model.elements.types.TextElement;
import fr.hattane.ilias.deseigner.model.elements.types.ButtonElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Zone de dessin principale affichant la grille et les éléments.
 */
public class ModelCanvas extends JPanel {

    private static final long serialVersionUID = 1465933412461813546L;

    private final JPopupMenu contextMenu = new JPopupMenu();
    private final List<ElementView> elements = new ArrayList<>();
    private final PropertyPanel propertyPanel;
    private final ProjectModel project;
    private Point lastClick = new Point();
    private ElementView target;
    private ElementView selected;
    private Point panStart;
    private float offsetX = 0;
    private float offsetY = 0;

    public ModelCanvas(PropertyPanel panel, ProjectModel project) {
        this.propertyPanel = panel;
        this.project = project;
        setBackground(Color.WHITE);
        setLayout(null);
        initContextMenu();
        for (DesignElement el : project.getElements()) {
            if (el instanceof RectangleElement) {
                ElementView view = new ElementView(this, (RectangleElement) el);
                view.setType(el.getType());
                elements.add(view);
                add(view);
                updateElementView(view);
            }
        }

        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showMenu(e);
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    panStart = e.getPoint();
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    selectElement(findElement(toModel(e.getPoint())));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) showMenu(e);
                if (SwingUtilities.isMiddleMouseButton(e)) panStart = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (panStart != null) {
                    float scale = project.getScale();
                    int dx = e.getX() - panStart.x;
                    int dy = e.getY() - panStart.y;
                    offsetX += dx / scale;
                    offsetY += dy / scale;
                    panStart = e.getPoint();
                    updateElementViews();
                    repaint();
                }
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                float old = project.getScale();
                float scale = old;
                if (e.getWheelRotation() < 0) {
                    scale *= 1.1f;
                } else {
                    scale /= 1.1f;
                }
                if (scale < 0.1f) scale = 0.1f;
                if (scale > 5f) scale = 5f;
                float factor = scale / old;
                offsetX = offsetX * factor;
                offsetY = offsetY * factor;
                project.setScale(scale);
                updateElementViews();
                repaint();
            }
        };
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);
    }

    private final JMenuItem editItem = new JMenuItem("Modifier l'élément");

    private void initContextMenu() {
        JMenu addMenu = new JMenu("Ajouter");
        JMenuItem rectItem = new JMenuItem("Rectangle");
        rectItem.addActionListener(e -> addElement(ElementTypes.RECTANGLE));
        addMenu.add(rectItem);
        JMenuItem textItem = new JMenuItem("Zone de texte");
        textItem.addActionListener(e -> addElement(ElementTypes.TEXT));
        addMenu.add(textItem);
        JMenuItem btnItem = new JMenuItem("Bouton");
        btnItem.addActionListener(e -> addElement(ElementTypes.BUTTON));
        addMenu.add(btnItem);
        contextMenu.add(addMenu);

        editItem.addActionListener(e -> propertyPanel.setElement(target));
        contextMenu.add(editItem);
    }

    private Point toModel(Point p) {
        float scale = project.getScale();
        int x = Math.round(p.x / scale - offsetX);
        int y = Math.round(p.y / scale - offsetY);
        return new Point(x, y);
    }

    private void addElement(ElementTypes type) {
        RectangleElement rect;
        if (type == ElementTypes.TEXT) {
            rect = new TextElement("Texte", lastClick.x, lastClick.y, 100, 40);
        } else if (type == ElementTypes.BUTTON) {
            rect = new ButtonElement("Bouton", lastClick.x, lastClick.y, 120, 40);
        } else {
            rect = new RectangleElement("Rectangle", lastClick.x, lastClick.y, 100, 60);
        }
        project.getElements().add(rect);
        ElementView el = new ElementView(this, rect);
        el.setType(type);
        elements.add(el);
        add(el);
        selectElement(el);
        updateElementView(el);
    }

    private ElementView findElement(Point p) {
        for (ElementView el : elements) {
            Rectangle r = new Rectangle(el.getModel().getX(), el.getModel().getY(), el.getModel().getWidth(), el.getModel().getHeight());
            if (r.contains(p)) return el;
        }
        return null;
    }

    private void showMenu(MouseEvent e) {
        lastClick = toModel(e.getPoint());
        target = findElement(lastClick);
        editItem.setEnabled(target != null);
        contextMenu.show(this, e.getX(), e.getY());
    }

    public void updateElementView(ElementView el) {
        RectangleElement m = el.getModel();
        float scale = project.getScale();
        int x = Math.round((m.getX() + offsetX) * scale);
        int y = Math.round((m.getY() + offsetY) * scale);
        int w = Math.round(m.getWidth() * scale);
        int h = Math.round(m.getHeight() * scale);
        el.setBounds(x, y, w, h);
        el.repaint();
    }

    private void updateElementViews() {
        for (ElementView el : elements) {
            updateElementView(el);
        }
    }

    public void selectElement(ElementView el) {
        if (selected != null) selected.setSelected(false);
        selected = el;
        if (el != null) {
            el.setSelected(true);
            propertyPanel.setElement(el);
        } else {
            propertyPanel.setElement(null);
        }
    }

    public void refreshPropertyPanel() {
        propertyPanel.refresh();
    }

    public float getScale() {
        return project.getScale();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        float scale = project.getScale();
        int step = Math.round(20 * scale);
        if (step < 1) step = 1;
        g2.setColor(new Color(230, 230, 230));
        int width = getWidth();
        int height = getHeight();
        int startX = Math.round((offsetX * scale) % step);
        if (startX > 0) startX -= step;
        for (int x = startX; x < width; x += step) {
            g2.drawLine(x, 0, x, height);
        }
        int startY = Math.round((offsetY * scale) % step);
        if (startY > 0) startY -= step;
        for (int y = startY; y < height; y += step) {
            g2.drawLine(0, y, width, y);
        }
    }
}
