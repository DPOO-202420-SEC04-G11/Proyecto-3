package model;

import java.util.ArrayList;
import java.util.List;

public class Examen extends Actividades {
    private List<Pregunta> preguntas;  
    private boolean aprobado;  
    private int puntajeMaximo;  

    // Constructor
    public Examen(String descripcion, String objetivo, String dificultad, int duracion, List<Actividades> prerequisitos, String titulo) {
        super(descripcion, objetivo, dificultad, duracion, prerequisitos, titulo);
        this.preguntas = new ArrayList<>();
        this.puntajeMaximo = 0;
        this.aprobado = false;
    }

    // Método para agregar preguntas al examen
    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
        puntajeMaximo += pregunta.getPuntaje();
    }

    // Método para recoger y procesar respuestas de los estudiantes
    public void recogerRespuestas(List<String> respuestas) {
        int puntajeObtenido = 0;

        System.out.println("Resultados del Examen:");
        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String respuestaEstudiante = respuestas.get(i);

            if (pregunta.validarRespuesta(respuestaEstudiante)) {
                puntajeObtenido += pregunta.getPuntaje();
                System.out.println("Pregunta: " + pregunta.getTexto());
                System.out.println("Respuesta correcta: " + respuestaEstudiante);
                System.out.println("Puntaje obtenido: " + pregunta.getPuntaje() + " (Correcta)");
            } else {
                System.out.println("Pregunta: " + pregunta.getTexto());
                System.out.println("Respuesta dada: " + respuestaEstudiante);
                System.out.println("Puntaje obtenido: 0 (Incorrecta)");
            }
        }

        // Cálculo de calificación
        double calificacion = (double) puntajeObtenido / puntajeMaximo * 5;
        System.out.println("Puntaje total: " + puntajeObtenido + "/" + puntajeMaximo);
        System.out.println("Calificación sobre 5: " + calificacion);

        aprobado = calificacion >= 3.0;
        System.out.println(aprobado ? "¡Aprobado!" : "No aprobado");
    }

    // Getters y Setters
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(int puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    @Override
    public void crearActividad() {
        throw new UnsupportedOperationException("Método no implementado.");
    }
}

