package formularios;

import java.awt.BorderLayout;

import javax.swing.JComboBox;

import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsulta;
import interfaces.IControladorClinicas;
import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;

public class ReservaConsulta extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public ReservaConsulta(IControladorClinicas clinicas, IControladorUsuarios usuarios,
            IControladorReservas reservas) {
        super("Reserva de Consulta", 500, 300);
        javax.swing.JPanel formulario = crearFormulario();
        JComboBox<String> dia = new JComboBox<>();
        JComboBox<String> consulta = new JComboBox<>();
        JComboBox<String> paciente = new JComboBox<>();
        recargar(dia, clinicas.listarDiasConsulta().stream()
                .map(DTDiaConsulta::getNombreDia).toList());
        recargar(paciente, usuarios.listarPacientes());
        dia.addActionListener(e -> ejecutarSeguro(() -> {
            consulta.removeAllItems();
            if (dia.getSelectedItem() != null) {
                clinicas.listarConsultasDia(dia.getSelectedItem().toString()).stream()
                        .map(DTConsultaSoloConsulta::getNombreConsulta).sorted()
                        .forEach(consulta::addItem);
            }
        }));
        agregarCampo(formulario, 0, "Dia de consulta:", dia);
        agregarCampo(formulario, 1, "Consulta:", consulta);
        agregarCampo(formulario, 2, "Paciente:", paciente);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            reservas.altaReservaConsulta(seleccionado(paciente, "paciente"),
                    seleccionado(consulta, "consulta"));
            informar("Reserva registrada correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
        if (dia.getItemCount() > 0) {
            dia.setSelectedIndex(-1);
            dia.setSelectedIndex(0);
        }
    }
}
