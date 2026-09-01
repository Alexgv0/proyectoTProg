package controladores;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import clases.BeneficioAnual;
import clases.Especialidad;
import clases.Medico;
import clases.Paciente;
import clases.Usuario;
import datatypes.DTBeneficioAnual;
import datatypes.DTEspecialidad;
import datatypes.DTFecha;
import datatypes.DTUsuario;
import interfaces.IControladorUsuarios;
import manejadores.ManejadorEspecialidades;
import manejadores.ManejadorUsuarios;

public class ControladorUsuarios implements IControladorUsuarios {
    private static final ControladorUsuarios INSTANCIA = new ControladorUsuarios();
    private final ManejadorUsuarios mu = ManejadorUsuarios.getInstancia();
    private final ManejadorEspecialidades me = ManejadorEspecialidades.getInstancia();

    private ControladorUsuarios() { }
    public static ControladorUsuarios getInstancia() { return INSTANCIA; }

    //cu altaUsuario
    
    @Override
    public Set<String> listarEspecialidades() {
        return me.listarEspecialidades().stream().map(Especialidad::getNombre).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }
    
    @Override
    public DTEspecialidad obtenerDatosEspecialidad(String nombre) {
        Especialidad especialidad = me.obtenerEspecialidad(nombre);
        if (especialidad == null) throw new IllegalArgumentException(
                "La especialidad indicada no existe.");
        return especialidad.getDatos();
    }
    
    
    /// SI QUEREMOS VALIDAR DATOS, SE PUEDE SACAR

    private void validarIdentidadNueva(String nickname, String correo) {
        Validaciones.texto(nickname, "nickname");
        Validaciones.texto(correo, "correo electronico");
        if (mu.existeUsuario(nickname.trim())) {
            throw new IllegalArgumentException("El nickname ya esta en uso.");
        }
        if (mu.existeCorreo(correo.trim())) {
            throw new IllegalArgumentException("El correo electronico ya esta en uso.");
        }
    }
    
    //ALTA USUARIO
    
    @Override
    public Usuario altaMedico(String nickname, String nombre, String apellido, String correo, int registro, String mesLicencia, String sitioWeb, Set<DTEspecialidad> especialidades, int porcentajeConsultas) {
        validarIdentidadNueva(nickname, correo);
        Validaciones.texto(nombre, "nombre");
        Validaciones.texto(apellido, "apellido");
        Validaciones.positivo(registro, "numero de registro");
        Validaciones.texto(mesLicencia, "mes de licencia");
        Validaciones.noNegativo(porcentajeConsultas, "porcentaje adicional");
        if (especialidades == null || especialidades.isEmpty()) {
            throw new IllegalArgumentException("El medico debe tener al menos una especialidad.");
        }
        
        Set<Especialidad> especialidadesDominio = new LinkedHashSet<>();
        
        for (DTEspecialidad dato : especialidades) {
            Especialidad especialidad = me.obtenerEspecialidad(dato.getNombre());
            
            if (especialidad == null) throw new IllegalArgumentException("La especialidad " + dato.getNombre() + " no existe."); 
            
            especialidadesDominio.add(especialidad);
        }
        Medico medico = new Medico(nickname.trim(), nombre.trim(), apellido.trim(), correo.trim(), registro, mesLicencia.trim(), sitioWeb == null ? "" : sitioWeb.trim(), especialidadesDominio, porcentajeConsultas);
        mu.agregarUsuario(medico);
        return medico;
    }

    
    @Override
    public Usuario altaPaciente(String nickname, String nombre, String apellido, String correo, DTFecha fecNacimiento, int documento, DTFecha fechaIngreso, Set<String> antecedentesMed) {
        validarIdentidadNueva(nickname, correo);
        Validaciones.texto(nombre, "nombre");
        Validaciones.texto(apellido, "apellido");
        Validaciones.positivo(documento, "numero de documento");
        
        if (fecNacimiento == null || fechaIngreso == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias.");
        }
        
        if (fechaIngreso.compareTo(fecNacimiento) < 0) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser anterior al nacimiento.");
        }
        return mu.crearPaciente(nickname.trim(), nombre.trim(), apellido.trim(), correo.trim(), fecNacimiento, documento, fechaIngreso, antecedentesMed == null ? Set.of() : antecedentesMed);
    }

    
    @Override
    public void altaUsuario(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("El usuario es obligatorio.");
        if (mu.existeUsuario(usuario.getNickname()) || mu.existeCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El nickname o el correo ya estan en uso.");
        }
        mu.agregarUsuario(usuario);
    }

    @Override
    public void cancelarAltaUsuario(Usuario usuario) { mu.cancelarUsuario(usuario); }

    @Override
    public Set<DTUsuario> listarUsuarios() { return mu.obtenerDatos(); }

    @Override
    public DTUsuario seleccionarUsuario(String nickname) {
        DTUsuario usuario = mu.obtenerDatosUsuario(nickname);
        if (usuario == null) throw new IllegalArgumentException("El usuario no existe.");
        return usuario;
    }

    @Override
    public Set<String> listarPacientes() {
        return mu.obtenerPacientes().stream().map(Paciente::getNickname).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<String> listarMedicos() {
        return mu.obtenerMedicos().stream().map(Medico::getNickname).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void modificarPaciente(String nickname, String nombre, String apellido, DTFecha nacimiento, int documento, DTFecha ingreso, Set<String> antecedentes) {
        Paciente paciente = mu.obtenerPaciente(nickname);
        
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        Validaciones.texto(nombre, "nombre");
        Validaciones.texto(apellido, "apellido");
        Validaciones.positivo(documento, "numero de documento");
        
        if (nacimiento == null || ingreso == null || ingreso.compareTo(nacimiento) < 0) {
            throw new IllegalArgumentException("Las fechas del paciente no son validas.");
        }
        
        paciente.setNombre(nombre.trim());
        paciente.setApellido(apellido.trim());
        paciente.setFecNacimiento(nacimiento);
        paciente.setDocumento(documento);
        paciente.setFechaIngreso(ingreso);
        paciente.setAntecedentesMed(antecedentes == null ? Set.of() : antecedentes);
    }

    
    @Override
    public void modificarMedico(String nickname, String nombre, String apellido, int registro, String mesLicencia, String sitioWeb, Set<String> especialidades, int porcentajeConsultas) {
        Medico medico = mu.obtenerMedico(nickname);
        if (medico == null) throw new IllegalArgumentException("El medico no existe.");
        Validaciones.texto(nombre, "nombre");
        Validaciones.texto(apellido, "apellido");
        Validaciones.positivo(registro, "numero de registro");
        Validaciones.texto(mesLicencia, "mes de licencia");
        Validaciones.noNegativo(porcentajeConsultas, "porcentaje adicional");
        if (especialidades == null || especialidades.isEmpty()) {
            throw new IllegalArgumentException(
                    "El medico debe tener al menos una especialidad.");
        }
        
        Set<Especialidad> nuevas = new LinkedHashSet<>();
        for (String nombreEspecialidad : especialidades) {
            Especialidad especialidad = me.obtenerEspecialidad(nombreEspecialidad);
            if (especialidad == null) throw new IllegalArgumentException("La especialidad " + nombreEspecialidad + " no existe.");
            nuevas.add(especialidad);
        }
        
        medico.setNombre(nombre.trim());
        medico.setApellido(apellido.trim());
        medico.setRegistro(registro);
        medico.setMesLicencia(mesLicencia.trim());
        medico.setSitioWeb(sitioWeb == null ? "" : sitioWeb.trim());
        medico.setEspecialidades(nuevas);
        medico.setPorcentajeCostoConsulta(porcentajeConsultas);
    }

    @Override
    public boolean ingresarDatosAltaEspecialidad(String nombre, int porcentajeAdicional) {
        String nombreLimpio = Validaciones.texto(nombre, "nombre");
        Validaciones.noNegativo(porcentajeAdicional, "porcentaje adicional");
        if (me.existeEspecialidad(nombreLimpio)) return false;
        me.altaEspecialidad(nombreLimpio, porcentajeAdicional);
        return true;
    }

    @Override
    public void altaBeneficioAnual(String nickPaciente, int anio, int consultasGratis, int ticketsGratis) {
        Paciente paciente = mu.obtenerPaciente(nickPaciente);
       
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        Validaciones.positivo(anio, "anio");
        Validaciones.noNegativo(consultasGratis, "consultas gratuitas");
        Validaciones.noNegativo(ticketsGratis, "tickets gratuitos");
        
        if (paciente.tieneBeneficio(anio)) throw new IllegalArgumentException("El paciente ya tiene un beneficio para ese anio.");
        
        paciente.agregarBeneficio(new BeneficioAnual(anio, ticketsGratis, consultasGratis));
    }

    @Override
    public Set<DTBeneficioAnual> listarBeneficiosAnuales(String nickPaciente) {
        Paciente paciente = mu.obtenerPaciente(nickPaciente);
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        return paciente.listarBeneficios();
    }

    @Override
    public DTBeneficioAnual seleccionarBeneficioAnual(String nickPaciente, int anio) {
        Paciente paciente = mu.obtenerPaciente(nickPaciente);
        if (paciente == null) throw new IllegalArgumentException("El paciente no existe.");
        BeneficioAnual beneficio = paciente.obtenerBeneficio(anio);
        if (beneficio == null) throw new IllegalArgumentException("El beneficio no existe.");
        return beneficio.getDatos();
    }
}
