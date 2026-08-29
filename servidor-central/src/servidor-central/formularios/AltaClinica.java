package formularios;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class AltaClinica extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtNombre, txtDescripcion, txtDireccion, txtSitioWeb, txtCostoMedicamento, txtCostoConsulta;

	public AltaClinica() {
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

		JPanel panelBotones = new JPanel(new FlowLayout());
		panelBotones.add(btnDarAlta);
		panelBotones.add(btnCancelar);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelCampos, BorderLayout.CENTER);
		panel.add(panelBotones, BorderLayout.SOUTH);

		add(panel);
	}
}