package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controlador; // Asegúrate de que la clase Controlador exista en este paquete
// Ajusta la ruta de import de LoginWindow según la ubicación real de tu clase LoginWindow.

public class PanelEste extends JPanel {
    public PanelEste() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Estirar horizontalmente

        // Título del panel
        JLabel titleLabel = new JLabel("Configuración", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(titleLabel, gbc);

        // Espacio vertical
        gbc.gridy++;
        gbc.weighty = 0.1;
        add(Box.createVerticalGlue(), gbc);

        // Botón Cerrar Programa
        JButton btnCerrarPrograma = new JButton("Cerrar Programa");
        btnCerrarPrograma.setPreferredSize(new Dimension(150, 50));
        btnCerrarPrograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saliendo del Sistema de Learning Paths...");
                System.exit(0);
            }
        });
        gbc.gridy++;
        gbc.weighty = 0.0;
        add(btnCerrarPrograma, gbc);

        // Espacio vertical
        gbc.gridy++;
        gbc.weighty = 0.1;
        add(Box.createVerticalGlue(), gbc);

        // Botón Volver a Autenticación
        JButton btnAutenticacion = new JButton("Volver a Autenticación");
        btnAutenticacion.setPreferredSize(new Dimension(150, 50));
        btnAutenticacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelEste.this);
                frame.dispose(); // Cierra la ventana principal
                
                // Iniciar el controlador y la ventana de Login nuevamente
                Controlador controlador = new Controlador();
                LoginWindow loginWindow = new LoginWindow(controlador);
                loginWindow.setVisible(true);
            }
        });
        gbc.gridy++;
        gbc.weighty = 0.0;
        add(btnAutenticacion, gbc);

        // Espacio vertical
        gbc.gridy++;
        gbc.weighty = 0.1;
        add(Box.createVerticalGlue(), gbc);

        // Botón Ayuda
        JButton btnAyuda = new JButton("Ayuda");
        btnAyuda.setPreferredSize(new Dimension(150, 50));
        btnAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Si necesita asistencia con el sistema de Learning Paths, contacte al soporte técnico.", 
                    "Ayuda", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        gbc.gridy++;
        gbc.weighty = 0.0;
        add(btnAyuda, gbc);

        // Espacio vertical para llenar la parte inferior
        gbc.gridy++;
        gbc.weighty = 1.0;
        add(Box.createVerticalGlue(), gbc);
    }
}

