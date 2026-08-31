package clases;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import datatypes.DTConsulta;
import enums.Dia;
import datatypes.DTConsultaSoloConsulta;

public class DiaConsulta{
	
	private Map<String, Consulta> consultas;
	
	private Especialidad especialidad;
	private Clinica clinica;
	
	private String nombre;
	private String descripcion;
	private Dia dia;
	private int horaInicio;
	private float costoConsulta;
	
	public DiaConsulta(
			String nom,
			String des,
			Dia dia,
			int hi,
			Especialidad especialidad,
            Clinica clinica,
			float cc
			) {
		this.nombre = nom;
		this.setDescripcion(des);
		this.setDia(dia);
		this.setHoraInicio(hi);
		this.setEspecialidad(especialidad);
        this.setClinica(clinica);
		this.setCostoConsulta(cc);
		
		this.consultas = new HashMap<>();
	}
	
	public Set<DTConsultaSoloConsulta> listarConsultas() {
    Set<DTConsultaSoloConsulta> resultado = new HashSet<>();

    for (Consulta c : consultas.values()) {
        resultado.add(c.getDatosSoloConsulta();
    }

    return resultado;
}
	
	}
	
	public String getNombre() {
		return nombre;
	}

	public Map<String, Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Map<String, Consulta> consultas) {
		this.consultas = consultas;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public float getCostoConsulta() {
		return costoConsulta;
	}

	public void setCostoConsulta(float costoConsulta) {
		this.costoConsulta = costoConsulta;
	}
}