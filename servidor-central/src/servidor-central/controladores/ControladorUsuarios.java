package controladores;

import manejadores.ManejadorUsuarios;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTEspecialidad;
import java.util.Set;

public class ControladorUsuarios {

    private static ControladorUsuarios instancia;

    private ManejadorUsuarios manejadorUsuarios;

    private ControladorUsuarios() {
        manejadorUsuarios = ManejadorUsuarios.getInstancia();
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
    	if (!manejadorUsuarios.existeUsuario(nickname)) {
            return manejadorUsuarios.crearMedico(
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
    	if (!manejadorUsuarios.existeUsuario(nickname)) {
            return manejadorUsuarios.crearPaciente(
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
        manejadorUsuarios.agregarUsuario(u);
    }
    
    public void cancelar(Usuario u) {
        manejadorUsuarios.cancelarUsuario(u);
    }
    
    //cu consultaUsuario
    
    //cu modificarDatosUsuario
    
    //
}