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

	    String nickPaciente = "";

	    Set<DTReservaConsulta> res =
	        controlador.listarReservasConsultas(nickPaciente);

	    assertNotNull(res);

	    boolean existeLGomez = false;
	    boolean existeMarFernandez = false;
	    boolean existeSLopez = false;

	    for (DTReservaConsulta reserva : res) {
	        if (reserva.getNickPaciente().equals("lgomez")) {
	            existeLGomez = true;
	        }

	        if (reserva.getNickPaciente().equals("mar.fernandez")) {
	            existeMarFernandez = true;
	        }

	        if (reserva.getNickPaciente().equals("s.lopez")) {
	            existeSLopez = true;
	        }
	    }

	    assertTrue(existeLGomez);
	    assertTrue(existeMarFernandez);
	    assertTrue(existeSLopez);
	}
	
	@Test
	void testSeleccionarReservaConsulta() {

	    DTReservaConsulta reserva1 = controlador.seleccionarReservaConsulta("lgomez", "Cardiología 240826");

	    assertNotNull(reserva1);
	    assertEquals("Cardiología 240826", reserva1.getNombreConsulta());
	    assertEquals("lgomez", reserva1.getNickPaciente());


	    DTReservaConsulta reserva2 = controlador.seleccionarReservaConsulta("lgomez", "Cardiología 240826");

	    assertNotNull(reserva2);
	    assertEquals("Cardiología 240826", reserva2.getNombreConsulta());
	    assertEquals("mar.fernandez", reserva2.getNickPaciente());

	    DTReservaConsulta reserva3 = controlador.seleccionarReservaConsulta("lgomez", "Pediatría 080926");
	    
	    assertNotNull(reserva3);
	    assertEquals("Pediatría 080926", reserva3.getNombreConsulta());
	    assertEquals("s.lopez", reserva3.getNickPaciente());
	    
	    DTReservaConsulta reserva4 = controlador.seleccionarReservaConsulta("lgomez", "Pediatría 080926");
	    
	    assertNotNull(reserva4);
	    assertEquals("Pediatría 080926", reserva4.getNombreConsulta());
	    assertEquals("lgomez", reserva4.getNickPaciente());


	    DTReservaConsulta reserva5 = controlador.seleccionarReservaConsulta("lgomez", "Medicina General 090926");

	    assertNotNull(reserva5);
	    assertEquals("Medicina General 090926", reserva5.getNombreConsulta());
	    assertEquals("mar.fernandez", reserva5.getNickPaciente());
	}
	
	@Test
	void testAltaReservaConsulta1() {

	    String nickPaciente = "alex";
	    String nombreConsulta = "Medicina General";

	    controlador.altaReservaConsulta(nickPaciente, nombreConsulta);

	    DTReservaConsulta res = controlador.seleccionarReservaConsulta(nickPaciente, nombreConsulta);

	    assertNotNull(res);

	    assertEquals(nickPaciente, res.getNickPaciente());
	    assertEquals(nombreConsulta, res.getNombreConsulta());
	}
	
	@Test
	public void testAltaReservaConsulta2() {

	    String nickPaciente = "Agustin";
	    String nombreConsulta = "Cardiología";
	    DTFecha fecha = UtilidadesTesting.ingresarFecha("01/09/2026");

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
	
}
