package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import model.Estudiante;
import model.LearningPath;
import model.Profesor;
import model.Inscripcion;

public class Persistencia {
    private static final String FILE_PATH = "data.json";

    public static Map<String, Object> cargarDatos() {
        Map<String, Object> datos = new HashMap<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            StringBuilder jsonData = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonData.append((char) i);
            }
            JSONObject jsonObject = new JSONObject(jsonData.toString());

            // Cargar estudiantes
            Map<String, Estudiante> estudiantes = new HashMap<>();
            JSONArray usuariosArray = jsonObject.getJSONArray("usuarios");
            for (int j = 0; j < usuariosArray.length(); j++) {
                JSONObject userJSON = usuariosArray.getJSONObject(j);
                String rol = userJSON.getString("rol");
                if (rol.equals("Estudiante")) {
                    Estudiante est = new Estudiante(
                        userJSON.getString("correo"),
                        userJSON.getString("nombre"),
                        userJSON.getString("correo"),
                        userJSON.getString("contraseña")
                    );
                    estudiantes.put(est.getId(), est);
                }
            }
            datos.put("estudiantes", estudiantes);

            // Cargar profesores
            Map<String, Profesor> profesores = new HashMap<>();
            for (int j = 0; j < usuariosArray.length(); j++) {
                JSONObject userJSON = usuariosArray.getJSONObject(j);
                String rol = userJSON.getString("rol");
                if (rol.equals("Profesor")) {
                    Profesor prof = new Profesor(
                        userJSON.getString("correo"),
                        userJSON.getString("nombre")
                    );
                    profesores.put(prof.getId(), prof);
                }
            }
            datos.put("profesores", profesores);

            // Cargar Learning Paths
            Map<String, LearningPath> learningPaths = new HashMap<>();
            JSONArray learningPathsArray = jsonObject.getJSONArray("learningPaths");
            for (int j = 0; j < learningPathsArray.length(); j++) {
                JSONObject lpJSON = learningPathsArray.getJSONObject(j);
                // Se asume que ya has agregado el constructor en LearningPath
                LearningPath lp = new LearningPath(
                    lpJSON.getString("titulo"),
                    lpJSON.getString("descripcion"),
                    lpJSON.getString("dificultad"),
                    lpJSON.getInt("duracion"),
                    lpJSON.getDouble("rating"),
                    lpJSON.getString("fecha_creacion")
                );
                learningPaths.put(lp.getTitulo(), lp);
            }
            datos.put("learningPaths", learningPaths);

            // Cargar inscripciones
            List<Inscripcion> inscripciones = new ArrayList<>();
            JSONArray inscripcionesArray = jsonObject.getJSONArray("inscripciones");
            for (int j = 0; j < inscripcionesArray.length(); j++) {
                JSONObject insJSON = inscripcionesArray.getJSONObject(j);
                Estudiante estudiante = estudiantes.get(insJSON.getJSONObject("estudiante").getString("correo"));
                LearningPath learningPath = learningPaths.get(insJSON.getJSONObject("learningPath").getString("titulo"));
                Date fechaInscripcion = new Date(); // Ajusta el parseo de fecha si lo requieres
                Inscripcion inscripcion = new Inscripcion(estudiante, learningPath, fechaInscripcion);
                inscripciones.add(inscripcion);
            }
            datos.put("inscripciones", inscripciones);

        } catch (IOException e) {
            System.out.println("Archivo no encontrado, iniciando sin datos.");
        }
        return datos;
    }

    public static void guardarDatos(Map<String, Object> datos) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            JSONObject jsonObject = new JSONObject();

            Map<String, Estudiante> estudiantes = (Map<String, Estudiante>) datos.get("estudiantes");
            Map<String, Profesor> profesores = (Map<String, Profesor>) datos.get("profesores");
            Map<String, LearningPath> learningPaths = (Map<String, LearningPath>) datos.get("learningPaths");
            List<Inscripcion> inscripciones = (List<Inscripcion>) datos.get("inscripciones");

            JSONArray usuariosArray = new JSONArray();
            // Guardar estudiantes
            for (Estudiante est : estudiantes.values()) {
                JSONObject estJSON = new JSONObject();
                estJSON.put("id", est.getId());
                estJSON.put("nombre", est.getName());
                estJSON.put("correo", est.getCorreo());
                estJSON.put("contraseña", est.getContrasena());
                estJSON.put("actividadesCompletadas", est.getActividadesCompletadas());
                estJSON.put("calificaciones", new JSONObject(est.getCalificaciones()));
                estJSON.put("rol", "Estudiante");
                usuariosArray.put(estJSON);
            }

            // Guardar profesores
            for (Profesor prof : profesores.values()) {
                JSONObject profJSON = new JSONObject();
                profJSON.put("id", prof.getId());
                profJSON.put("nombre", prof.getName());
                profJSON.put("rol", "Profesor");
                usuariosArray.put(profJSON);
            }
            jsonObject.put("usuarios", usuariosArray);

            // Guardar Learning Paths
            JSONArray learningPathsArray = new JSONArray();
            for (LearningPath lp : learningPaths.values()) {
                JSONObject lpJSON = new JSONObject();
                lpJSON.put("titulo", lp.getTitulo());
                lpJSON.put("descripcion", lp.getDescripcion());
                lpJSON.put("dificultad", lp.getDificultad());
                lpJSON.put("duracion", lp.getDuracion());
                lpJSON.put("rating", lp.getRating());
                lpJSON.put("fecha_creacion", lp.getFechaCreacion());
                // Si deseas guardar el creadorId: lpJSON.put("creadorId", lp.getCreadorId());
                // También puedes persistir las actividades si lo requieres
                learningPathsArray.put(lpJSON);
            }
            jsonObject.put("learningPaths", learningPathsArray);

            // Guardar inscripciones
            JSONArray inscripcionesArray = new JSONArray();
            for (Inscripcion ins : inscripciones) {
                JSONObject insJSON = new JSONObject();
                insJSON.put("estudiante", new JSONObject()
                        .put("nombre", ins.getEstudiante().getName())
                        .put("correo", ins.getEstudiante().getCorreo())
                        .put("rol", "Estudiante"));
                insJSON.put("learningPath", new JSONObject()
                        .put("titulo", ins.getLearningPath().getTitulo()));
                insJSON.put("fechaInscripcion", ins.getFechaInscripcion().toString());
                insJSON.put("progreso", ins.getProgreso());
                inscripcionesArray.put(insJSON);
            }
            jsonObject.put("inscripciones", inscripcionesArray);

            writer.write(jsonObject.toString(4)); // Pretty print JSON
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}

