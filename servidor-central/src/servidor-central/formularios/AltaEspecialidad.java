package formularios;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import interfaces.IControladorUsuarios;

public class AltaEspecialidad extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaEspecialidad(IControladorUsuarios controlador) {
        super("Alta de Especialidad", 430, 230);
        javax.swing.JPanel formulario = crearFormulario();
        JTextField nombre = new JTextField();
        JTextField porcentaje = new JTextField("0");
        agregarCampo(formulario, 0, "Nombre:", nombre);
        agregarCampo(formulario, 1, "Porcentaje adicional:", porcentaje);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            boolean creada = controlador.ingresarDatosAltaEspecialidad(nombre.getText(),
                    leerEntero(porcentaje.getText(), "porcentaje adicional"));
            if (!creada) throw new IllegalArgumentException(
                    "Ya existe una especialidad con ese nombre.");
            informar("Especialidad registrada correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}