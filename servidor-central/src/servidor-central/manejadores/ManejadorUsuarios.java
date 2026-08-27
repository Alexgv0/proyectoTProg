package manejadores;

import java.util.HashMap;
import java.util.Map;

import clases.Usuario;

public class ManejadorUsuarios {

    private static ManejadorUsuarios instancia;

    private Map<String, Usuario> usuarios;

    private ManejadorUsuarios() {
        usuarios = new HashMap<>();
    }

    public static ManejadorUsuarios getInstancia() {

        if (instancia == null) {
            instancia = new ManejadorUsuarios();
        }

        return instancia;
    }
    
    //operaciones q pueden ser utiles
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNickname(), usuario);
    }

    public Usuario buscarUsuario(String nickname) {
        return usuarios.get(nickname);
    }

    public boolean existeUsuario(String nickname) {
        return usuarios.containsKey(nickname);
    }

    public void eliminarUsuario(String nickname) {
        usuarios.remove(nickname);
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    
    //implementar funciones de los manejadores
}