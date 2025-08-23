package fr.hattane.ilias.deseigner.model.elements.types;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.elements.DesignElement;
import fr.hattane.ilias.deseigner.model.utils.ColorValue;
import fr.hattane.ilias.deseigner.model.utils.Dimensions;

/**
 * Modèle représentant un rectangle simple.
 */
public class RectangleElement extends DesignElement {

    private int x;
    private int y;
    private ColorValue background = new ColorValue(200, 200, 200);
    private ColorValue borderColor = new ColorValue(0, 0, 0);
    private int borderWidth = 1;
    private boolean shadow = false;
    private ColorValue shadowColor = new ColorValue(0, 0, 0, 0.5f);
    private int shadowOffsetX = 2;
    private int shadowOffsetY = 2;
    private int shadowBlur = 4;

    public RectangleElement(String name, int x, int y, int width, int height) {
        this(name, x, y, width, height, ElementTypes.RECTANGLE);
    }

    protected RectangleElement(String name, int x, int y, int width, int height, ElementTypes type) {
        super(name, type, 0, new Dimensions(width, height));
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return getDimensions().getWidth();
    }

    public void setWidth(int width) {
        getDimensions().setWidth(width);
    }

    public int getHeight() {
        return getDimensions().getHeight();
    }

    public void setHeight(int height) {
        getDimensions().setHeight(height);
    }

    public ColorValue getBackground() {
        return background;
    }

    public void setBackground(ColorValue background) {
        this.background = background;
    }

    public ColorValue getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(ColorValue borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean isShadowEnabled() {
        return shadow;
    }

    public void setShadowEnabled(boolean shadow) {
        this.shadow = shadow;
    }

    public ColorValue getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(ColorValue shadowColor) {
        this.shadowColor = shadowColor;
    }

    public int getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public int getShadowOffsetY() {
        return shadowOffsetY;
    }

    public void setShadowOffsetY(int shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
    }

    public int getShadowBlur() {
        return shadowBlur;
    }

    public void setShadowBlur(int shadowBlur) {
        this.shadowBlur = shadowBlur;
    }
}

