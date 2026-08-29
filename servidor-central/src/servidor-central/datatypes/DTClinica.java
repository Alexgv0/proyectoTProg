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
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public float getCostoMedicamento() {
		return costoMedicamento;
	}

	public float getCostoConsulta() {
		return costoConsulta;
	}

}
