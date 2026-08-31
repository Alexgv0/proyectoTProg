package controladores;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import clases.Medico;
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
	private static final ControladorClinicas instancia = new ControladorClinicas();
    private final ManejadorClinicas mc;
    private final ManejadorEspecialidades me;
    private final ManejadorDiasConsulta mdc;
    private final ManejadorConsultas mcons;
    private final ManejadorUsuarios mu;

    private ControladorClinicas() {
        this.mc = ManejadorClinicas.getInstancia();
        this.me = ManejadorEspecialidades.getInstancia();
        this.mdc = ManejadorDiasConsulta.getInstancia();
        this.mcons = ManejadorConsultas.getInstancia();
        this.mu = ManejadorUsuarios.getInstancia();
    }

    
    // Public
    public static ControladorClinicas getInstancia() {
        return instancia;
    }
    
    
    
    // CU :: Consulta de Clinica
    
    @Override
    public Set<String> listarClinicas() {
        return mc.listarClinicas().stream().map(Clinica::getNombre).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }
    
    @Override
    public DTClinica obtenerDatosClinica(String nombre) {
        Clinica clinica = mc.obtenerClinica(nombre);
        if (clinica == null) throw new IllegalArgumentException("La clinica no existe.");
        return clinica.getDatosClinica();
    }
    
    @Override
    public void altaClinica(String nombre, String descripcion, String direccion, String sitioWeb, float costoMedicamento, float costoConsulta) {
        String nombreLimpio = Validaciones.texto(nombre, "nombre");
        Validaciones.texto(descripcion, "descripcion");
        Validaciones.texto(direccion, "direccion");
        Validaciones.noNegativo(costoMedicamento, "costo del ticket");
        Validaciones.noNegativo(costoConsulta, "costo de la orden");
        if (mc.obtenerClinica(nombreLimpio) != null) {
            throw new IllegalArgumentException("Ya existe una clinica con ese nombre.");
        }
        mc.agregarClinica(new Clinica(nombreLimpio, descripcion.trim(), direccion.trim(),
                sitioWeb == null ? "" : sitioWeb.trim(), costoMedicamento,
                costoConsulta));
    }

    @Override
    public Set<String> listarEspecialidades() { /// A CHEQUEAR
        return me.listarEspecialidades().stream().map(Especialidad::getNombre).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void altaDiaConsulta(String nombre, String descripcion, Dia diaSemana,
            int horaInicio, String especialidad, String clinica) {
        String nombreLimpio = Validaciones.texto(nombre, "nombre");
        Validaciones.texto(descripcion, "descripcion");
        if (diaSemana == null) throw new IllegalArgumentException(
                "Debe seleccionar un dia de la semana.");
        if (horaInicio < 0 || horaInicio > 23) throw new IllegalArgumentException(
                "La hora de inicio debe estar entre 0 y 23.");
        if (mdc.existeDiaConsulta(nombreLimpio)) throw new IllegalArgumentException(
                "Ya existe un dia de consulta con ese nombre.");
        Clinica objetoClinica = mc.obtenerClinica(clinica);
        if (objetoClinica == null) throw new IllegalArgumentException(
                "La clinica indicada no existe.");
        Especialidad objetoEspecialidad = me.obtenerEspecialidad(especialidad);
        if (objetoEspecialidad == null) throw new IllegalArgumentException(
                "La especialidad indicada no existe.");
        float costo = objetoClinica.getCostoConsulta()
                * (1 + objetoEspecialidad.getPorcentajeCostoConsultaAdicional() / 100.0f);
        DiaConsulta nuevo = new DiaConsulta(nombreLimpio, descripcion.trim(), diaSemana,
                horaInicio, objetoEspecialidad, objetoClinica, costo);
        mdc.agregarDiaConsulta(nuevo);
        objetoClinica.agregarDiaConsulta(nuevo);
    }

    @Override
    public Set<DTDiaConsulta> listarDiasConsulta() { //// A CHEQUEAR
        return mdc.obtenerDiasConsulta().stream().map(d -> new DTDiaConsulta(d.getNombre())).sorted((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre())).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public DTDiaConsultaCompleto obtenerDatosDiaConsulta(String nombre) {
        DiaConsulta dia = mdc.buscarDiaConsulta(nombre);
        if (dia == null) throw new IllegalArgumentException(
                "El dia de consulta no existe.");
        return dia.getDatosCompletos();
    }

    @Override
    public void altaConsulta(String nombre, DTFecha fecha, int numeroSala, int cantidadNumeros, String nombreDiaConsulta, String nicknameMedico) {
        	String nombreLimpio = Validaciones.texto(nombre, "nombre");
        	if (fecha == null) throw new IllegalArgumentException("La fecha es obligatoria.");
        	
        	Validaciones.positivo(numeroSala, "numero de sala");
        	Validaciones.positivo(cantidadNumeros, "cantidad de numeros");
        
        	if (mcons.existeConsulta(nombreLimpio)) throw new IllegalArgumentException("Ya existe una consulta con ese nombre.");
        	DiaConsulta dia = mdc.buscarDiaConsulta(nombreDiaConsulta);
        	
        	if (dia == null) throw new IllegalArgumentException("El dia de consulta indicado no existe.");
        	Medico medico = mu.obtenerMedico(nicknameMedico);
        	
        	if (medico == null) throw new IllegalArgumentException("El medico no existe.");
        	
        	if (!medico.tieneEspecialidad(dia.getEspecialidad().getNombre())) {
        		throw new IllegalArgumentException("El medico no tiene la especialidad asociada al dia de consulta.");
        	}
        	
        	if (convertirDia(fecha.toLocalDate().getDayOfWeek()) != dia.getDia()) {
        		throw new IllegalArgumentException("La fecha no corresponde al dia de la semana seleccionado.");
        	}
        	
        	Consulta consulta = new Consulta(nombreLimpio, fecha, numeroSala,
                cantidadNumeros, dia, medico);
        mcons.agregarConsulta(consulta);
        dia.agregarConsulta(consulta);
        medico.agregarConsulta(consulta);
    }

    private Dia convertirDia(DayOfWeek dia) {
        switch (dia) {
        case MONDAY: return Dia.LUNES;
        case TUESDAY: return Dia.MARTES;
        case WEDNESDAY: return Dia.MIERCOLES;
        case THURSDAY: return Dia.JUEVES;
        case FRIDAY: return Dia.VIERNES;
        case SATURDAY: return Dia.SABADO;
        case SUNDAY: return Dia.DOMINGO;
        default: throw new IllegalArgumentException("Dia de la semana invalido.");
        }
    }

    @Override
    public Set<DTConsultaSoloConsulta> listarConsultasDia(String nombreDiaConsulta) {
        DiaConsulta dia = mdc.buscarDiaConsulta(nombreDiaConsulta);
        if (dia == null) throw new IllegalArgumentException(
                "El dia de consulta no existe.");
        return dia.listarConsultas();
    }

    @Override
    public Set<DTConsultaSoloConsulta> listarConsultas(DTUsuario usuario) {
        
    	if (usuario == null) throw new IllegalArgumentException("El usuario es obligatorio.");
        
    	Medico medico = mu.obtenerMedico(usuario.getNickname());
        
        if (medico == null) return new LinkedHashSet<>();
        
        return medico.getConsultas().values().stream().map(Consulta::getDatosSoloConsulta).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<DTConsulta> seleccionarDiaConsulta(String nombreDiaConsulta) {
        DiaConsulta dia = mdc.buscarDiaConsulta(nombreDiaConsulta);
        if (dia == null) throw new IllegalArgumentException("El dia de consulta no existe.");
        
        return dia.getConsultas().values().stream().map(Consulta::getDatosConsulta).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public DTConsulta seleccionarConsulta(String nombreConsulta) {
        Consulta consulta = mcons.buscarConsulta(nombreConsulta);
        if (consulta == null) throw new IllegalArgumentException("La consulta no existe.");
        return consulta.getDatosConsulta();
    }
}
