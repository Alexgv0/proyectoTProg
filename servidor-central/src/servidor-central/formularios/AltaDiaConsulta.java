package formularios;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import clases.DiaConsulta;
import interfaces.IControladorClinicas;
import utilidades.UtilidadesSwing;
import enums.Dia;

public class AltaDiaConsulta extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtHoraInicio;

    private JComboBox<Dia> comboDia;
    private JComboBox<String> comboEspecialidad;
    private JComboBox<String> comboClinica;

    private JButton btnAceptar;
    private JButton btnCancelar;

    private IControladorClinicas controlador;

    public AltaDiaConsulta(IControladorClinicas cc) {
    	controlador = cc;

    	setTitle("Alta de Dia de Consulta");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        inicializarComponentes();
    }
    private void inicializarComponentes() {

        JPanel panelDatos = new JPanel(new GridLayout(6, 2, 5, 5));

        // Nombre
        panelDatos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelDatos.add(txtNombre);

        // Descripción
        panelDatos.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelDatos.add(txtDescripcion);

        // Día
        panelDatos.add(new JLabel("Día de la semana:"));
        comboDia = new JComboBox<>(Dia.values());
        panelDatos.add(comboDia);

        // Hora
        panelDatos.add(new JLabel("Hora de inicio:"));
        txtHoraInicio = new JTextField();
        panelDatos.add(txtHoraInicio);

        // Especialidad
        panelDatos.add(new JLabel("Especialidad:"));
        comboEspecialidad = new JComboBox<>();
        panelDatos.add(comboEspecialidad);

        // Clínica
        panelDatos.add(new JLabel("Clínica:"));
        comboClinica = new JComboBox<>();
        panelDatos.add(comboClinica);

        
        JPanel panelBotones = new JPanel();

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        setLayout(new BorderLayout());

        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarClinicas();
        cargarEspecialidades();

        btnAceptar.addActionListener(_ -> aceptar());

        btnCancelar.addActionListener(_ -> dispose());
    }

    private void cargarClinicas() {

    	Set<String> clinicas = controlador.listarClinicas();

        UtilidadesSwing.cargarComboBox(comboClinica, clinicas, "No existen clinicas.");
    }

    private void cargarEspecialidades() {
    	Set<String> especialidades =
                controlador.listarEspecialidades();

        UtilidadesSwing.cargarComboBox(comboEspecialidad, especialidades, "No existen especialidades.");
    }

    private void aceptar() {

        try {

            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            Dia dia = (Dia) comboDia.getSelectedItem();

            int horaInicio =
                    Integer.parseInt(txtHoraInicio.getText());

            String especialidad =
                    (String) comboEspecialidad.getSelectedItem();

            String clinica =
                    (String) comboClinica.getSelectedItem();

            controlador.altaDiaConsulta(
                    nombre,
                    descripcion,
                    dia,
                    horaInicio,
                    especialidad,
                    clinica
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Día de consulta creado correctamente."
            );

            dispose();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La hora debe ser un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}