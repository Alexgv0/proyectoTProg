package pruebas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import datatypes.DTFecha;

public class UtilidadesTesting {
	private UtilidadesTesting() {
		
	}
	
    public static DTFecha ingresarFecha(String texto) {
        LocalDate fecha = LocalDate.parse(
            texto.trim(),
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT)
        );

        return DTFecha.from(fecha);
    }
}
