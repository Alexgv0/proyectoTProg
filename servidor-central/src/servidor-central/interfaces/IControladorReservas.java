package interfaces;

import java.util.Set;
import datatypes.DTReservaConsulta;


public interface IControladorReservas {

    // CU :: consultaUsuario y CU :: Consulta de Reserva de Consulta
    public Set<DTReservaConsulta> listarReservasConsultas(String nickPaciente);
    
    public DTReservaConsulta seleccionarReservaConsulta(String nickPaciente, String nombreConsulta);
   

    //reserva de consulta

    public void altaReservaConsulta(String nickPaciente, String nombreConsulta);
    //consulta de reserva de consulta
    

}
