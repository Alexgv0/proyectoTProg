package interfaces;

import java.util.Set;

import clases.Usuario;
import datatypes.DTBeneficioAnual;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;
import datatypes.DTUsuario;

public interface IControladorUsuarios {
    Set<String> listarEspecialidades();
    DTEspecialidad obtenerDatosEspecialidad(String nombre);

    Usuario altaMedico(String nickname, String nombre, String apellido, String correo,
            int registro, String mesLicencia, String sitioWeb,
            Set<DTEspecialidad> especialidades, int porcentajeConsultas);
    Usuario altaPaciente(String nickname, String nombre, String apellido, String correo,
            DTFecha fecNacimiento, int documento, DTFecha fechaIngreso,
            Set<String> antecedentesMed);
    void altaUsuario(Usuario usuario);
    void cancelarAltaUsuario(Usuario usuario);

    Set<DTUsuario> listarUsuarios();
    DTUsuario seleccionarUsuario(String nickname);
    Set<String> listarPacientes();
    Set<String> listarMedicos();

    void modificarPaciente(String nickname, String nombre, String apellido,
            DTFecha fecNacimiento, int documento, DTFecha fechaIngreso,
            Set<String> antecedentesMed);
    void modificarMedico(String nickname, String nombre, String apellido, int registro,
            String mesLicencia, String sitioWeb, Set<String> especialidades,
            int porcentajeConsultas);

    boolean ingresarDatosAltaEspecialidad(String nombre, int porcentajeAdicional);

    void altaBeneficioAnual(String nickPaciente, int anio, int consultasGratis,
            int ticketsGratis);
    Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickPaciente);
    DTBeneficioAnual seleccionarBeneficioAnual(String nickPaciente, int anio);
}