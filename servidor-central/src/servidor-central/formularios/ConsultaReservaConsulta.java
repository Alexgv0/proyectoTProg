package formularios;

import java.util.HashSet;
import java.util.Set;

import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;
import utilidades.UtilidadesSwing;
import datatypes.DTFecha;
import datatypes.DTReserva;
import datatypes.DTReservaDetallada;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class ConsultaReservaConsulta extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private IControladorReservas cr;
	private IControladorUsuarios cu;
	
	private String nickname;
	
	private JComboBox<String> comboBoxP;
	private JComboBox<String> comboBoxR;
	private JButton btnCancelar;
	private JPanel panel;
	private JTextPane textPane;

    public ConsultaReservaConsulta(IControladorUsuarios cu, IControladorReservas cr) {
    	// Inicializacion de los Controladores
    	this.cu = cu;
    	this.cr = cr;
    	
    	// Inicializacion de datos recordados
    	this.nickname = null;
    	
    	// Propiedades de la ventana
        setTitle("Consulta de Reserva de Consulta");
        setSize(600, 300);
        setMinimumSize(new Dimension(600, 300));
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        this.comboBoxP = new JComboBox<>();
        getContentPane().add(comboBoxP, BorderLayout.WEST);
        this.comboBoxP.addActionListener(_ -> seleccionarPaciente());
        
        this.comboBoxR = new JComboBox<>();
        getContentPane().add(comboBoxR, BorderLayout.EAST);
        this.comboBoxR.addActionListener(_ -> seleccionarReserva());
        
        this.textPane = new JTextPane();
        getContentPane().add(textPane, BorderLayout.CENTER);
        this.textPane.setEditable(false);
        
        this.panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        
        this.btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar);
        this.btnCancelar.addActionListener(_ -> dispose());
        
        cargarPacientes();
        
    }

    
	private void cargarPacientes() {
		Set<String> pacientes = null;
		
		pacientes = this.cu.listarPacientes();	
		
		UtilidadesSwing.cargarComboBox(comboBoxP, pacientes, "No existen pacientes.");
		try {
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
                    this,
                    "No existen pacientes.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
		}
	}

	private void seleccionarPaciente() {
		this.nickname = (String) comboBoxP.getSelectedItem();
		
		if (this.nickname == null) return;
		
		cargarReservas();
		
	}
	
	private void cargarReservas() {
		if (nickname == null) return;

		Set<DTReserva> reservas = this.cr.listarReservasConsultas(this.nickname);

		Set<String> info = new HashSet<>();
		if (reservas != null) {
			for (DTReserva reserva : reservas) {
				String infoReserva = 
						"Fecha: " + reserva.getFecha() + "\n" +
								"N° Cliente: " + reserva.getNumeroCliente() + "\n" +
								"Costo: " + reserva.getCostoReserva() + "\n";
				info.add(infoReserva);
			}	
		}
		
		UtilidadesSwing.cargarComboBox(comboBoxR, info, "No existen reservas.");
		try {	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
                    this,
                    "No existen reservas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
		}
	}
	
	private void seleccionarReserva() {
		if (nickname == null) return;
		
		try {			
			String nombreConsulta = (String) comboBoxR.getSelectedItem();
			
			DTReservaDetallada datosReserva = this.cr.seleccionarReservaConsulta(this.nickname, nombreConsulta);
			
			mostrarInfo(datosReserva);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
                    this,
                    "No existen reservas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
		}
	}
	
	private void mostrarInfo(DTReservaDetallada drd) {

	    DTFecha fecha = null;
	    Integer nroCliente = null;
	    Float costoReserva = null;
	    String nombreConsulta = null;
	    String nicknamePaciente = null;
	    Integer consultasGratisRestantes = null;

	    if (drd != null) {
	        // Info Reserva
	    	fecha = drd.getFechaReserva();
	        nroCliente = drd.getNumeroClienteReserva();
	        costoReserva = drd.getCostoReserva();
	        // Info Consulta
	        nombreConsulta = drd.getNombreConsulta();
	        // Info Paciente
	        nicknamePaciente = drd.getNicknamePaciente();
	        // Info Beneficio Anual
	        consultasGratisRestantes = drd.getConsultasGratisBeneficioAnual();
	    }
	    
	    String texto = "Informacion de la reserva seleccionada:\n\n" +
		        "Fecha: " + UtilidadesSwing.mostrarSiExiste(fecha) +
		        "\nN° Cliente: " + UtilidadesSwing.mostrarSiExiste(nroCliente) +
		        "\nCosto: " + UtilidadesSwing.mostrarSiExiste(costoReserva) +
		        "\nNombre de la consulta: " + UtilidadesSwing.mostrarSiExiste(nombreConsulta) +
		        "\nNickname del paciente: " + UtilidadesSwing.mostrarSiExiste(nicknamePaciente) +
		        "\nConsultas gratis restantes: " + UtilidadesSwing.mostrarSiExiste(consultasGratisRestantes);
	    
	    textPane.setText(texto);
	}
}