package datatypes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import enums.Dia;

public final class DTDiaConsultaCompleto {
    private final String nombre;
    private final String descripcion;
    private final Dia dia;
    private final int horaInicio;
    private final String especialidad;
    private final String clinica;
    private final float costoConsulta;
    private final Set<DTConsultaSoloConsulta> consultas;


    public DTDiaConsultaCompleto(String nombre, String descripcion, Dia dia, int horaInicio, String especialidad, String clinica, float costoConsulta, Set<DTConsultaSoloConsulta> consultas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.especialidad = especialidad;
        this.clinica = clinica;
        this.costoConsulta = costoConsulta;
        this.consultas = Collections.unmodifiableSet(new LinkedHashSet<>(consultas));
    }

    public DTDiaConsultaCompleto(String nombre, String descripcion, Dia dia, int horaInicio, float costoConsulta) {
        this(nombre, descripcion, dia, horaInicio, "", "", costoConsulta, Collections.emptySet());
    }

    public String getNombre() {
    	return nombre; 
    }
    
    public String getDescripcion() {
    	return descripcion;
    }
    
    public Dia getDia() {
    	return dia;
    }
    
    public int getHoraInicio() { 
    	return horaInicio;
    }
    
    public String getEspecialidad() {
    	return especialidad;
    }
    
    public String getClinica() {
    	return clinica;
    }
    
    public float getCostoConsulta() {
    	return costoConsulta;
    }
    
    public Set<DTConsultaSoloConsulta> getConsultas() {
    	return consultas;
    }

}