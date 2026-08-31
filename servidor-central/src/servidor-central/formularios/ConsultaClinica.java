package formularios;

import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import datatypes.DTClinica;
import interfaces.IControladorClinicas;
import utilidades.UtilidadesSwing;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ConsultaClinica extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private IControladorClinicas cc;
	
	private JComboBox<String> comboBox;
	private JTextPane textPane;
	private JPanel panel;
	private JButton btnCancelar;

    public ConsultaClinica(IControladorClinicas cc) {
        // Inicializacion del Controlador
    	this.cc = cc;
    	
    	// Propiedades de la ventana
    	setTitle("Consulta de Clinica");
        setSize(400, 300);
        setMinimumSize(new Dimension(400, 300));
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Ingrese la Clinica a consultar.");
        getContentPane().add(lblNewLabel, BorderLayout.NORTH);
        
        this.comboBox = new JComboBox<>();
        getContentPane().add(comboBox, BorderLayout.WEST);
        this.comboBox.addActionListener(_ -> seleccionar());
        
        this.textPane = new JTextPane();
        getContentPane().add(textPane, BorderLayout.CENTER);
        this.textPane.setEditable(false);
        
        this.panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        
        this.btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar);
        this.btnCancelar.addActionListener(_ -> dispose());

        cargarClinicas();
    }

    private void cargarClinicas() {
    	try {
    		Set<String> clinicas = this.cc.listarClinicas();
    		
    		UtilidadesSwing.cargarComboBox(comboBox, clinicas, "No existen clínicas");
		} catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error.",
                    e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
		}
    }

	private void seleccionar() {
        String nombreClinica = (String) this.comboBox.getSelectedItem();

        if (nombreClinica == null) return;

        try {
        	DTClinica datosClinica = this.cc.obtenerDatosClinica(nombreClinica);

            mostrarInfo(datosClinica);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "No existen clínicas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

	private void mostrarInfo(DTClinica datosClinica) {

	    String nombre = "";
	    String descripcion = "";
	    String direccion = "";
	    String sitioWeb = "";
	    String costoMedicamento = "";
	    String costoConsulta = "";

	    if (datosClinica != null) {
	        nombre = datosClinica.getNombre();
	        descripcion = datosClinica.getDescripcion();
	        direccion = datosClinica.getDireccion();
	        sitioWeb = datosClinica.getSitioWeb();
	        costoMedicamento = String.valueOf(datosClinica.getCostoMedicamento());
	        costoConsulta = String.valueOf(datosClinica.getCostoConsulta());
	    }

	    textPane.setText(
	        "Informacion de la clinica seleccionada:\n\n" +
	        "Nombre: " + nombre +
	        "\nDescripción: " + descripcion +
	        "\nDirección: " + direccion +
	        "\nSitio web: " + sitioWeb +
	        "\nCosto de medicamentos: " + costoMedicamento +
	        "\nCosto de la consulta: " + costoConsulta
	    );
	}
}