package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controlador;
import model.Estudiante;
import model.LearningPath;
import model.Actividades;

import java.util.List;

public class PanelEstudiante extends JPanel {
    private Controlador controller;
    private Estudiante estudiante;
    private JTextArea textArea;

    public PanelEstudiante(Controlador controller) {
        this.controller = controller;
        // Crea un estudiante de ejemplo o recíbelo como parámetro en el constructor si se requiere
        this.estudiante = new Estudiante("1", "Tomas Osuna", "tomasosuna@uniandes.edu.co", "tomas1234");
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Botón: Ver Learning Paths Disponibles
        JButton btnVerLP = new JButton("Ver Learning Paths Disponibles");
        btnVerLP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verLearningPathsDisponibles();
            }
        });
        panelBotones.add(btnVerLP, gbc);

        // Botón: Inscribirse en un Learning Path
        JButton btnInscribirse = new JButton("Inscribirse en Learning Path");
        btnInscribirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscribirseEnLearningPath();
            }
        });
        panelBotones.add(btnInscribirse, gbc);

        // Botón: Completar Actividad
        JButton btnCompletarActividad = new JButton("Completar Actividad");
        btnCompletarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarActividad();
            }
        });
        panelBotones.add(btnCompletarActividad, gbc);

        // Botón: Ver Progreso
        JButton btnVerProgreso = new JButton("Ver Progreso en un Learning Path");
        btnVerProgreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verProgresoEnLearningPath();
            }
        });
        panelBotones.add(btnVerProgreso, gbc);

        add(panelBotones, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void verLearningPathsDisponibles() {
        // Este método asume que el controlador tiene una forma de obtener todos los Learning Paths
        List<LearningPath> paths = controller.getLearningPaths(); // Ajusta según tu lógica
        textArea.append("Learning Paths Disponibles:\n");
        for (LearningPath lp : paths) {
            textArea.append("- " + lp.getTitulo() + ": " + lp.getDescripcion() + "\n");
        }
    }

    private void inscribirseEnLearningPath() {
        // Solicitar el título del Learning Path al que se quiere inscribir
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path al que desea inscribirse:");
        if (titulo == null || titulo.isEmpty()) {
            textArea.append("No se ingresó un título.\n");
            return;
        }

        LearningPath lp = controller.getLearningPath(titulo); // Ajusta según tu lógica, un método en el controlador para obtener un LP por título
        if (lp == null) {
            textArea.append("No se encontró un Learning Path con el título: " + titulo + "\n");
            return;
        }

        controller.inscribirEstudianteEnLearningPath(estudiante, lp);
        textArea.append("Estudiante " + estudiante.getName() + " inscrito en " + lp.getTitulo() + "\n");
    }

    private void completarActividad() {
        // Solicitar el título de la actividad a completar
        String actividadTitulo = JOptionPane.showInputDialog(this, "Ingrese el título de la actividad a completar:");
        if (actividadTitulo == null || actividadTitulo.isEmpty()) {
            textArea.append("No se ingresó un título de actividad.\n");
            return;
        }

        // Aquí asumes que puedes obtener una instancia de la Actividad del controlador o del LP correspondiente
        // Dependiendo de tu lógica, podrías necesitar primero elegir el Learning Path.
        // Por simplicidad, supongamos que existe un método para obtener la actividad por título:
        Actividades actividad = controller.getActividadPorTitulo(actividadTitulo); // Ajustar según tu lógica
        if (actividad == null) {
            textArea.append("No se encontró la actividad: " + actividadTitulo + "\n");
            return;
        }

        controller.completarActividad(estudiante, actividad);
        textArea.append("Actividad '" + actividadTitulo + "' completada por " + estudiante.getName() + "\n");
    }

    private void verProgresoEnLearningPath() {
        // Solicitar el título del Learning Path
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path para ver su progreso:");
        if (titulo == null || titulo.isEmpty()) {
            textArea.append("No se ingresó un título de Learning Path.\n");
            return;
        }

        LearningPath lp = controller.getLearningPath(titulo); // Ajustar según tu lógica
        if (lp == null) {
            textArea.append("No se encontró un Learning Path con el título: " + titulo + "\n");
            return;
        }

        textArea.append("Progreso de " + estudiante.getName() + " en " + lp.getTitulo() + ":\n");
        // El controlador mostrará el progreso (o retornará datos para imprimir)
        controller.mostrarProgresoEstudiante(estudiante, lp);
        // Suponiendo que mostrarProgresoEstudiante imprime en consola, podrías modificar ese método para que retorne datos
        // o adaptar aquí la lógica para mostrarlo en textArea.
    }
}

