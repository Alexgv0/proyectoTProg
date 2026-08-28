package controladores;

import manejadores.ManejadorReservas;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTUsuario;
import datatypes.DTEspecialidad;
import java.util.Set;
import datatypes.DTReservaConsulta;

public class ControladorReservas {

    private static ControladorReservas instancia;

    private ManejadorReservas mr;

    private ControladorReservas() {
    	mr = ManejadorReservas.getInstancia();
    }

    public static ControladorReservas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorReservas();
        }
        return instancia;
    }
    
    
    //cu consultaUsuario
    public Set<DTUsuario> listarReservasConsultas(String nickPaciente){
    	return null;//todo
    }
    
    public DTReservaConsulta seleccionarReservaConsulta(String nickPaciente, String nombreConsulta) {
    	return null;//todo
    }
    
    /*todo este grupito va en controladorclinicas
    //alta de dia de consulta
    //consulta de dia de consulta
    //alta de consulta
    //consulta de consulta*/
    
    
    //reserva de consulta
    
    //consulta de reserva de consulta
}