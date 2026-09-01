package formularios;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import controladores.ControladorClinicas;

public class AltaClinica extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private ControladorClinicas controlador;

	private JTextField txtNombre, txtDescripcion, txtDireccion, txtSitioWeb, txtCostoMedicamento, txtCostoConsulta;

	public AltaClinica() {
		controlador = ControladorClinicas.getInstancia();

		setTitle("Alta de Clínica");
		setSize(400, 400);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);

		JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));

		panelCampos.add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		panelCampos.add(txtNombre);

		panelCampos.add(new JLabel("Descripción:"));
		txtDescripcion = new JTextField();
		panelCampos.add(txtDescripcion);

		panelCampos.add(new JLabel("Dirección:"));
		txtDireccion = new JTextField();
		panelCampos.add(txtDireccion);

		panelCampos.add(new JLabel("Costo ticket medicamento:"));
		txtCostoMedicamento = new JTextField();
		panelCampos.add(txtCostoMedicamento);

		panelCampos.add(new JLabel("Costo orden consulta médica:"));
		txtCostoConsulta = new JTextField();
		panelCampos.add(txtCostoConsulta);

		panelCampos.add(new JLabel("Sitio web (opcional):"));
		txtSitioWeb = new JTextField();
		panelCampos.add(txtSitioWeb);

		JButton btnDarAlta = new JButton("Dar de alta");
		JButton btnCancelar = new JButton("Cancelar");

		btnDarAlta.addActionListener(_ -> darAlta());
		btnCancelar.addActionListener(_ -> dispose());

		JPanel panelBotones = new JPanel(new FlowLayout());
		panelBotones.add(btnDarAlta);
		panelBotones.add(btnCancelar);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelCampos, BorderLayout.CENTER);
		panel.add(panelBotones, BorderLayout.SOUTH);

		add(panel);
	}

	private void darAlta() {
		try {
			String nombre = txtNombre.getText();
			String descripcion = txtDescripcion.getText();
			String direccion = txtDireccion.getText();
			String sitioWeb = txtSitioWeb.getText();
			float costoMedicamento = Float.parseFloat(txtCostoMedicamento.getText());
			float costoConsulta = Float.parseFloat(txtCostoConsulta.getText());

			controlador.altaClinica(nombre, descripcion, direccion, sitioWeb, costoMedicamento, costoConsulta);

			JOptionPane.showMessageDialog(this, "Clínica dada de alta correctamente.");
			dispose();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Los costos deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
