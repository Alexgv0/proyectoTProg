package clases;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import datatypes.DTEspecialidad;

public class Medico extends Usuario {

    private int registro;
    private String mesLicencia;
    private int porcentajeConsultas;
    private String sitioWeb;

    private Set<DTEspecialidad> especialidades;
    private Map<String, Consulta> consultas;
    
    public Medico(
            String nickname,
            String nombre,
            String apellido,
            String email,
            int registro,
            String mesLicencia,
            String sitioWeb,
            Set<DTEspecialidad> especialidades,
            int porcentajeConsultas) {

        super(nickname, nombre, apellido, email);

        this.setRegistro(registro);
        this.setMesLicencia(mesLicencia);
        this.setSitioWeb(sitioWeb);
        this.setPorcentajeConsultas(porcentajeConsultas);

        this.setEspecialidades(especialidades);
        this.consultas = new HashMap<>();
    }

	public int getRegistro() {
		return registro;
	}

	public void setRegistro(int registro) {
		this.registro = registro;
	}

	public String getMesLicencia() {
		return mesLicencia;
	}

	public void setMesLicencia(String mesLicencia) {
		this.mesLicencia = mesLicencia;
	}

	public int getPorcentajeConsultas() {
		return porcentajeConsultas;
	}

	public void setPorcentajeConsultas(int porcentajeConsultas) {
		this.porcentajeConsultas = porcentajeConsultas;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public Set<DTEspecialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<DTEspecialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public Map<String, Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Map<String, Consulta> consultas) {
		this.consultas = consultas;
	}

}