package manejadores;

import clases.DiaConsulta;

import java.util.HashMap;
import java.util.Map;

public class ManejadorDiasConsulta {

    private static ManejadorDiasConsulta instancia;

    private Map<String, DiaConsulta> diasConsulta;

    private ManejadorDiasConsulta() {
        diasConsulta = new HashMap<>();
    }

    public static ManejadorDiasConsulta getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDiasConsulta();
        }
        return instancia;
    }
    
    public boolean existeDiaConsulta(String nombreDiaConsulta) {
	    return diasConsulta.containsKey(nombreDiaConsulta);
	}
    
}