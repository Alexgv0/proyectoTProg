package clases;
import datatypes.DTUsuario;

public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;                 /// cambiar a correo
	
	public Usuario(String nickname, String nombre, String apellido, String correo) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
		
	public String getNickname() {
        return nickname;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {                     /      //Cambio a correo
        return correo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


      public DTUsuario getDatos() {                          
        return new DTUsuario(nickname, nombre, apellido, correo);
    }  

    //otras operaciones de la clase usuario to do
}
