package formularios;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import interfaces.IControladorClinicas;

public class AltaClinica extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaClinica(IControladorClinicas controlador) {
        super("Alta de Clinica", 540, 420);
        javax.swing.JPanel formulario = crearFormulario();
        JTextField nombre = new JTextField();
        JTextField descripcion = new JTextField();
        JTextField direccion = new JTextField();
        JTextField sitioWeb = new JTextField();
        JTextField costoTicket = new JTextField("0");
        JTextField costoOrden = new JTextField("0");
        agregarCampo(formulario, 0, "Nombre:", nombre);
        agregarCampo(formulario, 1, "Descripcion:", descripcion);
        agregarCampo(formulario, 2, "Direccion:", direccion);
        agregarCampo(formulario, 3, "Sitio web (opcional):", sitioWeb);
        agregarCampo(formulario, 4, "Costo ticket medicamento:", costoTicket);
        agregarCampo(formulario, 5, "Costo orden consulta:", costoOrden);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            controlador.altaClinica(nombre.getText(), descripcion.getText(),
                    direccion.getText(), sitioWeb.getText(),
                    leerReal(costoTicket.getText(), "costo del ticket"),
                    leerReal(costoOrden.getText(), "costo de la orden"));
            informar("Clinica registrada correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}
