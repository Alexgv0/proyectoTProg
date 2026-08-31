package formularios;

import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controladores.ControladorUsuarios;
import datatypes.DTEspecialidad;

public class AltaMedico extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNickname;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtRegistro;
    private JTextField txtMesLicencia;
    private JTextField txtSitioWeb;
    private JTextField txtPorcentaje;

    private JComboBox<String> comboEspecialidad;
    
    private DefaultListModel<String> modeloEspecialidades;
    private JList<String> listaEspecialidades;
    
    private ControladorUsuarios controlador;
    
    public AltaMedico() {

        setTitle("Alta de Médico");
        setSize(550, 550);
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

        // Número de registro
        JLabel lblRegistro = new JLabel("Número de registro:");
        lblRegistro.setBounds(30, 170, 150, 25);
        getContentPane().add(lblRegistro);

        txtRegistro = new JTextField();
        txtRegistro.setBounds(190, 170, 300, 25);
        getContentPane().add(txtRegistro);

        // Mes de licencia
        JLabel lblMesLicencia = new JLabel("Mes de licencia:");
        lblMesLicencia.setBounds(30, 205, 150, 25);
        getContentPane().add(lblMesLicencia);

        txtMesLicencia = new JTextField();
        txtMesLicencia.setBounds(190, 205, 300, 25);
        getContentPane().add(txtMesLicencia);

        // Sitio web
        JLabel lblSitioWeb = new JLabel("Sitio web (opcional):");
        lblSitioWeb.setBounds(30, 240, 150, 25);
        getContentPane().add(lblSitioWeb);

        txtSitioWeb = new JTextField();
        txtSitioWeb.setBounds(190, 240, 300, 25);
        getContentPane().add(txtSitioWeb);

        // Especialidad
        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(30, 275, 150, 25);
        getContentPane().add(lblEspecialidad);

        comboEspecialidad = new JComboBox<>();
        comboEspecialidad.setBounds(190, 275, 200, 25);
        getContentPane().add(comboEspecialidad);
        
        // Botón agregar especialidad
        JButton btnAgregarEspecialidad = new JButton("Agregar");
        btnAgregarEspecialidad.setBounds(400, 275, 90, 25);
        getContentPane().add(btnAgregarEspecialidad);

        // Lista de especialidades seleccionadas
        JLabel lblSeleccionadas = new JLabel("Seleccionadas:");
        lblSeleccionadas.setBounds(30, 315, 150, 25);
        getContentPane().add(lblSeleccionadas);

        modeloEspecialidades = new DefaultListModel<>();

        listaEspecialidades = new JList<>(modeloEspecialidades);

        JScrollPane scrollEspecialidades =
                new JScrollPane(listaEspecialidades);

        scrollEspecialidades.setBounds(190, 310, 300, 70);
        getContentPane().add(scrollEspecialidades);

        // Porcentaje adicional
        JLabel lblPorcentaje = new JLabel("Porcentaje adicional:");
        lblPorcentaje.setBounds(30, 400, 150, 25);
        getContentPane().add(lblPorcentaje);

        txtPorcentaje = new JTextField();
        txtPorcentaje.setBounds(190, 400, 300, 25);
        getContentPane().add(txtPorcentaje);

        // Botón Dar de alta
        JButton btnAlta = new JButton("Dar de alta");
        btnAlta.setBounds(130, 450, 120, 30);
        getContentPane().add(btnAlta);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 450, 120, 30);
        getContentPane().add(btnCancelar);

        cargarEspecialidades();
        
     // Agregar especialidad
        btnAgregarEspecialidad.addActionListener(_ -> {

            String especialidad =
                    (String) comboEspecialidad.getSelectedItem();

            if (especialidad == null) {
                return;
            }

            // Evitar duplicados
            if (!modeloEspecialidades.contains(especialidad)) {
                modeloEspecialidades.addElement(especialidad);
            }
        });
        
        // Evento Cancelar
        btnCancelar.addActionListener(_ -> {
            dispose();
        });
     // Evento Dar de alta
        btnAlta.addActionListener(_ -> altaMedico());
    }
    
    private void cargarEspecialidades() {

        Set<String> especialidades =
                controlador.listarEspecialidades();

        comboEspecialidad.setModel(
                new DefaultComboBoxModel<>(
                        especialidades.toArray(new String[0])
                )
        );
    }
    
    private void altaMedico() {
    	
    	try {
    		
    		String nickname = txtNickname.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String correo = txtCorreo.getText().trim();

            int registro =
                    Integer.parseInt(txtRegistro.getText().trim());

            String mesLicencia =
                    txtMesLicencia.getText().trim();

            String sitioWeb =
                    txtSitioWeb.getText().trim();

            int porcentaje =
                    Integer.parseInt(txtPorcentaje.getText().trim());

            // Verificar que haya al menos una especialidad
            if (modeloEspecialidades.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "El médico debe tener al menos una especialidad.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Set<DTEspecialidad> especialidad =
                    new HashSet<>();
            
            for (int i = 0;
                    i < modeloEspecialidades.size();
                    i++) {

                   String nombreEspecialidad =
                           modeloEspecialidades.getElementAt(i);

                   especialidad.add(
                           controlador.obtenerDatosEspecialidad(
                                   nombreEspecialidad
                           )
                   );
               }

               // Llamar al controlador
               controlador.altaMedico(
                       nickname,
                       nombre,
                       apellido,
                       correo,
                       registro,
                       mesLicencia,
                       sitioWeb,
                       especialidad,
                       porcentaje
               );
            
            JOptionPane.showMessageDialog(
                    this,
                    "Medico dado de alta correctamente."
            );

            dispose();
            
    	} catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "El registro y el porcentaje deben ser números.",
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

}