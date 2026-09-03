package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import datatypes.DTEspecialidad;

public class DatosPruebaTest {
	
	@Test
	public void testCargarUsuarios() {
	    
		DatosPrueba.cargarUsuarios();

        String[] datosUS01 = DatosPrueba.obtenerUsuarioPorRef("US01");

        assertNotNull(datosUS01);
        assertEquals(4, datosUS01.length);
        assertEquals("ana.perez", datosUS01[0]);
        assertEquals("Ana", datosUS01[1]);
        assertEquals("Pérez", datosUS01[2]);
        assertEquals("ana.perez@tuclinica.com", datosUS01[3]);

        String[] datosUS02 = DatosPrueba.obtenerUsuarioPorRef("US02");

        assertNotNull(datosUS02);
        assertEquals(4, datosUS02.length);
        assertEquals("bsilva", datosUS02[0]);
        assertEquals("Bruno", datosUS02[1]);
        assertEquals("Silva", datosUS02[2]);
        assertEquals("bruno.silva@hospital.com", datosUS02[3]);

        String[] datosUS03 = DatosPrueba.obtenerUsuarioPorRef("US03");

        assertNotNull(datosUS03);
        assertEquals(4, datosUS03.length);
        assertEquals("carla.rodriguez", datosUS03[0]);
        assertEquals("Carla", datosUS03[1]);
        assertEquals("Rodríguez", datosUS03[2]);
        assertEquals("carla.rodriguez@hospital.com", datosUS03[3]);

        String[] datosUS04 = DatosPrueba.obtenerUsuarioPorRef("US04");

        assertNotNull(datosUS04);
        assertEquals(4, datosUS04.length);
        assertEquals("lgomez", datosUS04[0]);
        assertEquals("Laura", datosUS04[1]);
        assertEquals("Gómez", datosUS04[2]);
        assertEquals("laura.gomez@gmail.com", datosUS04[3]);

        String[] datosUS05 = DatosPrueba.obtenerUsuarioPorRef("US05");

        assertNotNull(datosUS05);
        assertEquals(4, datosUS05.length);
        assertEquals("mar.fernandez", datosUS05[0]);
        assertEquals("Mario", datosUS05[1]);
        assertEquals("Fernández", datosUS05[2]);
        assertEquals("mario.fernandez@hotmail.com", datosUS05[3]);

        String[] datosUS06 = DatosPrueba.obtenerUsuarioPorRef("US06");

        assertNotNull(datosUS06);
        assertEquals(4, datosUS06.length);
        assertEquals("s.lopez", datosUS06[0]);
        assertEquals("Sofía", datosUS06[1]);
        assertEquals("López", datosUS06[2]);
        assertEquals("sofia.lopez@gmail.com", datosUS06[3]);
	}
	
	@Test
	public void testCargarEspecialidades() {

	    DatosPrueba.cargarEspecialidades();

	    //ES01
	    DTEspecialidad especialidad1 =
	            DatosPrueba.obtenerEspecialidadPorNombre("Cardiología");

	    assertNotNull(especialidad1);
	    assertEquals("Cardiología", especialidad1.getNombre());
	    assertEquals(20, especialidad1.getPorcentajeAdicional());

	    //ES02
	    DTEspecialidad especialidad2 =
	            DatosPrueba.obtenerEspecialidadPorNombre("Pediatría");

	    assertNotNull(especialidad2);
	    assertEquals("Pediatría", especialidad2.getNombre());
	    assertEquals(10, especialidad2.getPorcentajeAdicional());

	    //ES03
	    DTEspecialidad especialidad3 =
	            DatosPrueba.obtenerEspecialidadPorNombre("Medicina General");

	    assertNotNull(especialidad3);
	    assertEquals("Medicina General", especialidad3.getNombre());
	    assertEquals(0, especialidad3.getPorcentajeAdicional());
	}
}
