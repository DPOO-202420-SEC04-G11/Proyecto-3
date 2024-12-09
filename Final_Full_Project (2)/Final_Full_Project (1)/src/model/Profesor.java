package model;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String id;
    private String name;
    private List<String> actividadesCreadas;
    private List<LearningPath> learningPathsCreados;

    public Profesor(String id, String name) {
        this.id = id;
        this.name = name;
        this.actividadesCreadas = new ArrayList<>();
        this.learningPathsCreados = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getActividadesCreadas() {
        return actividadesCreadas;
    }

    public void crearActividad(String actividad) {
        actividadesCreadas.add(actividad);
    }

    public void agregarLearningPath(LearningPath lp) {
        learningPathsCreados.add(lp);
    }

    public List<LearningPath> getLearningPathsCreados() {
        return learningPathsCreados;
    }

    public LearningPath buscarLearningPath(String titulo) {
        for (LearningPath lp : learningPathsCreados) {
            if (lp.getTitulo().equalsIgnoreCase(titulo)) {
                return lp;
            }
        }
        return null;
    }
}
