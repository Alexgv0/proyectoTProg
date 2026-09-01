package formularios;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import interfaces.IControladorUsuarios;

public class AltaUsuario extends FormularioBase {
    private static final long serialVersionUID = 1L;

    public AltaUsuario(IControladorUsuarios controlador) {
        super("Alta de Usuario", 380, 220);
        JRadioButton paciente = new JRadioButton("Paciente", true);
        JRadioButton medico = new JRadioButton("Medico");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(paciente);
        grupo.add(medico);

        JPanel opciones = new JPanel(new GridLayout(0, 1, 5, 5));
        opciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 35, 10, 35));
        opciones.add(new JLabel("Seleccione el tipo de usuario:"));
        opciones.add(paciente);
        opciones.add(medico);

        JPanel botones = new JPanel();
        JButton continuar = new JButton("Continuar");
        JButton cancelar = new JButton("Cancelar");
        continuar.addActionListener(evento -> {
            if (getDesktopPane() == null) return;
            FormularioBase ventana = paciente.isSelected()
                    ? new AltaPaciente(controlador) : new AltaMedico(controlador);
            getDesktopPane().add(ventana);
            ventana.setVisible(true);
            dispose();
        });
        cancelar.addActionListener(evento -> dispose());
        botones.add(continuar);
        botones.add(cancelar);
        ocuparCentro(opciones);
        getContentPane().add(botones, BorderLayout.SOUTH);
    }
}
