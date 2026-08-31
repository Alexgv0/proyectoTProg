package clases;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import datatypes.DTClinica;

public class Clinica {
    private final Map<String, DiaConsulta> diasConsulta = new LinkedHashMap<>();
    private final String nombre;
    private final String descripcion;
    private final String direccion;
    private final String sitioWeb;
    private final float costoMedicamento;
    private final float costoConsulta;

    public Clinica(String nombre, String descripcion, String direccion, String sitioWeb,
            float costoMedicamento, float costoConsulta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.sitioWeb = sitioWeb;
        this.costoMedicamento = costoMedicamento;
        this.costoConsulta = costoConsulta;
    }

    public DTClinica getDatosClinica() {
        return new DTClinica(nombre, descripcion, direccion, sitioWeb,
                costoMedicamento, costoConsulta);
    }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getDireccion() { return direccion; }
    public String getSitioWeb() { return sitioWeb; }
    public float getCostoMedicamento() { return costoMedicamento; }
    public float getCostoConsulta() { return costoConsulta; }
    public Map<String, DiaConsulta> getDiasConsulta() {
        return Collections.unmodifiableMap(diasConsulta);
    }
    public void agregarDiaConsulta(DiaConsulta dia) {
        diasConsulta.put(dia.getNombre(), dia);
    }
}
