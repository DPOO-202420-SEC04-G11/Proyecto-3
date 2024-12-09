package interfaade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controlador;
import interfaade.visualizations.HeatMapFrame;

public class PanelProfesor extends JPanel {
    private Controlador controller;
    private JTextArea textArea;
    // Suponemos que tienes un identificador de profesor. Puedes pasarlo por el constructor o definirlo.
    private String profesorId; // Ajustar según tu lógica (ej: obtenlo del login)

    public PanelProfesor(Controlador controller) {
        this.controller = controller;
        // Podrías establecer el profesorId aquí o recibirlo por el constructor.
        // Por ejemplo, si el correo del profesor es el ID:
        this.profesorId = "a.mesat@uniandes.edu.co"; // Ajustar según tu lógica real.

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Botón para mostrar Heatmap
        JButton heatmapButton = new JButton("Mostrar Heatmap");
        heatmapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    HeatMapFrame demo = new HeatMapFrame("Heat Map de Actividades");
                    demo.pack();
                    demo.setLocationRelativeTo(null);
                    demo.setVisible(true);
                });
            }
        });
        add(heatmapButton, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Botón para crear un nuevo Learning Path
        JButton btnCrearLP = new JButton("Crear Learning Path");
        btnCrearLP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearLearningPath();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panelBotones.add(btnCrearLP, gbc);

        // Botón para editar un Learning Path existente
        JButton btnEditarLP = new JButton("Editar Learning Path");
        btnEditarLP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarLearningPath();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBotones.add(btnEditarLP, gbc);

        // Botón para aprobar tareas (actividades) de estudiantes
        JButton btnAprobarTareas = new JButton("Aprobar Tareas de Estudiantes");
        btnAprobarTareas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aprobarTareasEstudiantes();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelBotones.add(btnAprobarTareas, gbc);

        // Botón para ver el progreso de los estudiantes en un Learning Path
        JButton btnVerProgreso = new JButton("Ver Progreso Estudiantes");
        btnVerProgreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verProgresoEstudiantes();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelBotones.add(btnVerProgreso, gbc);

        add(panelBotones, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void crearLearningPath() {
        // Simular una encuesta o formulario utilizando JOptionPane
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del Learning Path:");
        if (titulo == null || titulo.trim().isEmpty()) {
            textArea.append("Creación cancelada o título inválido.\n");
            return;
        }

        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción del Learning Path:");
        if (descripcion == null || descripcion.trim().isEmpty()) {
            textArea.append("Creación cancelada o descripción inválida.\n");
            return;
        }

        String dificultad = JOptionPane.showInputDialog(this, "Ingrese la dificultad (por ejemplo: 'Fácil'):");
        if (dificultad == null || dificultad.trim().isEmpty()) {
            textArea.append("Creación cancelada o dificultad inválida.\n");
            return;
        }

        int duracion = 0;
        String duracionStr = JOptionPane.showInputDialog(this, "Ingrese la duración en minutos:");
        if (duracionStr == null) {
            textArea.append("Creación cancelada.\n");
            return;
        }
        try {
            duracion = Integer.parseInt(duracionStr.trim());
        } catch (NumberFormatException ex) {
            textArea.append("Duración inválida.\n");
            return;
        }

        double rating = 0.0;
        String ratingStr = JOptionPane.showInputDialog(this, "Ingrese el rating inicial (por ejemplo, 4.5):");
        if (ratingStr == null) {
            textArea.append("Creación cancelada.\n");
            return;
        }
        try {
            rating = Double.parseDouble(ratingStr.trim());
        } catch (NumberFormatException ex) {
            textArea.append("Rating inválido.\n");
            return;
        }

        String fechaCreacion = JOptionPane.showInputDialog(this, "Ingrese la fecha de creación (YYYY-MM-DD):");
        if (fechaCreacion == null || fechaCreacion.trim().isEmpty()) {
            textArea.append("Creación cancelada o fecha inválida.\n");
            return;
        }

        // Llamar al controlador para crear el Learning Path
        controller.crearLearningPath(profesorId, titulo, descripcion, dificultad, duracion, rating, fechaCreacion);
        textArea.append("Learning Path '" + titulo + "' creado satisfactoriamente.\n");
    }

    private void editarLearningPath() {
        textArea.append("Editando un Learning Path existente...\n");
        // Lógica real: controller.editarLearningPath(...) u otra acción
    }

    private void aprobarTareasEstudiantes() {
        textArea.append("Aprobando tareas enviadas por estudiantes...\n");
        // Lógica real: controller.aprobarTareasEstudiantes() u otra acción
    }

    private void verProgresoEstudiantes() {
        textArea.append("Mostrando el progreso de los estudiantes en sus Learning Paths...\n");
        // Lógica real: controller.verProgresoEstudiantes(...) u otra acción
    }
}

