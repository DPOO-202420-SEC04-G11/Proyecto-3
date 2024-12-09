
package model;

import java.util.List;

public abstract class Actividades {
    private String descripcion;
    private String objetivo;
    private String dificultad;
    private int duracion;
    private boolean completado;
    private List<Actividades> prerequisitos;
    private String titulo;
    
    public Actividades(String descripcion, String objetivo, String dificultad, int duracion,List<Actividades> prerequisitos, String titulo) {
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.dificultad = dificultad;
        this.duracion = duracion;
        this.completado = false;
        this.prerequisitos = prerequisitos;
        this.titulo= titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public List<Actividades> getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(List<Actividades> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    public abstract void crearActividad();

    public void mostrarPrerequisitos() {
        if (prerequisitos.isEmpty()) {
            System.out.println("No hay actividades previas para esta actividad.");
        } else {
            System.out.println("La actividad tiene los siguientes prerequisitos:");
            for (Actividades actividad : prerequisitos) {
                System.out.println("- " + actividad.getDescripcion());
            }
        }
    }

    public boolean verificarPrerequisitos() {
        for (Actividades actividad : prerequisitos) {
            if (!actividad.isCompletado()) {
                System.out.println("Advertencia: No se ha completado el prerequisito: " + actividad.getDescripcion());
                return false;
            }
        }
        return true;
    }

    public void completarActividad() {
        if (verificarPrerequisitos()) {
            this.completado = true;
            System.out.println("Actividad completada: " + descripcion);
        } else {
            System.out.println("No se puede completar la actividad hasta cumplir con los prerequisitos.");
        }
    }
}
    
