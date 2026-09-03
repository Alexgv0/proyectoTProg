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

        assertEquals("Hospital Británico", clinica.getNombre());
        assertEquals("Hospital general privado", clinica.getDescripcion());
        assertEquals("Av. Italia 2420", clinica.getDireccion());
        assertEquals("https://www.hospitalbritanico.org.uy/", clinica.getSitioWeb());
        assertEquals(350, clinica.getCostoMedicamento());
        assertEquals(1200, clinica.getCostoConsulta());
        
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

        // CO01 y CO05 (oficiales) están asociadas al día "Cardiología - Lunes"
        Set<DTConsultaSoloConsulta> consultas =
                controladorClinicas.listarConsultasDia(
                        "Cardiología - Lunes"
                );

        assertNotNull(consultas);

        assertTrue(
                consultas.stream()
                        .anyMatch(c -> c.getNombreConsulta().equals("Cardiología 240826"))
        );
        assertTrue(
                consultas.stream()
                        .anyMatch(c -> c.getNombreConsulta().equals("Cardiología 140926"))
        );
        assertTrue(
                consultas.stream()
                        .allMatch(c -> c.getNombreMedico().equals("ana.perez"))
        );
    }


    @Test
    void testListarConsultas() {

        // US01 = ana.perez
        // Es médico y tiene las consultas CO01 y CO05 (oficiales).

        datatypes.DTUsuario usuario =
                new datatypes.DTUsuario(
                        "ana.perez",
                        "Ana",
                        "Pérez",
                        "ana.perez@tuclinica.com"
                );

        Set<DTConsultaSoloConsulta> consultas = controladorClinicas.listarConsultas(usuario);

        assertNotNull(consultas);

        assertTrue(
                consultas.stream()
                        .anyMatch(c -> c.getNombreConsulta().equals("Cardiología 240826"))
        );
        assertTrue(
                consultas.stream()
                        .anyMatch(c -> c.getNombreConsulta().equals("Cardiología 140926"))
        );
    }


    @Test
    void testSeleccionarDiaConsulta() {

        Set<DTConsulta> consultas =
                controladorClinicas.seleccionarDiaConsulta(
                        "Cardiología - Lunes"
                );

        assertNotNull(consultas);

        DTConsulta co01 = consultas.stream()
                .filter(c -> c.getNombreConsulta().equals("Cardiología 240826"))
                .findFirst()
                .orElse(null);

        assertNotNull(co01);
        assertEquals(101, co01.getNroSala());
        assertEquals(3, co01.getCantidadNumeros());
        assertEquals("ana.perez", co01.getNombreMedico());
        assertEquals(1656f, co01.getCosto());
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
            () -> controladorClinicas.obtenerDatosClinica("ASSE.Test")
        );

        assertEquals("La clinica no existe.", excepcion.getMessage());
    }

    @Test
    void testAltaClinicaNombreInvalido() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo nombre es obligatorio.", excepcion.getMessage());
    }

    @Test
    void testAltaClinicaDescripcionInvalida() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo descripcion es obligatorio.", excepcion.getMessage());
    }

    @Test
    void testAltaClinicaDireccionInvalida() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo direccion es obligatorio.", excepcion.getMessage());
    }

    @Test
    void testAltaClinicaCostoMedicamentoNegativo() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo costo del ticket debe ser un numero no negativo.", excepcion.getMessage());
    }

    @Test
    void testAltaClinicaCostoConsultaNegativo() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo costo de la orden debe ser un numero no negativo.", excepcion.getMessage());
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
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo nombre es obligatorio.", excepcion.getMessage());
    }

    @Test
    void testAltaDiaConsultaDescripcionInvalida() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo descripcion es obligatorio.", excepcion.getMessage());
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
                "Hospital Británico"
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
                "Neurocirujano",
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
            () -> controladorClinicas.obtenerDatosDiaConsulta("Sabado - Neurocirujano")
        );

        assertEquals(
            "El dia de consulta no existe.",
            excepcion.getMessage()
        );
    }

    @Test
    void testAltaConsultaNombreInvalido() {
        IllegalArgumentException excepcion = assertThrows(
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

        assertEquals("El campo nombre es obligatorio.", excepcion.getMessage());
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
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                0,
                10,
                "Cardiología - Lunes",
                "ana.perez"
            )
        );

        assertEquals("El campo numero de sala debe ser mayor que cero.", excepcion.getMessage());
    }

    @Test
    void testAltaConsultaCantidadNumerosInvalida() {
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> controladorClinicas.altaConsulta(
                "ConsultaTest",
                new DTFecha(24, 8, 2026),
                1,
                0,
                "Cardiología - Lunes",
                "ana.perez"
            )
        );

        assertEquals("El campo cantidad de numeros debe ser mayor que cero.", excepcion.getMessage());
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
                "Lunes - Neurocirugia",
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
            () -> controladorClinicas.listarConsultasDia("Martes - Neurocirugia")
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
            () -> controladorClinicas.seleccionarDiaConsulta("Miercoles - Cirugia General")
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
            () -> controladorClinicas.seleccionarConsulta("Cirugia General 11188")
        );

        assertEquals(
            "La consulta no existe.",
            excepcion.getMessage()
        );
    }
}