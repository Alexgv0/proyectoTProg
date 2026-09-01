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
import datatypes.DTConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsulta;
import datatypes.DTDiaConsultaCompleto;
import clases.Consulta;


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
                controladorClinicas.obtenerDatosClinica(
                        "Hospital Británico"
                );

        assertNotNull(clinica);

        assertEquals(
                "Hospital Británico",
                clinica.getNombre()
        );
    }
    
    //datos propios para la prueba
    @Test
    void testAltaClinica() {
    	
    	controladorClinicas.altaClinica(
    			"Clinica Nueva",
    			"Clinica de prueba",
    			"Direccion de prueba",
    			"https://www.clinicanueva.com",
    			300.0f,
    			1000.0f
    			);
    	
    	DTClinica clinica =
    			controladorClinicas.obtenerDatosClinica(
    					"Clinica Nueva"
    					);
    	
    	assertNotNull(clinica);
    	
    	assertEquals(
    			"Clinica Nueva",
    			clinica.getNombre()
    			);
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
                "Cardiología - Lunes Test",
                "Atención cardiológica semanal",
                enums.Dia.LUNES,
                9,
                "Cardiología",
                "Hospital Británico"
        );

        DTDiaConsultaCompleto dia =
                controladorClinicas.obtenerDatosDiaConsulta(
                        "Cardiología - Lunes Test"
                );

        assertNotNull(dia);

        assertEquals(
                "Cardiología - Lunes Test",
                dia.getNombre()
        );
    }

    
    @Test
    void testListarDiasConsulta() {
    	
    	Set<DTDiaConsulta> dias =
    			controladorClinicas.listarDiasConsulta();
    	
    	assertNotNull(dias);
    	
    	assertTrue(
    			dias.stream()
    			.anyMatch(d ->
    			d.getNombre().equals(
    					"Cardiología - Lunes"
    					)
    					)
    			);
    	
    	assertTrue(
    			dias.stream()
    			.anyMatch(d ->
    			d.getNombre().equals(
    					"Pediatría - Martes"
    					)
    					)
    			);
    }

    @Test
    void testObtenerDatosDiaConsulta() {

        DTDiaConsultaCompleto dia =
                controladorClinicas.obtenerDatosDiaConsulta(
                        "Cardiología - Lunes"
                );

        assertNotNull(dia);

        assertEquals(
                "Cardiología - Lunes",
                dia.getNombre()
        );
    }
    
    @Test
    void testAltaConsulta() {

        controladorClinicas.altaConsulta(
                "Consulta Test",
                new datatypes.DTFecha(17, 8, 2026),
                301,
                3,
                "Cardiología - Lunes",
                "ana.perez"
        );

        DTConsulta consulta =
                controladorClinicas.seleccionarConsulta(
                        "Consulta Test"
                );

        assertNotNull(consulta);

        assertEquals(
                "Consulta Test",
                consulta.getNombreConsulta()
        );
    }
    
    @Test
    void testListarConsultasDia() {

        Set<DTConsultaSoloConsulta> consultas =
                controladorClinicas.listarConsultasDia(
                        "Cardiología - Lunes"
                );

        assertNotNull(consultas);
    }


    @Test
    void testListarConsultas() {

        // US01 = ana.perez
        // Es médico y tiene consultas.

        datatypes.DTUsuario usuario =
                new datatypes.DTUsuario(
                        "ana.perez",
                        "Ana",
                        "Pérez",
                        "ana.perez@tuclinica.com"
                );

        Set<DTConsultaSoloConsulta> consultas =
                controladorClinicas.listarConsultas(usuario);

        assertNotNull(consultas);
    }


    @Test
    void testSeleccionarDiaConsulta() {

        Set<DTConsulta> consultas =
                controladorClinicas.seleccionarDiaConsulta(
                        "Cardiología - Lunes"
                );

        assertNotNull(consultas);
    }


    @Test
    void testSeleccionarConsulta() {

        DTConsulta consulta =
                controladorClinicas.seleccionarConsulta(
                        "Cardiología 240826"
                );

        assertNotNull(consulta);

        assertEquals(
                "Cardiología 240826",
                consulta.getNombreConsulta()
        );
    }
}