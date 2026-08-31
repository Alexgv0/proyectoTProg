import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fabrica.Fabrica;
import formularios.AltaBeneficio;
import formularios.AltaClinica;
import formularios.AltaConsulta;
import formularios.AltaDiaConsulta;
import formularios.AltaEspecialidad;
import formularios.AltaUsuario;
import formularios.ConsultaBeneficio;
import formularios.ConsultaClinica;
import formularios.ConsultaConsulta;
import formularios.ConsultaDiaConsulta;
import formularios.ConsultaReservaConsulta;
import formularios.ConsultaUsuario;
import formularios.ModificarUsuario;
import formularios.ReservaConsulta;
import interfaces.IControladorClinicas;
import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JDesktopPane escritorio = new JDesktopPane();
    private final IControladorUsuarios usuarios;
    private final IControladorClinicas clinicas;
    private final IControladorReservas reservas;

    public VentanaPrincipal() {
        Fabrica fabrica = Fabrica.getInstancia();
        usuarios = fabrica.getControladorUsuarios();
        clinicas = fabrica.getControladorClinicas();
        reservas = fabrica.getControladorReservas();
        setTitle("salud.uy - Estacion de Trabajo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 650));
        setSize(1100, 760);
        setLocationRelativeTo(null);
        getContentPane().add(escritorio, BorderLayout.CENTER);
        setJMenuBar(crearMenu());
    }

    private JMenuBar crearMenu() {
        JMenuBar barra = new JMenuBar();

        JMenu menuUsuarios = new JMenu("Usuarios");
        agregar(menuUsuarios, "Alta de Usuario", () -> new AltaUsuario(usuarios));
        agregar(menuUsuarios, "Consulta de Usuario",
                () -> new ConsultaUsuario(usuarios, clinicas, reservas));
        agregar(menuUsuarios, "Modificar Datos de Usuario",
                () -> new ModificarUsuario(usuarios));
        agregar(menuUsuarios, "Alta de Especialidad",
                () -> new AltaEspecialidad(usuarios));
        barra.add(menuUsuarios);

        JMenu menuClinicas = new JMenu("Clinicas");
        agregar(menuClinicas, "Alta de Clinica", () -> new AltaClinica(clinicas));
        agregar(menuClinicas, "Consulta de Clinica", () -> new ConsultaClinica(clinicas));
        barra.add(menuClinicas);

        JMenu menuDias = new JMenu("Dias de Consulta");
        agregar(menuDias, "Alta de Dia de Consulta", () -> new AltaDiaConsulta(clinicas));
        agregar(menuDias, "Consulta de Dia de Consulta",
                () -> new ConsultaDiaConsulta(clinicas));
        barra.add(menuDias);

        JMenu menuConsultas = new JMenu("Consultas");
        agregar(menuConsultas, "Alta de Consulta",
                () -> new AltaConsulta(clinicas, usuarios));
        agregar(menuConsultas, "Consulta de Consulta",
                () -> new ConsultaConsulta(clinicas));
        agregar(menuConsultas, "Reserva de Consulta",
                () -> new ReservaConsulta(clinicas, usuarios, reservas));
        agregar(menuConsultas, "Consulta de Reserva de Consulta",
                () -> new ConsultaReservaConsulta(usuarios, reservas));
        barra.add(menuConsultas);

        JMenu menuBeneficios = new JMenu("Beneficios Anuales");
        agregar(menuBeneficios, "Alta de Beneficio Anual",
                () -> new AltaBeneficio(usuarios));
        agregar(menuBeneficios, "Consulta de Beneficio Anual",
                () -> new ConsultaBeneficio(usuarios));
        barra.add(menuBeneficios);
        return barra;
    }

    private void agregar(JMenu menu, String texto, CreadorVentana creador) {
        JMenuItem item = new JMenuItem(texto);
        item.addActionListener(_ -> abrir(creador.crear()));
        menu.add(item);
    }

    private void abrir(JInternalFrame ventana) {
        escritorio.add(ventana);
        int x = Math.max(0, (escritorio.getWidth() - ventana.getWidth()) / 2);
        int y = Math.max(0, (escritorio.getHeight() - ventana.getHeight()) / 2);
        ventana.setLocation(x, y);
        ventana.setVisible(true);
        try {
            ventana.setSelected(true);
        } catch (java.beans.PropertyVetoException ignorada) {
            // La ventana igualmente queda visible.
        }
    }

    @FunctionalInterface
    private interface CreadorVentana {
        JInternalFrame crear();
    }
}
