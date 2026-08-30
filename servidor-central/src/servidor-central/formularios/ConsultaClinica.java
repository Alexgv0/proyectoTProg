package formularios;

import java.util.Set;

import javax.swing.JInternalFrame;

import datatypes.DTClinica;
import interfaces.IControladorClinicas;

public class ConsultaClinica extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private IControladorClinicas cc;

    public ConsultaClinica(IControladorClinicas cc) {
        // Inicializacion del Controlador
    	this.cc = cc;
    	
    	// Propiedades de la ventana
    	setTitle("Alta de Usuario");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
    
        ejecutar();
    }

	private void ejecutar() {
		String nombreClinica = "";
		
		Set<String> clinicas = cc.listarClinicas();
		
		DTClinica datosClinica = cc.obtenerDatosClinica(nombreClinica);
		
	}
    
    
}