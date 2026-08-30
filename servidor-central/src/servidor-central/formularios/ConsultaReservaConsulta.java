package formularios;

import java.util.Set;

import javax.swing.JInternalFrame;

import datatypes.DTReserva;
import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;

public class ConsultaReservaConsulta extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private IControladorReservas cr;
	private IControladorUsuarios cu;

    public ConsultaReservaConsulta(IControladorUsuarios cu, IControladorReservas cr) {
    	// Inicializacion de los Controladores
    	this.cu = cu;
    	this.cr = cr;
    	
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
		String nickname = "";
		String nombreConsulta = "";
		
		Set<String> pacientes = cu.listarPacientes();
		
		Set<DTReserva> reservas = cr.listarReservasConsultas(nickname);
		
		DTReserva datosReserva = cr.seleccionarReservaConsulta(nickname, nombreConsulta);
	}
}