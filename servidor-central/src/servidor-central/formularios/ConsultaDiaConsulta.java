package formularios;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsulta;
import datatypes.DTDiaConsultaCompleto;
import interfaces.IControladorClinicas;

public class ConsultaDiaConsulta extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ConsultaDiaConsulta(IControladorClinicas controlador) {
        super("Consulta de Dia de Consulta", 760, 480);
        JComboBox<String> dias = new JComboBox<>();
        recargar(dias, controlador.listarDiasConsulta().stream()
                .map(DTDiaConsulta::getNombreDia).toList());
        JTextArea detalle = new JTextArea();
        detalle.setEditable(false);
        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> consultas = new JList<>(modelo);
        JTextArea detalleConsulta = new JTextArea();
        detalleConsulta.setEditable(false);

        dias.addActionListener(e -> ejecutarSeguro(() -> {
            if (dias.getSelectedItem() == null) return;
            DTDiaConsultaCompleto dato = controlador.obtenerDatosDiaConsulta(
                    dias.getSelectedItem().toString());
            detalle.setText(Formato.dia(dato));
            modelo.clear();
            dato.getConsultas().stream().map(DTConsultaSoloConsulta::getNombreConsulta)
                    .sorted().forEach(modelo::addElement);
            detalleConsulta.setText("");
        }));
        consultas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && consultas.getSelectedValue() != null) {
                ejecutarSeguro(() -> detalleConsulta.setText(Formato.consulta(
                        controlador.seleccionarConsulta(consultas.getSelectedValue()))));
            }
        });

        javax.swing.JPanel superior = new javax.swing.JPanel(new BorderLayout(8, 8));
        superior.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 4, 8));
        superior.add(new javax.swing.JLabel("Dia de consulta:"), BorderLayout.WEST);
        superior.add(dias, BorderLayout.CENTER);
        getContentPane().add(superior, BorderLayout.NORTH);
        JScrollPane scrollDia = new JScrollPane(detalle);
        scrollDia.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del dia"));
        JScrollPane scrollDetalle = new JScrollPane(detalleConsulta);
        scrollDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(
                "Detalle de consulta"));
        JScrollPane scrollConsultas = new JScrollPane(consultas);
        scrollConsultas.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultas"));
        JSplitPane izquierda = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                scrollDia, scrollDetalle);
        izquierda.setResizeWeight(0.5);
        JSplitPane centro = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, izquierda,
                scrollConsultas);
        centro.setResizeWeight(0.7);
        ocuparCentro(centro);
        getContentPane().add(crearBotones(this::dispose), BorderLayout.SOUTH);
        if (dias.getItemCount() > 0) {
            dias.setSelectedIndex(-1);
            dias.setSelectedIndex(0);
        }
    }
}