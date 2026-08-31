package clases;

import java.time.LocalDate;
import java.time.Period;
import datatypes.DTReservaConsulta;
import datatypes.DTFecha;
import datatypes.DTFecha;
import datatypes.DTReserva;

public class Reserva{
	private Consulta consulta;
	private Paciente paciente;
	private BeneficioAnual beneficio;
	private DTFecha fecha;
	private int numeroCliente;
	private float costoReserva;
	
	public Reserva(DTFecha fecha, Consulta consulta, Paciente paciente) {  // si no tiene beneficio anual
	    this(fecha, consulta, paciente, null);
	}
  
	public Reserva(DTFecha fecha, Consulta consulta, Paciente paciente, BeneficioAnual b) {   // si tiene beneficio anual
	    this.setFecha(fecha);
	    this.setConsulta(consulta);
	    this.setPaciente(paciente);
	    this.setBeneficioAnual(b);
	    this.numeroCliente = consulta.obtenerMenorNumeroDisponible();
	    this.costoReserva = calcularCostoReserva();
		if (beneficio != null) {
        beneficio.consumirConsultaGratis();
    }
	}


	private float calcularCostoReserva() {
    if (beneficio != null) {
        return 0.0f;
    }
    float costo = consulta.getCostoConsulta();

    if (esPacienteMayorDe60()) {
        costo = costo * 0.80f;
    }
    return costo;
	}


	private boolean esPacienteMayorDe60() {
    DTFecha nacimientoDT = paciente.getFecNacimiento();
    LocalDate nacimiento = LocalDate.of(nacimientoDT.getAnio(),nacimientoDT.getMes(),nacimientoDT.getDia());
    LocalDate fechaDeReserva = LocalDate.of(fecha.getAnio(),fecha.getMes(),fecha.getDia());
    int edad = Period.between(nacimiento,fechaDeReserva).getYears();
    return edad > 60;
}


	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public BeneficioAnual getBeneficio() {
		return beneficio;
	}

	public void setBeneficioAnual(BeneficioAnual beneficio) {
		this.beneficio = beneficio;
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

	public DTFecha getFecha() {
		return fecha;
	}

	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}


	public float getCostoReserva() {
		return costoReserva;
	}

	
	
	public DTReserva crearDTReserva() {
		return new DTReserva(fecha, numeroCliente, costoReserva);
	}
	
	public DTReservaConsulta getDatosReservaConsulta() {
    	return new DTReservaConsulta(consulta.getNombre(), fecha, numeroCliente, costoReserva);
	}
}