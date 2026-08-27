package clases;

import java.util.Set;
import datatypes.DTConsulta;
import enums.Dia;

public class DiaConsulta{
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
			float cc
			) {
		this.nombre = nom;
		this.descripcion = des;
		this.dia = dia;
		this.horaInicio = hi;
		this.costoConsulta = cc;
	}
	
	public Set<DTConsulta> listarConsultas() {
		return null;
		// TODO
	}
}