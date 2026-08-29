package formularios;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ConsultaConsulta extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private JList<String> listaDiasConsulta;
	private JList<String> listaConsultas;
	private JTextArea areaDetalle;

	public ConsultaConsulta() {
		setTitle("Consulta de Consulta");
		setSize(500, 400);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);

		String[] dias = { "d1", "d2", "d3" };
		listaDiasConsulta = new JList<>(dias);

		String[] consultas = { "c1", "c2" };
		listaConsultas = new JList<>(consultas);

		areaDetalle = new JTextArea("nombre / médico / fecha / sala / números / costo / reservas");
		areaDetalle.setEditable(false);

		JPanel panelListas = new JPanel(new GridLayout(1, 2, 5, 5));
		panelListas.add(new JScrollPane(listaDiasConsulta));
		panelListas.add(new JScrollPane(listaConsultas));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Seleccione día y consulta:"), BorderLayout.NORTH);
		panel.add(panelListas, BorderLayout.CENTER);
		panel.add(new JScrollPane(areaDetalle), BorderLayout.SOUTH);

		add(panel);
	}
}