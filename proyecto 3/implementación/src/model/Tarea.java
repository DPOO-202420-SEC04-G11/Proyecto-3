package model;

import java.util.List;

public class Tarea extends Actividades {
    private String estadoEntrega; // Ejemplo: "No enviada", "Recibido"
    private String resultado;     // Ejemplo: "No exitoso", "Validado"

    // Constructor sin prerequisitos
    // Se asume que la descripción también se usa como título.
    public Tarea(String descripcion, String objetivo, String dificultad, int duracion,
                 String estadoEntrega, String resultado) {
        super(descripcion, objetivo, dificultad, duracion, null, descripcion); 
        this.estadoEntrega = estadoEntrega;
        this.resultado = resultado;
    }

    // Constructor con prerequisitos
    public Tarea(String descripcion, String objetivo, String dificultad, int duracion, 
                 List<Actividades> prerequisitos, String titulo, String estadoEntrega, String resultado) {
        super(descripcion, objetivo, dificultad, duracion, prerequisitos, titulo);
        this.estadoEntrega = estadoEntrega;
        this.resultado = resultado;
    }

    // Método para verificar si la tarea está terminada
    public boolean terminado() {
        return "Recibido".equalsIgnoreCase(estadoEntrega) && "Validado".equalsIgnoreCase(resultado);
    }

    // Getters y Setters
    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public void crearActividad() {
        throw new UnsupportedOperationException("Método 'crearActividad' no implementado.");
    }

    // Método para representar la tarea en formato legible
    @Override
    public String toString() {
        return "Tarea{" +
                "descripcion='" + getDescripcion() + '\'' +
                ", objetivo='" + getObjetivo() + '\'' +
                ", dificultad='" + getDificultad() + '\'' +
                ", duracion=" + getDuracion() +
                ", estadoEntrega='" + estadoEntrega + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}

