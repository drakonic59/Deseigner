package fr.hattane.ilias.deseigner.model.elements.types;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.utils.ColorValue;

/**
 * Modèle représentant un bouton (rectangle avec texte).
 */
public class ButtonElement extends TextElement {

    public ButtonElement(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height, ElementTypes.BUTTON);
        // Couleur de fond chaude et texte contrasté
        setBackground(new ColorValue(240, 173, 78));
        setTextColor(new ColorValue(255, 255, 255));
        // Petite ombre noire par défaut
        setShadowEnabled(true);
        setShadowColor(new ColorValue(0, 0, 0, 0.5f));
        setShadowOffsetX(2);
        setShadowOffsetY(2);
        setShadowBlur(4);
    }
}
