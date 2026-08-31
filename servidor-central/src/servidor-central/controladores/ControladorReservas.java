package controladores;

import interfaces.IControladorReservas;
import manejadores.ManejadorUsuarios;
import clases.BeneficioAnual;
import clases.Consulta;
import clases.Paciente;
import clases.Reserva;
import datatypes.DTReserva;
import datatypes.DTReservaDetallada;

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
    	if (p == null) return null;
    	Set<DTReserva> reservas = p.listarReservas();
    	return reservas;
    }
    
    public DTReservaDetallada seleccionarReservaConsulta(String nickPaciente, String nombreConsulta) {
    	Paciente p = mu.obtenerPaciente(nickPaciente);
    	if (p == null) return null;
    	Reserva r = p.obtenerReserva(nombreConsulta);
    	if (r == null) return null;
    	Paciente pr = r.getPaciente();
    	Consulta cr = r.getConsulta();
    	BeneficioAnual bar = r.getBeneficio();
    	if (pr == null || cr == null) return null;
    	if (bar == null) return new DTReservaDetallada(
    			r.getFecha(),
    			r.getNumeroCliente(),
    			r.getCostoReserva(),
    			cr.getNombre(),
    			pr.getNickname(),
    			null
    			);
    	return new DTReservaDetallada(
    			r.getFecha(),
    			r.getNumeroCliente(),
    			r.getCostoReserva(),
    			cr.getNombre(),
    			pr.getNickname(),
    			bar.getConsultasGratis()
    			);
    }
    
   
    //reserva de consulta
    
    //consulta de reserva de consulta
}