package formularios;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import datatypes.DTConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsulta;
import datatypes.DTReserva;
import interfaces.IControladorClinicas;

public class ConsultaConsulta extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ConsultaConsulta(IControladorClinicas controlador) {
        super("Consulta de Consulta", 850, 540);
        JComboBox<String> dias = new JComboBox<>();
        recargar(dias, controlador.listarDiasConsulta().stream()
                .map(DTDiaConsulta::getNombreDia).toList());
        DefaultListModel<String> modeloConsultas = new DefaultListModel<>();
        JList<String> consultas = new JList<>(modeloConsultas);
        JTextArea detalleConsulta = new JTextArea();
        detalleConsulta.setEditable(false);
        DefaultListModel<String> modeloReservas = new DefaultListModel<>();
        JList<String> reservas = new JList<>(modeloReservas);
        JTextArea detalleReserva = new JTextArea();
        detalleReserva.setEditable(false);
        Map<String, DTReserva> reservasActuales = new LinkedHashMap<>();

        dias.addActionListener(_ -> ejecutarSeguro(() -> {
            modeloConsultas.clear();
            if (dias.getSelectedItem() != null) {
                controlador.listarConsultasDia(dias.getSelectedItem().toString()).stream()
                        .map(DTConsultaSoloConsulta::getNombreConsulta).sorted()
                        .forEach(modeloConsultas::addElement);
            }
            detalleConsulta.setText("");
            modeloReservas.clear();
            detalleReserva.setText("");
        }));
        consultas.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() || consultas.getSelectedValue() == null) return;
            ejecutarSeguro(() -> {
                DTConsulta consulta = controlador.seleccionarConsulta(
                        consultas.getSelectedValue());
                detalleConsulta.setText(Formato.consulta(consulta));
                reservasActuales.clear();
                modeloReservas.clear();
                for (DTReserva reserva : consulta.getReservas()) {
                    reservasActuales.put(reserva.getNickPaciente(), reserva);
                    modeloReservas.addElement(reserva.getNickPaciente());
                }
                detalleReserva.setText("");
            });
        });
        reservas.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() || reservas.getSelectedValue() == null) return;
            DTReserva reserva = reservasActuales.get(reservas.getSelectedValue());
            detalleReserva.setText("Paciente: " + reserva.getNickPaciente()
                    + "\nFecha de reserva: " + reserva.getFecha()
                    + "\nNumero otorgado: " + reserva.getNumeroCliente()
                    + "\nCosto: $ " + String.format("%.2f", reserva.getCostoReserva()));
        });

        JPanel superior = new JPanel(new BorderLayout(8, 8));
        superior.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 4, 8));
        superior.add(new javax.swing.JLabel("Dia de consulta:"), BorderLayout.WEST);
        superior.add(dias, BorderLayout.CENTER);
        getContentPane().add(superior, BorderLayout.NORTH);
        JScrollPane scrollConsultas = new JScrollPane(consultas);
        scrollConsultas.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));
        JScrollPane scrollReservas = new JScrollPane(reservas);
        scrollReservas.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservas"));
        JScrollPane scrollDetalleConsulta = new JScrollPane(detalleConsulta);
        scrollDetalleConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Detalle de consulta"));
        JScrollPane scrollDetalleReserva = new JScrollPane(detalleReserva);
        scrollDetalleReserva.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Detalle de reserva"));
        JPanel listas = new JPanel(new GridLayout(1, 2, 6, 6));
        listas.add(scrollConsultas);
        listas.add(scrollReservas);
        JSplitPane detalles = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollDetalleConsulta, scrollDetalleReserva);
        detalles.setResizeWeight(0.5);
        JSplitPane centro = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listas, detalles);
        centro.setResizeWeight(0.45);
        ocuparCentro(centro);
        getContentPane().add(crearBotones(this::dispose), BorderLayout.SOUTH);
        if (dias.getItemCount() > 0) {
            dias.setSelectedIndex(-1);
            dias.setSelectedIndex(0);
        }
    }
}
