  package manejadores;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import clases.Consulta;

public class ManejadorConsultas {
    private static final ManejadorConsultas INSTANCIA = new ManejadorConsultas();
    private final Map<String, Consulta> consultas = new LinkedHashMap<>();
    private ManejadorConsultas() {}


    public static ManejadorConsultas getInstancia() {return INSTANCIA; }
    
    
    public Map<String, Consulta> getConsultas() {return Collections.unmodifiableMap(consultas);}
    public boolean existeConsulta(String nombre) { return consultas.containsKey(nombre); }
    public void agregarConsulta(Consulta consulta) { consultas.put(consulta.getNombre(), consulta); }
    public Consulta buscarConsulta(String nombre) { return consultas.get(nombre); }
    public void limpiar() { consultas.clear(); }
    
    }
