package datatypes;

import java.util.Set;

public class DTConsultaCompleta {
	private String nombreConsulta;
	private String nombreMedico;
	private DTFecha fecha;
	private int nroSala;
	private int cantidadNumeros;
	private float costo;
	private Set<DTReservaConsulta> reservas;

	public DTConsultaCompleta(
			String nombreConsulta, String nombreMedico, DTFecha fecha,
			int nroSala, int cantidadNumeros, float costo, Set<DTReservaConsulta> reservas) {
		this.nombreConsulta = nombreConsulta;
		this.nombreMedico = nombreMedico;
		this.fecha = fecha;
		this.nroSala = nroSala;
		this.cantidadNumeros = cantidadNumeros;
		this.costo = costo;
		this.reservas = reservas;
	}

	public String getNombreConsulta() { return nombreConsulta; }
	public String getNombreMedico() { return nombreMedico; }
	public DTFecha getFecha() { return fecha; }
	public int getNroSala() { return nroSala; }
	public int getCantidadNumeros() { return cantidadNumeros; }
	public float getCosto() { return costo; }
	public Set<DTReservaConsulta> getReservas() { return reservas; }
}