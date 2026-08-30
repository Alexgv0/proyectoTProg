package controladores;

import interfaces.IControladorReservas;
import manejadores.ManejadorUsuarios;
import clases.Paciente;
import clases.Reserva;
import datatypes.DTReserva;

import java.util.Set;

public class ControladorReservas implements IControladorReservas {

    private static ControladorReservas instancia;

    private ManejadorUsuarios mu;

    private ControladorReservas() {
    	mu = ManejadorUsuarios.getInstancia();
    }

    public static ControladorReservas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorReservas();
        }
        return instancia;
    }
    
    
    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    public Set<DTReserva> listarReservasConsultas(String nickPaciente){
    	Paciente p = mu.obtenerPaciente(nickPaciente);
    	Set<DTReserva> reservas = p.listarReservas();
    	return reservas;
    }
    
    public DTReserva seleccionarReservaConsulta(String nickPaciente, String nombreConsulta) {
    	Paciente p = mu.obtenerPaciente(nickPaciente);
    	Reserva r = p.obtenerReserva(nombreConsulta);
    	return r.crearDTReserva();
    }
    
   
    //reserva de consulta
    
    //consulta de reserva de consulta
}