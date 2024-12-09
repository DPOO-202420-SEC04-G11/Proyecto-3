package model;

import java.util.ArrayList;
import java.util.List;

public class LearningPath {
    private String titulo;
    private String descripcion;
    private String dificultad;
    private int duracion; // Duración en minutos
    private double rating; // Calificación promedio
    private String fechaCreacion; // Fecha de creación en formato String
    private List<Actividades> actividades; 
    private String creadorId; // ID del profesor que creó el Learning Path

    // Constructor
    public LearningPath(String titulo, String descripcion, String dificultad, int duracion, double rating, String fechaCreacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.duracion = duracion;
        this.rating = rating;
        this.fechaCreacion = fechaCreacion;
        this.actividades = new ArrayList<>();
        this.creadorId = null; // Si tu lógica lo requiere, puedes dejarlo en null u otro valor por defecto.
    }


    // Métodos para manejar actividades
    public void agregarActividad(Actividades actividad) {
        actividades.add(actividad);
    }

    public void quitarActividad(int indice) {
        if (indice >= 0 && indice < actividades.size()) {
            actividades.remove(indice);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void editarActividad(int indice, Actividades nuevaActividad) {
        if (indice >= 0 && indice < actividades.size()) {
            actividades.set(indice, nuevaActividad);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void modificarOrdenActividades(int indice, int nuevoIndice) {
        if (indice >= 0 && indice < actividades.size() && nuevoIndice >= 0 && nuevoIndice < actividades.size()) {
            Actividades actividad = actividades.remove(indice);
            actividades.add(nuevoIndice, actividad);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividades> actividades) {
        this.actividades = actividades;
    }

    public String getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(String creadorId) {
        this.creadorId = creadorId;
    }

    @Override
    public String toString() {
        return "LearningPath{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", duracion=" + duracion +
                ", rating=" + rating +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", actividades=" + actividades +
                ", creadorId='" + creadorId + '\'' +
                '}';
    }
}
