package controladores;

import manejadores.ManejadorClinicas;

public class ControladorClinicas {

	private static ControladorClinicas intancia;
	
	private ManejadorClinicas mc;
	
	private ControladorClinicas() {
		this.mc = ManejadorClinicas.getInstance();
	}
	
}
