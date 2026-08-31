package datatypes;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class DTConsulta {
	private String nombreConsulta;
	private DTFecha fecha;
	private int nroSala;
	private int cantidadNumeros;
	private String nombreMedico;
	private float costo;
	private Set<DTReserva> reservas;

	
	public DTConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros) {
        this(nombreConsulta, fecha, nroSala, cantidadNumeros, "", 0f, Collections.emptySet());
    }

    public DTConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros, String nombreMedico, float costo, Set<DTReserva> reservas) {
        super(nombreConsulta, fecha, nroSala, cantidadNumeros, nombreMedico, costo);
        this.reservas = Collections.unmodifiableSet(new LinkedHashSet<>(reservas));
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

	public Set<DTReserva> getReservas() { 
		return reservas; 
		}
}