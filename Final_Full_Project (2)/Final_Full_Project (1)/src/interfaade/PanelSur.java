package interfaade;

import javax.swing.*;
import java.awt.*;

public class PanelSur extends JPanel {
    public PanelSur() {
        setLayout(new GridLayout(1, 2));

        JTextField inputField = new JTextField();
        inputField.setToolTipText("Ingrese el título del Learning Path o la acción que desea realizar");

        JButton actionButton = new JButton("Buscar Learning Path");
        actionButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                // Aquí podrías invocar un método del controlador para buscar un Learning Path por título,
                // o realizar alguna otra acción correspondiente.
                System.out.println("Buscando Learning Path: " + input);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un título antes de buscar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(inputField);
        add(actionButton);
    }
}
