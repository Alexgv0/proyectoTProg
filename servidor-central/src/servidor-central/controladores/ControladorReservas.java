package controladores;

import manejadores.ManejadorReservas;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTEspecialidad;
import java.util.Set;

public class ControladorReservas {

    private static ControladorReservas instancia;

    private ManejadorReservas manejadorUsuarios;

    private ControladorReservas() {
    	ManejadorReservas mr = ManejadorReservas.getInstancia();
    }

    public static ControladorReservas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorReservas();
        }
        return instancia;
    }
}