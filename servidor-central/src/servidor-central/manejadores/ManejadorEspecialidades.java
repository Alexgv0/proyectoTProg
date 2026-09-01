package manejadores;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import clases.Especialidad;

public class ManejadorEspecialidades {
    private static final ManejadorEspecialidades INSTANCIA = new ManejadorEspecialidades();
    private final Map<String, Especialidad> especialidades = new LinkedHashMap<>();
    private ManejadorEspecialidades() { }

    public static ManejadorEspecialidades getInstancia() { return INSTANCIA; }

    public Especialidad obtenerEspecialidad(String nombre) { return especialidades.get(nombre); }
    public Set<Especialidad> listarEspecialidades() {return new LinkedHashSet<>(especialidades.values());}
    public boolean existeEspecialidad(String nombre) { return especialidades.containsKey(nombre); }
    public void altaEspecialidad(String nombre, int porcentaje) {agregarEspecialidad(new Especialidad(nombre, porcentaje));}
    public void agregarEspecialidad(Especialidad especialidad) {especialidades.put(especialidad.getNombre(), especialidad);}
    public void limpiar() { especialidades.clear(); }

}