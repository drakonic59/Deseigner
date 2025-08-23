package fr.hattane.ilias.deseigner.view.panels.editor;

import fr.hattane.ilias.deseigner.model.ElementTypes;
import fr.hattane.ilias.deseigner.model.elements.types.TextElement;
import fr.hattane.ilias.deseigner.model.utils.ColorValue;
import fr.hattane.ilias.deseigner.model.utils.BorderType;
import fr.hattane.ilias.deseigner.model.elements.types.RectangleElement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Panneau affichant et éditant les propriétés de l'élément sélectionné.
 */
public class PropertyPanel extends JPanel {
	
	private static final long serialVersionUID = -5823019595713087538L;
	
        private final JTextField nameField = new JTextField();
    private final JTextField idField = new JTextField();
    private final JTextField descField = new JTextField();
    private final JTextField zIndexField = new JTextField();
    private final JTextField widthField = new JTextField();
    private final JTextField heightField = new JTextField();
    private final JTextField borderWidthField = new JTextField();
    private final JComboBox<BorderType> borderTypeBox = new JComboBox<>(BorderType.values());
    private final JTextField borderRadiusField = new JTextField();
    private final JCheckBox shadowEnableBox = new JCheckBox("Ombre");
    private final JTextField shadowOffsetXField = new JTextField();
    private final JTextField shadowOffsetYField = new JTextField();
    private final JTextField shadowBlurField = new JTextField();
    private final JButton shadowColorButton = new JButton("Couleur ombre");
    private final JComboBox<String> fontBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    private final JTextField textSizeField = new JTextField();
    private final JComboBox<TextElement.TextAlignment> alignBox = new JComboBox<>(TextElement.TextAlignment.values());
    private final JButton textColorButton = new JButton("Couleur texte");
    private final JCheckBox boldBox = new JCheckBox("Gras");
    private final JCheckBox italicBox = new JCheckBox("Italique");
    private final JPanel textOptions = new JPanel();
    private final JCheckBox topBox = new JCheckBox("Collé haut");
    private final JCheckBox bottomBox = new JCheckBox("Collé bas");
    private final JCheckBox leftBox = new JCheckBox("Collé gauche");
    private final JCheckBox rightBox = new JCheckBox("Collé droite");
    private final JComboBox<RectangleElement.Alignment> alignElementBox = new JComboBox<>(RectangleElement.Alignment.values());
    private ElementView current;
    private boolean updating;

    public PropertyPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10,10,10,10));

        addLabeledField("Nom", nameField);
        idField.setEditable(false);
        addLabeledField("Id", idField);
        addLabeledField("Description", descField);
        addLabeledField("Z-index", zIndexField);
        addLabeledField("Largeur", widthField);
        addLabeledField("Hauteur", heightField);
        addLabeledField("Bordure", borderWidthField);
        addLabeledField("Type de bordure", borderTypeBox);
        addLabeledField("Rayon", borderRadiusField);

        JButton colorButton = new JButton("Couleur de fond");
        colorButton.addActionListener(e -> chooseColor());
        add(colorButton);
        add(Box.createVerticalStrut(8));
        JButton borderButton = new JButton("Couleur bordure");
        borderButton.addActionListener(e -> chooseBorderColor());
        add(borderButton);

        add(Box.createVerticalStrut(8));
        shadowEnableBox.addActionListener(e -> apply());
        add(shadowEnableBox);
        addLabeledField("Décalage X", shadowOffsetXField);
        addLabeledField("Décalage Y", shadowOffsetYField);
        addLabeledField("Flou", shadowBlurField);
        shadowColorButton.addActionListener(e -> chooseShadowColor());
        add(shadowColorButton);

        textOptions.setLayout(new BoxLayout(textOptions, BoxLayout.Y_AXIS));
        textOptions.add(Box.createVerticalStrut(8));
        textOptions.add(new JLabel("Police"));
        textOptions.add(fontBox);
        fontBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, fontBox.getPreferredSize().height));
        textOptions.add(Box.createVerticalStrut(8));
        textOptions.add(new JLabel("Taille texte"));
        textOptions.add(textSizeField);
        textSizeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textSizeField.getPreferredSize().height));
        textOptions.add(Box.createVerticalStrut(8));
        textOptions.add(new JLabel("Alignement"));
        textOptions.add(alignBox);
        alignBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, alignBox.getPreferredSize().height));
        textOptions.add(Box.createVerticalStrut(8));
        textOptions.add(boldBox);
        textOptions.add(italicBox);
        boldBox.addActionListener(e -> apply());
        italicBox.addActionListener(e -> apply());
        textOptions.add(Box.createVerticalStrut(8));
        textOptions.add(new JLabel("Couleur texte"));
        textColorButton.addActionListener(e -> chooseTextColor());
        textColorButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, textColorButton.getPreferredSize().height));
        textColorButton.setBackground(Color.BLACK);
        textColorButton.setOpaque(true);
        textOptions.add(textColorButton);
        add(textOptions);
        textOptions.setVisible(false);

        add(Box.createVerticalStrut(8));
        add(topBox);
        add(bottomBox);
        add(leftBox);
        add(rightBox);
        add(new JLabel("Alignement"));
        add(alignElementBox);

        DocumentListener textListener = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { apply(); }
            @Override public void removeUpdate(DocumentEvent e) { apply(); }
            @Override public void changedUpdate(DocumentEvent e) { apply(); }
        };
        nameField.getDocument().addDocumentListener(textListener);
        descField.getDocument().addDocumentListener(textListener);

        FocusAdapter numberFocus = new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { apply(); }
        };
        ActionListener numberAction = e -> apply();

        zIndexField.addFocusListener(numberFocus);
        zIndexField.addActionListener(numberAction);
        widthField.addFocusListener(numberFocus);
        widthField.addActionListener(numberAction);
        heightField.addFocusListener(numberFocus);
        heightField.addActionListener(numberAction);
        borderWidthField.addFocusListener(numberFocus);
        borderWidthField.addActionListener(numberAction);
        borderRadiusField.addFocusListener(numberFocus);
        borderRadiusField.addActionListener(numberAction);
        shadowOffsetXField.addFocusListener(numberFocus);
        shadowOffsetXField.addActionListener(numberAction);
        shadowOffsetYField.addFocusListener(numberFocus);
        shadowOffsetYField.addActionListener(numberAction);
        shadowBlurField.addFocusListener(numberFocus);
        shadowBlurField.addActionListener(numberAction);
        textSizeField.addFocusListener(numberFocus);
        textSizeField.addActionListener(numberAction);

        fontBox.addActionListener(e -> apply());
        alignBox.addActionListener(e -> apply());
        borderTypeBox.addActionListener(e -> apply());
        topBox.addActionListener(e -> apply());
        bottomBox.addActionListener(e -> apply());
        leftBox.addActionListener(e -> apply());
        rightBox.addActionListener(e -> apply());
        alignElementBox.addActionListener(e -> apply());
    }

    private void addLabeledField(String label, JTextField field) {
        add(new JLabel(label));
        add(field);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        add(Box.createVerticalStrut(8));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width = 260;
        return d;
    }

    /**
     * Charge l'élément dans le panneau afin d'en modifier les propriétés.
     */
    public void setElement(ElementView element) {
        this.current = element;
        Container scroll = SwingUtilities.getAncestorOfClass(JScrollPane.class, this);
        if (scroll != null) scroll.setVisible(element != null);
        updating = true;
        if (element == null) {
            nameField.setText("");
            idField.setText("");
            descField.setText("");
            zIndexField.setText("");
            widthField.setText("");
            heightField.setText("");
            borderWidthField.setText("");
            borderTypeBox.setSelectedItem(BorderType.SQUARE);
            borderRadiusField.setText("");
            topBox.setSelected(false);
            bottomBox.setSelected(false);
            leftBox.setSelected(false);
            rightBox.setSelected(false);
            alignElementBox.setSelectedItem(RectangleElement.Alignment.LEFT);
            shadowEnableBox.setSelected(false);
            shadowOffsetXField.setText("");
            shadowOffsetYField.setText("");
            shadowBlurField.setText("");
            fontBox.setSelectedItem(null);
            textSizeField.setText("");
            alignBox.setSelectedItem(TextElement.TextAlignment.LEFT);
            textOptions.setVisible(false);
            updating = false;
            return;
        }
        textOptions.setVisible(element.getType() == ElementTypes.TEXT || element.getType() == ElementTypes.BUTTON);
        refresh();
        updating = false;
    }

    public void refresh() {
        if (current == null) return;
        updating = true;
        nameField.setText(current.getElementName());
        idField.setText(current.getElementId());
        descField.setText(current.getDescription());
        zIndexField.setText(String.valueOf(current.getZIndex()));
        widthField.setText(String.valueOf(current.getModel().getWidth()));
        heightField.setText(String.valueOf(current.getModel().getHeight()));
        borderWidthField.setText(String.valueOf(current.getModel().getBorderWidth()));
        borderTypeBox.setSelectedItem(current.getModel().getBorderType());
        borderRadiusField.setText(String.valueOf(current.getModel().getBorderRadius()));
        topBox.setSelected(current.getModel().isStickTop());
        bottomBox.setSelected(current.getModel().isStickBottom());
        leftBox.setSelected(current.getModel().isStickLeft());
        rightBox.setSelected(current.getModel().isStickRight());
        alignElementBox.setSelectedItem(current.getModel().getAlignment());
        shadowEnableBox.setSelected(current.getModel().isShadowEnabled());
        shadowOffsetXField.setText(String.valueOf(current.getModel().getShadowOffsetX()));
        shadowOffsetYField.setText(String.valueOf(current.getModel().getShadowOffsetY()));
        shadowBlurField.setText(String.valueOf(current.getModel().getShadowBlur()));
        if (current.getModel() instanceof TextElement) {
            TextElement t = (TextElement) current.getModel();
            fontBox.setSelectedItem(t.getFont());
            textSizeField.setText(String.valueOf(t.getFontSize()));
            alignBox.setSelectedItem(t.getAlignment());
            ColorValue tc = t.getTextColor();
            textColorButton.setBackground(new Color(tc.getRed(), tc.getGreen(), tc.getBlue()));
            boldBox.setSelected(t.isBold());
            italicBox.setSelected(t.isItalic());
        }
        updating = false;
    }

    private void apply() {
        if (current == null || updating) return;
        current.setElementName(nameField.getText());
        current.setDescription(descField.getText());
        try { current.setZIndex(Integer.parseInt(zIndexField.getText())); } catch (NumberFormatException ignored) {}
        try { current.getModel().setWidth(Integer.parseInt(widthField.getText())); } catch (NumberFormatException ignored) {}
        try { current.getModel().setHeight(Integer.parseInt(heightField.getText())); } catch (NumberFormatException ignored) {}
        try { current.getModel().setBorderWidth(Integer.parseInt(borderWidthField.getText())); } catch (NumberFormatException ignored) {}
        current.getModel().setBorderType((BorderType) borderTypeBox.getSelectedItem());
        try { current.getModel().setBorderRadius(Integer.parseInt(borderRadiusField.getText())); } catch (NumberFormatException ignored) {}
        current.getModel().setStickTop(topBox.isSelected());
        current.getModel().setStickBottom(bottomBox.isSelected());
        current.getModel().setStickLeft(leftBox.isSelected());
        current.getModel().setStickRight(rightBox.isSelected());
        current.getModel().setAlignment((RectangleElement.Alignment) alignElementBox.getSelectedItem());
        current.getModel().setShadowEnabled(shadowEnableBox.isSelected());
        try { current.getModel().setShadowOffsetX(Integer.parseInt(shadowOffsetXField.getText())); } catch (NumberFormatException ignored) {}
        try { current.getModel().setShadowOffsetY(Integer.parseInt(shadowOffsetYField.getText())); } catch (NumberFormatException ignored) {}
        try { current.getModel().setShadowBlur(Integer.parseInt(shadowBlurField.getText())); } catch (NumberFormatException ignored) {}
        if (current.getModel() instanceof TextElement) {
            TextElement t = (TextElement) current.getModel();
            t.setFont((String) fontBox.getSelectedItem());
            try { t.setFontSize(Integer.parseInt(textSizeField.getText())); } catch (NumberFormatException ignored) {}
            TextElement.TextAlignment al = (TextElement.TextAlignment) alignBox.getSelectedItem();
            if (al != null) t.setAlignment(al);
            t.setBold(boldBox.isSelected());
            t.setItalic(italicBox.isSelected());
        }
        current.getCanvas().updateElementView(current);
        current.repaint();
    }

    private void chooseColor() {
        if (current == null) return;
        Color c = JColorChooser.showDialog(this, "Couleur de fond", current.getBackgroundColor());
        if (c != null) {
            current.setBackgroundColor(c);
        }
    }

    private void chooseBorderColor() {
        if (current == null) return;
        Color c = JColorChooser.showDialog(this, "Couleur bordure", Color.BLACK);
        if (c != null) {
            current.getModel().setBorderColor(new fr.hattane.ilias.deseigner.model.utils.ColorValue(c.getRed(), c.getGreen(), c.getBlue()));
            current.repaint();
        }
    }

    private void chooseTextColor() {
        if (current == null || !(current.getModel() instanceof TextElement)) return;
        TextElement t = (TextElement) current.getModel();
        Color c = JColorChooser.showDialog(this, "Couleur texte",
                new Color(t.getTextColor().getRed(), t.getTextColor().getGreen(), t.getTextColor().getBlue()));
        if (c != null) {
            t.setTextColor(new fr.hattane.ilias.deseigner.model.utils.ColorValue(c.getRed(), c.getGreen(), c.getBlue()));
            textColorButton.setBackground(c);
            current.repaint();
        }
    }

    private void chooseShadowColor() {
        if (current == null) return;
        Color c = JColorChooser.showDialog(this, "Couleur ombre",
                new Color(current.getModel().getShadowColor().getRed(), current.getModel().getShadowColor().getGreen(),
                        current.getModel().getShadowColor().getBlue(),
                        Math.round(current.getModel().getShadowColor().getAlpha() * 255)));
        if (c != null) {
            current.getModel().setShadowColor(new fr.hattane.ilias.deseigner.model.utils.ColorValue(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()/255f));
            current.repaint();
        }
    }

}
