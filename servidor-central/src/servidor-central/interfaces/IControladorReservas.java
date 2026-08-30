package interfaces;

import java.util.Set;

import datatypes.DTReserva;

public interface IControladorReservas {

    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    public Set<DTReserva> listarReservasConsultas(String nickPaciente);
    
    public DTReserva seleccionarReservaConsulta(String nickPaciente, String nombreConsulta);
   
    //reserva de consulta
    
    //consulta de reserva de consulta
}
