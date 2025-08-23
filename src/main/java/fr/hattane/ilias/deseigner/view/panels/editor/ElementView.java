package fr.hattane.ilias.deseigner.view.panels.editor;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.elements.types.RectangleElement;
import fr.hattane.ilias.deseigner.model.elements.types.TextElement;
import fr.hattane.ilias.deseigner.model.utils.ColorValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Représentation graphique d'un élément rectangle sur la zone de dessin.
 */
public class ElementView extends JComponent {

    private static final long serialVersionUID = 3548738642211048029L;

    private final RectangleElement model;
    private final ModelCanvas canvas;
    private String description = "";
    private int zIndex = 0;
    private Point lastMouse;
    private boolean selected;
    private ElementTypes type = ElementTypes.RECTANGLE;
    private ResizeDirection resizeDir = ResizeDirection.NONE;

    private static final int RESIZE_MARGIN = 5;

    private enum ResizeDirection { NONE, NORTH, SOUTH, EAST, WEST }

    public ElementView(ModelCanvas canvas, RectangleElement model) {
        this.canvas = canvas;
        this.model = model;
        setOpaque(false);
        setBounds(0,0,0,0);
        canvas.updateElementView(this);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    canvas.selectElement(ElementView.this);
                    updateResizeDirection(e);
                    lastMouse = e.getLocationOnScreen();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastMouse = null;
                resizeDir = ResizeDirection.NONE;
                setCursor(Cursor.getDefaultCursor());
                canvas.refreshPropertyPanel();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastMouse == null) return;
                float scale = canvas.getScale();
                int dx = Math.round((e.getXOnScreen() - lastMouse.x) / scale);
                int dy = Math.round((e.getYOnScreen() - lastMouse.y) / scale);
                if (resizeDir == ResizeDirection.EAST) {
                    model.setWidth(Math.max(1, model.getWidth() + dx));
                } else if (resizeDir == ResizeDirection.WEST) {
                    int newWidth = Math.max(1, model.getWidth() - dx);
                    model.setX(model.getX() + dx);
                    model.setWidth(newWidth);
                } else if (resizeDir == ResizeDirection.SOUTH) {
                    model.setHeight(Math.max(1, model.getHeight() + dy));
                } else if (resizeDir == ResizeDirection.NORTH) {
                    int newHeight = Math.max(1, model.getHeight() - dy);
                    model.setY(model.getY() + dy);
                    model.setHeight(newHeight);
                } else {
                    model.setX(model.getX() + dx);
                    model.setY(model.getY() + dy);
                }
                lastMouse = e.getLocationOnScreen();
                canvas.updateElementView(ElementView.this);
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!selected) return;
                updateResizeDirection(e);
            }
        });
    }

    private void updateResizeDirection(MouseEvent e) {
        if (e.getX() < RESIZE_MARGIN) {
            resizeDir = ResizeDirection.WEST;
            setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        } else if (e.getX() > getWidth() - RESIZE_MARGIN) {
            resizeDir = ResizeDirection.EAST;
            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else if (e.getY() < RESIZE_MARGIN) {
            resizeDir = ResizeDirection.NORTH;
            setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        } else if (e.getY() > getHeight() - RESIZE_MARGIN) {
            resizeDir = ResizeDirection.SOUTH;
            setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else {
            resizeDir = ResizeDirection.NONE;
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public RectangleElement getModel() {
        return model;
    }

    public String getElementName() {
        return model.getName();
    }

    public void setElementName(String elementName) {
        model.setName(elementName);
    }

    public String getElementId() {
        return String.valueOf(model.getId());
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
        if (canvas != null) {
            canvas.reorderElements();
        }
    }

    public Color getBackgroundColor() {
        ColorValue cv = model.getBackground();
        return new Color(cv.getRed(), cv.getGreen(), cv.getBlue(), Math.round(cv.getAlpha() * 255));
    }

    public void setBackgroundColor(Color background) {
        model.setBackground(new ColorValue(background.getRed(), background.getGreen(), background.getBlue(), background.getAlpha() / 255f));
        repaint();
    }

    public ElementTypes getType() {
        return type;
    }

    public void setType(ElementTypes type) {
        this.type = type;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public ModelCanvas getCanvas() {
        return canvas;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        if (model.isShadowEnabled()) {
            ColorValue sc = model.getShadowColor();
            g2.setColor(new Color(sc.getRed(), sc.getGreen(), sc.getBlue(), Math.round(sc.getAlpha() * 255)));
            g2.fillRoundRect(model.getShadowOffsetX(), model.getShadowOffsetY(), getWidth(), getHeight(), model.getShadowBlur(), model.getShadowBlur());
        }
        g2.setColor(getBackgroundColor());
        if (model.getBorderType() == fr.hattane.ilias.deseigner.model.utils.BorderType.ROUNDED) {
            int r = model.getBorderRadius();
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r);
        } else {
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        ColorValue bc = model.getBorderColor();
        Color border = new Color(bc.getRed(), bc.getGreen(), bc.getBlue());
        g2.setColor(selected ? new Color(0, 120, 215) : border);
        g2.setStroke(new BasicStroke(model.getBorderWidth()));
        if (model.getBorderType() == fr.hattane.ilias.deseigner.model.utils.BorderType.ROUNDED) {
            int r = model.getBorderRadius();
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, r, r);
        } else {
            g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        if ((type == ElementTypes.TEXT || type == ElementTypes.BUTTON) && model instanceof TextElement) {
            TextElement t = (TextElement) model;
            ColorValue tc = t.getTextColor();
            g2.setColor(new Color(tc.getRed(), tc.getGreen(), tc.getBlue()));
            int style = Font.PLAIN;
            if (t.isBold()) style |= Font.BOLD;
            if (t.isItalic()) style |= Font.ITALIC;
            g2.setFont(new Font(t.getFont(), style, t.getFontSize()));
            FontMetrics fm = g2.getFontMetrics();
            String text = model.getName();
            int textWidth = fm.stringWidth(text);
            int x;
            switch (t.getAlignment()) {
                case CENTER:
                    x = (getWidth() - textWidth) / 2;
                    break;
                case RIGHT:
                    x = getWidth() - textWidth - 5;
                    break;
                default:
                    x = 5;
            }
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(text, x, y);
        }
        g2.dispose();
    }
}
