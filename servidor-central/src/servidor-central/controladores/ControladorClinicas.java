package controladores;

import java.util.HashSet;
import java.util.Set;

import interfaces.IControladorClinicas;
import manejadores.ManejadorClinicas;
import manejadores.ManejadorConsultas;
import manejadores.ManejadorEspecialidades;
import manejadores.ManejadorUsuarios;
import manejadores.ManejadorDiasConsulta;
import clases.Clinica;
import clases.Consulta;
import clases.DiaConsulta;
import clases.Especialidad;
import datatypes.DTClinica;
import datatypes.DTConsulta;
import datatypes.DTDatosConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTReserva;
import datatypes.DTFecha;
import datatypes.DTUsuario;
import datatypes.DTDiaConsultaCompleto;
import clases.Paciente;
import clases.Reserva;
import enums.Dia;
import datatypes.DTDiaConsulta;

public class ControladorClinicas implements IControladorClinicas {

    // Private
	private static ControladorClinicas instancia;
	private DTFecha fechaActual;
    private ManejadorClinicas mc;
    private ManejadorEspecialidades me;
    private ManejadorDiasConsulta mdc;
    private ManejadorConsultas mcons;
    private ManejadorUsuarios mu;

    private ControladorClinicas() {
        this.mc = ManejadorClinicas.getInstancia();
        this.me = ManejadorEspecialidades.getInstancia();
        this.mdc = ManejadorDiasConsulta.getInstancia();
        this.mcons = ManejadorConsultas.getInstancia();
        this.mu = ManejadorUsuarios.getInstancia();
    }

    
    // Public
    public static ControladorClinicas getInstancia() {
        if (instancia == null) {
            instancia = new ControladorClinicas();
        }
        return instancia;
    }
    
    
    
    // CU :: Consulta de Clinica
    public Set<String> listarClinicas() {
    	Set<Clinica> clinicas = mc.listarClinicas();

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
    
    public DTFecha getFechaActual() {
    	return this.fechaActual;
    }
    
    // CU ::consulta usuario                                                
    public Set<DTConsultaSoloConsulta> listarConsultas(DTUsuario u){
        Map<String, Consulta> consultas = mcons.getConsultas();
        Set<DTConsultaSoloConsulta> resultado = new HashSet<>();

    for (Consulta c : consultas.values()) {
        String nick = c.getNicknameMedico();  

        if (nick.equals(u.getNickname())) {
            DTConsultaSoloConsulta dtc = c.getDatosSoloConsulta();   //// solo los datos de la consulta no con las reservas
            resultado.add(dtc);
        }
    }
    return resultado;
    }



    public Set<DTReserva> listarReservasConsultas(String nickPaciente) {        // lista todas las reservas de un paciente
        Paciente p = (Paciente) mu.obtenerPaciente(nickPaciente);
        Set<DTReserva> resultado =   p.listarReservas();

        return resultado;
}



    }
    
    // CU :: ALTA de Dia de Consulta
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
    
    // CU ::alta de clinica
    public void altaClinica(
		String nombre, String descripcion, String direccion,
		String sitioWeb, float costoMedicamento, float costoConsulta) {
	
		if (mc.obtenerClinica(nombre) != null) {
			throw new IllegalArgumentException("Ya existe una clínica con ese nombre.");
		}
	
		Clinica nuevaClinica = new Clinica(nombre, descripcion, direccion, sitioWeb, costoMedicamento, costoConsulta);
		mc.agregarClinica(nuevaClinica);
	}
    // CU ::consulta de clinica /////////////////////////////////////////////////////

    // CU ::Consulta Día Consulta

    public Set<DTDiaConsulta> listarDiasConsulta() {
    Set<DTDiaConsulta> resultado = new HashSet<>();

    for (DiaConsulta d : mdc.getDiasConsulta().values()) {
        String nombre = d.getNombre();
        resultado.add(new DTDiaConsulta(nombre));
    }

    return resultado;
}

    public DTDiaConsultaCompleto obtenerDatosDiaConsulta(String nombre) {       //lista incluyendo el listado de consultas asociadas
    DiaConsulta d = mdc. buscarDiaConsulta(nombre);

    if (d!= null){
     DTDiaConsultaCompleto res = new DTDiaConsultaCompleto(
            d.getNombre(),
            d.getDescripcion(),
            d.getDia(),
            d.getHoraInicio(),
            d.getCostoConsulta()
        );
        return res; 
    }
    else return null;

   
}

    // CU ::Alta Consulta
    
    // CU ::Consulta Consulta
    public Set<DTConsulta> seleccionarDiaConsulta(String nombreDiaConsulta) {
		DiaConsulta dc = mdc.buscarDiaConsulta(nombreDiaConsulta);
		Set<DTConsulta> res = new HashSet<>();
		for (Consulta c : dc.getConsultas().values()) {
			res.add(c.getDatosConsulta());
		}
		return res;
	}

	public DTConsulta seleccionarConsulta(String nombreConsulta) {
		Consulta c = mcons.buscarConsulta(nombreConsulta);
		return c.getDatosConsulta();
	}



    
}

