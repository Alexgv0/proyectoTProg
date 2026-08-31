package clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import datatypes.DTEspecialidad;

public class Medico extends Usuario {
    private int registro;
    private String mesLicencia;
    private int porcentajeCostoConsulta;
    private String sitioWeb;
    private Set<Especialidad> especialidades;
    private Map<String, Consulta> consultas;

    public Medico(String nickname,
			String nombre,
            String apellido,
            String email,
            int registro,
            String mesLicencia,
            String sitioWeb,
            Set<DTEspecialidad> especialidades,
            int porcentajeCostoConsulta) {
        super(nickname, nombre, apellido, email);
        this.setRegistro(registro);
        this.setMesLicencia(mesLicencia);
        this.setSitioWeb(sitioWeb);
        this.setPorcentajeCostoConsulta(porcentajeCostoConsulta);
        this.setEspecialidades(¡especialidades)¡);
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

    public int getPorcentajeCostoConsulta() {
        return porcentajeCostoConsulta;
    }

    public void setPorcentajeCostoConsulta(int porcentajeCostoConsulta) {
        this.porcentajeCostoConsulta = porcentajeCostoConsulta;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public Set<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Map<String, Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Map<String, Consulta> consultas) {
        this.consultas = consultas;
    }
}