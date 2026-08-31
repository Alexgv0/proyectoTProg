package clases;
import datatypes.DTBeneficioAnual;

public class BeneficioAnual{
	private int anio;
	private int ticketsGratis;
	private int consultasGratis;
	
	public BeneficioAnual(int anio, int ticketsGratis, int consultasGratis) {
        this.anio = anio;
        this.ticketsGratis = ticketsGratis;
        this.consultasGratis = consultasGratis;
    }

 	public int getAnio() {return anio;}
	public int getTicketsGratis() {return ticketsGratis;}
	public int getConsultasGratis() {return consultasGratis;}
	
	public void setTicketsGratis(int ticketsGratis) {this.ticketsGratis = ticketsGratis;}
	public void setConsultasGratis(int consultasGratis) {this.consultasGratis = consultasGratis;}

	public void consumirConsultaGratis() {
        if (consultasGratis <= 0) {
            throw new IllegalStateException(
                "El beneficio no tiene consultas gratuitas disponibles"
            );
        }

        consultasGratis--;
	}
	public DTBeneficioAnual getDatos() {
        return new DTBeneficioAnual(anio, ticketsGratis, consultasGratis);
    }
}
