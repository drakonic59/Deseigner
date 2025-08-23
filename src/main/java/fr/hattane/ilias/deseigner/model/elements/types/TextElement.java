package fr.hattane.ilias.deseigner.model.elements.types;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.utils.ColorValue;

/**
 * Modèle représentant une zone de texte avec propriétés de police.
 */
public class TextElement extends RectangleElement {

    public enum TextAlignment { LEFT, CENTER, RIGHT }

    private String font = "SansSerif";
    private int fontSize = 14;
    private ColorValue textColor = new ColorValue(0, 0, 0);
    private TextAlignment alignment = TextAlignment.LEFT;
    private boolean bold = false;
    private boolean italic = false;

    public TextElement(String name, int x, int y, int width, int height) {
        this(name, x, y, width, height, ElementTypes.TEXT);
    }

    protected TextElement(String name, int x, int y, int width, int height, ElementTypes type) {
        super(name, x, y, width, height, type);
        setBackground(new ColorValue(0, 0, 0, 0));
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public ColorValue getTextColor() {
        return textColor;
    }

    public void setTextColor(ColorValue textColor) {
        this.textColor = textColor;
    }

    public TextAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(TextAlignment alignment) {
        this.alignment = alignment;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }
}
