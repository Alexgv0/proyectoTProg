package formularios;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

public class ConsultaClinica extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ConsultaClinica(IControladorClinicas controlador) {
        super("Consulta de Clinica", 560, 390);
        JComboBox<String> clinicas = new JComboBox<>();
        recargar(clinicas, controlador.listarClinicas());
        JTextArea detalle = new JTextArea();
        detalle.setEditable(false);
        detalle.setLineWrap(true);
        detalle.setWrapStyleWord(true);
        clinicas.addActionListener(e -> ejecutarSeguro(() -> {
            if (clinicas.getSelectedItem() != null) {
                detalle.setText(Formato.clinica(controlador.obtenerDatosClinica(
                        clinicas.getSelectedItem().toString())));
            }
        }));
        javax.swing.JPanel superior = new javax.swing.JPanel(new BorderLayout(8, 8));
        superior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        superior.add(new javax.swing.JLabel("Clinica:"), BorderLayout.WEST);
        superior.add(clinicas, BorderLayout.CENTER);
        getContentPane().add(superior, BorderLayout.NORTH);
        ocuparCentro(new JScrollPane(detalle));
        getContentPane().add(crearBotones(this::dispose), BorderLayout.SOUTH);
        if (clinicas.getItemCount() > 0) {
            clinicas.setSelectedIndex(-1);
            clinicas.setSelectedIndex(0);
        }
    }
}