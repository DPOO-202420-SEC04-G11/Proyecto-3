 /*
package test;

import model.*;
import controller.Controlador;

public class ProfesorTest {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        // Crear objeto profesor y un LearningPath
        Profesor profesor = new Profesor("Anderson Mesa", "a.mesat@uniandes.edu.co", "anderson1234");
        System.out.println("Estado inicial del profesor: " + profesor.getNombre());

        // Crear un LearningPath
        System.out.println("Creando Learning Path...");
        controlador.crearLearningPath(profesor, "Introducción a la Programación", 
            "Curso básico de programación", "Fácil", 120);

        // Agregar actividades al LearningPath
        LearningPath learningPath = new LearningPath("Introducción a la Programación", 
            "Curso básico", "Fácil", 120, 4.5f, new java.util.Date());
        System.out.println("Learning Path creado: " + learningPath.getTitulo());

        // Calificar actividad
        Estudiante estudiante = new Estudiante("Tomas Osuna", "tomasosuna@uniandes.edu.co", "tomas1234");
        Tarea tarea = new Tarea("Resolver ejercicios de variables", "Practicar variables", 
            "Fácil", 60, "No enviada", "No exitoso");
        
        System.out.println("Calificando la tarea...");
        controlador.calificarActividad(profesor, tarea, estudiante, 80);
    }
}
*/