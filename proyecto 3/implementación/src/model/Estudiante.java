package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estudiante {
    private String id; // Identificador único
    private String name; // Nombre del estudiante
    private String correo; // Correo electrónico
    private String contrasena; // Contraseña
    private List<String> actividadesCompletadas; // Actividades completadas
    private Map<String, Integer> calificaciones; // Calificaciones (actividad -> calificación)

    // Constructor completo
    public Estudiante(String id, String name, String correo, String contrasena) {
        this.id = id;
        this.name = name;
        this.correo = correo;
        this.contrasena = contrasena;
        this.actividadesCompletadas = new ArrayList<>();
        this.calificaciones = new HashMap<>();
    }

    // Constructor sobrecargado (id y name)
    public Estudiante(String id, String name) {
        this.id = id;
        this.name = name;
        this.correo = ""; // Valor predeterminado
        this.contrasena = ""; // Valor predeterminado
        this.actividadesCompletadas = new ArrayList<>();
        this.calificaciones = new HashMap<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public List<String> getActividadesCompletadas() {
        return actividadesCompletadas;
    }

    public Map<String, Integer> getCalificaciones() {
        return calificaciones;
    }

    // Métodos para manejar actividades
    public void completarActividad(String actividad) {
        actividadesCompletadas.add(actividad);
    }

    public void agregarCalificacion(String actividad, int calificacion) {
        calificaciones.put(actividad, calificacion);
    }

    // toString
    @Override
    public String toString() {
        return "Estudiante{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", correo='" + correo + '\'' +
                ", actividadesCompletadas=" + actividadesCompletadas +
                ", calificaciones=" + calificaciones +
                '}';
    }
}

