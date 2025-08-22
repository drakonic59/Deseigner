package fr.hattane.ilias.deseigner.view.panels.editor;

import fr.hattane.ilias.deseigner.model.ElementTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Représentation graphique simple d'un élément sur la zone de dessin.
 */
public class ElementView extends JComponent {
	
	private static final long serialVersionUID = 3548738642211048029L;
	
	private String elementName = "Élément";
    private String elementId = "";
    private String description = "";
    private int zIndex = 0;
    private Color background = Color.LIGHT_GRAY;
    private ElementTypes type = ElementTypes.RECTANGLE;
    private Point dragOffset;

    public ElementView(int x, int y, int width, int height) {
    	
        setBounds(x, y, width, height);
        setOpaque(false);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragOffset = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragOffset == null) return;
                int newX = getX() + e.getX() - dragOffset.x;
                int newY = getY() + e.getY() - dragOffset.y;
                setLocation(newX, newY);
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
        getParent().setComponentZOrder(this, zIndex);
    }

    public Color getBackgroundColor() {
        return background;
    }

    public void setBackgroundColor(Color background) {
        this.background = background;
        repaint();
    }

    public ElementTypes getType() {
        return type;
    }

    public void setType(ElementTypes type) {
        this.type = type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(background);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        g2.dispose();
    }
    
}
