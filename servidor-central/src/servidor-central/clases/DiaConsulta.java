package clases;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;

import datatypes.DTConsulta;
import enums.Dia;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsultaCompleto;

public class DiaConsulta{
	
	private final Map<String, Consulta> consultas = new LinkedHashMap<>();
	private final Especialidad especialidad;
	private final Clinica clinica;
	private final String nombre;
	private final String descripcion;
	private final Dia dia;
	private final int horaInicio;
	private final float costoConsulta;	
	
	public DiaConsulta(String nombre, String descripcion, Dia dia, int horaInicio, Especialidad especialidad, Clinica clinica, float costoConsulta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.especialidad = especialidad;
        this.clinica = clinica;
        this.costoConsulta = costoConsulta;
    }
	
	public Set<DTConsultaSoloConsulta> listarConsultas() {
	    Set<DTConsultaSoloConsulta> res = new LinkedHashSet<>();
	    for (Consulta c : consultas.values()) {
	        res.add(c.getDatosSoloConsulta());
	    }
	    return res;
	}
	
	public void agregarConsulta(Consulta consulta) {
        consultas.put(consulta.getNombre(), consulta);
    }
	
    public DTDiaConsultaCompleto getDatosCompletos() {
        return new DTDiaConsultaCompleto(nombre, descripcion, dia, horaInicio, especialidad.getNombre(), clinica.getNombre(), costoConsulta,listarConsultas());
    }
    
    public String getNombre() { return nombre; }
    
    public Map<String, Consulta> getConsultas() {
        return Collections.unmodifiableMap(consultas);
    }
    
    public Especialidad getEspecialidad() { return especialidad; }
    
    public Clinica getClinica() { return clinica; }
    
    public String getDescripcion() { return descripcion; }
    
    public Dia getDia() { return dia; }
    
    public int getHoraInicio() { return horaInicio; }
    
    public float getCostoConsulta() { return costoConsulta; }
}