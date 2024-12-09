package model;

import java.util.List;

public class Pregunta {
    private String texto;
    private List<String> opciones;
    private String respuestaCorrecta; // Cambiado a String para representar la opción correcta
    private int puntaje;
    private String explicacion;

    // Constructor
    public Pregunta(String texto, List<String> opciones, String respuestaCorrecta, int puntaje, String explicacion) {
        this.texto = texto;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.puntaje = puntaje;
        this.explicacion = explicacion;
    }

    // Getters
    public String getTexto() {
        return texto;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getExplicacion() {
        return explicacion;
    }

    // Métodos
    public boolean validarRespuesta(String respuestaSeleccionada) {
        return respuestaSeleccionada != null && respuestaSeleccionada.equalsIgnoreCase(respuestaCorrecta);
    }

    public String mostrarExplicacion() {
        return explicacion;
    }

    @Override
    public String toString() {
        return "Pregunta: " + texto + "\nOpciones: " + opciones + "\nExplicación: " + explicacion;
    }
}
