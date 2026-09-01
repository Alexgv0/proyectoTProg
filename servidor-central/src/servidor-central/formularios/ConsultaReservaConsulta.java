package formularios;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import datatypes.DTReservaConsulta;
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

public class ConsultaReservaConsulta extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ConsultaReservaConsulta(IControladorUsuarios usuarios,
            IControladorReservas reservas) {
        super("Consulta de Reserva de Consulta", 700, 440);
        JComboBox<String> pacientes = new JComboBox<>();
        recargar(pacientes, usuarios.listarPacientes());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> lista = new JList<>(modelo);
        JTextArea detalle = new JTextArea();
        detalle.setEditable(false);
        pacientes.addActionListener(e -> ejecutarSeguro(() -> {
            modelo.clear();
            if (pacientes.getSelectedItem() != null) {
                reservas.listarReservasConsultas(pacientes.getSelectedItem().toString())
                        .stream().map(DTReservaConsulta::getNombreConsulta).sorted()
                        .forEach(modelo::addElement);
            }
            detalle.setText("");
        }));
        lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && lista.getSelectedValue() != null) {
                ejecutarSeguro(() -> detalle.setText(Formato.reserva(
                        reservas.seleccionarReservaConsulta(
                                seleccionado(pacientes, "paciente"),
                                lista.getSelectedValue()))));
            }
        });
        javax.swing.JPanel superior = new javax.swing.JPanel(new BorderLayout(8, 8));
        superior.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 4, 8));
        superior.add(new javax.swing.JLabel("Paciente:"), BorderLayout.WEST);
        superior.add(pacientes, BorderLayout.CENTER);
        getContentPane().add(superior, BorderLayout.NORTH);
        JSplitPane centro = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(lista), new JScrollPane(detalle));
        centro.setResizeWeight(0.35);
        ocuparCentro(centro);
        getContentPane().add(crearBotones(this::dispose), BorderLayout.SOUTH);
        if (pacientes.getItemCount() > 0) {
            pacientes.setSelectedIndex(-1);
            pacientes.setSelectedIndex(0);
        }
    }
}