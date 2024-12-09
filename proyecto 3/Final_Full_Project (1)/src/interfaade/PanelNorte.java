package interfaade;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNorte extends JPanel {
    private JLabel lblImg;

    public PanelNorte() {
        lblImg = new JLabel();
        add(lblImg);

        // Ajusta la ruta y el nombre del archivo de imagen según tu proyecto.
        ImageIcon iconito = new ImageIcon("./datos/imagenes/header_learningpaths.png");
        lblImg.setIcon(iconito);
    }
}
