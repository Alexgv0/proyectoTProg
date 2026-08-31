package datatypes;

public class DTPaciente {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private DTFecha decNacimiento;
		
	public DTPaciente(String nickname, String nombre, String apellido, String correo, DTFecha decNacimiento) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.decNacimiento = decNacimiento;
		this.porcentajeCostoConsultaAdicional =  porcentajeCostoConsultaAdicional;
	}
	