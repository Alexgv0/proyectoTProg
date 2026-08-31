package clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DTConsulta;
import datatypes.DTFecha;
import datatypes.DTReserva;
import datatypes.DTUsuario;
import datatypes.DTDatosConsulta;
import datatypes.DTConsultaSoloConsulta;

public class Consulta{
	private DiaConsulta diaConsulta;
	private Medico medico;                               ///// cambiarlo entero el map medicos
	private Set<Reserva> reservas;
	
	private String nombre;
	private DTFecha fecha;
	private int numeroSala;
	private int numsDisponibles;
	private float costoConsulta;
	
	public Consulta(
			String nombre,
			DTFecha fecha,
			int numeroSala,
			int numsDisponibles,
			float costoConsulta,
			DiaConsulta diaConsulta
			) {
		this.nombre = nombre;
		this.fecha= fecha;
		this.numeroSala = numeroSala;
		this.numsDisponibles = numsDisponibles;
		this.costoConsulta = costoConsulta;
		
		this.diaConsulta = diaConsulta;
		this.reservas = new HashSet<>();
	}
	
	public boolean perteneceMedicoConsulta(String nick ) {
    if (medico.getNickname().equals(nick)) {
        return true;
    }
    return false;
}
	}
	
	public DTConsulta getDatosConsulta() { 
	String nombreMedico = medico.getNickname();
	Set<DTReserva> dtReservas = new HashSet<>();
	for (Reserva r : this.reservas) {
		dtReservas.add(new DTReserva(
				r.getPaciente().getNickname(),
				r.getFecha(),
				r.getNumeroCliente(),
				r.getCostoReserva()
		));
	}

	return new DTConsulta(
			this.nombre,
			this.fecha,
			this.numeroSala,
			this.numsDisponibles,
			nombreMedico,
			this.costoConsulta,
			dtReservas
	);
}
	public DTConsultaSoloConsulta getDatosSoloConsulta(){
		DTConsultaSoloConsulta res = new DTConsultaSoloConsulta(
			this.getNombre(),
			this.getFecha(),
			this.getNumeroSala(),
			this.getNumsDisponibles(),
			this.medico.getNickname(),
			this.getCostoConsulta());
		return res;
	}
	
	
	public void agregarReserva(Reserva res) {
		this.reservas.add(res);
	}	

	public String getNombre() {
		return this.nombre;
	}

	public DiaConsulta getDiaConsulta() {
		return diaConsulta;
	}

	public void setDiaConsulta(DiaConsulta diaConsulta) {
		this.diaConsulta = diaConsulta;
	}

	public string getNicknameMedico() {
		return medico.getNickname();
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public DTFecha getFecha() {
		return fecha;
	}

	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public int getNumsDisponibles() {
		return numsDisponibles;
	}

	public void setNumsDisponibles(int numsDisponibles) {
		this.numsDisponibles = numsDisponibles;
	}

	public float getCostoConsulta() {
		return costoConsulta;
	}

	public void setCostoConsulta(float costoConsulta) {
		this.costoConsulta = costoConsulta;
	}
	
	public DTDatosConsulta obtenerDatosConsulta(String nombreConsulta) {
		return new DTDatosConsulta(this.getNumsDisponibles(), this.getCostoConsulta());
	}
	
	public void nuevaReservaConsulta(Reserva r) {
		this.reservas.add(r);
	}
}