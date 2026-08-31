package clases;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import datatypes.DTFecha;
import datatypes.DTReserva;
import clases.BeneficioAnual;
import datatypes.DTReservaConsulta;
import datatypes.DTPaciente;
import clases.Reserva;

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
        return reservas.values().stream().map(Reserva::getDatosReservaConsulta).collect(Collectors.toCollection(LinkedHashSet::new));
    }

	public DTPaciente obtenerDTPaciente() {
		 DTPaciente dtp = new DTPaciente(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getFecNacimiento());
		 return dtp;
	}
	
	public Reserva obtenerReserva(String nombreConsulta) {
		for(Reserva r : this.reservas) {
			if (r.getConsulta().getNombre().equals(nombreConsulta)) return r;
		}
		return null;
	}


	public DTFecha getFecNacimiento() {
		return fecNacimiento;
	}


	public void setFecNacimiento(DTFecha fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}


	public DTFecha getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(DTFecha fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public int getDocumento() {
		return documento;
	}


	public void setDocumento(int documento) {
		this.documento = documento;
	}


	public Set<String> getAntecedentesMed() {
		return antecedentesMed;
	}
	
	public void setAntecedentesMed(Set<String> antecedentesMed) {
		this.antecedentesMed = antecedentesMed;
	}



	public void setBeneficios(Set<BeneficioAnual> beneficios) {
		this.beneficios = beneficios;
	}
	
	public boolean tieneReservaConsulta(String nombreConsulta) {
		for (Reserva r : this.reservas) {
			if (r.getConsulta().getNombre().equals(nombreConsulta)) {
				return true;
			}
		}
		return false;
	}


public void agregarReserva(Reserva reserva) {
    reservas.add(reserva);
}

public BeneficioAnual obtenerBeneficioConsultaDisponible(int anioActual) {
    BeneficioAnual seleccionado = null;
    for (BeneficioAnual beneficio : beneficios) {
        boolean corresponde = beneficio.getAnio() <= anioActual
                && beneficio.getConsultasGratis() > 0;

        if (corresponde && (seleccionado == null|| beneficio.getAnio() < seleccionado.getAnio())) {
        	seleccionado = beneficio;
        }
    }

    return seleccionado;
}
	
	
}