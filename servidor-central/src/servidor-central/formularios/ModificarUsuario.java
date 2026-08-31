package formularios;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import datatypes.DTEspecialidad;
import datatypes.DTMedico;
import datatypes.DTPaciente;
import datatypes.DTUsuario;
import interfaces.IControladorUsuarios;

public class ModificarUsuario extends FormularioBase {
    private static final long serialVersionUID = 1L;
    private static final String PACIENTE = "Paciente";
    private static final String MEDICO = "Medico";
    private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre",
            "Noviembre", "Diciembre" };

    public ModificarUsuario(IControladorUsuarios controlador) {
        super("Modificar Datos de Usuario", 700, 650);
        JPanel formulario = crearFormulario();
        JComboBox<String> usuarios = new JComboBox<>();
        recargar(usuarios, controlador.listarUsuarios().stream()
                .map(DTUsuario::getNickname).toList());
        JTextField nickname = new JTextField();
        nickname.setEditable(false);
        JTextField correo = new JTextField();
        correo.setEditable(false);
        JTextField nombre = new JTextField();
        JTextField apellido = new JTextField();

        JPanel panelPaciente = crearFormulario();
        JTextField nacimiento = new JTextField();
        JTextField documento = new JTextField();
        JTextField ingreso = new JTextField();
        JTextArea antecedentes = new JTextArea(4, 25);
        agregarCampo(panelPaciente, 0, "Fecha de nacimiento:", nacimiento);
        agregarCampo(panelPaciente, 1, "Documento:", documento);
        agregarCampo(panelPaciente, 2, "Fecha de ingreso:", ingreso);
        agregarCampo(panelPaciente, 3, "Antecedentes:", new JScrollPane(antecedentes));

        JPanel panelMedico = crearFormulario();
        JTextField registro = new JTextField();
        JComboBox<String> licencia = new JComboBox<>(MESES);
        JTextField sitioWeb = new JTextField();
        JList<String> especialidades = new JList<>(controlador.listarEspecialidades()
                .stream().sorted().toArray(String[]::new));
        especialidades.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        especialidades.setVisibleRowCount(4);
        JTextField porcentaje = new JTextField();
        agregarCampo(panelMedico, 0, "Numero de registro:", registro);
        agregarCampo(panelMedico, 1, "Mes de licencia:", licencia);
        agregarCampo(panelMedico, 2, "Sitio web:", sitioWeb);
        agregarCampo(panelMedico, 3, "Especialidades:", new JScrollPane(especialidades));
        agregarCampo(panelMedico, 4, "Porcentaje adicional:", porcentaje);

        CardLayout tarjetas = new CardLayout();
        JPanel panelEspecifico = new JPanel(tarjetas);
        panelEspecifico.add(panelPaciente, PACIENTE);
        panelEspecifico.add(panelMedico, MEDICO);
        agregarCampo(formulario, 0, "Seleccionar usuario:", usuarios);
        agregarCampo(formulario, 1, "Nickname:", nickname);
        agregarCampo(formulario, 2, "Correo:", correo);
        agregarCampo(formulario, 3, "Nombre:", nombre);
        agregarCampo(formulario, 4, "Apellido:", apellido);
        agregarCampo(formulario, 5, "Datos especificos:", panelEspecifico);
        final DTUsuario[] actual = new DTUsuario[1];

        usuarios.addActionListener(e -> ejecutarSeguro(() -> {
            if (usuarios.getSelectedItem() == null) return;
            DTUsuario dato = controlador.seleccionarUsuario(
                    usuarios.getSelectedItem().toString());
            actual[0] = dato;
            nickname.setText(dato.getNickname());
            correo.setText(dato.getCorreo());
            nombre.setText(dato.getNombre());
            apellido.setText(dato.getApellido());
            if (dato instanceof DTPaciente) {
                DTPaciente paciente = (DTPaciente) dato;
                tarjetas.show(panelEspecifico, PACIENTE);
                nacimiento.setText(paciente.getFechaNacimiento().toString());
                documento.setText(String.valueOf(paciente.getDocumento()));
                ingreso.setText(paciente.getFechaIngreso().toString());
                antecedentes.setText(String.join("\n", paciente.getAntecedentesMedicos()));
            } else {
                DTMedico medico = (DTMedico) dato;
                tarjetas.show(panelEspecifico, MEDICO);
                registro.setText(String.valueOf(medico.getRegistro()));
                licencia.setSelectedItem(medico.getMesLicencia());
                sitioWeb.setText(medico.getSitioWeb());
                porcentaje.setText(String.valueOf(medico.getPorcentajeConsultas()));
                Set<String> nombresEspecialidades = new LinkedHashSet<>();
                for (DTEspecialidad especialidad : medico.getEspecialidades()) {
                    nombresEspecialidades.add(especialidad.getNombre());
                }
                especialidades.clearSelection();
                for (int i = 0; i < especialidades.getModel().getSize(); i++) {
                    if (nombresEspecialidades.contains(especialidades.getModel().getElementAt(i))) {
                        especialidades.addSelectionInterval(i, i);
                    }
                }
            }
        }));

        ocuparCentro(new JScrollPane(formulario));
        getContentPane().add(crearBotones(() -> {
            if (actual[0] == null) throw new IllegalArgumentException(
                    "Debe seleccionar un usuario.");
            if (actual[0] instanceof DTPaciente) {
                controlador.modificarPaciente(nickname.getText(), nombre.getText(),
                        apellido.getText(),
                        leerFecha(nacimiento.getText(), "fecha de nacimiento"),
                        leerEntero(documento.getText(), "documento"),
                        leerFecha(ingreso.getText(), "fecha de ingreso"),
                        leerLista(antecedentes.getText()));
            } else {
                controlador.modificarMedico(nickname.getText(), nombre.getText(),
                        apellido.getText(), leerEntero(registro.getText(), "registro"),
                        licencia.getSelectedItem().toString(), sitioWeb.getText(),
                        new LinkedHashSet<>(especialidades.getSelectedValuesList()),
                        leerEntero(porcentaje.getText(), "porcentaje adicional"));
            }
            informar("Datos actualizados correctamente.");
            dispose();
        }), BorderLayout.SOUTH);
        if (usuarios.getItemCount() > 0) {
            usuarios.setSelectedIndex(-1);
            usuarios.setSelectedIndex(0);
        }
    }
}
