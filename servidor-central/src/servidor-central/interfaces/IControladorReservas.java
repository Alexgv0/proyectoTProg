package interfaces;

import java.util.Set;

import datatypes.DTReserva;
import datatypes.DTReservaDetallada;

public interface IControladorReservas {

    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    public Set<DTReserva> listarReservasConsultas(String nickPaciente);
    
    public DTReservaDetallada seleccionarReservaConsulta(String nickPaciente, String nombreConsulta);
   
    //reserva de consulta
    
    //consulta de reserva de consulta
}
