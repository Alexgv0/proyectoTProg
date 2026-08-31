package formularios;

import java.awt.Dimension;

import javax.swing.*;

import interfaces.IControladorUsuarios;

public class AltaEspecialidad extends JInternalFrame {

	private static final long serialVersionUID = -746306758404459214L;
	
	private IControladorUsuarios cu;

	private JTextField txtNombre;
    private JTextField txtPorcentaje;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    public AltaEspecialidad(IControladorUsuarios cu) {
    	// Inicializamos el Controlador
    	this.cu = cu;
    	
    	// Propiedades de la ventana
        setTitle("Alta de Especialidad");
        setSize(400, 150);
        setMinimumSize(new Dimension(400, 150));
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 11, 88, 24);
        panel.add(lblNombre);

        this.txtNombre = new JTextField();
        this.txtNombre.setBounds(111, 12, 263, 23);
        panel.add(txtNombre);

        JLabel lblPorcentaje = new JLabel("% costo adicional:");
        lblPorcentaje.setBounds(10, 46, 88, 23);
        panel.add(lblPorcentaje);

        this.txtPorcentaje = new JTextField();
        this.txtPorcentaje.setBounds(111, 46, 263, 23);
        panel.add(txtPorcentaje);

        this.btnAceptar = new JButton("Aceptar");
        this.btnAceptar.setBounds(188, 80, 88, 24);
        this.btnAceptar.addActionListener(_ -> aceptar());

        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setBounds(286, 80, 88, 24);
        panel.add(btnCancelar);
        this.btnCancelar.addActionListener(_ -> dispose());

        getContentPane().add(panel);
    }

    private void aceptar() {

        String nombre = txtNombre.getText().trim();
        int porcentaje;

        try {
            porcentaje = Integer.parseInt(txtPorcentaje.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "El porcentaje debe ser un número entero.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Llama al controlador con nombre y porcentaje
    	try {
    		boolean creado = this.cu.ingresarDatosAltaEspecialidad(nombre, porcentaje);
    		
    	    if (creado) {

    	        JOptionPane.showMessageDialog(
    	            this,
    	            "Especialidad creada correctamente.",
    	            "Éxito",
    	            JOptionPane.INFORMATION_MESSAGE
    	        );

    	        dispose();

    	    } else {

    	        JOptionPane.showMessageDialog(
    	            this,
    	            "Ya existe una especialidad con ese nombre.",
    	            "Error",
    	            JOptionPane.ERROR_MESSAGE
    	        );
    	    }
    		
        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}