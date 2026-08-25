package formularios;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AltaMedico extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNickname;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtRegistro;
    private JTextField txtMesLicencia;
    private JTextField txtSitioWeb;
    private JTextField txtEspecialidad;
    private JTextField txtPorcentaje;

    public AltaMedico() {

        setTitle("Alta de Médico");
        setSize(550, 500);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

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

        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(190, 275, 300, 25);
        getContentPane().add(txtEspecialidad);

        // Porcentaje adicional
        JLabel lblPorcentaje = new JLabel("Porcentaje adicional:");
        lblPorcentaje.setBounds(30, 310, 150, 25);
        getContentPane().add(lblPorcentaje);

        txtPorcentaje = new JTextField();
        txtPorcentaje.setBounds(190, 310, 300, 25);
        getContentPane().add(txtPorcentaje);

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
    }
}