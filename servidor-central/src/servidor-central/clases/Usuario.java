package clases;

import datatypes.DTUsuario;

public abstract class Usuario {
    private final String nickname;
    private final String correo;
    private String nombre;
    private String apellido;

    protected Usuario(String nickname, String nombre, String apellido, String correo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getNickname() { return nickname; }
    
    public String getNombre() { return nombre; }
    
    public String getApellido() { return apellido; }
    
    public String getCorreo() { return correo; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public void setApellido(String apellido) { this.apellido = apellido; }

    public abstract DTUsuario getDatos();
}