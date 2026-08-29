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
	
	public Reserva(
			DTFecha fecha, 
			int numCli, 
			float costoRes, 
			Consulta consulta, 
			Paciente paciente, 
			BeneficioAnual beneficio
			) {
		this.setFecha(fecha);
		this.setNumeroCliente(numCli);
		this.setCostoReserva(costoRes);
		this.setConsulta(consulta); 
		this.setPaciente(paciente); 
		this.setBeneficio(beneficio);
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public BeneficioAnual getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(BeneficioAnual beneficio) {
		this.beneficio = beneficio;
	}

	public DTFecha getFecha() {
		return fecha;
	}

	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public float getCostoReserva() {
		return costoReserva;
	}

	public void setCostoReserva(float costoReserva) {
		this.costoReserva = costoReserva;
	}

	public DTReserva crearDTReserva() {
		return new DTReserva(fecha, numeroCliente, costoReserva);
	}
	
}