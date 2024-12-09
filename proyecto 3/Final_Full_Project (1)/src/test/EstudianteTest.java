package test;

import model.*;
import controller.Controlador;

public class EstudianteTest {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        // Crear objeto estudiante e inscribirse en un LearningPath
        Estudiante estudiante = new Estudiante("1", "Tomas Osuna", "tomasosuna@uniandes.edu.co", "tomas1234");
        System.out.println("Estado inicial del estudiante: " + estudiante.getName());

        LearningPath learningPath = new LearningPath(
            "Introducción a la Programación",
            "Curso básico",
            "Fácil",
            120,
            4.5f,
            "2024-01-15"
        );

        controlador.inscribirEstudianteEnLearningPath(estudiante, learningPath);
        System.out.println("Estudiante inscrito en: " + learningPath.getTitulo());

        // Crear y completar una actividad
       /* Tarea tarea = new Tarea(
            "Resolver ejercicios de variables",
            "Practicar variables",
            "Fácil",
            60,
            "No enviada",
            "No exitoso"
        );

        System.out.println("Intentando completar tarea...");
        controlador.completarActividad(estudiante, tarea);*/


        // Ver progreso del estudiante
        System.out.println("Progreso actual del estudiante en el Learning Path:");
        controlador.mostrarProgresoEstudiante(estudiante, learningPath);
    }
}

