package controladores;

import java.util.HashSet;
import java.util.Set;

import manejadores.ManejadorClinicas;
import manejadores.ManejadorEspecialidades;
import manejadores.ManejadorDiasConsulta;
import clases.Clinica;
import clases.DiaConsulta;
import clases.Especialidad;
import datatypes.DTFecha;
import datatypes.DTClinica;
import datatypes.DTEspecialidad;
import datatypes.DTConsulta;
import datatypes.DTUsuario;
import enums.Dia;


public class ControladorClinicas {

    // private
	private static ControladorClinicas instancia;
	
    private ManejadorClinicas mc;
    private ManejadorEspecialidades me;
    private ManejadorDiasConsulta mdc;

    private ControladorClinicas() {
        this.mc = ManejadorClinicas.getInstancia();
        this.me = ManejadorEspecialidades.getInstancia();
        this.mdc = ManejadorDiasConsulta.getInstancia();
    }

    
    // public
    public static ControladorClinicas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorClinicas();
        }
        return instancia;
    }
    
    // CU :: Consulta de Clinica
    
    public Set<String> listarClinicas() {
    	Set<Clinica> clinicas = mc.obtenerClinicas();
    	Set<String> res = new HashSet<>();
    	
    	for(Clinica c : clinicas) {
    		res.add(c.getNombre());
    	}
    	
    	return res;
    }
    
    public DTClinica obtenerDatosClinica(String nombre) {
		Clinica c = mc.obtenerClinica(nombre);
    	return c.getDatosClinica();
    }
    
    //consulta usuario
    public Set<DTConsulta> listarConsultas(DTUsuario u){
    	return null; //todo
    }
    
    public DTConsulta seleccionarConsulta(String nombreConsulta) {
    	return null; //todo
    }
    
    //ALTA DE dia de consulta
    public Set<String> listarEspecialidades() {

        Set<Especialidad> especialidades = me.listarEspecialidades();

        Set<String> res = new HashSet<>();

        for (Especialidad e : especialidades) {
            res.add(e.getNombre());
        }

        return res;
    }
    
    public void altaDiaConsulta(
            String nombre,
            String descripcion,
            Dia diaSemana,
            int horaInicio,
            String especialidad,
            String clinica) {

    	if (mdc.existeDiaConsulta(nombre)) {
            throw new IllegalArgumentException(
                "Ya existe un día de consulta con ese nombre."
            );
        }

    	Clinica c = mc.obtenerClinica(clinica);
        if (c == null) {
            throw new IllegalArgumentException(
                "La clínica no existe."
            );
        }
        
        Especialidad e = me.obtenerEspecialidad(especialidad);
        if (e == null) {
            throw new IllegalArgumentException(
                "La especialidad no existe."
            );
        }
        
        float costo = c.getCostoConsulta()
                * (1 + e.getPorcentajeCostoConsultaAdicional() / 100.0f);

        DiaConsulta nuevoDiaConsulta = new DiaConsulta(
                nombre,
                descripcion,
                diaSemana,
                horaInicio,
                e,
                c,
                costo
            );

        mdc.agregarDiaConsulta(nuevoDiaConsulta);
    }
    
    //alta de clinica
    
    //consulta de clinica
    
    //Consulta Día Consulta
    
    //Alta Consulta
    
    //Consulta Consulta
}
