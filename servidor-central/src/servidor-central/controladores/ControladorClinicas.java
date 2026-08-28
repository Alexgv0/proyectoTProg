package controladores;

import manejadores.ManejadorClinicas;
import manejadores.ManejadorUsuarios;
import clases.Clinica;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTClinica;
import datatypes.DTEspecialidad;

import java.util.HashSet;
import java.util.Set;

public class ControladorClinicas {

    // private
	private static ControladorClinicas instancia;
	
    private ManejadorClinicas mc;
    private ManejadorUsuarios mu;

    private ControladorClinicas() {
        this.mc = ManejadorClinicas.getInstancia();
        this.mu = ManejadorUsuarios.getInstancia();
    }

    
    // public
    public static ControladorClinicas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorClinicas();
        }
        return instancia;
    }
    
    // CU :: Consulta de Clinica
    
    public Set<String> listarClinicas() {
    	Set<Clinica> clinicas = mc.obtenerClinicas();
    	Set<String> res = new HashSet<>();
    	
    	for(Clinica c : clinicas) {
    		res.add(c.getNombre());
    	}
    	
    	return res;
    }
    
    public DTClinica obtenerDatosClinica(String nombre) {
		Clinica c = mc.obtenerClinica(nombre);
    	return c.getDatosClinica();
    }
    
    
    
    //alta de clinica
    
    //consulta de clinica
}
