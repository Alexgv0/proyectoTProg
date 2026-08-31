package controladores;

import java.time.LocalDate;         // capz hay que seterla manual 


import interfaces.IControladorReservas;
import manejadores.ManejadorUsuarios;
import manejadores.ManejadorConsultas;
import clases.Consulta;
import clases.Paciente;
import clases.Reserva;
import clases.BeneficioAnual;
import datatypes.DTReserva;
import datatypes.DTReservaConsulta;
import datatypes.DTFecha;

import java.util.Set;

public class ControladorReservas implements IControladorReservas {

    private static ControladorReservas instancia;

    private ManejadorUsuarios mu;
    private ManejadorConsultas mc;

    private ControladorReservas() {
    	mu = ManejadorUsuarios.getInstancia();
		mc = ManejadorConsultas.getInstancia();
    }

    public static ControladorReservas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorReservas();
        }
        return instancia;
    }
    
    
    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    public Set<DTReservaConsulta> listarReservasConsultas(String nickPaciente){
    	 Paciente paciente =mu.obtenerPaciente(nickPaciente);
    if (paciente == null) {
        throw new IllegalArgumentException(
            "No existe el paciente"
        );
    }
    return paciente.listarReservas();
    }
    
    public DTReservaConsulta seleccionarReservaConsulta(String nickPaciente, String nombreConsulta) {
    	Paciente p = mu.obtenerPaciente(nickPaciente);
    	Reserva r = p.obtenerReserva(nombreConsulta);
    	return r.getDatosReservaConsulta();
    }
    
  //Alta ReservaDeConsulta

	public void altaReservaConsulta(String nickPaciente, String nombreConsulta) {
     Paciente paciente = mu.obtenerPaciente(nickPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("No existe el paciente indicado" );
        }

        Consulta consulta = mc.buscarConsulta(nombreConsulta);

        if (consulta == null) {throw new IllegalArgumentException("No existe la consulta indicada");
        }
        if (consulta.tieneReservaPaciente(paciente)) {
            throw new IllegalStateException("El paciente ya tiene una reserva para esta consulta");
        }

        if (consulta.estaCompleta()) {throw new IllegalStateException("La consulta no tiene números disponibles");
        }

        LocalDate hoy = LocalDate.now();
        DTFecha fechaReserva = new DTFecha(hoy.getDayOfMonth(), hoy.getMonthValue(), hoy.getYear());
        BeneficioAnual beneficio = paciente.obtenerBeneficioConsultaDisponible(hoy.getYear());
		
        Reserva reserva = new Reserva(fechaReserva,consulta,paciente,beneficio);

        consulta.agregarReserva(reserva);
        paciente.agregarReserva(reserva);
    }

    }



    //consulta de reserva de consulta //////////////


}