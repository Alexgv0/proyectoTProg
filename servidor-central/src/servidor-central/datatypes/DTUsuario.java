package datatypes;

public class DTUsuario {
	private final String nickname;
	private final String nombre;
	private final String apellido;
	private final String correo;
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	
	public String getNickname() {return nickname;}
	public String getNombre() {return nombre;}
	public String getApellido() {return apellido;}
	public String getCorreo() {return correo;}
    public String getTipo() { return "Usuario"; }

	 @Override
    public String toString() { return nickname; }

    /*@Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTUsuario)) return false;
        return Objects.equals(nickname, ((DTUsuario) objeto).nickname);
    }

    @Override
    public int hashCode() { return Objects.hash(nickname); }
 */ 
}