package clases;

import datatypes.DTConsulta;
import datatypes.DTFecha;
import datatypes.DTUsuario;

public class Consulta{
	private String nombre;
	private DTFecha fecha;
	private int numeroSala;
	private int numsDisponibles;
	private float costoConsulta;
	
	public Consulta(
			String nombre,
			DTFecha fecha,
			int numeroSala,
			int numsDisponibles,
			float costoConsulta
			) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.numeroSala = numeroSala;
		this.numsDisponibles = numsDisponibles;
		this.costoConsulta = costoConsulta;
	}
	
	public boolean perteneceMedicoConsulta(DTUsuario u) {
		return false;
	}
	
	public DTConsulta getDatosConsulta() {
		return null;
	}
	
	public void agregarReserva(Reserva res) {
		
	}
}