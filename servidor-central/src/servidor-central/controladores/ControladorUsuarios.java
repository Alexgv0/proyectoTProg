package controladores;

import manejadores.ManejadorUsuarios;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTEspecialidad;
import java.util.Set;
import datatypes.DTUsuario;

public class ControladorUsuarios {

    private static ControladorUsuarios instancia;

    private ManejadorUsuarios mu;

    private ControladorUsuarios() {
        mu = ManejadorUsuarios.getInstancia();
    }

    public static ControladorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ControladorUsuarios();
        }
        return instancia;
    }
    
    //cu altaUsuario
    public Usuario altaMedico(
    		String nickname, 
    		String nombre,
            String apellido, 
            String email, 
            int registro, 
            String mesLicencia, 
            String sitioWeb, 
            Set<DTEspecialidad> especialidades, 
            int porcentajeConsultas) {
    	if (!mu.existeUsuario(nickname)) {
            return mu.crearMedico(
                    nickname,
                    nombre,
                    apellido,
                    email,
                    registro,
                    mesLicencia,
                    sitioWeb,
                    especialidades,
                    porcentajeConsultas
            );
        }
        return null;
    }
    
    public Usuario altaPaciente(String nickname, 
    		String nombre, 
    		String apellido, 
    		String email, 
    		DTFecha fecNacimiento, 
    		int documento, 
    		DTFecha fechaIngreso, 
    		Set<String>antecedentesMed) {
    	if (!mu.existeUsuario(nickname)) {
            return mu.crearPaciente(
                    nickname,
                    nombre,
                    apellido,
                    email,
                    fecNacimiento,
                    documento,
                    fechaIngreso,
                    antecedentesMed
            );
        }
        return null;
    } 
    
    public void altaUsuario(Usuario u) {
        mu.agregarUsuario(u);
    }
    
    public void cancelarAltaUsuario(Usuario u) {
        mu.cancelarUsuario(u);
    }
    
    //cu consultaUsuario
    public Set<DTUsuario> listarUsuarios(){
    	return null;//todo
    }
    
    public Set<DTUsuario> SeleccionarUsuario(String nickname){
    	return null;//todo
    }
    
    //cu modificarDatosUsuario opcional
    
    //alta de especialidad
    
    //alta de beneficio anual opcional
    
    // consulta de beneficio anual opcional
}