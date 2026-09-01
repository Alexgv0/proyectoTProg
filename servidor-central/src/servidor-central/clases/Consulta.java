package clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DTConsultaCompleta;
import datatypes.DTFecha;
import datatypes.DTReservaConsulta;
import datatypes.DTUsuario;

public class Consulta{
	private DiaConsulta diaConsulta;
	private Map<String, Medico> medicos;
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
		this.setFecha(fecha);
		this.setNumeroSala(numeroSala);
		this.setNumsDisponibles(numsDisponibles);
		this.setCostoConsulta(costoConsulta);
		
		this.setDiaConsulta(diaConsulta);
		this.medicos = new HashMap<>();
		this.reservas = new HashSet<>();
	}
	
	public boolean perteneceMedicoConsulta(DTUsuario u) {
		return false;
	}
	
public DTConsultaCompleta getDatosConsulta() {
	String nombreMedico = "";
	if (!this.medicos.isEmpty()) {
		nombreMedico = this.medicos.values().iterator().next().getNickname();
	}
	Set<DTReservaConsulta> dtReservas = new HashSet<>();
	for (Reserva r : this.reservas) {
		dtReservas.add(new DTReservaConsulta(
				r.getPaciente().getNickname(),
				r.getFecha(),
				r.getNumeroCliente(),
				r.getCostoReserva()
		));
	}
	return new DTConsultaCompleta(
			this.nombre, nombreMedico, this.fecha, this.numeroSala,
			this.numsDisponibles, this.costoConsulta, dtReservas
	);
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

	public Map<String, Medico> getMedicos() {
		return medicos;
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
}