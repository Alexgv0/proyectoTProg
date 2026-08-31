package datatypes;

public class DTReservaDetallada {
	
	private DTFecha fechaReserva;
	private int numeroClienteReserva;
	private float costoReserva;
	private String nombreConsulta;
	private String nicknamePaciente;
	private Integer consultasGratisBeneficioAnual;
	
	public DTReservaDetallada(
			DTFecha fechaReserva, 
			int numeroClienteReserva, 
			float costoReserva, 
			String nombreConsulta,
			String nicknamePaciente,
			Integer consultasGratisBeneficioAnual
			) {
		// Info DTReserva
		this.fechaReserva = fechaReserva;
		this.numeroClienteReserva = numeroClienteReserva;
		this.costoReserva = costoReserva;
		// Clave DTConsulta
		this.nombreConsulta = nombreConsulta;
		// Clave Paciente
		this.nicknamePaciente = nicknamePaciente;
		// Info BeneficioAnual
		this.consultasGratisBeneficioAnual = consultasGratisBeneficioAnual; 
	}
	
	public DTFecha getFechaReserva() {
		return fechaReserva;
	}

	public int getNumeroClienteReserva() {
		return numeroClienteReserva;
	}

	public float getCostoReserva() {
		return costoReserva;
	}

	public String getNombreConsulta() {
		return nombreConsulta;
	}

	public String getNicknamePaciente() {
		return nicknamePaciente;
	}

	public int getConsultasGratisBeneficioAnual() {
		return consultasGratisBeneficioAnual;
	}
}
