package datatypes;

public class DTReservaConsulta {
	private String nickPaciente;
	private DTFecha fecha;
	private int numeroCliente;
	private float costoReserva;

	public DTReservaConsulta(String nickPaciente, DTFecha fecha, int numCli, float costoRes) {
		this.nickPaciente = nickPaciente;
		this.fecha = fecha;
		this.numeroCliente = numCli;
		this.costoReserva = costoRes;
	}

	public String getNickPaciente() { return nickPaciente; }
	public DTFecha getFecha() { return fecha; }
	public int getNumeroCliente() { return numeroCliente; }
	public float getCostoReserva() { return costoReserva; }
}