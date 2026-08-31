package datatypes;

public class DTReserva {
	private DTFecha fecha;
	private int numeroCliente;
	private float costoReserva;

	public DTReserva(DTFecha fecha, int numCli, float costoRes) {
		this.fecha = fecha;
		this.numeroCliente = numCli;
		this.costoReserva = costoRes;
	}

	public DTFecha getFecha() { return this.fecha; }
	public int getNumeroCliente() { return this.numeroCliente; }
	public float getCostoReserva() { return this.costoReserva; }
}