package datatypes;
import enums.Dia;

public class DTDiaConsultaCompleto {
    private String nombre;
    private String descripcion;
    private Dia dia;
    private String horaInicio;
    private float costoConsulta;

    public DTDiaConsultaCompleto(String nombre, String descripcion, Dia dia, String horaInicio, float costoConsulta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.costoConsulta = costoConsulta;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public float getCostoConsulta() {
        return costoConsulta;
    }
}