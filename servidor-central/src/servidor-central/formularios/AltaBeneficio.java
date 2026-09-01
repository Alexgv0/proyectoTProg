package formularios;

import java.awt.BorderLayout;
import java.time.Year;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import interfaces.IControladorUsuarios;

public class AltaBeneficio extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaBeneficio(IControladorUsuarios controlador) {
        super("Alta de Beneficio Anual", 500, 320);
        javax.swing.JPanel formulario = crearFormulario();
        JComboBox<String> paciente = new JComboBox<>();
        recargar(paciente, controlador.listarPacientes());
        JSpinner anio = new JSpinner(new SpinnerNumberModel(Year.now().getValue(), 1900,
                3000, 1));
        JSpinner ordenes = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
        JSpinner tickets = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
        agregarCampo(formulario, 0, "Paciente:", paciente);
        agregarCampo(formulario, 1, "Anio:", anio);
        agregarCampo(formulario, 2, "Ordenes gratuitas:", ordenes);
        agregarCampo(formulario, 3, "Tickets gratuitos:", tickets);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            controlador.altaBeneficioAnual(seleccionado(paciente, "paciente"),
                    (Integer) anio.getValue(), (Integer) ordenes.getValue(),
                    (Integer) tickets.getValue());
            informar("Beneficio anual registrado correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}
