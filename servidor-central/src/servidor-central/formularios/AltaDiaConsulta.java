package formularios;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import enums.Dia;
import interfaces.IControladorClinicas;

public class AltaDiaConsulta extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaDiaConsulta(IControladorClinicas controlador) {
        super("Alta de Dia de Consulta", 560, 430);
        javax.swing.JPanel formulario = crearFormulario();
        JTextField nombre = new JTextField();
        JTextField descripcion = new JTextField();
        JComboBox<Dia> dia = new JComboBox<>(Dia.values());
        JSpinner hora = new JSpinner(new SpinnerNumberModel(8, 0, 23, 1));
        JComboBox<String> especialidad = new JComboBox<>();
        JComboBox<String> clinica = new JComboBox<>();
        recargar(especialidad, controlador.listarEspecialidades());
        recargar(clinica, controlador.listarClinicas());
        agregarCampo(formulario, 0, "Nombre:", nombre);
        agregarCampo(formulario, 1, "Descripcion:", descripcion);
        agregarCampo(formulario, 2, "Dia de la semana:", dia);
        agregarCampo(formulario, 3, "Hora de inicio:", hora);
        agregarCampo(formulario, 4, "Especialidad:", especialidad);
        agregarCampo(formulario, 5, "Clinica:", clinica);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            controlador.altaDiaConsulta(nombre.getText(), descripcion.getText(),
                    (Dia) dia.getSelectedItem(), (Integer) hora.getValue(),
                    seleccionado(especialidad, "especialidad"),
                    seleccionado(clinica, "clinica"));
            informar("Dia de consulta registrado correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}
