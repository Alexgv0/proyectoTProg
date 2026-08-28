package controladores;

import manejadores.ManejadorReservas;
import manejadores.ManejadorUsuarios;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTUsuario;
import datatypes.DTEspecialidad;
import java.util.Set;
import datatypes.DTReservaConsulta;

public class ControladorReservas {

    private static ControladorReservas instancia;

    private ManejadorReservas mr;
    private ManejadorUsuarios mu;

    private ControladorReservas() {
    	mr = ManejadorReservas.getInstancia();
    	mu = ManejadorUsuarios.getInstancia();
    }

    public static ControladorReservas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorReservas();
        }
        return instancia;
    }
    
    
    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    public Set<DTUsuario> listarReservasConsultas(String nickPaciente){
    	Paciente p = mu.obtenerPaciente(nickPaciente);
    }
    
    public DTReservaConsulta seleccionarReservaConsulta(String nickPaciente, String nombreConsulta) {
    	return null;//todo
    }
    
   
    //reserva de consulta
    
    //consulta de reserva de consulta
}