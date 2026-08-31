package datatypes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class DTConsulta extends DTConsultaSoloConsulta {
    private final Set<DTReserva> reservas;

    public DTConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros) {
        this(nombreConsulta, fecha, nroSala, cantidadNumeros, "", 0f,
                Collections.emptySet());
    }

    public DTConsulta(String nombreConsulta, DTFecha fecha, int nroSala, int cantidadNumeros,
            String nombreMedico, float costo, Set<DTReserva> reservas) {
        super(nombreConsulta, fecha, nroSala, cantidadNumeros, nombreMedico, costo);
        this.reservas = Collections.unmodifiableSet(new LinkedHashSet<>(reservas));
    }

    public Set<DTReserva> getReservas() { return reservas; }
}