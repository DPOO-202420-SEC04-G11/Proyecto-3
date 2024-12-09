package model;

import java.util.Date;

public class Inscripcion {
    private Estudiante estudiante; // El estudiante inscrito
    private LearningPath learningPath; // El Learning Path asociado
    private Date fechaInscripcion; // Fecha de inscripción
    private int progreso; // Progreso del estudiante en el Learning Path

    // Constructor
    public Inscripcion(Estudiante estudiante, LearningPath learningPath, Date fechaInscripcion) {
        this.estudiante = estudiante;
        this.learningPath = learningPath;
        this.fechaInscripcion = fechaInscripcion;
        this.progreso = 0; // Inicialmente sin progreso
    }

    // Getters
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public int getProgreso() {
        return progreso;
    }

    // Métodos
    public void actualizarProgreso(int nuevoProgreso) {
        if (nuevoProgreso >= 0 && nuevoProgreso <= 100) {
            this.progreso = nuevoProgreso;
        } else {
            System.out.println("Progreso no válido. Debe estar entre 0 y 100.");
        }
    }

    @Override
    public String toString() {
        return "Inscripcion: " + estudiante.getName() + " en " + learningPath.getTitulo() +
               ", Progreso: " + progreso + "%, Fecha de inscripción: " + fechaInscripcion;
    }
}

