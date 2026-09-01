package datatypes;
//import java.util.Objects;

public class DTReserva {
	private final String nickPaciente;
    private final DTFecha fecha;
    private final int numeroCliente;
    private final float costoReserva;


	public DTReserva(DTFecha fecha, int numeroCliente, float costoReserva) {
        this("", fecha, numeroCliente, costoReserva);
    }
	public DTReserva(String nickPaciente, DTFecha fecha, int numeroCliente, float costoReserva) {
        this.nickPaciente = nickPaciente;
        this.fecha = fecha;
        this.numeroCliente = numeroCliente;
        this.costoReserva = costoReserva;
    }


	public String getNickPaciente() { return nickPaciente; }
    public DTFecha getFecha() { return fecha; }
    public int getNumeroCliente() { return numeroCliente; }
    public float getCostoReserva() { return costoReserva; }

	@Override
    public String toString() { return (nickPaciente == null || nickPaciente.isBlank() ? "Reserva" : nickPaciente)+ " - numero " + numeroCliente;}
	
	/*@Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTReserva)) return false;
        DTReserva otra = (DTReserva) objeto;
        return numeroCliente == otra.numeroCliente
                && Objects.equals(nickPaciente, otra.nickPaciente);
    }

    @Override
    public int hashCode() { return Objects.hash(nickPaciente, numeroCliente); }
*/
}