
package model;

public class User {
    private String nombre;
    private String correo;
    private String contraseña;
    private String rol; // "Profesor" o "Estudiante"
    
    public User(String nombre, String correo, String contraseña, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    // Métodos de acceso y modificación
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    // Métodos de responsabilidad
    public boolean iniciarSesion(String correo, String contraseña) {
        return this.correo.equals(correo) && this.contraseña.equals(contraseña);
    }
    
    public void verPerfil() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Rol: " + rol);
    }

    public boolean esProfesor() {
        return "Profesor".equalsIgnoreCase(this.rol);
    }
    
    public boolean esEstudiante() {
        return "Estudiante".equalsIgnoreCase(this.rol);
    }
}
