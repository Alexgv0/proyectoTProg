package interfaces;

import java.util.Set;

import datatypes.DTClinica;
import datatypes.DTConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsulta;
import datatypes.DTDiaConsultaCompleto;
import datatypes.DTFecha;
import datatypes.DTUsuario;
import enums.Dia;

public interface IControladorClinicas {    
	void altaClinica(String nombre, String descripcion, String direccion,String sitioWeb, float costoMedicamento, float costoConsulta);
	Set<String> listarClinicas();
    DTClinica obtenerDatosClinica(String nombre);

    Set<String> listarEspecialidades();

    void altaDiaConsulta(String nombre, String descripcion, Dia diaSemana,int horaInicio, String especialidad, String clinica);
    Set<DTDiaConsulta> listarDiasConsulta();
    DTDiaConsultaCompleto obtenerDatosDiaConsulta(String nombre);
	Set<DTConsultaSoloConsulta> listarConsultasDia(String nombreDiaConsulta); //devuelve todas las consultas asociadas a un dia de consulta 
	Set<DTConsulta> seleccionarDiaConsulta(String nombreDiaConsulta);

    void altaConsulta(String nombre, DTFecha fecha, int numeroSala, int cantidadNumeros, String nombreDiaConsulta, String nicknameMedico);
    Set<DTConsultaSoloConsulta> listarConsultas(DTUsuario usuario);
    DTConsulta seleccionarConsulta(String nombreConsulta);
}
