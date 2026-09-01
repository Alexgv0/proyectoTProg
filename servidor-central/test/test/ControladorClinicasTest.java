package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Usuario;
import fabrica.Fabrica;
import interfaces.IControladorClinicas;

import java.util.HashSet;
import java.util.Set;
import datatypes.DTClinica;
import datatypes.DTEspecialidad;

public class ControladorClinicasTest {

    private static IControladorClinicas controladorClinicas;

    @BeforeAll
    static void iniciar() {

        Fabrica fabrica = Fabrica.getInstancia();

        controladorClinicas = fabrica.getControladorClinicas();

        DatosPrueba.cargarTodosLosDatos();
    }
    
    @Test
    void testListarClinicas() {

    	Set<String> clinicas = controladorClinicas.listarClinicas();

        assertNotNull(clinicas);

        assertTrue(clinicas.contains("Hospital Británico"));
        assertTrue(clinicas.contains("CASMU"));
    }
    
    @Test
    void testObtenerDatosClinica() {

        DTClinica clinica =
            controladorClinicas.obtenerDatosClinica("Hospital Británico");

        assertNotNull(clinica);

        assertEquals("CASMU", clinica.getNombre());
    }
    
    @Test
    void testListarEspecialidades() {

        Set<String> especialidades =
            controladorClinicas.listarEspecialidades();

        assertNotNull(especialidades);

        assertTrue(especialidades.contains("Cardiología"));
        assertTrue(especialidades.contains("Pediatría"));
        assertTrue(especialidades.contains("Medicina General"));
    }
    
    @Test
    void testAltaDiaConsulta() {

        controladorClinicas.altaDiaConsulta(
            "Cardiología - Lunes",
            "Atención cardiológica semanal",
            Dia.LUNES,
            9,
            "Cardiología",
            "Hospital Británico"
        );

        assertNotNull(
            controladorClinicas.seleccionarDiaConsulta(
                "Cardiología - Lunes"
            )
        );
    }
    
    

    
}