package fabrica;

import interfaces.IControladorUsuarios;
import interfaces.IControladorReservas;
import interfaces.IControladorClinicas;
import controladores.ControladorUsuarios;
import controladores.ControladorReservas;
import controladores.ControladorClinicas;

public class Fabrica {
	private static Fabrica instancia;
	
	private Fabrica() {
		instancia = null;
	}
	
	public static Fabrica getInstancia() {
		if (instancia == null) instancia = new Fabrica();
		return instancia;
	}
	
	public IControladorUsuarios getControladorUsuarios() {
		return ControladorUsuarios.getInstancia();
	}
	
	public IControladorClinicas getControladorClinicas() {
		return ControladorClinicas.getInstancia();
	}
	
	public IControladorReservas getControladorReservas() {
		return ControladorReservas.getInstancia();
	}
}
