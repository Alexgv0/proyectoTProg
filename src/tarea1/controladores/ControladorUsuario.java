package controladores;

import manejadores.ManejadorUsuarios;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTEspecialidad;
import java.util.Set;
//import java.util.HashSet;

public class ControladorUsuario {

    private static ControladorUsuario instancia;

    private ManejadorUsuarios manejadorUsuarios;

    private ControladorUsuario() {
        manejadorUsuarios = ManejadorUsuarios.getInstancia();
    }

    public static ControladorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }
        return instancia;
    }
    
    public Usuario altaMedico(String nickname, String nombre,
            String apellido, String email, int registro, String mesLicencia, 
            String sitioWeb, Set<DTEspecialidad> especialidades, int porcentajeConsultas) {
    	//to do
    }
    
    public Usuario altaPaciente(String nickname, String nombre, 
    		String apellido, String email, DTFecha fecNacimiento, 
    		int documento, DTFecha fechaIngreso, Set<String>antecedentesMed) {
    	// to do
    } 
    
    // ALTA DE USUARIO
    public void altaUsuario(Usuario u) {

        if (manejadorUsuarios.existeUsuario(u.getNickname())) {
            throw new IllegalArgumentException(
                "El nickname ya está registrado."
            );
        }

        Usuario usuario = new Usuario(
            u.getNickname(),
            u.getNombre(),
            u.getApellido(),
            u.getEmail()
        );

        manejadorUsuarios.agregarUsuario(usuario);
    }
    
    public void cancelar(Usuario u) {
    	//to do
    }
    
}