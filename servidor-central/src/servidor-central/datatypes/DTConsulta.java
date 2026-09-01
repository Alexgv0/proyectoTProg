package datatypes;

public class DTConsulta {
	private String nombreConsulta;
	private DTFecha fecha;
	private int nroSala;
	private int cantidadNumeros;
	private float costo;

	public DTConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros, float costo) {
		this.nombreConsulta = nombreConsulta;
		this.fecha = fecha;
		this.nroSala = nroSala;
		this.cantidadNumeros = cantidadNumeros;
		this.costo = costo; //TODO:
	}

	public String getNombreConsulta() { return nombreConsulta; }

	public DTFecha getFecha() { return fecha; }

	public int getNroSala() { return nroSala; }

	public int getCantidadNumeros() { return cantidadNumeros; }

	public float getCosto() { return costo; }
}