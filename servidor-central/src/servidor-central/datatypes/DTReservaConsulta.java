package datatypes;

public class DTReservaConsulta {

    private String nombreConsulta;
    private DTFecha fecha;
    private int numeroCliente;
    private float costoReserva;

    public DTReservaConsulta(
            String nombreConsulta,
            DTFecha fecha,
            int numeroCliente,
            float costoReserva) {

        this.nombreConsulta = nombreConsulta;
        this.fecha = fecha;
        this.numeroCliente = numeroCliente;
        this.costoReserva = costoReserva;
    }

    public String getNombreConsulta() {
        return nombreConsulta;
    }

    public DTFecha getFecha() {
        return fecha;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public float getCostoReserva() {
        return costoReserva;
    }
}