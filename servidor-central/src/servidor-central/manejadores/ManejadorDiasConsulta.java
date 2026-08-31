package manejadores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clases.DiaConsulta;

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
    
    public void agregarDiaConsulta(DiaConsulta diaConsulta) {
        diasConsulta.put(diaConsulta.getNombre(), diaConsulta);
    }

    public DiaConsulta buscarDiaConsulta(String nombre) {
        return diasConsulta.get(nombre);
    }
    
    //puede ser util
    
    public Map<String, DiaConsulta> getDiasConsulta() {
    return diasConsulta;
}
    
}