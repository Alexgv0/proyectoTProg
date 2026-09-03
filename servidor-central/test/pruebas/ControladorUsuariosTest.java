package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fabrica.Fabrica;
import interfaces.IControladorUsuarios;

import java.util.HashSet;
import java.util.Set;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;
import datatypes.DTUsuario;
import clases.Paciente;
import datatypes.DTBeneficioAnual;

import clases.Usuario;

public class ControladorUsuariosTest {

    private static IControladorUsuarios controladorUsuarios;

    @BeforeAll
    static void iniciar() {

        Fabrica fabrica = Fabrica.getInstancia();

        controladorUsuarios = fabrica.getControladorUsuarios();

        DatosPrueba.cargarTodosLosDatos();
    }

    @Test
    void testListarEspecialidades() {

        assertNotNull(controladorUsuarios);

        assertTrue(
            controladorUsuarios.listarEspecialidades()
                .contains("Cardiología")
        );

        assertTrue(
            controladorUsuarios.listarEspecialidades()
                .contains("Pediatría")
        );

        assertTrue(
            controladorUsuarios.listarEspecialidades()
                .contains("Medicina General")
        );
    }
    
    @Test
    void testObtenerDatosEspecialidad() {
    	
    	DTEspecialidad cardiologia =
    			controladorUsuarios.obtenerDatosEspecialidad("Cardiología");
    	
    	assertNotNull(cardiologia);
    	assertEquals("Cardiología", cardiologia.getNombre());
    	assertEquals(20, cardiologia.getPorcentajeCostoConsultaAdicional());
    	
    	
    	DTEspecialidad pediatria =
    			controladorUsuarios.obtenerDatosEspecialidad("Pediatría");
    	
    	assertNotNull(pediatria);
    	assertEquals("Pediatría", pediatria.getNombre());
    	assertEquals(10, pediatria.getPorcentajeCostoConsultaAdicional());
    	
    	
    	DTEspecialidad medicinaGeneral =
    			controladorUsuarios.obtenerDatosEspecialidad("Medicina General");
    	
    	assertNotNull(medicinaGeneral);
    	assertEquals("Medicina General", medicinaGeneral.getNombre());
    	assertEquals(0, medicinaGeneral.getPorcentajeCostoConsultaAdicional());
    }

    @Test
    void testAltaMedico() {
    	
    	Set<DTEspecialidad> especialidades1 = new HashSet<>();
    	especialidades1.add(
    			controladorUsuarios.obtenerDatosEspecialidad("Cardiología")
    			);
    	especialidades1.add(
    			controladorUsuarios.obtenerDatosEspecialidad("Medicina General")
    			);
    	
    	Usuario m1 = controladorUsuarios.altaMedico(
    			"ana.perez.test",
    			"Ana",
    			"Pérez",
    			"ana.perez.test@tuclinica.com",
    			2001 ,
    			"Enero",
    			"https://www.hospitalbritanico.org.uy/",
    			especialidades1,
    			15);

    	assertNotNull(m1);

    	assertEquals("ana.perez.test", m1.getNickname());
    	assertEquals("Ana", m1.getNombre());
    	assertEquals("Pérez", m1.getApellido());
    	assertEquals("ana.perez.test@tuclinica.com", m1.getCorreo());


    	Set<DTEspecialidad> especialidades2 = new HashSet<>();
    	especialidades2.add(
    			controladorUsuarios.obtenerDatosEspecialidad("Pediatría")
    			);

    	Usuario m2 = controladorUsuarios.altaMedico(
    			"bsilva.test",
    			"Bruno",
    			"Silva",
    			"bruno.silva.test@hospital.com",
    			2002 ,
    			"Julio",
    			"",
    			especialidades2,
    			5);

    	assertNotNull(m2);

    	assertEquals("bsilva.test", m2.getNickname());
    	assertEquals("Bruno", m2.getNombre());
    	assertEquals("Silva", m2.getApellido());
    	assertEquals("bruno.silva.test@hospital.com", m2.getCorreo());


    	Set<DTEspecialidad> especialidades3 = new HashSet<>();
    	especialidades3.add(
    			controladorUsuarios.obtenerDatosEspecialidad("Cardiología")
    			);
    	especialidades3.add(
    			controladorUsuarios.obtenerDatosEspecialidad("Medicina General")
    			);

    	Usuario m3 = controladorUsuarios.altaMedico(
    			"carla.rodriguez.test",
    			"Carla",
    			"Rodríguez",
    			"carla.rodriguez.test@hospital.com",
    			2003 ,
    			"Febrero",
    			"https://www.hospitalbritanico.org.uy/",
    			especialidades3,
    			10);

    	assertNotNull(m3);

    	assertEquals("carla.rodriguez.test", m3.getNickname());
    	assertEquals("Carla", m3.getNombre());
    	assertEquals("Rodríguez", m3.getApellido());
    	assertEquals("carla.rodriguez.test@hospital.com", m3.getCorreo());
    }
    
    @Test
    void testAltaPaciente() {
    	
    	Set<String> antecedentes1 = new HashSet<>();
    	antecedentes1.add("Sin antecedentes relevantes.");
    	
    	Usuario p1 = controladorUsuarios.altaPaciente(
    			"lgomez.test",
    			"Laura",
    			"Gómez",
    			"laura.gomez.test@gmail.com",
    			new DTFecha(12, 4, 1990),
    			45678902,
    			new DTFecha(1, 3, 2024),
    			antecedentes1
    			);

    	assertNotNull(p1);
    	assertEquals("lgomez.test", p1.getNickname());
    	assertEquals("Laura", p1.getNombre());
    	assertEquals("Gómez", p1.getApellido());
    	assertEquals("laura.gomez.test@gmail.com", p1.getCorreo());


    	Set<String> antecedentes2 = new HashSet<>();
    	antecedentes2.add(
    			"Hipertensión arterial diagnosticada en 2018, actualmente controlada con tratamiento farmacológico. Antecedentes de colesterol elevado y episodios ocasionales de migraña. En 2022 fue intervenido quirúrgicamente por una hernia inguinal, sin complicaciones posteriores."
    			);

    	Usuario p2 = controladorUsuarios.altaPaciente(
    			"mar.fernandez.test",
    			"Mario",
    			"Fernández",
    			"mario.fernandez.test@hotmail.com",
    			new DTFecha(8, 11, 1955),
    			32145679,
    			new DTFecha(15, 6, 2022),
    			antecedentes2
    			);

    	assertNotNull(p2);
    	assertEquals("mar.fernandez.test", p2.getNickname());
    	assertEquals("Mario", p2.getNombre());
    	assertEquals("Fernández", p2.getApellido());
    	assertEquals("mario.fernandez.test@hotmail.com", p2.getCorreo());


    	Set<String> antecedentes3 = new HashSet<>();
    	antecedentes3.add("Alergia a penicilina.");

    	Usuario p3 = controladorUsuarios.altaPaciente(
    			"s.lopez.test",
    			"Sofía",
    			"López",
    			"sofia.lopez.test@gmail.com",
    			new DTFecha(20, 9, 2014),
    			58963215,
    			new DTFecha(10, 2, 2025),
    			antecedentes3
    			);

    	assertNotNull(p3);
    	assertEquals("s.lopez.test", p3.getNickname());
    	assertEquals("Sofía", p3.getNombre());
    	assertEquals("López", p3.getApellido());
    	assertEquals("sofia.lopez.test@gmail.com", p3.getCorreo());
    }
    
    //datos propios
    @Test
    void testAltaUsuario() {

        Usuario usuario =
                new Paciente(
                        "usuario.test",
                        "Usuario",
                        "Test",
                        "usuario.test@gmail.com",
                        new DTFecha(10, 5, 1995),
                        70000002,
                        new DTFecha(1, 9, 2026),
                        new HashSet<>()
                );

        controladorUsuarios.altaUsuario(usuario);

        DTUsuario datos =
                controladorUsuarios.seleccionarUsuario(
                        "usuario.test"
                );

        assertNotNull(datos);

        assertEquals(
                "usuario.test",
                datos.getNickname()
        );
    }
    
    //datos propios
    @Test
    void testCancelarAltaUsuario() {

        Usuario usuario =
                new Paciente(
                        "usuario.cancelar",
                        "Usuario",
                        "Cancelar",
                        "usuario.cancelar@gmail.com",
                        new DTFecha(10, 5, 1995),
                        70000003,
                        new DTFecha(1, 9, 2026),
                        new HashSet<>()
                );

        controladorUsuarios.altaUsuario(usuario);

        controladorUsuarios.cancelarAltaUsuario(usuario);

        assertThrows(
                IllegalArgumentException.class,
                () -> controladorUsuarios.seleccionarUsuario(
                        "usuario.cancelar"
                )
        );
    }
    
    @Test
    void testListarUsuarios() {

        Set<DTUsuario> usuarios =
                controladorUsuarios.listarUsuarios();

        assertNotNull(usuarios);

        assertTrue(
                usuarios.stream()
                        .anyMatch(u ->
                                u.getNickname().equals("ana.perez")
                        )
        );

        assertTrue(
                usuarios.stream()
                        .anyMatch(u ->
                                u.getNickname().equals("lgomez")
                        )
        );
    }
    
    @Test
    void testSeleccionarUsuario() {

        DTUsuario usuario =
                controladorUsuarios.seleccionarUsuario(
                        "ana.perez"
                );

        assertNotNull(usuario);

        assertEquals(
                "ana.perez",
                usuario.getNickname()
        );

        assertEquals(
                "Ana",
                usuario.getNombre()
        );
    }
    
    @Test
    void testListarPacientes() {

        assertTrue(
            controladorUsuarios.listarPacientes()
                .contains("lgomez")
        );

        assertTrue(
            controladorUsuarios.listarPacientes()
                .contains("mar.fernandez")
        );

        assertTrue(
            controladorUsuarios.listarPacientes()
                .contains("s.lopez")
        );
    }
    
    @Test
    void testListarMedicos() {

        Set<String> medicos =
                controladorUsuarios.listarMedicos();

        assertNotNull(medicos);

        assertTrue(medicos.contains("ana.perez"));
        assertTrue(medicos.contains("bsilva"));
        assertTrue(medicos.contains("carla.rodriguez"));
    }
    
    @Test
    void testModificarPaciente() {

        controladorUsuarios.modificarPaciente(
                "lgomez",
                "Laura Modificada",
                "Gómez",
                new DTFecha(12, 4, 1990),
                45678901,
                new DTFecha(1, 3, 2024),
                new HashSet<>()
        );

        DTUsuario usuario =
                controladorUsuarios.seleccionarUsuario(
                        "lgomez"
                );

        assertNotNull(usuario);
        assertEquals(
                "Laura Modificada",
                usuario.getNombre()
        );
        
        controladorUsuarios.modificarPaciente(
                "lgomez",
                "Laura",
                "Gómez",
                new DTFecha(12, 4, 1990),
                45678901,
                new DTFecha(1, 3, 2024),
                new HashSet<>()
        );
        
        usuario =
                controladorUsuarios.seleccionarUsuario(
                        "lgomez"
                );
        
        assertNotNull(usuario);
        assertEquals(
                "Laura",
                usuario.getNombre()
        );
    }
    
    @Test
    void testModificarMedico() {

        Set<String> especialidades = new HashSet<>();

        especialidades.add("Cardiología");
        especialidades.add("Medicina General");

        controladorUsuarios.modificarMedico(
                "ana.perez",
                "Ana Modificada",
                "Pérez",
                1001,
                "Enero",
                "https://www.hospitalbritanico.org.uy/",
                especialidades,
                15
        );

        DTUsuario usuario =
                controladorUsuarios.seleccionarUsuario(
                        "ana.perez"
                );

        assertNotNull(usuario);
        assertEquals(
                "Ana Modificada",
                usuario.getNombre()
        );
        
        controladorUsuarios.modificarMedico(
                "ana.perez",
                "Ana",
                "Pérez",
                1001,
                "Enero",
                "https://www.hospitalbritanico.org.uy/",
                especialidades,
                15
        );
        
        usuario =
                controladorUsuarios.seleccionarUsuario(
                        "ana.perez"
                );
        
        assertNotNull(usuario);
        assertEquals(
                "Ana",
                usuario.getNombre()
        );
    }


    /*@Test
    void testIngresarDatosAltaEspecialidad() {

        boolean resultado =
                controladorUsuarios.ingresarDatosAltaEspecialidad(
                        "Dermatología",
                        15
                );

        assertTrue(resultado);

        DTEspecialidad especialidad =
                controladorUsuarios.obtenerDatosEspecialidad(
                        "Dermatología"
                );

        assertNotNull(especialidad);

        assertEquals(
                "Dermatología",
                especialidad.getNombre()
        );

        assertEquals(
                15,
                especialidad.getPorcentajeCostoConsultaAdicional()
        );
    }*/


    @Test
    void testAltaBeneficioAnual() {

        controladorUsuarios.altaBeneficioAnual(
                "lgomez",
                2030,
                3,
                4
        );

        Set<DTBeneficioAnual> beneficios =
                controladorUsuarios.listarBeneficiosAnuales(
                        "lgomez"
                );

        assertNotNull(beneficios);

        assertTrue(
                beneficios.stream()
                        .anyMatch(b ->
                                b.getAnio() == 2030
                        )
        );
    }


    @Test
    void testListarBeneficiosAnuales() {

        Set<DTBeneficioAnual> beneficios =
                controladorUsuarios.listarBeneficiosAnuales(
                        "mar.fernandez"
                );

        assertNotNull(beneficios);
    }

    @Test
    void testSeleccionarBeneficioAnual() {

        DTBeneficioAnual beneficio =
                controladorUsuarios.seleccionarBeneficioAnual(
                        "mar.fernandez",
                        2026
                );

        assertNotNull(beneficio);

        assertEquals(
                2026,
                beneficio.getAnio()
        );
    }
    
    @Test
    void testIngresarDatosAltaEspecialidad() {

        boolean resultado =
            controladorUsuarios.ingresarDatosAltaEspecialidad(
                "Dermatología",
                15
            );

        assertTrue(resultado);

        DTEspecialidad especialidad =
            controladorUsuarios.obtenerDatosEspecialidad("Dermatología");

        assertNotNull(especialidad);
        assertEquals("Dermatología", especialidad.getNombre());
        assertEquals(
            15,
            especialidad.getPorcentajeCostoConsultaAdicional()
        );
    }
    
} 
