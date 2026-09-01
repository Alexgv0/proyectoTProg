package formularios;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import datatypes.DTDiaConsulta;
import interfaces.IControladorClinicas;
import interfaces.IControladorUsuarios;

public class AltaConsulta extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaConsulta(IControladorClinicas clinicas, IControladorUsuarios usuarios) {
        super("Alta de Consulta", 560, 430);
        javax.swing.JPanel formulario = crearFormulario();
        JComboBox<String> dia = new JComboBox<>();
        JComboBox<String> medico = new JComboBox<>();
        recargar(dia, clinicas.listarDiasConsulta().stream()
                .map(DTDiaConsulta::getNombreDia).toList());
        recargar(medico, usuarios.listarMedicos());
        JTextField nombre = new JTextField();
        JTextField fecha = new JTextField("dd/mm/aaaa");
        JTextField sala = new JTextField();
        JTextField numeros = new JTextField();
        agregarCampo(formulario, 0, "Dia de consulta:", dia);
        agregarCampo(formulario, 1, "Medico:", medico);
        agregarCampo(formulario, 2, "Nombre:", nombre);
        agregarCampo(formulario, 3, "Fecha:", fecha);
        agregarCampo(formulario, 4, "Numero de sala:", sala);
        agregarCampo(formulario, 5, "Cantidad de numeros:", numeros);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            clinicas.altaConsulta(nombre.getText(), leerFecha(fecha.getText(), "fecha"),
                    leerEntero(sala.getText(), "numero de sala"),
                    leerEntero(numeros.getText(), "cantidad de numeros"),
                    seleccionado(dia, "dia de consulta"),
                    seleccionado(medico, "medico"));
            informar("Consulta registrada correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}
