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
    			"ana.perez",
    			"Ana",
    			"Pérez",
    			"ana.perez@tuclinica.com",
    			1001 ,
    			"Enero", 
    			"https://www.hospitalbritanico.org.uy/",
    			especialidades1,
    			15);
    	
    	assertNotNull(m1);

        assertEquals("ana.perez", m1.getNickname());
        assertEquals("Ana", m1.getNombre());
        assertEquals("Pérez", m1.getApellido());
        assertEquals("ana.perez@tuclinica.com", m1.getEmail());
        
        
        Set<DTEspecialidad> especialidades2 = new HashSet<>();
    	especialidades2.add(
    	    controladorUsuarios.obtenerDatosEspecialidad("Pediatría")
    	);

    	Usuario m2 = controladorUsuarios.altaMedico(
    			"bsilva",
    			"Bruno",
    			"Silva",
    			"bruno.silva@hospital.com",
    			1002 ,
    			"Julio", 
    			"",
    			especialidades2,
    			5);
    	
    	assertNotNull(m2);

        assertEquals("bsilva", m2.getNickname());
        assertEquals("Bruno", m2.getNombre());
        assertEquals("Silva", m2.getApellido());
        assertEquals("bruno.silva@hospital.com", m2.getEmail());
        
        
        Set<DTEspecialidad> especialidades3 = new HashSet<>();
    	especialidades3.add(
    	    controladorUsuarios.obtenerDatosEspecialidad("Cardiología")
    	);
    	especialidades3.add(
    		controladorUsuarios.obtenerDatosEspecialidad("Medicina General")
    	);
    	
    	Usuario m3 = controladorUsuarios.altaMedico(
    			"carla.rodriguez",
    			"Carla",
    			"Rodríguez",
    			"carla.rodriguez@hospital.com",
    			1003 ,
    			"Febrero", 
    			"https://www.hospitalbritanico.org.uy/",
    			especialidades3,
    			10);
    	
    	assertNotNull(m3);

        assertEquals("carla.rodriguez", m3.getNickname());
        assertEquals("Carla", m3.getNombre());
        assertEquals("Rodríguez", m3.getApellido());
        assertEquals("carla.rodriguez@hospital.com", m3.getEmail());
    }
    
    @Test
    void testAltaPaciente() {

        Set<String> antecedentes1 = new HashSet<>();
        antecedentes1.add("Sin antecedentes relevantes.");

        Usuario p1 = controladorUsuarios.altaPaciente(
            "lgomez",
            "Laura",
            "Gómez",
            "laura.gomez@gmail.com",
            new DTFecha(12, 4, 1990),
            45678901,
            new DTFecha(1, 3, 2024),
            antecedentes1
        );

        assertNotNull(p1);
        assertEquals("lgomez", p1.getNickname());
        assertEquals("Laura", p1.getNombre());
        assertEquals("Gómez", p1.getApellido());
        assertEquals("laura.gomez@gmail.com", p1.getEmail());


        Set<String> antecedentes2 = new HashSet<>();
        antecedentes2.add(
            "Hipertensión arterial diagnosticada en 2018, actualmente controlada con tratamiento farmacológico. Antecedentes de colesterol elevado y episodios ocasionales de migraña. En 2022 fue intervenido quirúrgicamente por una hernia inguinal, sin complicaciones posteriores."
        );

        Usuario p2 = controladorUsuarios.altaPaciente(
            "mar.fernandez",
            "Mario",
            "Fernández",
            "mario.fernandez@hotmail.com",
            new DTFecha(8, 11, 1955),
            32145678,
            new DTFecha(15, 6, 2022),
            antecedentes2
        );

        assertNotNull(p2);
        assertEquals("mar.fernandez", p2.getNickname());
        assertEquals("Mario", p2.getNombre());
        assertEquals("Fernández", p2.getApellido());
        assertEquals("mario.fernandez@hotmail.com", p2.getEmail());


        Set<String> antecedentes3 = new HashSet<>();
        antecedentes3.add("Alergia a penicilina.");

        Usuario p3 = controladorUsuarios.altaPaciente(
            "s.lopez",
            "Sofía",
            "López",
            "sofia.lopez@gmail.com",
            new DTFecha(20, 9, 2014),
            58963214,
            new DTFecha(10, 2, 2025),
            antecedentes3
        );

        assertNotNull(p3);
        assertEquals("s.lopez", p3.getNickname());
        assertEquals("Sofía", p3.getNombre());
        assertEquals("López", p3.getApellido());
        assertEquals("sofia.lopez@gmail.com", p3.getEmail());
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
    
    @Test
    void testIngresarDatosAltaEspecialidadRepetida() {

        boolean resultado =
            controladorUsuarios.ingresarDatosAltaEspecialidad(
                "Cardiología",
                20
            );

        assertFalse(resultado);
    }
}
