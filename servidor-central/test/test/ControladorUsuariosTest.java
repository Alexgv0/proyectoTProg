package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Usuario;
import datatypes.DTFecha;
import fabrica.Fabrica;
import interfaces.IControladorUsuarios;

class ControladorUsuariosTest {

	private IControladorUsuarios controladorUsuarios;
	
	@BeforeEach
	void iniciar() {
	    Fabrica fabrica = Fabrica.getInstancia();
	    controladorUsuarios = fabrica.getControladorUsuarios();
	}
	
	@Test
	void testAltaPacienteOK() {
	
	    String nickname = "test_paciente_01";
	    String nombre = "Juan";
	    String apellido = "Pérez";
	    String email = "test_paciente_01@gmail.com";
	
	    DTFecha fechaNacimiento = new DTFecha(15, 5, 2000);
	    int documento = 12345678;
	    DTFecha fechaIngreso = new DTFecha(31, 8, 2026);
	
	    Set<String> antecedentes = new HashSet<>();
	    antecedentes.add("Sin antecedentes relevantes.");
	
	    Usuario usuario = controladorUsuarios.altaPaciente(
	            nickname,
	            nombre,
	            apellido,
	            email,
	            fechaNacimiento,
	            documento,
	            fechaIngreso,
	            antecedentes
	    );
	
	    assertNotNull(usuario);
	    assertEquals(nickname, usuario.getNickname());
	    assertEquals(nombre, usuario.getNombre());
	    assertEquals(apellido, usuario.getApellido());
	    assertEquals(email, usuario.getEmail());
	}
	
	@Test
	void testAltaPacienteNicknameRepetido() {
	
	    String nickname = "test_paciente_02";
	    String nombre = "Juan";
	    String apellido = "Pérez";
	    String email = "test_paciente_02@gmail.com";
	
	    DTFecha fechaNacimiento = new DTFecha(15, 5, 2000);
	    int documento = 12345678;
	    DTFecha fechaIngreso = new DTFecha(31, 8, 2026);
	
	    Set<String> antecedentes = new HashSet<>();
	    antecedentes.add("Sin antecedentes relevantes.");
	
	    controladorUsuarios.altaPaciente(
	            nickname,
	            nombre,
	            apellido,
	            email,
	            fechaNacimiento,
	            documento,
	            fechaIngreso,
	            antecedentes
	    );
	
	    assertThrows(
	            IllegalArgumentException.class,
	            () -> controladorUsuarios.altaPaciente(
	                    nickname,
	                    "Pedro",
	                    "Gómez",
	                    "otrocorreo@gmail.com",
	                    fechaNacimiento,
	                    87654321,
	                    fechaIngreso,
	                    antecedentes
	            )
	    );
	}

	@Test
	void testAltaPacienteEmailRepetido() {

	String nickname1 = "test_paciente_03";
	String emailRepetido = "test_email_repetido@gmail.com";

	DTFecha fechaNacimiento = new DTFecha(15, 5, 2000);
	DTFecha fechaIngreso = new DTFecha(31, 8, 2026);

	Set<String> antecedentes = new HashSet<>();
	antecedentes.add("Sin antecedentes relevantes.");

	// Primer paciente: debe crearse correctamente.
	controladorUsuarios.altaPaciente(
	        nickname1,
	        "Juan",
	        "Pérez",
	        emailRepetido,
	        fechaNacimiento,
	        12345678,
	        fechaIngreso,
	        antecedentes
	);

	// Segundo paciente con otro nickname pero el mismo correo: debe fallar.
	assertThrows(
	        IllegalArgumentException.class,
	        () -> controladorUsuarios.altaPaciente(
	                "test_paciente_04",
	                "Pedro",
	                "Gómez",
	                emailRepetido,
	                fechaNacimiento,
	                87654321,
	                fechaIngreso,
	                antecedentes
	        )
		);
	}
	
	@Test
	void testAltaMedicoOK() {

	String nickname = "test_medico_01";
	String nombre = "Carlos";
	String apellido = "Gómez";
	String email = "test_medico_01@gmail.com";

	int registro = 12345;
	String mesLicencia = "Enero";
	String sitioWeb = "https://www.ejemplo.com";
	int porcentajeConsultas = 10;

	Set<datatypes.DTEspecialidad> especialidades = new HashSet<>();

	especialidades.add(
	        controladorUsuarios.obtenerDatosEspecialidad("Cardiología")
	);

	Usuario usuario = controladorUsuarios.altaMedico(
	        nickname,
	        nombre,
	        apellido,
	        email,
	        registro,
	        mesLicencia,
	        sitioWeb,
	        especialidades,
	        porcentajeConsultas
	);

	assertNotNull(usuario);
	assertEquals(nickname, usuario.getNickname());
	assertEquals(nombre, usuario.getNombre());
	assertEquals(apellido, usuario.getApellido());
	assertEquals(email, usuario.getEmail());

	}

}
