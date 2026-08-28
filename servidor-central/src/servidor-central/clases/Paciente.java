package clases;

import java.util.Set;
import datatypes.DTFecha;
import datatypes.DTReserva;

import java.util.HashSet;

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

        this.fecNacimiento = fecNacimiento;
        this.documento = documento;
        this.fechaIngreso = fechaIngreso;
        this.antecedentesMed = antecedentesMed;
        
        this.beneficios = new HashSet<>();
    }


	public Set<DTReserva> listarReservas() {
		Set<DTReserva> res = new HashSet<>();
		for(Reserva r: this.reservas) {
			res.add(r.crearDTReserva());
		}
		return res;
	}
}