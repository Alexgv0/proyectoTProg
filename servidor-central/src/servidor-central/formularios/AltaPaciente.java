package formularios;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clases.Usuario;
import interfaces.IControladorUsuarios;

public class AltaPaciente extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaPaciente(IControladorUsuarios controlador) {
        super("Alta de Paciente", 570, 500);
        javax.swing.JPanel formulario = crearFormulario();
        JTextField nickname = new JTextField();
        JTextField nombre = new JTextField();
        JTextField apellido = new JTextField();
        JTextField correo = new JTextField();
        JTextField nacimiento = new JTextField("dd/mm/aaaa");
        JTextField documento = new JTextField();
        JTextField ingreso = new JTextField("dd/mm/aaaa");
        JTextArea antecedentes = new JTextArea(4, 25);
        antecedentes.setLineWrap(true);
        antecedentes.setWrapStyleWord(true);
        agregarCampo(formulario, 0, "Nickname:", nickname);
        agregarCampo(formulario, 1, "Nombre:", nombre);
        agregarCampo(formulario, 2, "Apellido:", apellido);
        agregarCampo(formulario, 3, "Correo:", correo);
        agregarCampo(formulario, 4, "Fecha de nacimiento:", nacimiento);
        agregarCampo(formulario, 5, "Documento:", documento);
        agregarCampo(formulario, 6, "Fecha de ingreso:", ingreso);
        agregarCampo(formulario, 7, "Antecedentes:", new JScrollPane(antecedentes));
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            Usuario usuario = controlador.altaPaciente(nickname.getText(), nombre.getText(),
                    apellido.getText(), correo.getText(), leerFecha(nacimiento.getText(), "fecha de nacimiento"), leerEntero(documento.getText(), "documento"),
                    leerFecha(ingreso.getText(), "fecha de ingreso"), leerLista(antecedentes.getText()));
            
            controlador.altaUsuario(usuario);
            informar("Paciente registrado correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}
