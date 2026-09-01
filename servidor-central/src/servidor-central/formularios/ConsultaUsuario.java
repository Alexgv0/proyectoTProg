package formularios;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import datatypes.DTConsultaSoloConsulta;
import datatypes.DTMedico;
import datatypes.DTPaciente;
import datatypes.DTReservaConsulta;
import datatypes.DTUsuario;
import interfaces.IControladorClinicas;
import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;

public class ConsultaUsuario extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ConsultaUsuario(IControladorUsuarios usuarios, IControladorClinicas clinicas,
            IControladorReservas reservas) {
        super("Consulta de Usuario", 800, 500);
        JComboBox<String> selector = new JComboBox<>();
        recargar(selector, usuarios.listarUsuarios().stream()
                .map(DTUsuario::getNickname).toList());
        JTextArea detalleUsuario = new JTextArea();
        detalleUsuario.setEditable(false);
        DefaultListModel<String> modeloAsociados = new DefaultListModel<>();
        JList<String> asociados = new JList<>(modeloAsociados);
        JTextArea detalleAsociado = new JTextArea();
        detalleAsociado.setEditable(false);
        final DTUsuario[] usuarioActual = new DTUsuario[1];

        selector.addActionListener(_ -> ejecutarSeguro(() -> {
            if (selector.getSelectedItem() == null) return;
            DTUsuario usuario = usuarios.seleccionarUsuario(
                    selector.getSelectedItem().toString());
            usuarioActual[0] = usuario;
            detalleUsuario.setText(Formato.usuario(usuario));
            modeloAsociados.clear();
            if (usuario instanceof DTMedico) {
                clinicas.listarConsultas(usuario).stream()
                        .map(DTConsultaSoloConsulta::getNombreConsulta).sorted()
                        .forEach(modeloAsociados::addElement);
            } else if (usuario instanceof DTPaciente) {
                reservas.listarReservasConsultas(usuario.getNickname()).stream()
                        .map(DTReservaConsulta::getNombreConsulta).sorted()
                        .forEach(modeloAsociados::addElement);
            }
            detalleAsociado.setText("");
        }));
        asociados.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() || asociados.getSelectedValue() == null
                    || usuarioActual[0] == null) return;
            ejecutarSeguro(() -> {
                if (usuarioActual[0] instanceof DTMedico) {
                    detalleAsociado.setText(Formato.consulta(
                            clinicas.seleccionarConsulta(asociados.getSelectedValue())));
                } else {
                    detalleAsociado.setText(Formato.reserva(
                            reservas.seleccionarReservaConsulta(
                                    usuarioActual[0].getNickname(),
                                    asociados.getSelectedValue())));
                }
            });
        });

        javax.swing.JPanel superior = new javax.swing.JPanel(new BorderLayout(8, 8));
        superior.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 4, 8));
        superior.add(new javax.swing.JLabel("Usuario:"), BorderLayout.WEST);
        superior.add(selector, BorderLayout.CENTER);
        getContentPane().add(superior, BorderLayout.NORTH);
        JScrollPane scrollAsociados = new JScrollPane(asociados);
        scrollAsociados.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Consultas / reservas"));
        JScrollPane scrollDetalleAsociado = new JScrollPane(detalleAsociado);
        scrollDetalleAsociado.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Detalle seleccionado"));
        JScrollPane scrollUsuario = new JScrollPane(detalleUsuario);
        scrollUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Datos del usuario"));
        JSplitPane asociadosYDetalle = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                scrollAsociados, scrollDetalleAsociado);
        asociadosYDetalle.setResizeWeight(0.45);
        JSplitPane centro = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollUsuario, asociadosYDetalle);
        centro.setResizeWeight(0.5);
        ocuparCentro(centro);
        getContentPane().add(crearBotones(this::dispose), BorderLayout.SOUTH);
        if (selector.getItemCount() > 0) {
            selector.setSelectedIndex(-1);
            selector.setSelectedIndex(0);
        }
    }
}
