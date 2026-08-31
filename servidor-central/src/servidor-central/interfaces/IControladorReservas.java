package interfaces;

import java.util.Set;
import datatypes.DTReservaConsulta;
import datatypes.DTFecha;

public interface IControladorReservas {

    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
     Set<DTReservaConsulta> listarReservasConsultas(String nickPaciente);
     DTReservaConsulta seleccionarReservaConsulta(String nickPaciente, String nombreConsulta);

    //reserva de consulta
       void altaReservaConsulta(String nickPaciente, String nombreConsulta);
       void altaReservaConsulta(String nickPaciente, String nombreConsulta,DTFecha fechaReserva);
    //consulta de reserva de consulta
    

}
