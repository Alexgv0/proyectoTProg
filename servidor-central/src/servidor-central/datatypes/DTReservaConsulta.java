package datatypes;

public class DTReservaConsulta {
	private DTFecha fecha;
	private int num; 
	private float costo;
	
	public DTReservaConsulta(DTFecha fecha, int num, float costo) {
		this.fecha = fecha;
		this.num = num;
		this.costo = costo;
	}
	
	public DTFecha getFecha() {
		return fecha;
	}
	
	public int getNum() {
		return num;
	}
	
	public float getCosto() {
		return costo;
	}
}
