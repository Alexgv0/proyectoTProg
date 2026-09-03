package manejadores;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import clases.Medico;
import clases.Paciente;
import clases.Usuario;
import datatypes.DTUsuario;

public class ManejadorUsuarios {
    private static final ManejadorUsuarios INSTANCIA = new ManejadorUsuarios();
    
    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();

    private ManejadorUsuarios() { }
    
    public static ManejadorUsuarios getInstancia() { return INSTANCIA; }

    public boolean existeUsuario(String nickname) { return usuarios.containsKey(nickname); }
    
    public boolean existeCorreo(String correo) {
        return usuarios.values().stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo));
    }
    
    public boolean existeEmail(String correo) { return existeCorreo(correo); }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNickname(), usuario);
    }
    
    public void cancelarUsuario(Usuario usuario) {
        if (usuario != null) usuarios.remove(usuario.getNickname(), usuario);
    }
    public Usuario buscarUsuario(String nickname) { return usuarios.get(nickname); }
    
    public Paciente obtenerPaciente(String nickname) {
        Usuario usuario = usuarios.get(nickname);
        return usuario instanceof Paciente ? (Paciente) usuario : null;
    }
    
    public Medico obtenerMedico(String nickname) {
        Usuario usuario = usuarios.get(nickname);
        return usuario instanceof Medico ? (Medico) usuario : null;
    }
    
    public Set<Paciente> obtenerPacientes() {
        Set<Paciente> pacientes = new LinkedHashSet<>();
        usuarios.values().stream().filter(Paciente.class::isInstance)
                .map(Paciente.class::cast).forEach(pacientes::add);
        return pacientes;
    }
    
    public Set<Medico> obtenerMedicos() {
        Set<Medico> medicos = new LinkedHashSet<>();
        usuarios.values().stream().filter(Medico.class::isInstance)
                .map(Medico.class::cast).forEach(medicos::add);
        return medicos;
    }
    
    public Set<DTUsuario> obtenerDatos() {
        Set<DTUsuario> datos = new LinkedHashSet<>();
        usuarios.values().stream().map(Usuario::getDatos).forEach(datos::add);
        return datos;
    }
    
    public DTUsuario obtenerDatosUsuario(String nickname) {
        Usuario usuario = buscarUsuario(nickname);
        return usuario == null ? null : usuario.getDatos();
    }
    
    public Map<String, Usuario> getUsuarios() {
        return Collections.unmodifiableMap(usuarios);
    }

    public void eliminarUsuario(String nickname) { usuarios.remove(nickname); }
    
    public void limpiar() { usuarios.clear(); }
}
