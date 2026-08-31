package datatypes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class DTPaciente extends DTUsuario{
	private final DTFecha fechaNacimiento;
    private final int documento;
    private final DTFecha fechaIngreso;
    private final Set<String> antecedentesMedicos;

	public DTPaciente(String nickname, String nombre, String apellido, String correo,DTFecha fechaNacimiento, int documento, DTFecha fechaIngreso,Set<String> antecedentesMedicos) {
        super(nickname, nombre, apellido, correo);
        this.fechaNacimiento = fechaNacimiento;
        this.documento = documento;
        this.fechaIngreso = fechaIngreso;
        this.antecedentesMedicos = Collections.unmodifiableSet(new LinkedHashSet<>(antecedentesMedicos));
    }

	public DTPaciente(String nickname, String nombre, String apellido, String correo,DTFecha fechaNacimiento) {
        this(nickname, nombre, apellido, correo, fechaNacimiento, 0,fechaNacimiento, Collections.emptySet());
    }

	public DTFecha getFechaNacimiento() { return fechaNacimiento; }
    public DTFecha getFecNacimiento() { return fechaNacimiento; }
    public int getDocumento() { return documento; }
    public DTFecha getFechaIngreso() { return fechaIngreso; }
    public Set<String> getAntecedentesMedicos() { return antecedentesMedicos; }
    public Set<String> getAntecedentesMed() { return antecedentesMedicos; }

    @Override
    public String getTipo() { return "Paciente"; }
}

		
	
	