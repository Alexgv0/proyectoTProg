package clases;

import datatypes.DTFecha;
import datatypes.DTReserva;

public class Reserva{
	private Consulta consulta;
	private Paciente paciente;
	private BeneficioAnual beneficio;
	
	private DTFecha fecha;
	private int numeroCliente;
	private float costoReserva;
	
	public Reserva(DTFecha fecha, int numCli, float costoRes) {
		this.fecha = fecha;
		this.numeroCliente = numCli;
		this.costoReserva = costoRes;
	}

	public DTReserva crearDTReserva() {
		return new DTReserva(fecha, numeroCliente, costoReserva);
	}

	public Consulta getConsulta() {
		return this.consulta;
	}
}