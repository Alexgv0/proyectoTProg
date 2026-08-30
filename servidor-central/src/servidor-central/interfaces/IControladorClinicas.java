package interfaces;

import java.util.Set;
import datatypes.DTClinica;
import datatypes.DTConsulta;
import datatypes.DTUsuario;
import enums.Dia;

public interface IControladorClinicas {    
	    
	    // CU :: Consulta de Clinica
	    public Set<String> listarClinicas();
	    
	    public DTClinica obtenerDatosClinica(String nombre);
	    
	    //consulta usuario
	    public Set<DTConsulta> listarConsultas(DTUsuario u);
	    
	    //ALTA DE dia de consulta
	    public Set<String> listarEspecialidades();
	    
	    public void altaDiaConsulta(
	            String nombre,
	            String descripcion,
	            Dia diaSemana,
	            int horaInicio,
	            String especialidad,
	            String clinica);
	    
	    //alta de clinica
	    public void altaClinica(
			String nombre, String descripcion, String direccion,
			String sitioWeb, float costoMedicamento, float costoConsulta);
	    
	    //consulta de clinica

	    //Consulta Día Consulta
	    
	    //Alta Consulta
	    
	    //Consulta Consulta
	    public Set<DTConsulta> seleccionarDiaConsulta(String nombreDiaConsulta);

	public DTConsulta seleccionarConsulta(String nombreConsulta);
}
