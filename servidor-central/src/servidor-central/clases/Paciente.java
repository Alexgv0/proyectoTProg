package clases;

import java.util.Set;
import datatypes.DTFecha;

import java.util.HashSet;

public class Paciente extends Usuario {

    private DTFecha fecNacimiento;
    private int documento;
    private DTFecha fechaIngreso;
    private Set<String> antecedentesMed;
    private Set<BeneficioAnual> beneficios;
    
    
    public Paciente(
            String nickname,
            String nombre,
            String apellido,
            String email,
            DTFecha fecNacimiento,
            int documento,
            DTFecha fechaIngreso,
            Set<String> antecedentesMed) {

        super(nickname, nombre, apellido, email);

        this.fecNacimiento = fecNacimiento;
        this.documento = documento;
        this.fechaIngreso = fechaIngreso;
        this.antecedentesMed = antecedentesMed;
        
        this.beneficios = new HashSet<>();
    }
}