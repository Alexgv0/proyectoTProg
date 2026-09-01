package formularios;

import java.util.stream.Collectors;

import datatypes.DTBeneficioAnual;
import datatypes.DTClinica;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDiaConsultaCompleto;
import datatypes.DTMedico;
import datatypes.DTPaciente;
import datatypes.DTReservaConsulta;
import datatypes.DTUsuario;

final class Formato {

    private Formato() {
    }

    static String usuario(DTUsuario usuario) {
        StringBuilder texto = new StringBuilder();

        texto.append("Tipo: ").append(usuario.getTipo())
                .append("\nNickname: ").append(usuario.getNickname())
                .append("\nNombre: ").append(usuario.getNombre())
                .append("\nApellido: ").append(usuario.getApellido())
                .append("\nCorreo: ").append(usuario.getCorreo());

        if (usuario instanceof DTPaciente) {
            DTPaciente paciente = (DTPaciente) usuario;

            texto.append("\nFecha de nacimiento: ")
                    .append(paciente.getFechaNacimiento())
                    .append("\nDocumento: ").append(paciente.getDocumento())
                    .append("\nFecha de ingreso: ").append(paciente.getFechaIngreso())
                    .append("\nAntecedentes: ")
                    .append(String.join(", ", paciente.getAntecedentesMedicos()));

        } else if (usuario instanceof DTMedico) {
            DTMedico medico = (DTMedico) usuario;

            texto.append("\nRegistro: ").append(medico.getRegistro())
                    .append("\nMes de licencia: ").append(medico.getMesLicencia())
                    .append("\nSitio web: ").append(medico.getSitioWeb())
                    .append("\nEspecialidades: ")
                    .append(medico.getEspecialidades().stream()
                            .map(especialidad -> especialidad.getNombre())
                            .collect(Collectors.joining(", ")))
                    .append("\nPorcentaje adicional: ")
                    .append(medico.getPorcentajeConsultas()).append('%');
        }

        return texto.toString();
    }

    static String clinica(DTClinica clinica) {
        return "Nombre: " + clinica.getNombre()
                + "\nDescripcion: " + clinica.getDescripcion()
                + "\nDireccion: " + clinica.getDireccion()
                + "\nSitio web: " + clinica.getSitioWeb()
                + "\nCosto ticket: $ "
                + String.format("%.2f", clinica.getCostoMedicamento())
                + "\nCosto orden: $ "
                + String.format("%.2f", clinica.getCostoConsulta());
    }

    static String dia(DTDiaConsultaCompleto dia) {
        return "Nombre: " + dia.getNombre()
                + "\nDescripcion: " + dia.getDescripcion()
                + "\nDia: " + dia.getDia()
                + "\nHora de inicio: " + dia.getHoraInicio() + ":00"
                + "\nEspecialidad: " + dia.getEspecialidad()
                + "\nClinica: " + dia.getClinica()
                + "\nCosto: $ " + String.format("%.2f", dia.getCostoConsulta());
    }

    static String consulta(DTConsultaSoloConsulta consulta) {
        return "Nombre: " + consulta.getNombreConsulta()
                + "\nMedico: " + consulta.getNombreMedico()
                + "\nFecha: " + consulta.getFecha()
                + "\nSala: " + consulta.getNroSala()
                + "\nCantidad de numeros: " + consulta.getCantidadNumeros()
                + "\nCosto: $ " + String.format("%.2f", consulta.getCosto());
    }

    static String reserva(DTReservaConsulta reserva) {
        return "Consulta: " + reserva.getNombreConsulta()
                + "\nPaciente: " + reserva.getNickPaciente()
                + "\nFecha de reserva: " + reserva.getFecha()
                + "\nNumero otorgado: " + reserva.getNumeroCliente()
                + "\nCosto: $ " + String.format("%.2f", reserva.getCostoReserva());
    }

    static String beneficio(DTBeneficioAnual beneficio) {
        return "Anio: " + beneficio.getAnio()
                + "\nOrdenes gratuitas disponibles: "
                + beneficio.getConsultasGratis()
                + "\nTickets gratuitos disponibles: "
                + beneficio.getTicketsGratis();
    }
}