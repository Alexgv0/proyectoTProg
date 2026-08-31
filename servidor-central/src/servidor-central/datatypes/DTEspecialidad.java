package datatypes;

import java.util.Objects;

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

    @Override
    public String toString() { return nombre; }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTEspecialidad)) return false;
        return Objects.equals(nombre, ((DTEspecialidad) objeto).nombre);
    }
    
    // @Override
    // public int hashCode() { return Objects.hash(nombre); }

}
