package clases;

import java.time.LocalDate;
import java.time.Period;
import datatypes.DTReservaConsulta;
import datatypes.DTFecha;
import datatypes.DTFecha;
import datatypes.DTReserva;

public class Reserva {
    private final Consulta consulta;
    private final Paciente paciente;
    private final BeneficioAnual beneficio;
    private final DTFecha fecha;
    private final int numeroCliente;
    private final float costoReserva;

    public Reserva(DTFecha fecha, Consulta consulta, Paciente paciente) {
        this(fecha, consulta, paciente, null);
    }

    public Reserva(DTFecha fecha, Consulta consulta, Paciente paciente,
            BeneficioAnual beneficio) {
        this.fecha = fecha;
        this.consulta = consulta;
        this.paciente = paciente;
        this.beneficio = beneficio;
        this.numeroCliente = consulta.obtenerMenorNumeroDisponible();
        this.costoReserva = calcularCostoReserva();
        if (beneficio != null) beneficio.consumirConsultaGratis();
    }


    private float calcularCostoReserva() {
        if (beneficio != null) {
            return 0f;
        }

        int edad = Period.between(paciente.getFecNacimiento().toLocalDate(), fecha.toLocalDate()).getYears();

        if (edad > 60) {
            return consulta.getCostoConsulta() * 0.80f;
        }

        return consulta.getCostoConsulta();
    }

    public Consulta getConsulta() { return consulta; }
    
    public Paciente getPaciente() { return paciente; }
    
    public BeneficioAnual getBeneficio() { return beneficio; }
    
    public DTFecha getFecha() { return fecha; }
    
    public int getNumeroCliente() { return numeroCliente; }
    
    public float getCostoReserva() { return costoReserva; }
    
    public DTReserva crearDTReserva() {
        return new DTReserva(paciente.getNickname(), fecha, numeroCliente, costoReserva);
    }
    
    public DTReservaConsulta getDatosReservaConsulta() {
        return new DTReservaConsulta(consulta.getNombre(), paciente.getNickname(), fecha, numeroCliente, costoReserva);
    }
}
