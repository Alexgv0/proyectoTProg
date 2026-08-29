package datatypes;

public class DTReserva {
	private String nickPaciente;
	private DTFecha fecha;
	private int numeroCliente;
	private float costoReserva;

	public DTReserva(DTFecha fecha, int numCli, float costoRes) {
		this(null, fecha, numCli, costoRes);
	}

	public DTReserva(String nickPaciente, DTFecha fecha, int numCli, float costoRes) {
		this.nickPaciente = nickPaciente;
		this.fecha = fecha;
		this.numeroCliente = numCli;
		this.costoReserva = costoRes;
	}

	public String getNickPaciente() { 
		return nickPaciente; 
		}

	public DTFecha getFecha() { 
		return this.fecha; 
		}

	public int getNumeroCliente() { 
		return this.numeroCliente; 
		}

	public float getCostoReserva() { 
		return this.costoReserva; 
		}
}