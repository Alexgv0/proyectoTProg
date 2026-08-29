package manejadores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clases.Especialidad;

public class ManejadorEspecialidades {

    private static ManejadorEspecialidades instancia;

    private Map<String, Especialidad> especialidades;

    private ManejadorEspecialidades() {
        especialidades = new HashMap<>();
    }

    public static ManejadorEspecialidades getInstancia() {

        if (instancia == null) {
            instancia = new ManejadorEspecialidades();
        }

        return instancia;
    }

    public Especialidad obtenerEspecialidad(String nombre) {
        return especialidades.get(nombre);
    }
    
    public Set<Especialidad> listarEspecialidades() {
    	return new HashSet<>(especialidades.values());
    }
    
    // CU :: AltaEspecialidad
    
    public boolean existeEspecialidad(String nombre) {
        return especialidades.containsKey(nombre);
    }

    public void altaEspecialidad(String nombre, int porcentajeAdicional) {
    	Especialidad especialidad = new Especialidad(nombre, porcentajeAdicional);

            especialidades.put(nombre, especialidad);
    }

    public void agregarEspecialidad(Especialidad especialidad) {
        especialidades.put(
            especialidad.getNombre(),
            especialidad
        );
    }
}