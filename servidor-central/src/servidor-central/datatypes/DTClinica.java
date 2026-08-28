package datatypes;

public class DTClinica {
	
	private String nombre; 
	private String descripcion;
	private String direccion;
	private String sitioWeb;
	private float costoMedicamento;
	private float costoConsulta;
	
	public DTClinica(
			String nombre, 
			String descripcion, 
			String direccion, 
			String sitioWeb,
			float costoMedicamento, 
			float costoConsulta
			) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.sitioWeb = sitioWeb;
		this.costoMedicamento = costoMedicamento;
		this.costoConsulta = costoConsulta;
	}
}
