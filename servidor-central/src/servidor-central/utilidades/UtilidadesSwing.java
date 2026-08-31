package utilidades;

import java.util.Set;

import javax.swing.JComboBox;

import datatypes.DTFecha;

public class UtilidadesSwing {
	private UtilidadesSwing() {
		
	}

	public static void cargarComboBox(
			JComboBox<String> comboBox,
			Set<String> datos,
			String mensajeVacio
			) {
		
		comboBox.removeAllItems();
		
		if (datos == null || datos.isEmpty()) {
			comboBox.addItem(mensajeVacio);
			comboBox.setEnabled(false);
		} else {
			comboBox.setEnabled(true);
			for (String dato : datos) {
				comboBox.addItem(dato);
			}
		}	
	}
	
	public static String mostrarSiExiste(Object valor) {
	    return valor != null ? valor.toString() : "N/A";
	}
	
    public static DTFecha convertirFecha(String texto) {

        String[] partes = texto.split("/");

        if (partes.length != 3) {
            throw new IllegalArgumentException(
                    "La fecha debe tener el formato dd/mm/aaaa."
            );
        }

        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        return new DTFecha(dia, mes, anio);
    }
}

