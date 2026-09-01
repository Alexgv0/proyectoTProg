package datatypes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class DTMedico extends DTUsuario {
    private final int registro;
    private final String mesLicencia;
    private final String sitioWeb;
    private final Set<DTEspecialidad> especialidades;
    private final int porcentajeConsultas;

    public DTMedico(String nickname, String nombre, String apellido, String correo,
            int registro, String mesLicencia, String sitioWeb,
            Set<DTEspecialidad> especialidades, int porcentajeConsultas) {
        super(nickname, nombre, apellido, correo);
        this.registro = registro;
        this.mesLicencia = mesLicencia;
        this.sitioWeb = sitioWeb;
        this.especialidades = Collections.unmodifiableSet(new LinkedHashSet<>(especialidades));
        this.porcentajeConsultas = porcentajeConsultas;
    }

    public int getRegistro() { return registro; }
    public String getMesLicencia() { return mesLicencia; }
    public String getSitioWeb() { return sitioWeb; }
    public Set<DTEspecialidad> getEspecialidades() { return especialidades; }
    public int getPorcentajeConsultas() { return porcentajeConsultas; }
    public int getPorcentajeCostoConsulta() { return porcentajeConsultas; }

    @Override
    public String getTipo() { return "Medico"; }
}
