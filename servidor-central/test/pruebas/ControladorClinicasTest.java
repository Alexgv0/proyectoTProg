package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fabrica.Fabrica;
import interfaces.IControladorClinicas;

import java.util.Set;
import datatypes.DTClinica;
import datatypes.DTFecha;
import enums.Dia;
import datatypes.DTConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsulta;
import datatypes.DTDiaConsultaCompleto;


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

        Set<DTConsultaSoloConsulta> consultas = controladorClinicas.listarConsultas(usuario);

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
    
    @Test
    void testObtenerDatosClinicaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.obtenerDatosClinica("ClinicaInexistente")
        );

        assertEquals("La clinica no existe.", excepcion.getMessage());
    }

    @Test
    void testAltaClinicaNombreInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaClinica(
                "",
                "Descripcion",
                "Direccion",
                "https://sitio.com",
                100,
                200
            )
        );
    }

    @Test
    void testAltaClinicaDescripcionInvalida() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaClinica(
                "ClinicaTest",
                "",
                "Direccion",
                "https://sitio.com",
                100,
                200
            )
        );
    }

    @Test
    void testAltaClinicaDireccionInvalida() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaClinica(
                "ClinicaTest",
                "Descripcion",
                "",
                "https://sitio.com",
                100,
                200
            )
        );
    }

    @Test
    void testAltaClinicaCostoMedicamentoNegativo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaClinica(
                "ClinicaTest",
                "Descripcion",
                "Direccion",
                "https://sitio.com",
                -1,
                200
            )
        );
    }

    @Test
    void testAltaClinicaCostoConsultaNegativo() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaClinica(
                "ClinicaTest",
                "Descripcion",
                "Direccion",
                "https://sitio.com",
                100,
                -1
            )
        );
    }

    @Test
    void testAltaClinicaYaExistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaClinica(
                "CASMU",
                "Descripcion",
                "Direccion",
                "https://sitio.com",
                100,
                200
            )
        );

        assertEquals(
            "Ya existe una clinica con ese nombre.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaDiaConsultaNombreInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "",
                "Descripcion",
                Dia.LUNES,
                10,
                "Medicina General",
                "CASMU"
            )
        );
    }

    @Test
    void testAltaDiaConsultaDescripcionInvalida() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "DiaConsultaTest",
                "",
                Dia.LUNES,
                10,
                "Cardiologia",
                "Hospital Británico"
            )
        );
    }

    @Test
    void testAltaDiaConsultaDiaNulo() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "DiaConsultaTest",
                "Descripcion",
                null,
                10,
                "Medicina General",
                "CASMU"
            )
        );

        assertEquals(
            "Debe seleccionar un dia de la semana.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaDiaConsultaHoraInvalida() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "DiaConsultaTest",
                "Descripcion",
                Dia.LUNES,
                24,
                "Cardiología",
                "Hospital Británico"
            )
        );

        assertEquals(
            "La hora de inicio debe estar entre 0 y 23.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaDiaConsultaYaExistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "Cardiología - Lunes",
                "Descripcion",
                Dia.LUNES,
                10,
                "Cardiologia",
                "fsdf Británico"
            )
        );

        assertEquals(
            "Ya existe un dia de consulta con ese nombre.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaDiaConsultaClinicaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "DiaConsultaTest",
                "Descripcion",
                Dia.LUNES,
                10,
                "Cardiologia",
                "Hospital de Clinicas"
            )
        );

        assertEquals(
            "La clinica indicada no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaDiaConsultaEspecialidadInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaDiaConsulta(
                "DiaConsultaTest",
                "Descripcion",
                Dia.LUNES,
                10,
                "Ingeniero",
                "Hospital Británico"
            )
        );

        assertEquals(
            "La especialidad indicada no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testObtenerDatosDiaConsultaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.obtenerDatosDiaConsulta("DiaConsultaInexistente")
        );

        assertEquals(
            "El dia de consulta no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaNombreInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "",
                new DTFecha(24, 8, 2026),
                1,
                10,
                "Cardiología - Lunes",
                "bsilva"
            )
        );
    }

    @Test
    void testAltaConsultaFechaNula() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                null,
                1,
                10,
                "DiaConsultaExistente",
                "medicoExistente"
            )
        );

        assertEquals(
            "La fecha es obligatoria.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaNumeroSalaInvalido() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                0,
                10,
                "DiaConsultaExistente",
                "medicoExistente"
            )
        );
    }

    @Test
    void testAltaConsultaCantidadNumerosInvalida() {
        assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                1,
                0,
                "DiaConsultaExistente",
                "medicoExistente"
            )
        );
    }

    @Test
    void testAltaConsultaYaExistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "Cardiología 240826",
                new DTFecha(3, 9, 2026),
                1,
                10,
                "Cardiología - Jueves",
                "carla.rodriguez"
            )
        );

        assertEquals(
            "Ya existe una consulta con ese nombre.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaDiaConsultaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                1,
                10,
                "DiaConsultaInexistente",
                "medicoExistente"
            )
        );

        assertEquals(
            "El dia de consulta indicado no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaMedicoInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                1,
                10,
                "Medicina General Central - Miércoles",
                "not.medico"
            )
        );

        assertEquals(
            "El medico no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaMedicoSinEspecialidad() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                1,
                10,
                "Medicina General Central - Miércoles",
                "bsilva"
            )
        );

        assertEquals(
            "El medico no tiene la especialidad asociada al dia de consulta.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaFechaNoCorrespondeDia() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(25, 8, 2026),
                1,
                10,
                "Cardiología - Lunes",
                "ana.perez"
            )
        );

        assertEquals(
            "La fecha no corresponde al dia de la semana seleccionado.",
            excepcion.getMessage()
        );
    }

    @Test
    void testListarConsultasDiaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.listarConsultasDia("DiaConsultaInexistente")
        );

        assertEquals(
            "El dia de consulta no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testListarConsultasUsuarioNulo() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.listarConsultas(null)
        );

        assertEquals(
            "El usuario es obligatorio.",
            excepcion.getMessage()
        );
    }

    @Test
    void testSeleccionarDiaConsultaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.seleccionarDiaConsulta("DiaConsultaInexistente")
        );

        assertEquals(
            "El dia de consulta no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testSeleccionarConsultaInexistente() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.seleccionarConsulta("ConsultaInexistente")
        );

        assertEquals(
            "La consulta no existe.",
            excepcion.getMessage()
        );
    }
}