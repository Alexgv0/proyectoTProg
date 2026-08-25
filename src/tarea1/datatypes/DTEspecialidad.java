package datatypes;

public class DTEspecialidad {
	private String nombre;
	private int porcentajeCostoConsultaAdicional;
	
	public DTEspecialidad(String nombre, int porcentajeCostoConsultaAdicional) {
		this.nombre = nombre;
		this.porcentajeCostoConsultaAdicional =  porcentajeCostoConsultaAdicional;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPorcentajeCostoConsultaAdicional() {
		return porcentajeCostoConsultaAdicional;
	}
}
