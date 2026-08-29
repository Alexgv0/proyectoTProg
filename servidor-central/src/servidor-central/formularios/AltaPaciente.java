package formularios;

import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;

import controladores.ControladorUsuarios;
import datatypes.DTFecha;

public class AltaPaciente extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNickname;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtFechaNacimiento;
    private JTextField txtDocumento;
    private JTextField txtFechaIngreso;
    private JTextField txtAntecedenteMedico;
    
    private DefaultListModel<String> modeloAntecedentesMedicos;
    private JList<String> listaAntecedentesMedicos;
    
    private ControladorUsuarios controlador;

    public AltaPaciente() {

        setTitle("Alta de Paciente");
        setSize(550, 500);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        controlador = ControladorUsuarios.getInstancia();
        
        getContentPane().setLayout(null);

        // Nickname
        JLabel lblNickname = new JLabel("Nickname:");
        lblNickname.setBounds(30, 30, 150, 25);
        getContentPane().add(lblNickname);

        txtNickname = new JTextField();
        txtNickname.setBounds(190, 30, 300, 25);
        getContentPane().add(txtNickname);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 65, 150, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(190, 65, 300, 25);
        getContentPane().add(txtNombre);

        // Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 100, 150, 25);
        getContentPane().add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(190, 100, 300, 25);
        getContentPane().add(txtApellido);

        // Correo
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(30, 135, 150, 25);
        getContentPane().add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(190, 135, 300, 25);
        getContentPane().add(txtCorreo);

        // Fecha de nacimiento
        JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
        lblFechaNacimiento.setBounds(30, 170, 150, 25);
        getContentPane().add(lblFechaNacimiento);

        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setBounds(190, 170, 300, 25);
        getContentPane().add(txtFechaNacimiento);

        // Documento
        JLabel lblDocumento = new JLabel("Número de documento:");
        lblDocumento.setBounds(30, 205, 150, 25);
        getContentPane().add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(190, 205, 300, 25);
        getContentPane().add(txtDocumento);

        // Fecha de ingreso
        JLabel lblFechaIngreso = new JLabel("Fecha de ingreso:");
        lblFechaIngreso.setBounds(30, 240, 150, 25);
        getContentPane().add(lblFechaIngreso);

        txtFechaIngreso = new JTextField();
        txtFechaIngreso.setBounds(190, 240, 300, 25);
        getContentPane().add(txtFechaIngreso);

        // Antecedentes médicos
        JLabel lblEspecialidad = new JLabel("Antecedentes Médicos:");
        lblEspecialidad.setBounds(30, 275, 150, 25);
        getContentPane().add(lblEspecialidad);

        txtAntecedenteMedico = new JTextField();
        txtAntecedenteMedico.setBounds(190, 275, 200, 25);
        getContentPane().add(txtAntecedenteMedico);

        // Botón agregar Antecedente
        JButton btnAgregarAntecedenteMedico = new JButton("Agregar");
        btnAgregarAntecedenteMedico.setBounds(400, 275, 90, 25);
        getContentPane().add(btnAgregarAntecedenteMedico);

        // Modelo de antecedentes seleccionados
        JLabel lblSeleccionadas = new JLabel("Seleccionados:");
        lblSeleccionadas.setBounds(30, 315, 150, 25);
        getContentPane().add(lblSeleccionadas);

        modeloAntecedentesMedicos = new DefaultListModel<>();

        listaAntecedentesMedicos = new JList<>(modeloAntecedentesMedicos);

        JScrollPane scrollAntecedentes =
                new JScrollPane(listaAntecedentesMedicos);

        scrollAntecedentes.setBounds(190, 310, 300, 70);
        getContentPane().add(scrollAntecedentes);

        // Agregar antecedente
        btnAgregarAntecedenteMedico.addActionListener(e -> {

        	String antecedente =
                    txtAntecedenteMedico.getText().trim();

            if (antecedente.isEmpty()) {
                return;
            }


            // Evitar duplicados
            if (!modeloAntecedentesMedicos.contains(antecedente)) {
                modeloAntecedentesMedicos.addElement(antecedente);
                txtAntecedenteMedico.setText("");
            }
        });
        
        // Botón Dar de alta
        JButton btnAlta = new JButton("Dar de alta");
        btnAlta.setBounds(130, 390, 120, 30);
        getContentPane().add(btnAlta);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 390, 120, 30);
        getContentPane().add(btnCancelar);

        // Evento Cancelar
        btnCancelar.addActionListener(e -> {
            dispose();
        });
        
     // Evento Dar de alta
        btnAlta.addActionListener(e -> altaPaciente());
    }
    
    private void altaPaciente() {

        try {

            String nickname = txtNickname.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();

            DTFecha fechaNacimiento =
                    convertirFecha(txtFechaNacimiento.getText().trim());

            int documento =
                    Integer.parseInt(txtDocumento.getText().trim());

            DTFecha fechaIngreso =
                    convertirFecha(txtFechaIngreso.getText().trim());

         // Crear el conjunto de antecedentes
            Set<String> antecedentes = new HashSet<>();

            for (int i = 0;
                 i < modeloAntecedentesMedicos.size();
                 i++) {

                String antecedente =
                        modeloAntecedentesMedicos.getElementAt(i);

                antecedentes.add(antecedente);
            }
            
            // Llamamos al controlador
            controlador.altaPaciente(
                    nickname,
                    nombre,
                    apellido,
                    correo,
                    fechaNacimiento,
                    documento,
                    fechaIngreso,
                    antecedentes
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente dado de alta correctamente."
            );

            dispose();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "El documento debe ser un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private DTFecha convertirFecha(String texto) {

        String[] partes = texto.split("/");

        if (partes.length != 3) {
            throw new IllegalArgumentException(
                    "La fecha debe tener el formato dd/mm/aaaa."
            );
        }

        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        return new DTFecha(dia, mes, anio);
    }
}