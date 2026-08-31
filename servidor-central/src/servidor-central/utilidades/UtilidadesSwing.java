package utilidades;

import java.util.Set;

import javax.swing.JComboBox;

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
}

