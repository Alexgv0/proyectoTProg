package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datatypes.*;
import enums.Dia;
import fabrica.Fabrica;
import interfaces.IControladorClinicas;
import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;

public class DatosPruebaTest {

    private IControladorUsuarios controladorUsuarios;
    private IControladorClinicas controladorClinicas;
    private IControladorReservas controladorReservas;

    @BeforeEach
    public void cargarDatos() {
        DatosPrueba.cargarTodosLosDatos();

        Fabrica fabrica = Fabrica.getInstancia();

        controladorUsuarios = fabrica.getControladorUsuarios();
        controladorClinicas = fabrica.getControladorClinicas();
        controladorReservas = fabrica.getControladorReservas();
    }
    
    @Test
    public void testCargaEspecialidades() {

        DTEspecialidad es01 = controladorUsuarios.obtenerDatosEspecialidad("Cardiología");

        assertNotNull(es01);
        assertEquals("Cardiología", es01.getNombre());
        assertEquals(20, es01.getPorcentajeCostoConsultaAdicional());


        DTEspecialidad es02 = controladorUsuarios.obtenerDatosEspecialidad("Pediatría");

        assertNotNull(es02);
        assertEquals("Pediatría", es02.getNombre());
        assertEquals(10, es02.getPorcentajeCostoConsultaAdicional());


        DTEspecialidad es03 = controladorUsuarios.obtenerDatosEspecialidad("Medicina General");

        assertNotNull(es03);
        assertEquals("Medicina General", es03.getNombre());
        assertEquals(0, es03.getPorcentajeCostoConsultaAdicional());
    }
    
    @Test
    public void testCargaPacientes() {

        Set<String> pacientes = controladorUsuarios.listarPacientes();

        assertTrue(pacientes.contains("lgomez"));
        assertTrue(pacientes.contains("mar.fernandez"));
        assertTrue(pacientes.contains("s.lopez"));


        DTUsuario laura = controladorUsuarios.seleccionarUsuario("lgomez");

        assertNotNull(laura);
        assertEquals("lgomez", laura.getNickname());
        assertEquals("Laura", laura.getNombre());
        assertEquals("Gómez", laura.getApellido());
        assertEquals("laura.gomez@gmail.com", laura.getCorreo());


        DTUsuario mario = controladorUsuarios.seleccionarUsuario("mar.fernandez");

        assertNotNull(mario);
        assertEquals("mar.fernandez", mario.getNickname());
        assertEquals("Mario", mario.getNombre());
        assertEquals("Fernández", mario.getApellido());
        assertEquals("mario.fernandez@hotmail.com", mario.getCorreo());


        DTUsuario sofia = controladorUsuarios.seleccionarUsuario("s.lopez");

        assertNotNull(sofia);
        assertEquals("s.lopez", sofia.getNickname());
        assertEquals("Sofía", sofia.getNombre());
        assertEquals("López", sofia.getApellido());
        assertEquals("sofia.lopez@gmail.com", sofia.getCorreo());
    }
    
    @Test
    public void testCargaMedicos() {

        Set<String> medicos = controladorUsuarios.listarMedicos();

        assertTrue(medicos.contains("ana.perez"));
        assertTrue(medicos.contains("bsilva"));
        assertTrue(medicos.contains("carla.rodriguez"));


        DTUsuario ana = controladorUsuarios.seleccionarUsuario("ana.perez");

        assertNotNull(ana);
        assertEquals("ana.perez", ana.getNickname());
        assertEquals("Ana", ana.getNombre());
        assertEquals("Pérez", ana.getApellido());
        assertEquals("ana.perez@tuclinica.com", ana.getCorreo());


        DTUsuario bruno = controladorUsuarios.seleccionarUsuario("bsilva");

        assertNotNull(bruno);
        assertEquals("bsilva", bruno.getNickname());
        assertEquals("Bruno", bruno.getNombre());
        assertEquals("Silva", bruno.getApellido());
        assertEquals("bruno.silva@hospital.com", bruno.getCorreo());


        DTUsuario carla = controladorUsuarios.seleccionarUsuario("carla.rodriguez");

        assertNotNull(carla);
        assertEquals("carla.rodriguez", carla.getNickname());
        assertEquals("Carla", carla.getNombre());
        assertEquals("Rodríguez", carla.getApellido());
        assertEquals("carla.rodriguez@hospital.com", carla.getCorreo());
    }
    
    @Test
    public void testCargaClinicas() {

        DTClinica clinica1 = controladorClinicas.obtenerDatosClinica("Hospital Británico");

        assertNotNull(clinica1);
        assertEquals("Hospital Británico", clinica1.getNombre());
        assertEquals("Hospital general privado", clinica1.getDescripcion());
        assertEquals("Av. Italia 2420", clinica1.getDireccion());
        assertEquals("https://www.hospitalbritanico.org.uy/", clinica1.getSitioWeb());
        assertEquals(350f, clinica1.getCostoMedicamento());
        assertEquals(1200f, clinica1.getCostoConsulta());


        DTClinica clinica2 = controladorClinicas.obtenerDatosClinica("CASMU");

        assertNotNull(clinica2);
        assertEquals("CASMU", clinica2.getNombre());
        assertEquals("Institución de asistencia médica", clinica2.getDescripcion());
        assertEquals("Av. 8 de Octubre 3310", clinica2.getDireccion());
        assertEquals("https://www.casmu.com.uy/", clinica2.getSitioWeb());
        assertEquals(200f, clinica2.getCostoMedicamento());
        assertEquals(950f, clinica2.getCostoConsulta());
    }
    
    @Test
    public void testCargaDiasConsulta() {

        DTDiaConsultaCompleto dc01 = controladorClinicas.obtenerDatosDiaConsulta("Cardiología - Lunes");

        assertNotNull(dc01);
        assertEquals("Cardiología - Lunes", dc01.getNombre());
        assertEquals("Atención cardiológica semanal", dc01.getDescripcion());
        assertEquals(Dia.LUNES, dc01.getDia());
        assertEquals(9, dc01.getHoraInicio());
        assertEquals("Cardiología" , dc01.getEspecialidad());
        assertEquals("Hospital Británico", dc01.getClinica());
        assertEquals(1440, dc01.getCostoConsulta());

        DTDiaConsultaCompleto dc02 = controladorClinicas.obtenerDatosDiaConsulta("Pediatría - Martes");

        assertNotNull(dc02);
        assertEquals("Pediatría - Martes", dc02.getNombre());
        assertEquals("Atención pediátrica semanal", dc02.getDescripcion());
        assertEquals(Dia.MARTES, dc02.getDia());
        assertEquals(14, dc02.getHoraInicio());
        assertEquals("Pediatría" , dc02.getEspecialidad());
        assertEquals("CASMU", dc02.getClinica());
        assertEquals(1045, dc02.getCostoConsulta());

        DTDiaConsultaCompleto dc03 = controladorClinicas.obtenerDatosDiaConsulta("Medicina General Central - Miércoles");

        assertNotNull(dc03);
        assertEquals("Medicina General Central - Miércoles", dc03.getNombre());
        assertEquals("Consulta general semanal", dc03.getDescripcion());
        assertEquals(Dia.MIERCOLES, dc03.getDia());
        assertEquals(10, dc03.getHoraInicio());
        assertEquals("Medicina General" , dc03.getEspecialidad());
        assertEquals("Hospital Británico", dc03.getClinica());
        assertEquals(1200, dc03.getCostoConsulta());

        DTDiaConsultaCompleto dc04 = controladorClinicas.obtenerDatosDiaConsulta("Cardiología - Jueves");

        assertNotNull(dc04);
        assertEquals("Cardiología - Jueves", dc04.getNombre());
        assertEquals("Atención cardiológica semanal", dc04.getDescripcion());
        assertEquals(Dia.JUEVES, dc04.getDia());
        assertEquals(16, dc04.getHoraInicio());
        assertEquals("Cardiología" , dc04.getEspecialidad());
        assertEquals("CASMU", dc04.getClinica());
        assertEquals(1140, dc04.getCostoConsulta());
    }
    
    @Test
    public void testCargaConsultas() {

        DTConsulta co01 = controladorClinicas.seleccionarConsulta("Cardiología 240826");

        assertNotNull(co01);

        DTConsulta co02 = controladorClinicas.seleccionarConsulta("Pediatría 080926");

        assertNotNull(co02);

        DTConsulta co03 = controladorClinicas.seleccionarConsulta("Medicina General 090926");

        assertNotNull(co03);

        DTConsulta co04 = controladorClinicas.seleccionarConsulta("Cardiología 130826");

        assertNotNull(co04);

        DTConsulta co05 = controladorClinicas.seleccionarConsulta("Cardiología 140926");

        assertNotNull(co05);
    }
    
    @Test
    public void testCargaReservas() {

        DTReservaConsulta rc01 = controladorReservas.seleccionarReservaConsulta(
        		"lgomez",
        		"Cardiología 240826"
        		);

        assertNotNull(rc01);
        assertEquals(new DTFecha(1, 8, 2026), rc01.getFecha());
        assertEquals(1, rc01.getNumeroCliente());
        assertEquals("lgomez", rc01.getNickPaciente());
        assertEquals("Cardiología 240826", rc01.getNombreConsulta());
        assertEquals(1656, rc01.getCostoReserva());

        DTReservaConsulta rc02 = controladorReservas.seleccionarReservaConsulta(
        		"mar.fernandez",
        		"Cardiología 240826"
        		);

        assertNotNull(rc02);
        assertEquals(new DTFecha(27, 7, 2026), rc02.getFecha());
        assertEquals(2, rc02.getNumeroCliente());
        assertEquals("mar.fernandez", rc02.getNickPaciente());
        assertEquals("Cardiología 240826", rc02.getNombreConsulta());
        assertEquals(1324.8, rc02.getCostoReserva());



        DTReservaConsulta rc03 = controladorReservas.seleccionarReservaConsulta(
        		"s.lopez",
        		"Pediatría 080926"
        		);

        assertNotNull(rc03);
        assertEquals(new DTFecha(27, 8, 2026), rc03.getFecha());
        assertEquals(1, rc03.getNumeroCliente());
        assertEquals("s.lopez", rc03.getNickPaciente());
        assertEquals("Pediatría 080926", rc03.getNombreConsulta());
        assertEquals(1097.25, rc03.getCostoReserva());



        DTReservaConsulta rc04 = controladorReservas.seleccionarReservaConsulta(
        		"lgomez",
        		"Pediatría 080926"
        		);

        assertNotNull(rc04);
        assertEquals(new DTFecha(28, 8, 2026), rc04.getFecha());
        assertEquals(2, rc04.getNumeroCliente());
        assertEquals("lgomez", rc04.getNickPaciente());
        assertEquals("Pediatría 080926", rc04.getNombreConsulta());
        assertEquals(1097.25, rc04.getCostoReserva());



        DTReservaConsulta rc05 = controladorReservas.seleccionarReservaConsulta(
        		"mar.fernandez",
        		"Medicina General 090926"
        		);

        assertNotNull(rc05);
        assertEquals(new DTFecha(29, 8, 2026), rc05.getFecha());
        assertEquals(1, rc05.getNumeroCliente());
        assertEquals("mar.fernandez", rc05.getNickPaciente());
        assertEquals("Medicina General 090926", rc05.getNombreConsulta());
        assertEquals(1056, rc05.getCostoReserva());

    }
    
    @Test
    public void testCargaBeneficiosAnuales() {

        DTBeneficioAnual ba01 = controladorUsuarios.seleccionarBeneficioAnual("lgomez", 2025);

        assertNotNull(ba01);
        assertEquals(2025, ba01.getAnio());
        assertEquals(2, ba01.getConsultasGratis());
        assertEquals(3, ba01.getTicketsGratis());


        DTBeneficioAnual ba02 = controladorUsuarios.seleccionarBeneficioAnual("lgomez", 2026);

        assertNotNull(ba02);
        assertEquals(2026, ba02.getAnio());
        assertEquals(3, ba02.getConsultasGratis());
        assertEquals(4, ba02.getTicketsGratis());


        DTBeneficioAnual ba03 = controladorUsuarios.seleccionarBeneficioAnual("mar.fernandez", 2026);

        assertNotNull(ba03);
        assertEquals(2026, ba03.getAnio());
        assertEquals(4, ba03.getConsultasGratis());
        assertEquals(5, ba03.getTicketsGratis());


        DTBeneficioAnual ba04 = controladorUsuarios.seleccionarBeneficioAnual("s.lopez", 2026);

        assertNotNull(ba04);
        assertEquals(2026, ba04.getAnio());
        assertEquals(2, ba04.getConsultasGratis());
        assertEquals(2, ba04.getTicketsGratis());
    }
    
    
}