package formularios;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Set;

import controladores.ControladorClinicas;
import datatypes.DTConsultaCompleta;
import datatypes.DTReservaConsulta;

public class ConsultaConsulta extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private ControladorClinicas controlador;

	private JList<String> listaDiasConsulta;
	private JList<String> listaConsultas;
	private JTextArea areaDetalle;

	public ConsultaConsulta() {
		controlador = ControladorClinicas.getInstancia();

		setTitle("Consulta de Consulta");
		setSize(500, 400);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);

		Set<String> dias = controlador.listarDiasConsulta();
		listaDiasConsulta = new JList<>(dias.toArray(new String[0]));

		listaConsultas = new JList<>(new String[0]);

		areaDetalle = new JTextArea();
		areaDetalle.setEditable(false);

		listaDiasConsulta.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && listaDiasConsulta.getSelectedValue() != null) {
				cargarConsultas(listaDiasConsulta.getSelectedValue());
			}
		});

		listaConsultas.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && listaConsultas.getSelectedValue() != null) {
				mostrarDetalle(listaConsultas.getSelectedValue());
			}
		});

		JPanel panelListas = new JPanel(new GridLayout(1, 2, 5, 5));
		panelListas.add(new JScrollPane(listaDiasConsulta));
		panelListas.add(new JScrollPane(listaConsultas));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Seleccione día y consulta:"), BorderLayout.NORTH);
		panel.add(panelListas, BorderLayout.CENTER);
		panel.add(new JScrollPane(areaDetalle), BorderLayout.SOUTH);

		add(panel);
	}

	private void cargarConsultas(String nombreDiaConsulta) {
		Set<DTConsultaCompleta> consultas = controlador.seleccionarDiaConsulta(nombreDiaConsulta);
		String[] nombres = new String[consultas.size()];
		int i = 0;
		for (DTConsultaCompleta c : consultas) {
			nombres[i++] = c.getNombreConsulta();
		}
		listaConsultas.setListData(nombres);
		areaDetalle.setText("");
	}

	private void mostrarDetalle(String nombreConsulta) {
		DTConsultaCompleta c = controlador.seleccionarConsulta(nombreConsulta);

		StringBuilder sb = new StringBuilder();
		sb.append("Nombre: ").append(c.getNombreConsulta()).append("\n");
		sb.append("Médico: ").append(c.getNombreMedico()).append("\n");
		sb.append("Fecha: ").append(c.getFecha()).append("\n");
		sb.append("Sala: ").append(c.getNroSala()).append("\n");
		sb.append("Números disponibles: ").append(c.getCantidadNumeros()).append("\n");
		sb.append("Costo: ").append(c.getCosto()).append("\n");
		sb.append("Reservas:\n");
		for (DTReservaConsulta r : c.getReservas()) {
			sb.append("  - ").append(r.getNickPaciente())
			  .append(" | num ").append(r.getNumeroCliente())
			  .append(" | costo ").append(r.getCostoReserva()).append("\n");
		}
		areaDetalle.setText(sb.toString());
	}
}
