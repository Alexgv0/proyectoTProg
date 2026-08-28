package controladores;

import manejadores.ManejadorUsuarios;
import clases.Usuario;
import datatypes.DTFecha;
import datatypes.DTEspecialidad;
import java.util.Set;

public class ControladorClinicas {

    private static ControladorClinicas instancia;

    //private ;

    private ControladorClinicas() {
        //.getInstancia();
    }

    public static ControladorClinicas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorClinicas();
        }
        return instancia;
    }
    
    //alta de clinica
    
    //consulta de clinica
}
