package clases;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import datatypes.DTEspecialidad;
import datatypes.DTMedico;

public class Medico extends Usuario {
    private int registro;
    private String mesLicencia;
    private int porcentajeCostoConsulta;
    private String sitioWeb;
    private Set<Especialidad> especialidades;
    private final Map<String, Consulta> consultas = new LinkedHashMap<>();

    public Medico(String nickname, String nombre, String apellido, String correo, int registro, String mesLicencia, String sitioWeb, Set<Especialidad> especialidades, int porcentajeCostoConsulta) {
        super(nickname, nombre, apellido, correo);
        this.registro = registro;
        this.mesLicencia = mesLicencia;
        this.sitioWeb = sitioWeb;
        this.especialidades = new LinkedHashSet<>(especialidades);
        this.porcentajeCostoConsulta = porcentajeCostoConsulta;
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
        return Collections.unmodifiableSet(especialidades);
    }
    
    public void setEspecialidades(Set<Especialidad> especialidades) {
        this.especialidades = new LinkedHashSet<>(especialidades);
    }
    
    public Map<String, Consulta> getConsultas() {
        return Collections.unmodifiableMap(consultas);
    }

    public void agregarConsulta(Consulta consulta) {
        consultas.put(consulta.getNombre(), consulta);
    }
    
    public boolean tieneEspecialidad(String nombreEspecialidad) {
        return especialidades.stream().anyMatch(e -> e.getNombre().equals(nombreEspecialidad));
    }

    @Override
    public DTMedico getDatos() {
        Set<DTEspecialidad> datosEspecialidades = especialidades.stream().map(Especialidad::getDatos).collect(Collectors.toCollection(LinkedHashSet::new));
        return new DTMedico(getNickname(), getNombre(), getApellido(), getCorreo(), registro, mesLicencia, sitioWeb, datosEspecialidades, porcentajeCostoConsulta);
    }
}
