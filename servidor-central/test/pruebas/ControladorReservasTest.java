package pruebas;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import datatypes.DTFecha;
import datatypes.DTReservaConsulta;
import fabrica.Fabrica;
import interfaces.IControladorReservas;

public class ControladorReservasTest {

	private static IControladorReservas controlador;

	@BeforeAll
	static void iniciar() {

        Fabrica fabrica = Fabrica.getInstancia();

        controlador = fabrica.getControladorReservas();

        DatosPrueba.cargarTodosLosDatos();
    }

	@Test
	void testListarReservasConsultas() {

	    // RC01 y RC04: lgomez tiene reserva en Cardiología 240826 y en Pediatría 080926
	    Set<DTReservaConsulta> reservasLGomez =
	        controlador.listarReservasConsultas("lgomez");

	    assertNotNull(reservasLGomez);

	    assertTrue(
	        reservasLGomez.stream()
	            .anyMatch(r -> r.getNombreConsulta().equals("Cardiología 240826"))
	    );
	    assertTrue(
	        reservasLGomez.stream()
	            .anyMatch(r -> r.getNombreConsulta().equals("Pediatría 080926"))
	    );

	    // RC02 y RC05: mar.fernandez tiene reserva en Cardiología 240826 y en Medicina General 090926
	    Set<DTReservaConsulta> reservasMarFernandez =
	        controlador.listarReservasConsultas("mar.fernandez");

	    assertNotNull(reservasMarFernandez);

	    assertTrue(
	        reservasMarFernandez.stream()
	            .anyMatch(r -> r.getNombreConsulta().equals("Cardiología 240826"))
	    );
	    assertTrue(
	        reservasMarFernandez.stream()
	            .anyMatch(r -> r.getNombreConsulta().equals("Medicina General 090926"))
	    );

	    // RC03: s.lopez tiene reserva en Pediatría 080926
	    Set<DTReservaConsulta> reservasSLopez =
	        controlador.listarReservasConsultas("s.lopez");

	    assertNotNull(reservasSLopez);

	    assertTrue(
	        reservasSLopez.stream()
	            .anyMatch(r -> r.getNombreConsulta().equals("Pediatría 080926"))
	    );
	}

	@Test
	void testSeleccionarReservaConsulta() {

	    // RC01
	    DTReservaConsulta reserva1 = controlador.seleccionarReservaConsulta("lgomez", "Cardiología 240826");

	    assertNotNull(reserva1);
	    assertEquals("Cardiología 240826", reserva1.getNombreConsulta());
	    assertEquals("lgomez", reserva1.getNickPaciente());

	    // RC02
	    DTReservaConsulta reserva2 = controlador.seleccionarReservaConsulta("mar.fernandez", "Cardiología 240826");

	    assertNotNull(reserva2);
	    assertEquals("Cardiología 240826", reserva2.getNombreConsulta());
	    assertEquals("mar.fernandez", reserva2.getNickPaciente());

	    // RC03
	    DTReservaConsulta reserva3 = controlador.seleccionarReservaConsulta("s.lopez", "Pediatría 080926");

	    assertNotNull(reserva3);
	    assertEquals("Pediatría 080926", reserva3.getNombreConsulta());
	    assertEquals("s.lopez", reserva3.getNickPaciente());

	    // RC04
	    DTReservaConsulta reserva4 = controlador.seleccionarReservaConsulta("lgomez", "Pediatría 080926");

	    assertNotNull(reserva4);
	    assertEquals("Pediatría 080926", reserva4.getNombreConsulta());
	    assertEquals("lgomez", reserva4.getNickPaciente());

	    // RC05
	    DTReservaConsulta reserva5 = controlador.seleccionarReservaConsulta("mar.fernandez", "Medicina General 090926");

	    assertNotNull(reserva5);
	    assertEquals("Medicina General 090926", reserva5.getNombreConsulta());
	    assertEquals("mar.fernandez", reserva5.getNickPaciente());
	}

	@Test
	void testAltaReservaConsulta1() {

	    // s.lopez todavia no tiene reserva en Cardiología 240826 (le queda 1 numero libre: 3 cupos, 2 ocupados)
	    String nickPaciente = "s.lopez";
	    String nombreConsulta = "Cardiología 240826";

	    controlador.altaReservaConsulta(nickPaciente, nombreConsulta);

	    DTReservaConsulta res = controlador.seleccionarReservaConsulta(nickPaciente, nombreConsulta);

	    assertNotNull(res);

	    assertEquals(nickPaciente, res.getNickPaciente());
	    assertEquals(nombreConsulta, res.getNombreConsulta());
	}

	@Test
	public void testAltaReservaConsulta2() {

	    // lgomez todavia no tiene reserva en Cardiología 130826 (2 cupos, 0 ocupados)
	    String nickPaciente = "lgomez";
	    String nombreConsulta = "Cardiología 130826";
	    DTFecha fecha = UtilidadesTesting.ingresarFecha("01/08/2026");

	    controlador.altaReservaConsulta(
	        nickPaciente,
	        nombreConsulta,
	        fecha
	    );

	    DTReservaConsulta res =
	        controlador.seleccionarReservaConsulta(
	            nickPaciente,
	            nombreConsulta
	        );

	    assertNotNull(res);

	    assertEquals(nickPaciente, res.getNickPaciente());
	    assertEquals(nombreConsulta, res.getNombreConsulta());
	}
	
	
	@Test
	void testListarReservasConsultasPacienteInexistente() {

	    IllegalArgumentException excepcion = assertThrows(
	        IllegalArgumentException.class,
	        () -> controlador.listarReservasConsultas("pacienteInexistente")
	    );

	    assertEquals("El paciente no existe.", excepcion.getMessage());
	}
	
	@Test
	void testSeleccionarReservaConsultaPacienteInexistente() {

	    IllegalArgumentException excepcion = assertThrows(
	        IllegalArgumentException.class,
	        () -> controlador.seleccionarReservaConsulta(
	            "pacienteInexistente",
	            "Cardiología 240826"
	        )
	    );

	    assertEquals("El paciente no existe.", excepcion.getMessage());
	}
	
	@Test
	void testSeleccionarReservaConsultaReservaInexistente() {

	    IllegalArgumentException excepcion = assertThrows(
	        IllegalArgumentException.class,
	        () -> controlador.seleccionarReservaConsulta(
	            "lgomez",
	            "ReservaInexistente"
	        )
	    );

	    assertEquals("La reserva no existe.", excepcion.getMessage());
	}
	
	@Test
	void testAltaReservaConsultaPacienteInexistente() {

	    IllegalArgumentException excepcion = assertThrows(
	        IllegalArgumentException.class,
	        () -> controlador.altaReservaConsulta(
	            "pacienteInexistente",
	            "Cardiología 240826",
	            new DTFecha(24, 8, 2026)
	        )
	    );

	    assertEquals("El paciente no existe.", excepcion.getMessage());
	}
	
	@Test
	void testAltaReservaConsultaConsultaInexistente() {

	    IllegalArgumentException excepcion = assertThrows(
	        IllegalArgumentException.class,
	        () -> controlador.altaReservaConsulta(
	            "lgomez",
	            "ConsultaInexistente",
	            new DTFecha(24, 8, 2026)
	        )
	    );

	    assertEquals("La consulta no existe.", excepcion.getMessage());
	}
	
	@Test
	void testAltaReservaConsultaFechaNula() {

	    IllegalArgumentException excepcion = assertThrows(
	        IllegalArgumentException.class,
	        () -> controlador.altaReservaConsulta(
	            "lgomez",
	            "Cardiología 240826",
	            null
	        )
	    );

	    assertEquals("La fecha de reserva es obligatoria.", excepcion.getMessage());
	}
	
	@Test
	void testAltaReservaConsultaReservaDuplicada() {

	    IllegalStateException excepcion = assertThrows(
	        IllegalStateException.class,
	        () -> controlador.altaReservaConsulta(
	            "lgomez",
	            "Cardiología 240826",
	            new DTFecha(24, 8, 2026)
	        )
	    );

	    assertEquals(
	        "El paciente ya tiene una reserva para esta consulta.",
	        excepcion.getMessage()
	    );
	}
	
	@Test
	void testAltaReservaConsultaConsultaCompleta() {

	    IllegalStateException excepcion = assertThrows(
	        IllegalStateException.class,
	        () -> controlador.altaReservaConsulta(
	            "s.lopez",
	            "NOMBRE_DE_CONSULTA_COMPLETA",
	            new DTFecha(8, 9, 2026)
	        )
	    );

	    assertEquals(
	        "La consulta no tiene numeros disponibles.",
	        excepcion.getMessage()
	    );
	}

}
