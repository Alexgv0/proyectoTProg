package clases;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import datatypes.DTConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDatosConsulta;
import datatypes.DTFecha;
import datatypes.DTReserva;

public class Consulta {
    private final DiaConsulta diaConsulta;
    private final Medico medico;
    private final Map<String, Reserva> reservas = new LinkedHashMap<>();
    private final String nombre;
    private final DTFecha fecha;
    private final int numeroSala;
    private final int numsDisponibles;
    private final float costoConsulta;

    public Consulta(String nombre, DTFecha fecha, int numeroSala, int numsDisponibles,
            DiaConsulta diaConsulta, Medico medico) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.numeroSala = numeroSala;
        this.numsDisponibles = numsDisponibles;
        this.diaConsulta = diaConsulta;
        this.medico = medico;
        this.costoConsulta = diaConsulta.getCostoConsulta()
                * (1 + medico.getPorcentajeCostoConsulta() / 100.0f);
    }

    public boolean perteneceMedicoConsulta(String nickname) {
        return medico.getNickname().equals(nickname);
    }
    public boolean tieneReservaPaciente(Paciente paciente) {
        return reservas.containsKey(paciente.getNickname());
    }
    public boolean estaCompleta() { return reservas.size() >= numsDisponibles; }
    public int obtenerMenorNumeroDisponible() {
        Set<Integer> usados = reservas.values().stream()
                .map(Reserva::getNumeroCliente).collect(Collectors.toSet());
        for (int numero = 1; numero <= numsDisponibles; numero++) {
            if (!usados.contains(numero)) return numero;
        }
        throw new IllegalStateException("No hay numeros disponibles.");
    }
    public void agregarReserva(Reserva reserva) {
        reservas.put(reserva.getPaciente().getNickname(), reserva);
    }
    public DTConsulta getDatosConsulta() {
        Set<DTReserva> datosReservas = reservas.values().stream()
                .map(Reserva::crearDTReserva)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return new DTConsulta(nombre, fecha, numeroSala, numsDisponibles,
                medico.getNickname(), costoConsulta, datosReservas);
    }
    public DTConsultaSoloConsulta getDatosSoloConsulta() {
        return new DTConsultaSoloConsulta(nombre, fecha, numeroSala,
                numsDisponibles, medico.getNickname(), costoConsulta);
    }
    public DTDatosConsulta obtenerDatosConsulta() {
        return new DTDatosConsulta(numsDisponibles, costoConsulta);
    }
    public DTDatosConsulta obtenerDatosConsulta(String nombreConsulta) {
        return obtenerDatosConsulta();
    }
    public String getNombre() { return nombre; }
    public DiaConsulta getDiaConsulta() { return diaConsulta; }
    public Medico getMedico() { return medico; }
    public String getNicknameMedico() { return medico.getNickname(); }
    public Map<String, Reserva> getReservas() {
        return Collections.unmodifiableMap(reservas);
    }
    public DTFecha getFecha() { return fecha; }
    public int getNumeroSala() { return numeroSala; }
    public int getNumsDisponibles() { return numsDisponibles; }
    public float getCostoConsulta() { return costoConsulta; }
}