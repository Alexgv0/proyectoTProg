package formularios;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import datatypes.DTBeneficioAnual;
import interfaces.IControladorUsuarios;

public class ConsultaBeneficio extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ConsultaBeneficio(IControladorUsuarios controlador) {
        super("Consulta de Beneficio Anual", 650, 420);
        JComboBox<String> pacientes = new JComboBox<>();
        recargar(pacientes, controlador.listarPacientes());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> beneficios = new JList<>(modelo);
        JTextArea detalle = new JTextArea();
        detalle.setEditable(false);
        pacientes.addActionListener(e -> ejecutarSeguro(() -> {
            modelo.clear();
            if (pacientes.getSelectedItem() != null) {
                controlador.listarBeneficiosAnuales(pacientes.getSelectedItem().toString()).stream().map(DTBeneficioAnual::getAnio).sorted().map(String::valueOf).forEach(modelo::addElement);
            }
            detalle.setText("");
        }));
        beneficios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && beneficios.getSelectedValue() != null) {
                ejecutarSeguro(() -> detalle.setText(Formato.beneficio(controlador.seleccionarBeneficioAnual(seleccionado(pacientes, "paciente"), Integer.parseInt(beneficios.getSelectedValue())))));
            }
        });
        javax.swing.JPanel superior = new javax.swing.JPanel(new BorderLayout(8, 8));
        superior.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 4, 8));
        superior.add(new javax.swing.JLabel("Paciente:"), BorderLayout.WEST);
        superior.add(pacientes, BorderLayout.CENTER);
        getContentPane().add(superior, BorderLayout.NORTH);
        JSplitPane centro = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(beneficios), new JScrollPane(detalle));
        centro.setResizeWeight(0.3);
        ocuparCentro(centro);
        getContentPane().add(crearBotones(this::dispose), BorderLayout.SOUTH);
        if (pacientes.getItemCount() > 0) {
            pacientes.setSelectedIndex(-1);
            pacientes.setSelectedIndex(0);
        }
    }
}
