package clases;

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

        this.registro = registro;
        this.mesLicencia = mesLicencia;
        this.sitioWeb = sitioWeb;
        this.especialidades = especialidades;
        this.porcentajeConsultas = porcentajeConsultas;
    }

}