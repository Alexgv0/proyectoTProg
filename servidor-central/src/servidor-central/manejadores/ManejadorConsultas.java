  package manejadores;

import java.util.HashMap;
import java.util.Map;

import clases.Consulta;

public class ManejadorConsultas {

    private static ManejadorConsultas instancia;

    private Map<String, Consulta> consultas;

    private ManejadorConsultas() {
        this.consultas = new HashMap<>();
    }

    public static ManejadorConsultas getInstancia() {

        if (instancia == null) {
            instancia = new ManejadorConsultas();
        }

        return instancia;
    }

	public Map<String, Consulta> getConsultas() {
		return consultas;
	}

	public boolean existeConsulta(String nombre) {
	    return consultas.containsKey(nombre);
    }

    public void agregarConsulta(Consulta consulta) {
	    consultas.put(consulta.getNombre(), consulta);
    }

    public Consulta buscarConsulta(String nombre) {
	    return consultas.get(nombre);
    }
}
