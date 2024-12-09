package interfaade;

import javax.swing.*;
import java.awt.*;

public class PanelIzq extends JPanel {

    public PanelIzq() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Título del panel
        JLabel titleLabel = new JLabel("Recursos Educativos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Panel central (antes visualizaba imagen, ahora vacío)
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Visualización del Recurso"));
        // Aquí ya no mostramos ninguna imagen ni directorio
        // Podrías agregar un JLabel informativo si quieres
        JLabel infoLabel = new JLabel("Aquí se podrían mostrar recursos, pero actualmente no se utilizan imágenes.");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(infoLabel, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior (antes tenía controles para imágenes)
        JPanel panelControles = new JPanel(new FlowLayout());
        panelControles.setBorder(BorderFactory.createTitledBorder("Controles del Recurso"));
        // No agregamos ningún botón ya que hemos eliminado la funcionalidad de imágenes
        JLabel noControles = new JLabel("No hay controles disponibles.");
        panelControles.add(noControles);
        add(panelControles, BorderLayout.SOUTH);
    }
}


