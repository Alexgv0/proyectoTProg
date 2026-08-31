package test.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fabrica.Fabrica;
import interfaces.IControladorUsuarios;

public class DatosPrueba {


	public static void cargarEspecialidades() {
	
	    IControladorUsuarios controladorUsuarios =
	            Fabrica.getInstancia().getControladorUsuarios();
	
	    String ruta = "src/servidor-central/CSVs/2026especialidades.csv";
	
	    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
	
	        // Saltear encabezado
	        br.readLine();
	
	        String linea;
	
	        while ((linea = br.readLine()) != null) {
	
	            linea = linea.trim();
	
	            if (linea.isEmpty()) {
	                continue;
	            }
	
	            String[] datos = linea.split(";");
	
	            String nombre = datos[1];
	            int porcentaje = Integer.parseInt(datos[2]);
	
	            controladorUsuarios.ingresarDatosAltaEspecialidad(
	                    nombre,
	                    porcentaje
	            );
	        }
	
	    } catch (IOException e) {
	        throw new RuntimeException(
	                "No se pudo leer el archivo de especialidades.",
	                e
	        );
	    }
	}


}