import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import formularios.AltaEspecialidad;
import formularios.AltaUsuario;
import formularios.ModificarUsuario;
import formularios.ConsultaUsuario;
import formularios.AltaBeneficio;
import formularios.ConsultaBeneficio;
import formularios.AltaDiaConsulta;
import formularios.ConsultaDiaConsulta;
import formularios.AltaConsulta;
import formularios.ConsultaConsulta;
import formularios.ReservaConsulta;
import formularios.ConsultaReservaConsulta;
import formularios.AltaClinica;
import formularios.ConsultaClinica;

import fabrica.Fabrica;

public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JDesktopPane desktopPane;
    
    private Fabrica fabrica;

    public VentanaPrincipal() {
    	
    	// Inicializamos la fabrica
    	this.fabrica = Fabrica.getInstancia();
    	
    	// Propiedades de la Ventana Principal
        setTitle("salud.uy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 650);

        // DesktopPane
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);

        // menuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Menus
        crearMenuUsuario(menuBar);
        crearMenuBeneficioAnual(menuBar);
        crearMenuDiaConsulta(menuBar);
        crearMenuConsulta(menuBar);
        crearMenuClinica(menuBar);
        	
        }
    
    // Menu Usuario
    private void crearMenuUsuario(JMenuBar menuBar) {	
    	JMenu menuUsuario = new JMenu("Usuario");
    	menuBar.add(menuUsuario);
    	
    	//
    	// CU :: AltaUsuario
    	//
    	JMenuItem itemAltaUsuario =
    			new JMenuItem("Alta de Usuario");
    	
    	itemAltaUsuario.addActionListener(e -> {
    		AltaUsuario ventana = new AltaUsuario(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuUsuario.add(itemAltaUsuario);
    	
    	//
    	// CU :: ModificarUsuario
    	//
    	JMenuItem itemModificarUsuario =
    			new JMenuItem("Modificar Usuario");
    	
    	itemModificarUsuario.addActionListener(e -> {
    		ModificarUsuario ventana = new ModificarUsuario(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuUsuario.add(itemModificarUsuario);
    	
    	//
    	// CU :: ConsultaUsuario
    	//
    	JMenuItem itemConsultaUsuario =
    			new JMenuItem("Consulta de Usuario");
    	
    	itemConsultaUsuario.addActionListener(e -> {
    		ConsultaUsuario ventana = new ConsultaUsuario(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuUsuario.add(itemConsultaUsuario);
    	
    	//
    	// CU :: AltaEspecialidad
    	//
    	JMenuItem itemAltaEspecialidad =
    			new JMenuItem("Alta de Especialidad");
    	
    	itemAltaEspecialidad.addActionListener(e -> {
    		AltaEspecialidad ventana = new AltaEspecialidad(fabrica.getControladorUsuarios());
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuUsuario.add(itemAltaEspecialidad);
    }
    
    
    // Menu BeneficioAnual
    private void crearMenuBeneficioAnual (JMenuBar menuBar) {
    	JMenu menuBeneficioAnual = new JMenu("Beneficio Anual");
    	menuBar.add(menuBeneficioAnual);
    	
    	//
    	// CU :: AltaBeneficio
    	//
    	JMenuItem itemAltaBeneficio =
    			new JMenuItem("Alta de Beneficio");
    	
    	itemAltaBeneficio.addActionListener(e -> {
    		AltaBeneficio ventana = new AltaBeneficio(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuBeneficioAnual.add(itemAltaBeneficio);
    	
    	//
    	// CU :: ConsultaBeneficio
    	//
    	JMenuItem itemConsultaBeneficio =
    			new JMenuItem("Consulta de Beneficio");
    	
    	itemConsultaBeneficio.addActionListener(e -> {
    		ConsultaBeneficio ventana = new ConsultaBeneficio(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuBeneficioAnual.add(itemConsultaBeneficio);	
    }
    
    
    // Menu DiaConsulta
    private void crearMenuDiaConsulta(JMenuBar menuBar) {
    	JMenu menuDiaConsulta = new JMenu("Dia Consulta");
    	menuBar.add(menuDiaConsulta);
    	
    	//
    	// CU :: AltaDiaConsulta
    	//
    	JMenuItem itemAltaDiaConsulta =
    			new JMenuItem("Alta de Dia de Consulta");
    	
    	itemAltaDiaConsulta.addActionListener(e -> {
    		AltaDiaConsulta ventana = new AltaDiaConsulta(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuDiaConsulta.add(itemAltaDiaConsulta);
    	
    	//
    	// CU :: ConsultaDiaConsulta
    	//
    	JMenuItem itemConsultaDiaConsulta =
    			new JMenuItem("Consulta de Dia de Consulta");
    	
    	itemConsultaDiaConsulta.addActionListener(e -> {
    		ConsultaDiaConsulta ventana = new ConsultaDiaConsulta(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuDiaConsulta.add(itemConsultaDiaConsulta);    	
    }
    
    
    // Menu Consulta
    private void crearMenuConsulta(JMenuBar menuBar) {
    	JMenu menuConsulta = new JMenu("Consulta");
    	menuBar.add(menuConsulta);
    	
    	//
    	// CU :: AltaConsulta
    	//
    	JMenuItem itemAltaConsulta = new JMenuItem("Alta de Consulta");
    	
    	itemAltaConsulta.addActionListener(e -> {
    		AltaConsulta ventana = new AltaConsulta(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuConsulta.add(itemAltaConsulta);
    	
    	//
    	// CU :: ConsultaConsulta
    	//
    	JMenuItem itemConsultaConsulta =
    			new JMenuItem("Consulta de Consulta");
    	
    	itemConsultaConsulta.addActionListener(e -> {
    		ConsultaConsulta ventana = new ConsultaConsulta(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuConsulta.add(itemConsultaConsulta);
    	
    	//
    	// CU :: ReservaConsulta
    	//
    	JMenuItem itemReservaConsulta =
    			new JMenuItem("Reserva de Consulta");
    	
    	itemReservaConsulta.addActionListener(e -> {
    		ReservaConsulta ventana = new ReservaConsulta(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuConsulta.add(itemReservaConsulta);
    	
    	//
    	// CU :: ConsultaReservaConsulta
    	//
    	JMenuItem itemConsultaReservaConsulta =
    			new JMenuItem("Consulta de Reserva de Consulta");
    	
    	itemConsultaReservaConsulta.addActionListener(e -> {
    		ConsultaReservaConsulta ventana = new ConsultaReservaConsulta(fabrica.getControladorUsuarios(), fabrica.getControladorReservas());
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuConsulta.add(itemConsultaReservaConsulta);
    }
    
    
    // Menu Clinica
    private void crearMenuClinica(JMenuBar menuBar) {
    	JMenu menuClinica = new JMenu("Clinica");
    	menuBar.add(menuClinica);
    	
    	//
    	// CU :: AltaClinica
    	//
    	JMenuItem itemAltaClinica =
    			new JMenuItem("Alta de Clinica");
    	
    	itemAltaClinica.addActionListener(e -> {
    		AltaClinica ventana = new AltaClinica(
    				// Ingresar Controlador con Fabrica
    				);
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuClinica.add(itemAltaClinica);
    	
    	//
    	// CU :: ConsultaClinica
    	//
    	JMenuItem itemConsultaClinica =
    			new JMenuItem("Consulta de Clinica");
    	
    	itemConsultaClinica.addActionListener(e -> {
    		ConsultaClinica ventana = new ConsultaClinica(fabrica.getControladorClinicas());
    		desktopPane.add(ventana);
    		ventana.setVisible(true);
    	});
    	
    	menuClinica.add(itemConsultaClinica);
    }
}
