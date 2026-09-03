package controladores;

import java.time.LocalDate;         // capz hay que seterla manual 


import interfaces.IControladorReservas;
import manejadores.ManejadorUsuarios;
import manejadores.ManejadorConsultas;
import clases.Consulta;
import clases.Paciente;
import clases.Reserva;
import clases.BeneficioAnual;
import datatypes.DTReservaConsulta;
import datatypes.DTFecha;

import java.util.Set;

public class ControladorReservas implements IControladorReservas {
    private static final ControladorReservas INSTANCIA = new ControladorReservas();
    private final ManejadorUsuarios mu = ManejadorUsuarios.getInstancia();
    private final ManejadorConsultas mc = ManejadorConsultas.getInstancia();

    private ControladorReservas() { }
    
    public static ControladorReservas getInstancia() { return INSTANCIA; }

    
    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    @Override
    public Set<DTReservaConsulta> listarReservasConsultas(String nickPaciente) {
        Paciente paciente = mu.obtenerPaciente(nickPaciente);
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        return paciente.listarReservas();
    }
    
    @Override
    public DTReservaConsulta seleccionarReservaConsulta(String nickPaciente, String nombreConsulta) {
        Paciente paciente = mu.obtenerPaciente(nickPaciente);
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        
        Reserva reserva = paciente.obtenerReserva(nombreConsulta);
        
        if (reserva == null) throw new IllegalArgumentException("La reserva no existe.");
        return reserva.getDatosReservaConsulta();
    }

    @Override
    public void altaReservaConsulta(String nickPaciente, String nombreConsulta) {
        altaReservaConsulta(nickPaciente, nombreConsulta, DTFecha.from(LocalDate.now()));
    }

    @Override
    public void altaReservaConsulta(String nickPaciente, String nombreConsulta, DTFecha fechaReserva) {
        Paciente paciente = mu.obtenerPaciente(nickPaciente);
        
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        Consulta consulta = mc.buscarConsulta(nombreConsulta);
        
        if (consulta == null) throw new IllegalArgumentException("La consulta no existe.");
        
        if (fechaReserva == null) throw new IllegalArgumentException("La fecha de reserva es obligatoria.");
        
        if (consulta.tieneReservaPaciente(paciente)) throw new IllegalStateException("El paciente ya tiene una reserva para esta consulta.");
        
        if (consulta.estaCompleta()) throw new IllegalStateException("La consulta no tiene numeros disponibles.");
        
        boolean consultaIgualFechaHora = paciente.listarReservas().stream().anyMatch(r -> {
            Consulta consultaReservada = mc.buscarConsulta(r.getNombreConsulta());

            return consultaReservada.getFecha().equals(fechaReserva)
                && consultaReservada.getDiaConsulta().getHoraInicio()
                    == consulta.getDiaConsulta().getHoraInicio();
        });

        if (consultaIgualFechaHora) {
            throw new IllegalArgumentException(
                "El paciente ya tiene una reserva para esa fecha y hora."
            );
        }
        
        BeneficioAnual beneficio = paciente.obtenerBeneficioConsultaDisponible(fechaReserva.getAnio());
        Reserva reserva = new Reserva(fechaReserva, consulta, paciente, beneficio);
        consulta.agregarReserva(reserva);
        paciente.agregarReserva(reserva);
    }
}