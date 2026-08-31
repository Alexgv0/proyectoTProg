package clases;

public class BeneficioAnual{
	private int ticketsGratis;
	private int consultasGratis;
	
	public BeneficioAnual(int ticketsGratis, int consultasGratis) {
		this.setTicketsGratis(ticketsGratis);
		this.setConsultasGratis(consultasGratis);
	}

	public int getTicketsGratis() {
		return ticketsGratis;
	}	

	public void setTicketsGratis(int ticketsGratis) {
		this.ticketsGratis = ticketsGratis;
	}

	public int getConsultasGratis() {
		return consultasGratis;
	}

	public void setConsultasGratis(int consultasGratis) {
		this.consultasGratis = consultasGratis;
	}
}