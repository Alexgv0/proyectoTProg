package manejadores;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import clases.DiaConsulta;

public class ManejadorDiasConsulta {
    private static final ManejadorDiasConsulta INSTANCIA = new ManejadorDiasConsulta();
    private final Map<String, DiaConsulta> diasConsulta = new LinkedHashMap<>();
    private ManejadorDiasConsulta() { }

    public static ManejadorDiasConsulta getInstancia() { return INSTANCIA; }

    public boolean existeDiaConsulta(String nombre) { return diasConsulta.containsKey(nombre); }
    public void agregarDiaConsulta(DiaConsulta dia) { diasConsulta.put(dia.getNombre(), dia); }
    public DiaConsulta buscarDiaConsulta(String nombre) { return diasConsulta.get(nombre); }
    public Map<String, DiaConsulta> getDiasConsulta() {return Collections.unmodifiableMap(diasConsulta);}
    public Set<DiaConsulta> obtenerDiasConsulta() {return new LinkedHashSet<>(diasConsulta.values());}
    public void limpiar() { diasConsulta.clear(); }  
}