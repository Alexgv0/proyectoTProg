package clases;

import java.util.Set;
import java.util.HashSet;
import datatypes.DTFecha;
import datatypes.DTReserva;
import clases.BeneficioAnual;
import clases.Reserva;

public class Paciente extends Usuario {

    private DTFecha fecNacimiento;
    private DTFecha fechaIngreso;
    private int documento;
    private Set<String> antecedentesMed;
    
    private Set<BeneficioAnual> beneficios;
    private Set<Reserva> reservas;
    
    
    public Paciente(
            String nickname,
            String nombre,
            String apellido,
            String email,
            DTFecha fecNacimiento,
            int documento,
            DTFecha fechaIngreso,
            Set<String> antecedentesMed) {

        super(nickname, nombre, apellido, email);

        this.setFecNacimiento(fecNacimiento);
        this.setDocumento(documento);
        this.setFechaIngreso(fechaIngreso);
        this.setAntecedentesMed(antecedentesMed);
        
        this.setBeneficios(new HashSet<>());
        this.reservas = new HashSet<>();
    }


	public Set<DTReserva> listarReservas() {
		Set<DTReserva> res = new HashSet<>();
		for(Reserva r: this.reservas) {
			res.add(r.crearDTReserva());
		}
		return res;
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


	public boolean usaConsultaGratis() {
		for (BeneficioAnual b : this.beneficios) {
			if (b.getConsultasGratis() > 0) {
				b.setConsultasGratis(b.getConsultasGratis() - 1);
				return true;
			}
		}
		return false;
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
	
	public Reserva nuevaReserva(int num, float cost, DTFecha fechaActual) {
		boolean usa = false;
		if (this.usaConsultaGratis()) {
			cost = 0;
			usa = true;
		}
		Reserva r = new Reserva(fechaActual, num, cost, usa);
		this.reservas.add(r);
		return r;
	}
}