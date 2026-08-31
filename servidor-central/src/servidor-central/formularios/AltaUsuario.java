package formularios;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import interfaces.IControladorUsuarios;

import javax.swing.JOptionPane;

public class AltaUsuario extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JRadioButton radioPaciente;
    private JRadioButton radioMedico;

    public AltaUsuario(IControladorUsuarios cu) {
    	
        setTitle("Alta de Usuario");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        // Etiqueta
        JLabel lblTipoUsuario = new JLabel("Tipo de usuario:");
        lblTipoUsuario.setBounds(50, 50, 150, 25);

        // RadioButton Paciente
        radioPaciente = new JRadioButton("Paciente");
        radioPaciente.setBounds(50, 90, 100, 25);

        // RadioButton Médico
        radioMedico = new JRadioButton("Médico");
        radioMedico.setBounds(50, 120, 100, 25);

        // Agrupar los RadioButton
        ButtonGroup grupoTipoUsuario = new ButtonGroup();
        grupoTipoUsuario.add(radioPaciente);
        grupoTipoUsuario.add(radioMedico);

        // Botón Continuar
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(80, 190, 100, 30);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 190, 100, 30);

        // Agregar componentes a la ventana
        getContentPane().setLayout(null);

        getContentPane().add(lblTipoUsuario);
        getContentPane().add(radioPaciente);
        getContentPane().add(radioMedico);
        getContentPane().add(btnContinuar);
        getContentPane().add(btnCancelar);

        // Evento del botón Cancelar
        btnCancelar.addActionListener(_ -> {
            dispose();
        });
        
     // Evento del botón Continuar
        btnContinuar.addActionListener(_ -> {

            if (radioPaciente.isSelected()) {

                AltaPaciente ventana = new AltaPaciente(cu);

                getDesktopPane().add(ventana);

                ventana.setVisible(true);

            } else if (radioMedico.isSelected()) {

                AltaMedico ventana = new AltaMedico(cu);

                getDesktopPane().add(ventana);

                ventana.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un tipo de usuario."
                );
            }
        });
    }
}