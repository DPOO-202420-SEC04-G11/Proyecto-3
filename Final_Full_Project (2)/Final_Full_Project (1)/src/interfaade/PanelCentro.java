package interfaade;

import javax.swing.*;
import java.awt.*;

public class PanelCentro extends JPanel {
    public PanelCentro() {
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel("Bienvenido al Sistema de Learning Paths", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        add(label, BorderLayout.CENTER);
    }
}
