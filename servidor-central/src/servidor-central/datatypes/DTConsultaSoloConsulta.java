package datatypes;

import java.util.Objects;

public class DTConsultaSoloConsulta {
    private final String nombreConsulta;
    private final DTFecha fecha;
    private final int nroSala;
    private final int cantidadNumeros;
    private final String nombreMedico;
    private final float costo;

    public DTConsultaSoloConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros) {
        this(nombreConsulta, fecha, nroSala, cantidadNumeros, "", 0f);
    }

    public DTConsultaSoloConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros, String nombreMedico, float costo) {
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