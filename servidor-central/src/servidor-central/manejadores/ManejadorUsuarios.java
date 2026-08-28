package manejadores;

import java.util.HashMap;
import java.util.Map;

import clases.Usuario;
import clases.Medico;
import clases.Paciente;
import java.util.Set;
//import java.util.HashSet;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;


public class ManejadorUsuarios {

    private static ManejadorUsuarios instancia;

    private Map<String, Usuario> usuarios;
    private Map<String, Paciente> pacientes;

    private ManejadorUsuarios() {
        usuarios = new HashMap<>();
    }

    public static ManejadorUsuarios getInstancia() {

        if (instancia == null) {
            instancia = new ManejadorUsuarios();
        }

        return instancia;
    }
    
    public boolean existeUsuario(String nickname) {
    	return usuarios.containsKey(nickname);
    }
    
    
    public Medico crearMedico(
    		String nickname, 
    		String nombre, 
    		String apellido, 
    		String email, 
    		int registro, 
    		String mesLicencia, 
    		String sitioWeb, 
    		Set<DTEspecialidad> especialidades, 
    		int porcentajeConsultas) {
    	Medico medico = new Medico(
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
    	usuarios.put(nickname, medico);
    	return medico;
    }
    
    public Paciente crearPaciente(
    		String nickname, 
    		String nombre, 
    		String apellido, 
    		String email, 
    		DTFecha fecNacimiento, 
    		int documento, 
    		DTFecha fechaIngreso, 
    		Set<String> antecedentesMed) {
    	Paciente paciente = new Paciente(
                nickname,
                nombre,
                apellido,
                email,
                fecNacimiento,
                documento,
                fechaIngreso,
                antecedentesMed
        );
    	usuarios.put(nickname, paciente);
    	return paciente;
    }
    
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNickname(), usuario);
    }
    
    public void cancelarUsuario(Usuario u) {
        usuarios.remove(u.getNickname());
    }

    //operaciones q pueden ser utiles
    public Usuario buscarUsuario(String nickname) {
        return usuarios.get(nickname);
    }
    

    public void eliminarUsuario(String nickname) {
        usuarios.remove(nickname);
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

	public Map<String, Paciente> obtenerPacientes() {
		return pacientes;
	}
    
    //implementar funciones de los manejadores

}