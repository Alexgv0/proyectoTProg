package controladores;
import datatypes.DTConsulta;
import datatypes.DTUsuario;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import interfaces.IControladorUsuarios;
import manejadores.ManejadorEspecialidades;
import manejadores.ManejadorUsuarios;
import clases.Paciente;
import clases.Usuario;
import clases.Especialidad;
import datatypes.DTFecha;
import datatypes.DTEspecialidad;


public class ControladorUsuarios implements IControladorUsuarios {

    private static ControladorUsuarios instancia;

    private ManejadorUsuarios mu;
    private ManejadorEspecialidades me;

    private ControladorUsuarios() {
        mu = ManejadorUsuarios.getInstancia();
        me = ManejadorEspecialidades.getInstancia();
    }

    public static ControladorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ControladorUsuarios();
        }
        return instancia;
    }
    
    //cu altaUsuario
    
    public Set<String> listarEspecialidades() {

        Set<Especialidad> especialidades =
                me.listarEspecialidades();

        Set<String> res = new HashSet<>();

        for (Especialidad e : especialidades) {
            res.add(e.getNombre());
        }

        return res;
    }
    
    public DTEspecialidad obtenerDatosEspecialidad(String nombre) {

        Especialidad e = me.obtenerEspecialidad(nombre);

        if (e == null) {
            throw new IllegalArgumentException(
                    "La especialidad no existe."
            );
        }

        return new DTEspecialidad(
                e.getNombre(),
                e.getPorcentajeCostoConsultaAdicional()
        );
    }
    
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
    	
    	if (mu.existeUsuario(nickname)) {
            throw new IllegalArgumentException(
                    "El nickname ya está en uso."
            );
        }

        if (mu.existeEmail(email)) {
            throw new IllegalArgumentException(
                    "El correo electrónico ya está en uso."
            );
        }

        if (especialidades == null || especialidades.isEmpty()) {
            throw new IllegalArgumentException(
                    "El médico debe tener al menos una especialidad."
            );
        }

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
    
    public Usuario altaPaciente(String nickname, 
    		String nombre, 
    		String apellido, 
    		String email, 
    		DTFecha fecNacimiento, 
    		int documento, 
    		DTFecha fechaIngreso, 
    		Set<String>antecedentesMed) {
    	
    	if (mu.existeUsuario(nickname)) {
            throw new IllegalArgumentException(
                "El nickname ya está en uso."
            );
        }

        if (mu.existeEmail(email)) {
            throw new IllegalArgumentException(
                "El correo electrónico ya está en uso."
            );
        }

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
    
    public void altaUsuario(Usuario u) {
        mu.agregarUsuario(u);
    }
    
    public void cancelarAltaUsuario(Usuario u) {
        mu.cancelarUsuario(u);
    }
    
    //cu consultaUsuario
    public Set<DTUsuario> listarUsuarios(){ 
        Set<DTUsuario> ret = new HashSet<>();
        ManejadorUsuarios m = ManejadorUsuarios.getInstancia();
        ret = m. obtenerDatos();                                   
        return ret;
    }
   
    public DTUsuario seleccionarUs(String nickname){       
        return mu.obtenerDatosUsuario(nickname);    
    
    }
    
  
   
    
    //cu modificarDatosUsuario opcional
    
    public Set<DTPaciente> listadoDePacientes(){
    	Set<DTPaciente> ps = mu.obtenerPacientes();
    	for (Paciente p : ps) {
    		DTPaciente dtp = p.obtenerDTPaciente();
    		ps.add(dtp);
    	}
    	return ps;
    }
    
    
    // CU :: AltaEspecialidad
    public boolean ingresarDatosAltaEspecialidad(String nombre, int porcentajeAdicional) {
        if (me.existeEspecialidad(nombre)) {
            return false;
        }

        me.altaEspecialidad(nombre, porcentajeAdicional);
        return true;
    }

    
    // cu Reserva de Consulta
    
    //alta de beneficio anual opcional
    
    // consulta de beneficio anual opcional
}