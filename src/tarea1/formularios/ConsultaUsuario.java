package formularios;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ConsultaUsuario extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JList<String> listaUsuarios;

    public ConsultaUsuario() {

        setTitle("Consulta de Usuario");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        // Título
        JLabel lblUsuarios = new JLabel("Seleccione un usuario:");

        // Lista de usuarios
        String[] usuarios = {
            "u1",
            "u2",
            "u3"
        };

        listaUsuarios = new JList<>(usuarios);

        // Scroll para la lista
        JScrollPane scrollUsuarios = new JScrollPane(listaUsuarios);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(lblUsuarios, BorderLayout.NORTH);
        panel.add(scrollUsuarios, BorderLayout.CENTER);

        add(panel);
    }
}