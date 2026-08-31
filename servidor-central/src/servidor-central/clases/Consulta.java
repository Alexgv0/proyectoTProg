package clases;

import java.util.HashSet;
import java.util.Set;

import datatypes.DTConsulta;
import datatypes.DTConsultaSoloConsulta;
import datatypes.DTDatosConsulta;
import datatypes.DTFecha;
import datatypes.DTReserva;

public class Consulta {
    private DiaConsulta diaConsulta;
    private Medico medico;
    private Set<Reserva> reservas;

    private String nombre;
    private DTFecha fecha;
    private int numeroSala;
    private int numsDisponibles;
    private float costoConsulta;

    public Consulta(String nombre,DTFecha fecha,int numeroSala,int numsDisponibles,DiaConsulta diaConsulta,Medico medico) {
		this.nombre = nombre;
        this.fecha = fecha;
        this.numeroSala = numeroSala;
        this.numsDisponibles = numsDisponibles;
        this.diaConsulta = diaConsulta;
        this.medico = medico;
        this.reservas = new HashSet<>();
        this.costoConsulta = calcularCostoConsulta();
    }

    
    private float calcularCostoConsulta() {
        float costoDia = diaConsulta.getCostoConsulta();
        float porcentajeMedico = medico.getPorcentajeConsultas();
	return costoDia * (1 + porcentajeMedico / 100.0f);
    }

    public boolean perteneceMedicoConsulta(String nick) {
        return medico.getNickname().equals(nick);
    }

    public boolean tieneReservaPaciente(Paciente paciente) {
        for (Reserva reserva : reservas) {
            String nickReserva = reserva.getPaciente().getNickname();
            if (nickReserva.equals(paciente.getNickname())) {
                return true;
            }
        }
        return false;
    }

    public boolean estaCompleta() {
        return reservas.size() >= numsDisponibles;
    }

    public int obtenerMenorNumeroDisponible() {
        Set<Integer> usados = new HashSet<>();
        for (Reserva reserva : reservas) {
            usados.add(reserva.getNumeroCliente()
            );
        }
		for (int numero = 1;numero <= numsDisponibles; numero++) {
            if (!usados.contains(numero)) {
                return numero;
            }
        }
        throw new IllegalStateException(
            "No hay números disponibles"
        );
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public DTConsulta getDatosConsulta() {			// con todas las reservas
        Set<DTReserva> dtReservas = new HashSet<>();
        for (Reserva reserva : reservas) {
			dtReservas.add(new DTReserva(reserva.getPaciente().getNickname(),reserva.getFecha(),reserva.getNumeroCliente(),reserva.getCostoReserva()
                )
            );
        }
        return new DTConsulta(nombre,fecha,numeroSala,numsDisponibles,medico.getNickname(),costoConsulta,dtReservas);
    }

    public DTConsultaSoloConsulta getDatosSoloConsulta() {
        return new DTConsultaSoloConsulta(nombre,fecha,numeroSala,numsDisponibles,medico.getNickname(),costoConsulta
        );
    }

    public DTDatosConsulta obtenerDatosConsulta(String nombreConsulta) {
        return new DTDatosConsulta(numsDisponibles,costoConsulta);
    }

    public String getNombre() {
        return nombre;
    }

    public DiaConsulta getDiaConsulta() {
        return diaConsulta;
    }

    public void setDiaConsulta( DiaConsulta diaConsulta) {
        this.diaConsulta = diaConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getNicknameMedico() {
        return medico.getNickname();
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public DTFecha getFecha() {
        return fecha;
    }

    public void setFecha(DTFecha fecha) {
        this.fecha = fecha;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getNumsDisponibles() {
        return numsDisponibles;
    }

    public void setNumsDisponibles(int numsDisponibles) {
		this.numsDisponibles = numsDisponibles;
    }

    public float getCostoConsulta() {
        return costoConsulta;
    }
}