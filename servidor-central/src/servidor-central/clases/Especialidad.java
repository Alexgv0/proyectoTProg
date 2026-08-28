package clases;

public class Especialidad{
	
	private String nombre;//cambie el atributo descripcion a nombre, la letra lo dice
	private int porcentajeCostoConsultaAdicional;

	public Especialidad(String nombre, int pcca) {
		this.nombre = nombre;
		this.porcentajeCostoConsultaAdicional = pcca;
	}
	
	public String getNombre() {
	    return nombre;
	}

    public int getPorcentajeCostoConsultaAdicional() {
        return porcentajeCostoConsultaAdicional;
    }
}