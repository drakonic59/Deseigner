package fr.hattane.ilias.deseigner.view.panels.editor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Panneau affichant et éditant les propriétés de l'élément sélectionné.
 */
public class PropertyPanel extends JPanel {
	
	private static final long serialVersionUID = -5823019595713087538L;
	
	private final JTextField nameField = new JTextField();
    private final JTextField idField = new JTextField();
    private final JTextField descField = new JTextField();
    private final JTextField zIndexField = new JTextField();
    private ElementView current;

    public PropertyPanel() {
        setPreferredSize(new Dimension(200, 0));
        setLayout(new GridLayout(0, 1));
        add(new JLabel("Nom"));
        add(nameField);
        add(new JLabel("Id"));
        add(idField);
        add(new JLabel("Description"));
        add(descField);
        add(new JLabel("Z-index"));
        add(zIndexField);
        JButton colorButton = new JButton("Couleur de fond");
        colorButton.addActionListener(e -> chooseColor());
        add(colorButton);

        DocumentListener docListener = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { apply(); }
            @Override public void removeUpdate(DocumentEvent e) { apply(); }
            @Override public void changedUpdate(DocumentEvent e) { apply(); }
        };
        nameField.getDocument().addDocumentListener(docListener);
        idField.getDocument().addDocumentListener(docListener);
        descField.getDocument().addDocumentListener(docListener);
        zIndexField.getDocument().addDocumentListener(docListener);
    }

    /**
     * Charge l'élément dans le panneau afin d'en modifier les propriétés.
     */
    public void setElement(ElementView element) {
        this.current = element;
        if (element == null) {
            nameField.setText("");
            idField.setText("");
            descField.setText("");
            zIndexField.setText("");
            setBackground(null);
            return;
        }
        nameField.setText(element.getElementName());
        idField.setText(element.getElementId());
        descField.setText(element.getDescription());
        zIndexField.setText(String.valueOf(element.getZIndex()));
        setBackground(element.getBackgroundColor());
    }

    private void apply() {
        if (current == null) return;
        current.setElementName(nameField.getText());
        current.setElementId(idField.getText());
        current.setDescription(descField.getText());
        try {
            int z = Integer.parseInt(zIndexField.getText());
            current.setZIndex(z);
        } catch (NumberFormatException ignored) {
        }
    }

    private void chooseColor() {
        if (current == null) return;
        Color c = JColorChooser.showDialog(this, "Couleur de fond", current.getBackgroundColor());
        if (c != null) {
            current.setBackgroundColor(c);
            setBackground(c);
        }
    }
    
}
