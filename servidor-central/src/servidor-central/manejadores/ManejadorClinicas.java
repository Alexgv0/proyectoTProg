package manejadores;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import clases.Clinica;

public class ManejadorClinicas {
    private static final ManejadorClinicas instancia = new ManejadorClinicas();
    private final Map<String, Clinica> clinicas =new LinkedHashMap<>();
    private ManejadorClinicas() {}

    public static ManejadorClinicas getInstancia() {
        return instancia;
    }


	public Set<Clinica> listarClinicas() {
        return new LinkedHashSet<>(clinicas.values());}

    public Clinica obtenerClinica(String nombre) {
        return clinicas.get(nombre);}

    public void agregarClinica(Clinica clinica) {
        clinicas.put(clinica.getNombre(), clinica);}

    public Map<String, Clinica> getClinicas() {
        return Collections.unmodifiableMap(clinicas);}

    public void limpiar() {
        clinicas.clear();}
}
