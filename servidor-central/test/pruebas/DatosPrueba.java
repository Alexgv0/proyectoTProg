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
import fabrica.Fabrica;
import interfaces.IControladorUsuarios;

public class DatosPrueba {

    private static final String RUTA_CSV =
            "src/servidor-central/CSVs/";

    private static final IControladorUsuarios controladorUsuarios =
            Fabrica.getInstancia().getControladorUsuarios();

    // Ref -> datos básicos del usuario
    private static final Map<String, String[]> usuarios =
            new HashMap<>();


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
                String costoTicketMedicamento = datos[5];
                String costoOrdenConsulta = datos[6];

                usuarios.put(
                        ref,
                        new String[]{
                                ref,
                                nombre,
                                descripcion,
                                direccion,
                                sitioWeb,
                                costoTicketMedicamento,
                                costoOrdenConsulta
                        }
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Clinicas.csv",
                    e
            );
        }
    }
    
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
                String diaSemana = datos[3];
                String horaInicio = datos[4];
                String especialidad = datos[5];
                String clinica = datos[6];
                String costo = datos[7];

                usuarios.put(
                        ref,
                        new String[]{
                                ref,
                                nombre,
                                descripcion,
                                diaSemana,
                                horaInicio,
                                especialidad,
                                clinica,
                                costo
                        }
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026DiaConsulta.csv",
                    e
            );
        }
    }
    
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
                String fecha = datos[2];
                String nroSala = datos[3];
                String cantNumeros = datos[4];
                String diaConsulta = datos[5];
                String medico = datos[6];
                String costo = datos[7];

                usuarios.put(
                        ref,
                        new String[]{
                                ref,
                                nombre,
                                fecha,
                                nroSala,
                                cantNumeros,
                                diaConsulta,
                                medico,
                                costo
                        }
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Consultas.csv",
                    e
            );
        }
    }
    
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

                String ref = datos[0];
                String fechaReserva = datos[1];
                String numero = datos[2];
                String paciente = datos[3];
                String consulta = datos[4];
                String costo = datos[5];

                usuarios.put(
                        ref,
                        new String[]{
                                ref,
                                fechaReserva,
                                numero,
                                paciente,
                                consulta ,
                                costo,
                        }
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026Reservas.csv",
                    e
            );
        }
    }
    
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

                String ref = datos[0];
                String paciente = datos[1];
                String anio = datos[2];
                String cantConsultas = datos[3];
                String cantMedicamentos = datos[4];

                usuarios.put(
                        ref,
                        new String[]{
                                ref,
                                paciente,
                                anio,
                                cantConsultas,
                                cantMedicamentos,
                        }
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "No se pudo leer 2026BeneficiosAnuales.csv",
                    e
            );
        }
    }
    
    public static void cargarTodosLosDatos() {

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