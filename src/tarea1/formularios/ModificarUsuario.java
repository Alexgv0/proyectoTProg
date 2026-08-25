package formularios;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ModificarUsuario extends JInternalFrame {
	private static final long serialVersionUID = 1L;

    public ModificarUsuario() {
        setTitle("Alta de Usuario");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        
        //titulo
        JLabel lblUsuarios = new JLabel("Seleccione un usuario:");
        
        //lista de usuarios
        String[] usuarios = {
        		"u1",
        		"u2",
        		"u3"
        };
        
        listaUsuarios = new JList<>(usuarios);
        
        //scroll para la lista
        JScrollPane scrollUsuarios = new JScrollPane(listaUsuarios);
        
        //panel principal
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(lblUsuarios, BorderLayout.NORTH);
        panel.add(scrollUsuarios, BorderLayout.CENTER);
        
        add(panel);
    }
}