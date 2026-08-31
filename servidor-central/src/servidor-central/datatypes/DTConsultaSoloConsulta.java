package datatypes;


public class DTConsultaSoloConsulta {
	private String nombreConsulta;
	private DTFecha fecha;
	private int nroSala;
	private int cantidadNumeros;
	private String nombreMedico;
	private float costo;
	

	
	public DTConsultaSoloConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros) {
		this(nombreConsulta, fecha, nroSala, cantidadNumeros, null, 0);
	}

	public DTConsultaSoloConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros,
			String nombreMedico, float costo) {
		this.nombreConsulta = nombreConsulta;
		this.fecha = fecha;
		this.nroSala = nroSala;
		this.cantidadNumeros = cantidadNumeros;
		this.nombreMedico = nombreMedico;
		this.costo = costo;
		
	}

	public String getNombreConsulta() { 
		return nombreConsulta; 
		}

	public DTFecha getFecha() { 
		return fecha; 
		}

	public int getNroSala() { 
		return nroSala; 
		}

	public int getCantidadNumeros() { 
		return cantidadNumeros; 
		}

	public String getNombreMedico() { 
		return nombreMedico; 
		}

	public float getCosto() { 
		return costo; 
		}

}