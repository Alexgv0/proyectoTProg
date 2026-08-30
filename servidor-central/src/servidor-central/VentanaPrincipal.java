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

public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JDesktopPane desktopPane;

    public VentanaPrincipal() {

        setTitle("salud.uy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 650);

        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        //User
        JMenu menuUsuario = new JMenu("Usuario");
        menuBar.add(menuUsuario);
        
        //altaUsuario
        JMenuItem itemAltaUsuario =
                new JMenuItem("Alta de Usuario");

        itemAltaUsuario.addActionListener(e -> {
            AltaUsuario ventana = new AltaUsuario();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuUsuario.add(itemAltaUsuario);
        
        //modificarUsuario
        JMenuItem itemModificarUsuario =
                new JMenuItem("Modificar Usuario");

        itemModificarUsuario.addActionListener(e -> {
        	ModificarUsuario ventana = new ModificarUsuario();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuUsuario.add(itemModificarUsuario);
        
        //ConsultaUsuario
        JMenuItem itemConsultaUsuario =
                new JMenuItem("Consulta de Usuario");

        itemConsultaUsuario.addActionListener(e -> {
        	ConsultaUsuario ventana = new ConsultaUsuario();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuUsuario.add(itemConsultaUsuario);
        
        //altaEspecialidad
        JMenuItem itemAltaEspecialidad =
                new JMenuItem("Alta de Especialidad");

        itemAltaEspecialidad.addActionListener(e -> {
            AltaEspecialidad ventana = new AltaEspecialidad();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuUsuario.add(itemAltaEspecialidad);
        
        
        //BeneficioAnual
        JMenu menuBeneficioAnual = new JMenu("Beneficio Anual");
        menuBar.add(menuBeneficioAnual);
        
        //AltaBeneficio
        JMenuItem itemAltaBeneficio =
                new JMenuItem("Alta de Beneficio");

        itemAltaBeneficio.addActionListener(e -> {
        	AltaBeneficio ventana = new AltaBeneficio();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuBeneficioAnual.add(itemAltaBeneficio);
        
        //ConsultaBeneficio
        JMenuItem itemConsultaBeneficio =
                new JMenuItem("Consulta de Beneficio");

        itemConsultaBeneficio.addActionListener(e -> {
        	ConsultaBeneficio ventana = new ConsultaBeneficio();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuBeneficioAnual.add(itemConsultaBeneficio);
        
        
        //DiaConsulta
        JMenu menuDiaConsulta = new JMenu("Dia Consulta");
        menuBar.add(menuDiaConsulta);
        
        //AltaDiaConsulta
        JMenuItem itemAltaDiaConsulta =
                new JMenuItem("Alta de Dia de Consulta");

        itemAltaDiaConsulta.addActionListener(e -> {
        	AltaDiaConsulta ventana = new AltaDiaConsulta();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuDiaConsulta.add(itemAltaDiaConsulta);
        
        //ConsultaDiaConsulta
        JMenuItem itemConsultaDiaConsulta =
                new JMenuItem("Consulta de Dia de Consulta");

        itemConsultaDiaConsulta.addActionListener(e -> {
        	ConsultaDiaConsulta ventana = new ConsultaDiaConsulta();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuDiaConsulta.add(itemConsultaDiaConsulta);
        
        
        //Consult
        JMenu menuConsulta = new JMenu("Consulta");
        menuBar.add(menuConsulta);
        
        //AltaConsulta
        JMenuItem itemAltaConsulta = new JMenuItem("Alta de Consulta");

        itemAltaConsulta.addActionListener(e -> {
        	AltaConsulta ventana = new AltaConsulta();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuConsulta.add(itemAltaConsulta);
        
        //ConsultaConsulta
        JMenuItem itemConsultaConsulta =
                new JMenuItem("Consulta de Consulta");

        itemConsultaConsulta.addActionListener(e -> {
        	ConsultaConsulta ventana = new ConsultaConsulta();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuConsulta.add(itemConsultaConsulta);
        
        //ReservaConsulta
        JMenuItem itemReservaConsulta =
                new JMenuItem("Reserva de Consulta");

        itemReservaConsulta.addActionListener(e -> {
        	ReservaConsulta ventana = new ReservaConsulta();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuConsulta.add(itemReservaConsulta);
        
        //ConsultaReservaConsulta
        JMenuItem itemConsultaReservaConsulta =
                new JMenuItem("Consulta de Reserva de Consulta");

        itemConsultaReservaConsulta.addActionListener(e -> {
        	ConsultaReservaConsulta ventana = new ConsultaReservaConsulta();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuConsulta.add(itemConsultaReservaConsulta);
        
        
        //Clinic
        JMenu menuClinica = new JMenu("Clinica");
        menuBar.add(menuClinica);
        
        //AltaClinica
        JMenuItem itemAltaClinica =
                new JMenuItem("Alta de Clinica");

        itemAltaClinica.addActionListener(e -> {
        	AltaClinica ventana = new AltaClinica();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuClinica.add(itemAltaClinica);
        
        //ConsultaClinica
        JMenuItem itemConsultaClinica =
                new JMenuItem("Consulta de Clinica");

        itemConsultaClinica.addActionListener(e -> {
        	ConsultaClinica ventana = new ConsultaClinica();
            desktopPane.add(ventana);
            ventana.setVisible(true);
        });

        menuClinica.add(itemConsultaClinica);
    }
}
