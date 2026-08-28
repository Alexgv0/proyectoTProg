package manejadores;

import java.util.Set;

import clases.Clinica;

public class ManejadorClinicas {
	private static ManejadorClinicas instancia;
	
	private ManejadorClinicas() {
		
	};
	
	public static ManejadorClinicas getInstancia() {
		if (instancia != null) instancia = new ManejadorClinicas();
		return instancia;
	}

	public Set<Clinica> obtenerClinicas() {
		// TODO Auto-generated method stub
		return null;
	}

}
