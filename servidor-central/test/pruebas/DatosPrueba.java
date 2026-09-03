package pruebas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DTFecha;
import datatypes.DTEspecialidad;
import enums.Dia;
import fabrica.Fabrica;
import interfaces.IControladorClinicas;
import interfaces.IControladorReservas;
import interfaces.IControladorUsuarios;
import manejadores.ManejadorClinicas;
import manejadores.ManejadorConsultas;
import manejadores.ManejadorDiasConsulta;
import manejadores.ManejadorEspecialidades;
import manejadores.ManejadorUsuarios;

public class DatosPrueba {

    private static final String RUTA_CSV =
            "src/servidor-central/CSVs/";

    private static final IControladorUsuarios controladorUsuarios =
            Fabrica.getInstancia().getControladorUsuarios();

    private static final IControladorClinicas controladorClinicas =
            Fabrica.getInstancia().getControladorClinicas();

    private static final IControladorReservas controladorReservas =
            Fabrica.getInstancia().getControladorReservas();

    // Ref -> datos básicos del usuario
    private static final Map<String, String[]> usuarios =
            new HashMap<>();

    // Ref -> nombre real ya dado de alta (para poder referenciarlos desde otros CSV)
    private static final Map<String, String> clinicasPorRef = new HashMap<>();
    private static final Map<String, String> diasConsultaPorRef = new HashMap<>();
    private static final Map<String, String> consultasPorRef = new HashMap<>();

    
    public static void cargarEspecialidades() {

        String ruta = RUTA_CSV + "2026Especialidades.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); // encabezado

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String nombre = datos[1];
                int porcentaje = Integer.parseInt(datos[2]);

                controladorUsuarios.ingresarDatosAltaEspecialidad(
                        nombre,
                        porcentaje
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Especialidades.csv",
                    e
            );
        }
    }

    /**
     * Lee los datos básicos de los usuarios.
     *
     * No crea todavía pacientes ni médicos.
     */
    public static void cargarUsuarios() {

        String ruta = RUTA_CSV + "2026Usuarios.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); // encabezado

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String ref = datos[0];
                String nickname = datos[2];
                String nombre = datos[3];
                String apellido = datos[4];
                String email = datos[5];

                usuarios.put(
                        ref,
                        new String[]{
                                nickname,
                                nombre,
                                apellido,
                                email
                        }
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Usuarios.csv",
                    e
            );
        }
    }

    public static String[] obtenerUsuarioPorRef(String ref) {
    	return usuarios.get(ref);
    }
    
    /**
     * Carga los pacientes oficiales.
     */
    public static void cargarPacientes() {

        String ruta = RUTA_CSV + "2026Pacientes.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); // encabezado

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String ref = datos[0];

                String[] datosUsuario = usuarios.get(ref);

                if (datosUsuario == null) {
                    throw new IllegalArgumentException(
                            "No existe el usuario asociado a la referencia: "
                                    + ref
                    );
                }

                String nickname = datosUsuario[0];
                String nombre = datosUsuario[1];
                String apellido = datosUsuario[2];
                String email = datosUsuario[3];

                DTFecha fechaNacimiento =
                        convertirFecha(datos[1]);

                DTFecha fechaIngreso =
                        convertirFecha(datos[2]);

                int documento =
                        Integer.parseInt(datos[3]);

                Set<String> antecedentes =
                        new HashSet<>();

                if (!datos[4].trim().isEmpty()) {
                    antecedentes.add(datos[4].trim());
                }

                controladorUsuarios.altaPaciente(
                        nickname,
                        nombre,
                        apellido,
                        email,
                        fechaNacimiento,
                        documento,
                        fechaIngreso,
                        antecedentes
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Pacientes.csv",
                    e
            );
        }
    }

    /**
     * Carga los médicos oficiales.
     */
    public static void cargarMedicos() {

        String ruta = RUTA_CSV + "2026Medicos.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); // encabezado

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String ref = datos[0];

                String[] datosUsuario = usuarios.get(ref);

                if (datosUsuario == null) {
                    throw new IllegalArgumentException(
                            "No existe el usuario asociado a la referencia: "
                                    + ref
                    );
                }

                String nickname = datosUsuario[0];
                String nombre = datosUsuario[1];
                String apellido = datosUsuario[2];
                String email = datosUsuario[3];

                // REG1001 -> 1001
                String registroTexto = datos[1].replace("REG", "");
                int registro = Integer.parseInt(registroTexto);

                String mesLicencia = datos[2];

                String sitioWeb = datos[3];

                int porcentajeConsultas =
                        Integer.parseInt(datos[4]);

                Set<DTEspecialidad> especialidades =
                        new HashSet<>();

                String[] referenciasEspecialidades =
                        datos[5].split(",");

                for (String refEspecialidad :
                        referenciasEspecialidades) {

                    refEspecialidad =
                            refEspecialidad.trim();

                    String nombreEspecialidad =
                            obtenerNombreEspecialidad(refEspecialidad);

                    especialidades.add(
                            controladorUsuarios
                                    .obtenerDatosEspecialidad(
                                            nombreEspecialidad
                                    )
                    );
                }

                controladorUsuarios.altaMedico(
                        nickname,
                        nombre,
                        apellido,
                        email,
                        registro,
                        mesLicencia,
                        sitioWeb,
                        especialidades,
                        porcentajeConsultas
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Medicos.csv",
                    e
            );
        }
    }

    /**
     * Carga las clínicas oficiales.
     */
    public static void cargarClinicas() {

        String ruta = RUTA_CSV + "2026Clinicas.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); // encabezado

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String ref = datos[0];
                String nombre = datos[1];
                String descripcion = datos[2];
                String direccion = datos[3];
                String sitioWeb = datos[4];
                float costoTicketMedicamento = Float.parseFloat(datos[5]);
                float costoOrdenConsulta = Float.parseFloat(datos[6]);

                controladorClinicas.altaClinica(
                        nombre,
                        descripcion,
                        direccion,
                        sitioWeb,
                        costoTicketMedicamento,
                        costoOrdenConsulta
                );

                clinicasPorRef.put(ref, nombre);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Clinicas.csv",
                    e
            );
        }
    }

    /**
     * Carga los días de consulta oficiales.
     */
    public static void cargarDiasConsulta() {

        String ruta = RUTA_CSV + "2026DiaConsulta.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine();

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String ref = datos[0];
                String nombre = datos[1];
                String descripcion = datos[2];
                Dia diaSemana = convertirDia(datos[3]);
                int horaInicio = convertirHora(datos[4]);
                String nombreEspecialidad = obtenerNombreEspecialidad(datos[5]);
                String nombreClinica = clinicasPorRef.get(datos[6]);

                if (nombreClinica == null) {
                    throw new IllegalArgumentException(
                            "No existe la clínica asociada a la referencia: " + datos[6]
                    );
                }

                // datos[7] (Costo) no se usa: el costo del día de consulta
                // se calcula solo a partir del costo de la clínica y el
                // porcentaje adicional de la especialidad.

                controladorClinicas.altaDiaConsulta(
                        nombre,
                        descripcion,
                        diaSemana,
                        horaInicio,
                        nombreEspecialidad,
                        nombreClinica
                );

                diasConsultaPorRef.put(ref, nombre);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026DiaConsulta.csv",
                    e
            );
        }
    }

    /**
     * Carga las consultas oficiales.
     */
    public static void cargarConsultas() {

        String ruta = RUTA_CSV + "2026Consultas.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine();

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String ref = datos[0];
                String nombre = datos[1];
                DTFecha fecha = convertirFecha(datos[2]);
                int nroSala = Integer.parseInt(datos[3]);
                int cantNumeros = Integer.parseInt(datos[4]);

                String nombreDiaConsulta = diasConsultaPorRef.get(datos[5]);

                if (nombreDiaConsulta == null) {
                    throw new IllegalArgumentException(
                            "No existe el día de consulta asociado a la referencia: " + datos[5]
                    );
                }

                String[] datosMedico = usuarios.get(datos[6]);

                if (datosMedico == null) {
                    throw new IllegalArgumentException(
                            "No existe el médico asociado a la referencia: " + datos[6]
                    );
                }

                String nicknameMedico = datosMedico[0];

                // datos[7] (Costo) no se usa: se calcula solo a partir del
                // costo del día de consulta y el porcentaje del médico.

                controladorClinicas.altaConsulta(
                        nombre,
                        fecha,
                        nroSala,
                        cantNumeros,
                        nombreDiaConsulta,
                        nicknameMedico
                );

                consultasPorRef.put(ref, nombre);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Consultas.csv",
                    e
            );
        }
    }

    /**
     * Carga las reservas oficiales.
     */
    public static void cargarReservas() {

        String ruta = RUTA_CSV + "2026Reservas.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine();

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                DTFecha fechaReserva = convertirFecha(datos[1]);
                // datos[2] (Numero) no se usa: el número de cliente lo
                // calcula el sistema solo (menor número disponible).

                String[] datosPaciente = usuarios.get(datos[3]);

                if (datosPaciente == null) {
                    throw new IllegalArgumentException(
                            "No existe el paciente asociado a la referencia: " + datos[3]
                    );
                }

                String nickPaciente = datosPaciente[0];

                String nombreConsulta = consultasPorRef.get(datos[4]);

                if (nombreConsulta == null) {
                    throw new IllegalArgumentException(
                            "No existe la consulta asociada a la referencia: " + datos[4]
                    );
                }

                // datos[5] (Costo) no se usa: se calcula solo.

                controladorReservas.altaReservaConsulta(
                        nickPaciente,
                        nombreConsulta,
                        fechaReserva
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Reservas.csv",
                    e
            );
        }
    }

    /**
     * Carga los beneficios anuales oficiales.
     */
    public static void cargarBeneficiosAnuales() {

        String ruta = RUTA_CSV + "2026BeneficiosAnuales.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); // encabezado

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String[] datosPaciente = usuarios.get(datos[1]);

                if (datosPaciente == null) {
                    throw new IllegalArgumentException(
                            "No existe el paciente asociado a la referencia: " + datos[1]
                    );
                }

                String nickPaciente = datosPaciente[0];
                int anio = Integer.parseInt(datos[2]);
                int cantConsultas = Integer.parseInt(datos[3]);
                int cantMedicamentos = Integer.parseInt(datos[4]);

                controladorUsuarios.altaBeneficioAnual(
                        nickPaciente,
                        anio,
                        cantConsultas,
                        cantMedicamentos
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026BeneficiosAnuales.csv",
                    e
            );
        }
    }

    /**
     * Convierte una fecha DD/MM/YYYY a DTFecha.
     */
    private static DTFecha convertirFecha(String fecha) {

        String[] partes = fecha.split("/");

        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);

        return new DTFecha(dia, mes, anio);
    }

    /**
     * Convierte "09:00" / "14:00" / "10:30" a la hora entera (9, 14, 10).
     */
    private static int convertirHora(String hora) {
        String[] partes = hora.split(":");
        return Integer.parseInt(partes[0]);
    }

    /**
     * Convierte "Lunes".."Domingo" (con o sin tilde) al enum Dia.
     */
    private static Dia convertirDia(String diaSemana) {

        String normalizado = diaSemana.trim().toUpperCase()
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U");

        switch (normalizado) {
            case "LUNES": return Dia.LUNES;
            case "MARTES": return Dia.MARTES;
            case "MIERCOLES": return Dia.MIERCOLES;
            case "JUEVES": return Dia.JUEVES;
            case "VIERNES": return Dia.VIERNES;
            case "SABADO": return Dia.SABADO;
            case "DOMINGO": return Dia.DOMINGO;
            default:
                throw new IllegalArgumentException(
                        "Día de la semana desconocido: " + diaSemana
                );
        }
    }

    /**
     * Obtiene el nombre de una especialidad a partir de su Ref.
     */
    private static String obtenerNombreEspecialidad(
            String refEspecialidad) {

        switch (refEspecialidad) {

            case "ES01":
                return "Cardiología";

            case "ES02":
                return "Pediatría";

            case "ES03":
                return "Medicina General";

            default:
                throw new IllegalArgumentException(
                        "Referencia de especialidad desconocida: "
                                + refEspecialidad
                );
        }
    }

    /**
     * Los Manejadores son singletons compartidos por todo el proceso, y las
     * tres clases de test (ControladorClinicasTest, ControladorReservasTest,
     * ControladorUsuariosTest) llaman cada una a cargarTodosLosDatos() en su
     * propio @BeforeAll. Si se corren juntas en la misma corrida de JUnit,
     * la segunda y la tercera intentan volver a dar de alta los mismos
     * nicknames/nombres ya cargados por la primera y explotan con
     * "ya existe/ya está en uso". Por eso se limpia todo antes de recargar.
     */
    private static void limpiarTodo() {
        ManejadorUsuarios.getInstancia().limpiar();
        ManejadorClinicas.getInstancia().limpiar();
        ManejadorDiasConsulta.getInstancia().limpiar();
        ManejadorConsultas.getInstancia().limpiar();
        ManejadorEspecialidades.getInstancia().limpiar();

        usuarios.clear();
        clinicasPorRef.clear();
        diasConsultaPorRef.clear();
        consultasPorRef.clear();
    }

    public static void cargarTodosLosDatos() {

        limpiarTodo();

        cargarEspecialidades();
        cargarUsuarios();
        cargarPacientes();
        cargarMedicos();
        cargarClinicas();
        cargarDiasConsulta();
        cargarConsultas();
        cargarReservas();
        cargarBeneficiosAnuales();
    }
}
