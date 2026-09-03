package clases;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import datatypes.DTBeneficioAnual;
import datatypes.DTFecha;
import datatypes.DTPaciente;
import datatypes.DTReservaConsulta;

public class Paciente extends Usuario {
    private DTFecha fecNacimiento;
    private DTFecha fechaIngreso;
    private int documento;
    private Set<String> antecedentesMed;
    private final Map<Integer, BeneficioAnual> beneficios = new LinkedHashMap<>();
    private final Map<String, Reserva> reservas = new LinkedHashMap<>();

    public Paciente(String nickname, String nombre, String apellido, String correo,
            DTFecha fecNacimiento, int documento, DTFecha fechaIngreso,
            Set<String> antecedentesMed) {
        super(nickname, nombre, apellido, correo);
        this.fecNacimiento = fecNacimiento;
        this.documento = documento;
        this.fechaIngreso = fechaIngreso;
        this.antecedentesMed = new LinkedHashSet<>(antecedentesMed);
    }

    public Set<DTReservaConsulta> listarReservas() {
        return reservas.values().stream().map(Reserva::getDatosReservaConsulta)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Reserva obtenerReserva(String nombreConsulta) { return reservas.get(nombreConsulta); }
    public void agregarReserva(Reserva reserva) {
        reservas.put(reserva.getConsulta().getNombre(), reserva);
    }
    public boolean tieneReservaConsulta(String nombreConsulta) {
        return reservas.containsKey(nombreConsulta);
    }

    public DTFecha getFecNacimiento() { return fecNacimiento; }
    public void setFecNacimiento(DTFecha fecha) { this.fecNacimiento = fecha; }
    
    public DTFecha getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(DTFecha fecha) { this.fechaIngreso = fecha; }
    
    public int getDocumento() { return documento; }
    public void setDocumento(int documento) { this.documento = documento; }
    
    public Set<String> getAntecedentesMed() {
        return Collections.unmodifiableSet(antecedentesMed);
    }
    public void setAntecedentesMed(Set<String> antecedentes) {
        this.antecedentesMed = new LinkedHashSet<>(antecedentes);
    }

    public Set<DTBeneficioAnual> listarBeneficios() {
    	return beneficios.values().stream().map(BeneficioAnual::getDatos)
    			.collect(Collectors.toCollection(LinkedHashSet::new));
    }
    public BeneficioAnual obtenerBeneficio(int anio) { return beneficios.get(anio); }
    public void agregarBeneficio(BeneficioAnual beneficio) {
        beneficios.put(beneficio.getAnio(), beneficio);
    }
    public boolean tieneBeneficio(int anio) { return beneficios.containsKey(anio); }
    public BeneficioAnual obtenerBeneficioConsultaDisponible(int anioActual) {
        return beneficios.values().stream()
                .filter(b -> b.getAnio() <= anioActual && b.getConsultasGratis() > 0)
                .min(Comparator.comparingInt(BeneficioAnual::getAnio)).orElse(null);
    }

    @Override
    public DTPaciente getDatos() {
        return new DTPaciente(getNickname(), getNombre(), getApellido(), getCorreo(),
                fecNacimiento, documento, fechaIngreso, antecedentesMed);
    }

    public DTPaciente obtenerDTPaciente() { return getDatos(); }
}