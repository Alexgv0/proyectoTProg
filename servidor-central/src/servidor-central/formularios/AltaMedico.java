package formularios;

import java.awt.BorderLayout;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import clases.Usuario;
import datatypes.DTEspecialidad;
import interfaces.IControladorUsuarios;

public class AltaMedico extends FormularioBase {
    private static final long serialVersionUID = 1L;
    private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre",
            "Noviembre", "Diciembre" };

    public AltaMedico(IControladorUsuarios controlador) {
        super("Alta de Medico", 600, 560);
        javax.swing.JPanel formulario = crearFormulario();
        JTextField nickname = new JTextField();
        JTextField nombre = new JTextField();
        JTextField apellido = new JTextField();
        JTextField correo = new JTextField();
        JTextField registro = new JTextField();
        JComboBox<String> licencia = new JComboBox<>(MESES);
        JTextField sitioWeb = new JTextField();
        JList<String> especialidades = new JList<>(controlador.listarEspecialidades()
                .stream().sorted().toArray(String[]::new));
        especialidades.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        especialidades.setVisibleRowCount(5);
        JTextField porcentaje = new JTextField("0");
        agregarCampo(formulario, 0, "Nickname:", nickname);
        agregarCampo(formulario, 1, "Nombre:", nombre);
        agregarCampo(formulario, 2, "Apellido:", apellido);
        agregarCampo(formulario, 3, "Correo:", correo);
        agregarCampo(formulario, 4, "Numero de registro:", registro);
        agregarCampo(formulario, 5, "Mes de licencia:", licencia);
        agregarCampo(formulario, 6, "Sitio web (opcional):", sitioWeb);
        agregarCampo(formulario, 7, "Especialidades:", new JScrollPane(especialidades));
        agregarCampo(formulario, 8, "Porcentaje adicional:", porcentaje);
        ocuparCentro(formulario);
        getContentPane().add(crearBotones(() -> {
            Set<DTEspecialidad> seleccionadas = new LinkedHashSet<>();
            
            for (String valor : especialidades.getSelectedValuesList()) {
                seleccionadas.add(controlador.obtenerDatosEspecialidad(valor));
            }
            
            Usuario usuario = controlador.altaMedico(
            		nickname.getText(), 
            		nombre.getText(), 
            		apellido.getText(), 
            		correo.getText(), 
            		leerEntero(registro.getText(), "numero de registro"), 
            		licencia.getSelectedItem().toString(), 
            		sitioWeb.getText(), 
            		seleccionadas, 
            		leerEntero(porcentaje.getText(), "porcentaje adicional"));
            
            controlador.altaUsuario(usuario);
            
            informar("Medico registrado correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
    }
}