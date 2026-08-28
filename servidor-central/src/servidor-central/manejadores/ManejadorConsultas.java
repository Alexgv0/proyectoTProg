  package manejadores;

import java.util.HashMap;
import java.util.Map;

import clases.Consulta;

public class ManejadorConsultas {

    private static ManejadorConsultas instancia;

    private Map<String, Consulta> consultas;

    private ManejadorConsultas() {
        consultas = new HashMap<>();
    }

    public static ManejadorConsultas getInstancia() {

        if (instancia == null) {
            instancia = new ManejadorConsultas();
        }

        return instancia;
    }
	
	
}
