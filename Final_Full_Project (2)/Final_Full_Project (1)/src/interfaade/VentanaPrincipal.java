package interfaade;

import javax.swing.*;
import java.awt.*;
import controller.Controlador;

public class VentanaPrincipal extends JFrame {
    private Controlador controlador;
    private String rolUsuario;

    public VentanaPrincipal(Controlador controlador, String rolUsuario) {
        this.controlador = controlador;
        this.rolUsuario = rolUsuario;
        initUI();
    }

    private void initUI() {
        // Paleta de colores
        Color fondoOscuro = new Color(30, 30, 60);   // Azul oscuro
        Color textoClaro = Color.WHITE;              // Texto blanco
        Color bordeCampos = new Color(200, 200, 200);// Gris para bordes
        Color botonFondo = new Color(0, 128, 0);     // Verde oscuro para botones
        Color botonFondoHover = new Color(34, 177, 76);

        setSize(800, 600);
        setTitle("Learning Path Recommendation System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Sur (barra inferior)
        PanelSur panelS = new PanelSur();
        estilizarPanel(panelS, fondoOscuro, textoClaro, botonFondo, botonFondoHover);
        add(panelS, BorderLayout.SOUTH);

        // Panel Norte (barra superior)
        PanelNorte panelN = new PanelNorte();
        estilizarPanel(panelN, fondoOscuro, textoClaro, botonFondo, botonFondoHover);
        add(panelN, BorderLayout.NORTH);

        // Panel Este (barra lateral derecha)
        PanelEste panelE = new PanelEste();
        estilizarPanel(panelE, fondoOscuro, textoClaro, botonFondo, botonFondoHover);
        add(panelE, BorderLayout.EAST);

        // Panel central según el rol del usuario
        JPanel panelC;
        switch (rolUsuario) {
            case "Profesor":
                panelC = new PanelProfesor(controlador);
                break;
            case "Estudiante":
                panelC = new PanelEstudiante(controlador);
                break;
            default:
                panelC = new JPanel();
                panelC.add(new JLabel("Rol no reconocido"));
        }

        estilizarPanel(panelC, fondoOscuro, textoClaro, botonFondo, botonFondoHover);
        add(panelC, BorderLayout.CENTER);
    }

    /**
     * Aplica la paleta de colores a un panel y sus componentes.
     * Se asume que los paneles internos pueden tener botones, labels, etc.
     * Este método intenta buscar componentes y estilizar botones y textos.
     */
    private void estilizarPanel(JPanel panel, Color fondoOscuro, Color textoClaro, Color botonFondo, Color botonFondoHover) {
        panel.setBackground(fondoOscuro);
        // Recorremos sus componentes y ajustamos colores
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                estilizarPanel((JPanel) comp, fondoOscuro, textoClaro, botonFondo, botonFondoHover);
            } else if (comp instanceof JLabel) {
                JLabel lbl = (JLabel) comp;
                lbl.setForeground(textoClaro);
                lbl.setBackground(fondoOscuro);
            } else if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                btn.setForeground(textoClaro);
                btn.setBackground(botonFondo);
                btn.setFocusPainted(false);

                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        btn.setBackground(botonFondoHover);
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        btn.setBackground(botonFondo);
                    }
                });
            } else if (comp instanceof JTextArea) {
                JTextArea ta = (JTextArea) comp;
                ta.setBackground(Color.DARK_GRAY);
                ta.setForeground(textoClaro);
                ta.setCaretColor(textoClaro);
            } else if (comp instanceof JScrollPane) {
                JScrollPane sp = (JScrollPane) comp;
                sp.getViewport().setBackground(fondoOscuro);
                // Si tiene un JTextArea dentro, ya se estilizó arriba cuando iteramos componentes del panel.
            } else {
                // Para otros componentes, si es posible, aplicar fondo y/o texto
                comp.setBackground(fondoOscuro);
                if (comp instanceof JComponent) {
                    ((JComponent) comp).setForeground(textoClaro);
                }
            }
        }
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal(controlador, "Estudiante");
            vp.setVisible(true);
        });
    }
}

