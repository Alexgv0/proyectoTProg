package clases;

import datatypes.DTEspecialidad;

public class Especialidad {
    private final String nombre;
    private final int porcentajeCostoConsultaAdicional;

    public Especialidad(String nombre, int porcentajeCostoConsultaAdicional) {
        this.nombre = nombre;
        this.porcentajeCostoConsultaAdicional = porcentajeCostoConsultaAdicional;
    }

    public String getNombre() { return nombre; }
    public int getPorcentajeCostoConsultaAdicional() {
        return porcentajeCostoConsultaAdicional;
    }
    public DTEspecialidad getDatos() {
        return new DTEspecialidad(nombre, porcentajeCostoConsultaAdicional);
    }
}
