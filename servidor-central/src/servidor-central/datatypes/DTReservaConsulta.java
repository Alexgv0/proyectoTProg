package datatypes;
//import java.util.Objects;

public class DTReservaConsulta {

    private final String nombreConsulta;
    private final String nickPaciente;
    private final DTFecha fecha;
    private final int numeroCliente;
    private final float costoReserva;

    public DTReservaConsulta(String nombreConsulta, DTFecha fecha, int numeroCliente,float costoReserva) {
        this(nombreConsulta, "", fecha, numeroCliente, costoReserva);
    }
    public DTReservaConsulta(String nombreConsulta, String nickPaciente, DTFecha fecha,int numeroCliente, float costoReserva) {
        this.nombreConsulta = nombreConsulta;
        this.nickPaciente = nickPaciente;
        this.fecha = fecha;
        this.numeroCliente = numeroCliente;
        this.costoReserva = costoReserva;
    }

    public String getNombreConsulta() { return nombreConsulta; }
    public String getNickPaciente() { return nickPaciente; }
    public DTFecha getFecha() { return fecha; }
    public int getNumeroCliente() { return numeroCliente; }
    public float getCostoReserva() { return costoReserva; }

    @Override
    public String toString() { return nombreConsulta + " - numero " + numeroCliente; }

    /*@Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTReservaConsulta)) return false;
        DTReservaConsulta otra = (DTReservaConsulta) objeto;
        return Objects.equals(nombreConsulta, otra.nombreConsulta)
                && Objects.equals(nickPaciente, otra.nickPaciente);
    }

    @Override
    public int hashCode() { return Objects.hash(nombreConsulta, nickPaciente); }
*/

}