
package model;

import java.util.List;

public class RecursoEducativo extends Actividades {
    private String tipoRecurso;
    private String url;
    
    public RecursoEducativo(String descripcion, String objetivo, String dificultad, int duracion, List<Actividades> prerequisitos, String titulo, String tipoRecurso, String url) {
        super(descripcion, objetivo, dificultad, duracion, prerequisitos, titulo);
        this.tipoRecurso = tipoRecurso;
        this.url = url;
    }
    
    public boolean clickeado() {
        return (url != null && !url.isEmpty());
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void crearActividad() {
        throw new UnsupportedOperationException("Unimplemented method 'crearActividad'");
    }


}
