package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controlador;

public class LoginWindow extends JFrame {
    private Controlador controlador;

    public LoginWindow(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Inicio de Sesión - Learning Path System");

        setPreferredSize(new Dimension(450, 250));
        setLayout(new BorderLayout());

        // Paleta de colores
        Color fondoOscuro = new Color(30, 30, 60);  // Azul oscuro con tono grisáceo
        Color textoClaro = Color.WHITE;              // Texto blanco
        Color bordeCampos = new Color(200, 200, 200); // Gris claro para bordes
        Color botonFondo = new Color(0, 128, 0);      // Verde oscuro para el botón
        Color botonFondoHover = new Color(34, 177, 76);
        
        // Panel principal oscuro
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(fondoOscuro);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Correo:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(textoClaro);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        userField.setBorder(BorderFactory.createLineBorder(bordeCampos));
        userField.setBackground(Color.DARK_GRAY);
        userField.setForeground(textoClaro);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setForeground(textoClaro);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(20);
        passField.setBorder(BorderFactory.createLineBorder(bordeCampos));
        passField.setBackground(Color.DARK_GRAY);
        passField.setForeground(textoClaro);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(passField, gbc);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBackground(botonFondo);
        loginButton.setForeground(textoClaro);
        loginButton.setFocusPainted(false);

        // Efecto hover en el botón
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(botonFondoHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(botonFondo);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = userField.getText();
                String contrasena = new String(passField.getPassword());
                boolean authenticated = controlador.iniciarSesion(correo, contrasena);
                if (authenticated) {
                    String role = controlador.getRolUsuario(correo);
                    VentanaPrincipal mainWindow = new VentanaPrincipal(controlador, role);
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        SwingUtilities.invokeLater(() -> {
            new LoginWindow(controlador).setVisible(true);
        });
    }
}


