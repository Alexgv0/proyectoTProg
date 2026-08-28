package manejadores;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import clases.Clinica;

public class ManejadorClinicas {
	private static ManejadorClinicas instancia;
	
	private Map<String, Clinica> clinicas;
	
	private ManejadorClinicas() {
		clinicas = new HashMap<>();
	};
	
	public static ManejadorClinicas getInstancia() {
		if (instancia == null) instancia = new ManejadorClinicas();
		return instancia;
	}

	public Set<Clinica> obtenerClinicas() {
		return new HashSet<>(clinicas.values());
	}

	public Clinica obtenerClinica(String nombre) {
		return clinicas.get(nombre);
	}
	
	//PUEDE SER UTIL
	public void agregarClinica(Clinica clinica) {
        clinicas.put(clinica.getNombre(), clinica);
    }

}
