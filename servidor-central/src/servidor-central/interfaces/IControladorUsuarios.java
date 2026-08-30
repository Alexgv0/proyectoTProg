package interfaces;

import java.util.Set;

import clases.Usuario;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;
import datatypes.DTUsuario;

public interface IControladorUsuarios {
    //cu altaUsuario
    
    public Set<String> listarEspecialidades();
    
    public DTEspecialidad obtenerDatosEspecialidad(String nombre);
    
    public Usuario altaMedico(
    		String nickname, 
    		String nombre,
            String apellido, 
            String email, 
            int registro, 
            String mesLicencia, 
            String sitioWeb, 
            Set<DTEspecialidad> especialidades, 
            int porcentajeConsultas);
    
    public Usuario altaPaciente(String nickname, 
    		String nombre, 
    		String apellido, 
    		String email, 
    		DTFecha fecNacimiento, 
    		int documento, 
    		DTFecha fechaIngreso, 
    		Set<String>antecedentesMed); 
    
    public void altaUsuario(Usuario u);
    
    public void cancelarAltaUsuario(Usuario u);
    
    //cu consultaUsuario
    public Set<DTUsuario> listarUsuarios();
    
    public Set<DTUsuario> seleccionarUsuario(String nickname);
    
    public Set<String> listarPacientes();
    
    //cu modificarDatosUsuario opcional
    
    // CU :: AltaEspecialidad
    public boolean ingresarDatosAltaEspecialidad(String nombre, int porcentajeAdicional);

    
    //alta de beneficio anual opcional
    
    // consulta de beneficio anual opcional
}
