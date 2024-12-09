package controller;

import model.*;
import persistence.Persistencia;

import java.util.*;

public class Controlador {
    private Map<String, Estudiante> estudiantes;
    private Map<String, Profesor> profesores;
    private List<Inscripcion> inscripciones;
    private Map<String, LearningPath> learningPaths; // Clave: título del LP, Valor: LP

    public Controlador() {
        // Cargar datos desde la persistencia
        Map<String, Object> datos = Persistencia.cargarDatos();
        estudiantes = (Map<String, Estudiante>) datos.getOrDefault("estudiantes", new HashMap<>());
        profesores = (Map<String, Profesor>) datos.getOrDefault("profesores", new HashMap<>());
        inscripciones = (List<Inscripcion>) datos.getOrDefault("inscripciones", new ArrayList<>());
        learningPaths = (Map<String, LearningPath>) datos.getOrDefault("learningPaths", new HashMap<>());
    }

    public void iniciarConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema. ¿Es usted Profesor o Estudiante?");
        String tipoUsuario = scanner.nextLine().toLowerCase();

        if (tipoUsuario.equals("profesor")) {
            manejarProfesor(scanner);
        } else if (tipoUsuario.equals("estudiante")) {
            manejarEstudiante(scanner);
        } else {
            System.out.println("Tipo de usuario no reconocido.");
        }

        guardarDatos(); // Guardar datos antes de salir
    }

    private void manejarProfesor(Scanner scanner) {
        System.out.println("Ingrese su ID:");
        String id = scanner.nextLine();

        Profesor profesor = profesores.getOrDefault(id, new Profesor(id, "Profesor " + id));
        profesores.putIfAbsent(id, profesor);

        System.out.println("Bienvenido, Profesor " + profesor.getName());
        while (true) {
            System.out.println("Opciones: 1. Crear Actividad  2. Ver Actividades  3. Poner Calificación  4. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el título de la actividad:");
                    String actividad = scanner.nextLine();
                    profesor.crearActividad(actividad);
                    System.out.println("Actividad creada.");
                }
                case 2 -> {
                    System.out.println("Actividades creadas:");
                    profesor.getActividadesCreadas().forEach(System.out::println);
                }
                case 3 -> calificarEstudiante(scanner);
                case 4 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void calificarEstudiante(Scanner scanner) {
        System.out.println("Ingrese el ID del estudiante:");
        String estudianteId = scanner.nextLine();
        Estudiante estudiante = estudiantes.get(estudianteId);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("Ingrese el título de la actividad:");
        String actividad = scanner.nextLine();
        if (!estudiante.getActividadesCompletadas().contains(actividad)) {
            System.out.println("El estudiante no ha completado esta actividad.");
            return;
        }

        System.out.println("Ingrese la calificación (0-100):");
        int calificacion = Integer.parseInt(scanner.nextLine());
        estudiante.agregarCalificacion(actividad, calificacion);
        System.out.println("Calificación asignada.");
    }

    private void manejarEstudiante(Scanner scanner) {
        System.out.println("Ingrese su ID:");
        String id = scanner.nextLine();

        Estudiante estudiante = estudiantes.getOrDefault(id, new Estudiante(id, "Estudiante " + id, "default@example.com", "password123"));
        estudiantes.putIfAbsent(id, estudiante);

        System.out.println("Bienvenido, Estudiante " + estudiante.getName());
        while (true) {
            System.out.println("Opciones: 1. Completar Actividad  2. Ver Actividades Completadas  3. Ver Calificaciones  4. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> completarActividadEstudiante(scanner, estudiante);
                case 2 -> {
                    System.out.println("Actividades completadas:");
                    estudiante.getActividadesCompletadas().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.println("Calificaciones:");
                    estudiante.getCalificaciones().forEach((act, calif) ->
                            System.out.println(act + ": " + calif));
                }
                case 4 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void completarActividadEstudiante(Scanner scanner, Estudiante estudiante) {
        System.out.println("Ingrese el título de la actividad:");
        String actividad = scanner.nextLine();
        estudiante.completarActividad(actividad);
        System.out.println("Actividad completada.");
    }

    private void guardarDatos() {
        Map<String, Object> datos = new HashMap<>();
        datos.put("estudiantes", estudiantes);
        datos.put("profesores", profesores);
        datos.put("inscripciones", inscripciones);
        datos.put("learningPaths", learningPaths);
        Persistencia.guardarDatos(datos);
    }

    // Métodos adicionales para interfaz gráfica o pruebas

    public void inscribirEstudianteEnLearningPath(Estudiante estudiante, LearningPath learningPath) {
        Date fecha = new Date();
        Inscripcion inscripcion = new Inscripcion(estudiante, learningPath, fecha);
        inscripciones.add(inscripcion);
        System.out.println("Estudiante " + estudiante.getName() + " inscrito en el Learning Path: " + learningPath.getTitulo());
    }

    public void completarActividad(Estudiante estudiante, Actividades actividad) {
        actividad.completarActividad();
        estudiante.completarActividad(actividad.getTitulo());
        System.out.println("Actividad " + actividad.getTitulo() + " completada por el estudiante " + estudiante.getName());
    }

    public void mostrarProgresoEstudiante(Estudiante estudiante, LearningPath learningPath) {
        System.out.println("Progreso del estudiante " + estudiante.getName() + " en el Learning Path " + learningPath.getTitulo() + ":");
        for (Actividades a : learningPath.getActividades()) {
            boolean completada = estudiante.getActividadesCompletadas().contains(a.getTitulo());
            System.out.println(" - " + a.getTitulo() + ": " + (completada ? "Completada" : "Pendiente"));
        }
    }

    public void crearLearningPath(String profesorId, String titulo, String descripcion, String dificultad, int duracion, double rating, String fechaCreacion) {
        Profesor profesor = profesores.get(profesorId);
        if (profesor == null) {
            System.out.println("No se encontró profesor con ID: " + profesorId);
            return;
        }
        LearningPath lp = new LearningPath(titulo, descripcion, dificultad, duracion, rating, fechaCreacion);
        lp.setCreadorId(profesorId);
        learningPaths.put(titulo, lp);
        profesor.agregarLearningPath(lp);
        System.out.println("Learning Path '" + titulo + "' creado por el Profesor " + profesor.getName());
    }

    public void editarLearningPath(String profesorId, String tituloLP, String nuevoTitulo, String nuevaDescripcion, String nuevaDificultad, int nuevaDuracion, double nuevoRating) {
        Profesor profesor = profesores.get(profesorId);
        if (profesor == null) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        LearningPath lp = profesor.buscarLearningPath(tituloLP);
        if (lp == null) {
            System.out.println("El profesor no tiene un Learning Path con el título: " + tituloLP);
            return;
        }

        // Editamos el LP con los nuevos datos
        lp.setTitulo(nuevoTitulo);
        lp.setDescripcion(nuevaDescripcion);
        lp.setDificultad(nuevaDificultad);
        lp.setDuracion(nuevaDuracion);
        lp.setRating(nuevoRating);

        // Actualizamos la entrada en el mapa learningPaths
        learningPaths.remove(tituloLP);
        learningPaths.put(nuevoTitulo, lp);

        System.out.println("Learning Path actualizado: " + nuevoTitulo);
    }

    public void aprobarTareasEstudiantes() {
        // Implementación simplificada
        System.out.println("Aprobando tareas enviadas por estudiantes...");
        // Aquí podrías iterar sobre las inscripciones y las actividades para aprobar tareas.
    }

    public void verProgresoEstudiantes() {
        // Mostrar el progreso de todos los estudiantes en todos los Learning Paths
        for (Inscripcion ins : inscripciones) {
            mostrarProgresoEstudiante(ins.getEstudiante(), ins.getLearningPath());
        }
    }

    public String getRolUsuario(String correo) {
        // Determina el rol según el correo
        if (profesores.containsKey(correo)) {
            return "Profesor";
        } else if (estudiantes.containsKey(correo)) {
            return "Estudiante";
        }
        return "Desconocido";
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        // Autenticación básica (ajusta según tu lógica)
        if (profesores.containsKey(correo)) {
            return true;
        } else if (estudiantes.containsKey(correo)) {
            return true;
        }
        return false;
    }

    // Métodos adicionales que podrían ser usados por PanelEstudiante:
    public List<LearningPath> getLearningPaths() {
        return new ArrayList<>(learningPaths.values());
    }

    public LearningPath getLearningPath(String titulo) {
        return learningPaths.get(titulo);
    }

    public Actividades getActividadPorTitulo(String titulo) {
        // Este método depende de cómo guardes las actividades.
        // Podrías iterar sobre todos los LearningPaths y sus actividades, buscando la que tenga ese título.
        for (LearningPath lp : learningPaths.values()) {
            for (Actividades a : lp.getActividades()) {
                if (a.getTitulo().equalsIgnoreCase(titulo)) {
                    return a;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.iniciarConsola();
    }
}

