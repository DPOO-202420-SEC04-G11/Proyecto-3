
package model;

public class Calificacion {
    private Actividades actividad;
    private Estudiante estudiante;
    private int puntaje;
    private boolean aprobado;

    public Calificacion(Actividades actividad, Estudiante estudiante, int puntaje, boolean aprobado) {
        this.actividad = actividad;
        this.estudiante = estudiante;
        this.puntaje = puntaje;
        this.aprobado = puntaje>= 3;
    }

      // Método para mostrar el resultado
      public void mostrarResultado() {
        String resultado = aprobado ? "Aprobada" : "No aprobada";
        System.out.println("Estudiante: " + estudiante.getName());
        System.out.println("Actividad: " + actividad.getDescripcion());
        System.out.println("Puntaje: " + puntaje);
        System.out.println("Resultado: " + resultado);
    }

    // Métodos Getter y Setter
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
        this.aprobado = puntaje >= 6.0; // Actualiza el estado de aprobación
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}