package clases;

import datatypes.DTFecha;

public class Reserva{
	private DTFecha fecha;
	private int numeroCliente;
	private float costoReserva;
	
	public Reserva(DTFecha fecha, int numCli, float costoRes) {
		this.fecha = fecha;
		this.numeroCliente = numCli;
		this.costoReserva = costoRes;
	}
}