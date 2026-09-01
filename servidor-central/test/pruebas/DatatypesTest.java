package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import datatypes.DTBeneficioAnual;
import datatypes.DTClinica;
import datatypes.DTConsulta;
import datatypes.DTConsultaCompleta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDatosConsulta;
import datatypes.DTDiaConsulta;
import datatypes.DTDiaConsultaCompleto;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;
import datatypes.DTMedico;
import datatypes.DTPaciente;
import datatypes.DTReserva;
import datatypes.DTReservaConsulta;
import datatypes.DTReservaDetallada;
import datatypes.DTUsuario;
import enums.Dia;

/**
 * Pruebas de los datatypes (DTOs). No prueban lógica de negocio, solo que
 * cada constructor y getter/toString/equals/hashCode funcionen como se
 * espera. Sirven principalmente para subir el cubrimiento de la capa de
 * datatypes, que al ser solo getters queda con muchas líneas sin ejecutar
 * si ningún otro test llega a usarlas.
 */
public class DatatypesTest {

    @Test
    void testDTFecha() {
        DTFecha fecha = new DTFecha(24, 8, 2026);

        assertEquals(24, fecha.getDia());
        assertEquals(8, fecha.getMes());
        assertEquals(2026, fecha.getAnio());
        assertEquals("24/08/2026", fecha.toString());

        DTFecha desdeLocalDate = DTFecha.from(fecha.toLocalDate());
        assertEquals(fecha, desdeLocalDate);
        assertEquals(fecha.hashCode(), desdeLocalDate.hashCode());

        DTFecha otraFecha = new DTFecha(1, 1, 2027);
        assertTrue(fecha.compareTo(otraFecha) < 0);
        assertTrue(otraFecha.compareTo(fecha) > 0);
        assertEquals(0, fecha.compareTo(desdeLocalDate));

        assertNotEquals(fecha, otraFecha);
        assertNotEquals(fecha, null);
        assertNotEquals(fecha, "24/08/2026");
        assertEquals(fecha, fecha);
    }

    @Test
    void testDTEspecialidad() {
        DTEspecialidad especialidad = new DTEspecialidad("Cardiología", 20);

        assertEquals("Cardiología", especialidad.getNombre());
        assertEquals(20, especialidad.getPorcentajeCostoConsultaAdicional());
    }

    @Test
    void testDTClinica() {
        DTClinica clinica = new DTClinica(
                "Hospital Británico",
                "Hospital general privado",
                "Av. Italia 2420",
                "https://www.hospitalbritanico.org.uy/",
                350f,
                1200f
        );

        assertEquals("Hospital Británico", clinica.getNombre());
        assertEquals("Hospital general privado", clinica.getDescripcion());
        assertEquals("Av. Italia 2420", clinica.getDireccion());
        assertEquals("https://www.hospitalbritanico.org.uy/", clinica.getSitioWeb());
        assertEquals(350f, clinica.getCostoMedicamento());
        assertEquals(1200f, clinica.getCostoConsulta());
    }

    @Test
    void testDTUsuario() {
        DTUsuario usuario = new DTUsuario("ana.perez", "Ana", "Pérez", "ana.perez@tuclinica.com");

        assertEquals("ana.perez", usuario.getNickname());
        assertEquals("Ana", usuario.getNombre());
        assertEquals("Pérez", usuario.getApellido());
        assertEquals("ana.perez@tuclinica.com", usuario.getCorreo());
        assertEquals("Usuario", usuario.getTipo());
        assertEquals("ana.perez", usuario.toString());
    }

    @Test
    void testDTPaciente() {
        DTFecha nacimiento = new DTFecha(12, 4, 1990);
        DTFecha ingreso = new DTFecha(1, 3, 2024);
        Set<String> antecedentes = new HashSet<>();
        antecedentes.add("Sin antecedentes relevantes.");

        DTPaciente paciente = new DTPaciente(
                "lgomez", "Laura", "Gómez", "laura.gomez@gmail.com",
                nacimiento, 45678901, ingreso, antecedentes
        );

        assertEquals("lgomez", paciente.getNickname());
        assertEquals(nacimiento, paciente.getFechaNacimiento());
        assertEquals(nacimiento, paciente.getFecNacimiento());
        assertEquals(45678901, paciente.getDocumento());
        assertEquals(ingreso, paciente.getFechaIngreso());
        assertEquals(antecedentes, paciente.getAntecedentesMedicos());
        assertEquals(antecedentes, paciente.getAntecedentesMed());
        assertEquals("Paciente", paciente.getTipo());

        // constructor reducido (datos propios de la prueba)
        DTPaciente pacienteMinimo = new DTPaciente(
                "prueba.min", "Prueba", "Minima", "prueba.min@gmail.com", nacimiento
        );
        assertEquals(0, pacienteMinimo.getDocumento());
        assertTrue(pacienteMinimo.getAntecedentesMed().isEmpty());
    }

    @Test
    void testDTMedico() {
        Set<DTEspecialidad> especialidades = new HashSet<>();
        especialidades.add(new DTEspecialidad("Cardiología", 20));

        DTMedico medico = new DTMedico(
                "ana.perez", "Ana", "Pérez", "ana.perez@tuclinica.com",
                1001, "Enero", "https://www.hospitalbritanico.org.uy/",
                especialidades, 15
        );

        assertEquals(1001, medico.getRegistro());
        assertEquals("Enero", medico.getMesLicencia());
        assertEquals("https://www.hospitalbritanico.org.uy/", medico.getSitioWeb());
        assertEquals(especialidades, medico.getEspecialidades());
        assertEquals(15, medico.getPorcentajeConsultas());
        assertEquals(15, medico.getPorcentajeCostoConsulta());
        assertEquals("Medico", medico.getTipo());
    }

    @Test
    void testDTConsultaSoloConsulta() {
        DTFecha fecha = new DTFecha(24, 8, 2026);

        DTConsultaSoloConsulta minima =
                new DTConsultaSoloConsulta("Cardiología 240826", fecha, 101, 3);

        assertEquals("Cardiología 240826", minima.getNombreConsulta());
        assertEquals(fecha, minima.getFecha());
        assertEquals(101, minima.getNroSala());
        assertEquals(3, minima.getCantidadNumeros());
        assertEquals("", minima.getNombreMedico());
        assertEquals(0f, minima.getCosto());

        DTConsultaSoloConsulta completa = new DTConsultaSoloConsulta(
                "Cardiología 240826", fecha, 101, 3, "ana.perez", 1656f
        );

        assertEquals("ana.perez", completa.getNombreMedico());
        assertEquals(1656f, completa.getCosto());
    }

    @Test
    void testDTConsulta() {
        DTFecha fecha = new DTFecha(24, 8, 2026);

        DTConsulta minima = new DTConsulta("Cardiología 240826", fecha, 101, 3);
        assertNotNull(minima.getReservas());
        assertTrue(minima.getReservas().isEmpty());

        Set<DTReserva> reservas = new HashSet<>();
        reservas.add(new DTReserva("lgomez", fecha, 1, 1656f));

        DTConsulta completa = new DTConsulta(
                "Cardiología 240826", fecha, 101, 3, "ana.perez", 1656f, reservas
        );

        assertEquals("ana.perez", completa.getNombreMedico());
        assertEquals(1, completa.getReservas().size());
    }

    @Test
    void testDTReserva() {
        DTFecha fecha = new DTFecha(1, 8, 2026);

        DTReserva sinNombre = new DTReserva(fecha, 1, 1656f);
        assertEquals("Reserva", sinNombre.toString().split(" - ")[0]);

        DTReserva conNombre = new DTReserva("lgomez", fecha, 1, 1656f);
        assertEquals("lgomez", conNombre.getNickPaciente());
        assertEquals(fecha, conNombre.getFecha());
        assertEquals(1, conNombre.getNumeroCliente());
        assertEquals(1656f, conNombre.getCostoReserva());
        assertTrue(conNombre.toString().startsWith("lgomez"));
    }

    @Test
    void testDTReservaConsulta() {
        DTFecha fecha = new DTFecha(1, 8, 2026);

        DTReservaConsulta reserva = new DTReservaConsulta(
                "Cardiología 240826", "lgomez", fecha, 1, 1656f
        );

        assertEquals("Cardiología 240826", reserva.getNombreConsulta());
        assertEquals("lgomez", reserva.getNickPaciente());
        assertEquals(fecha, reserva.getFecha());
        assertEquals(1, reserva.getNumeroCliente());
        assertEquals(1656f, reserva.getCostoReserva());
        assertTrue(reserva.toString().contains("Cardiología 240826"));

        DTReservaConsulta sinPaciente = new DTReservaConsulta(
                "Cardiología 240826", fecha, 1, 1656f
        );
        assertEquals("", sinPaciente.getNickPaciente());
    }

    @Test
    void testDTDiaConsulta() {
        DTDiaConsulta dia = new DTDiaConsulta("Cardiología - Lunes");
        DTDiaConsulta mismoDia = new DTDiaConsulta("Cardiología - Lunes");
        DTDiaConsulta otroDia = new DTDiaConsulta("Pediatría - Martes");

        assertEquals("Cardiología - Lunes", dia.getNombreDia());
        assertEquals("Cardiología - Lunes", dia.getNombre());
        assertEquals("Cardiología - Lunes", dia.toString());

        assertEquals(dia, mismoDia);
        assertEquals(dia.hashCode(), mismoDia.hashCode());
        assertNotEquals(dia, otroDia);
        assertNotEquals(dia, null);
        assertNotEquals(dia, "Cardiología - Lunes");
        assertEquals(dia, dia);
    }

    @Test
    void testDTDiaConsultaCompleto() {
        DTDiaConsultaCompleto minimo = new DTDiaConsultaCompleto(
                "Cardiología - Lunes", "Atención cardiológica semanal",
                Dia.LUNES, 9, 1440f
        );

        assertEquals("Cardiología - Lunes", minimo.getNombre());
        assertEquals(Dia.LUNES, minimo.getDia());
        assertEquals(9, minimo.getHoraInicio());
        assertEquals(1440f, minimo.getCostoConsulta());
        assertTrue(minimo.getConsultas().isEmpty());

        DTDiaConsultaCompleto completo = new DTDiaConsultaCompleto(
                "Cardiología - Lunes", "Atención cardiológica semanal",
                Dia.LUNES, 9, "Cardiología", "Hospital Británico", 1440f,
                Set.of()
        );

        assertEquals("Cardiología", completo.getEspecialidad());
        assertEquals("Hospital Británico", completo.getClinica());
    }

    @Test
    void testDTBeneficioAnual() {
        DTBeneficioAnual beneficio = new DTBeneficioAnual(2026, 4, 5);

        assertEquals(2026, beneficio.getAnio());
        assertEquals(4, beneficio.getTicketsGratis());
        assertEquals(5, beneficio.getConsultasGratis());
        assertEquals("2026", beneficio.toString());
    }

    // Los siguientes tres DTOs no los usa ningún Controlador todavía
    // (quedaron declarados pero sin conectar). Se prueban igual para
    // no dejar cubrimiento en 0%, pero conviene confirmar con el equipo
    // si van a usarse o se pueden borrar.

    @Test
    void testDTConsultaCompleta() {
        DTFecha fecha = new DTFecha(24, 8, 2026);

        DTConsultaCompleta consulta = new DTConsultaCompleta(
                "Cardiología 240826", "ana.perez", fecha, 101, 3, 1656f, Set.of()
        );

        assertEquals("Cardiología 240826", consulta.getNombreConsulta());
        assertEquals("ana.perez", consulta.getNombreMedico());
        assertEquals(fecha, consulta.getFecha());
        assertEquals(101, consulta.getNroSala());
        assertEquals(3, consulta.getCantidadNumeros());
        assertEquals(1656f, consulta.getCosto());
        assertNotNull(consulta.getReservas());
    }

    @Test
    void testDTReservaDetallada() {
        DTFecha fecha = new DTFecha(1, 8, 2026);

        DTReservaDetallada detalle = new DTReservaDetallada(
                fecha, 1, 1656f, "Cardiología 240826", "lgomez", 3
        );

        assertEquals(fecha, detalle.getFechaReserva());
        assertEquals(1, detalle.getNumeroClienteReserva());
        assertEquals(1656f, detalle.getCostoReserva());
        assertEquals("Cardiología 240826", detalle.getNombreConsulta());
        assertEquals("lgomez", detalle.getNicknamePaciente());
        assertEquals(3, detalle.getConsultasGratisBeneficioAnual());
    }

    @Test
    void testDTDatosConsulta() {
        DTDatosConsulta datos = new DTDatosConsulta(3, 1656f);

        assertEquals(3, datos.getNums());
        assertEquals(1656f, datos.getCost());
    }
}
