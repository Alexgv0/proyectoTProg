package interfaces;

import java.util.Set;

import clases.Usuario;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;
import datatypes.DTUsuario;
//import datatypes.DTBeneficioAnual;

public interface IControladorUsuarios {
    //cu altaUsuario
    Set<String> listarEspecialidades();
    DTEspecialidad obtenerDatosEspecialidad(String nombre);
    
    Usuario altaMedico(String nickname, String nombre,String apellido, String email, int registro, String mesLicencia, String sitioWeb, Set<DTEspecialidad> especialidades, int porcentajeConsultas);
    Usuario altaPaciente(String nickname, String nombre, String apellido, String email, DTFecha fecNacimiento, int documento, DTFecha fechaIngreso, Set<String>antecedentesMed); 
    
    void altaUsuario(Usuario u);
    void cancelarAltaUsuario(Usuario u);
    
    //cu consultaUsuario
    Set<DTUsuario> listarUsuarios();
    Set<DTUsuario> seleccionarUsuario(String nickname);
    Set<String> listarPacientes();
    Set<String> listarMedicos();
    
    //cu modificarDatosUsuario opcional
    //void modificarPaciente(String nickname, String nombre, String apellido,DTFecha fecNacimiento, int documento, DTFecha fechaIngreso,Set<String> antecedentesMed);
    //void modificarMedico(String nickname, String nombre, String apellido, int registro,String mesLicencia, String sitioWeb, Set<String> especialidades,int porcentajeConsultas);


    // CU :: AltaEspecialidad
    boolean ingresarDatosAltaEspecialidad(String nombre, int porcentajeAdicional);

    //alta de beneficio anual opcional
    //void altaBeneficioAnual(String nickPaciente, int anio, int consultasGratis,int ticketsGratis);

    // consulta de beneficio anual opcional
    //DTBeneficioAnual seleccionarBeneficioAnual(String nickPaciente, int anio);

}
