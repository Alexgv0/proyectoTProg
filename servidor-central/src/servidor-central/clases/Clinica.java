package clases;

import java.util.HashMap;
import java.util.Map;

import datatypes.DTClinica;

public class Clinica{
	
	private Map<String, DiaConsulta> diasConsulta;

	private String nombre;
	private String descripcion;
	private String direccion;
	private String sitioWeb;
	private float costoMedicamento;
	private float costoConsulta;
	
	public Clinica(
			String nom, 
			String des, 
			String dir, 
			String sw, 
			float costoMed, 
			float costoCon
			) {
		this.nombre = nom;
		this.descripcion = des;
		this.direccion = dir;
		this.sitioWeb = sw;
		this.costoMedicamento = costoMed;
		this.costoConsulta = costoCon;
		
		this.diasConsulta = new HashMap<>();
	}
	
	public DTClinica getDatosClinica() {
		return new DTClinica(
				this.nombre,
				this.descripcion,
				this.direccion,
				this.sitioWeb,
				this.costoMedicamento,
				this.costoConsulta
				);
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public float getCostoConsulta() {
        return this.costoConsulta;
    }

	public Map<String, DiaConsulta> getDiasConsulta() {
		return diasConsulta;
	}
	
}