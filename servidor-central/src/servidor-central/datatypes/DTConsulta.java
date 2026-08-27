package datatypes;

public class DTConsulta {
	private String nombreConsulta;
	private DTFecha fecha;
	private int nroSala;
	private int cantidadNumeros;
	
	public DTConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros) {
		this.nombreConsulta = nombreConsulta;
		this.fecha = fecha;
		this.nroSala = nroSala;
		this.cantidadNumeros = cantidadNumeros;
	}
	
	public String getNombreConsulta() {
		return nombreConsulta;
	}
	
	public DTFecha getFecha() {
		return fecha;
	}
	
	public int getNroSala() {
		return nroSala;
	}
	
	public int getCantidadNumeros() {
		return cantidadNumeros;
	}
}
