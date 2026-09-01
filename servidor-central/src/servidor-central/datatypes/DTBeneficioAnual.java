package datatypes;

public final class DTBeneficioAnual {
    private final int anio;
    private final int ticketsGratis;
    private final int consultasGratis;

    public DTBeneficioAnual(int anio, int ticketsGratis, int consultasGratis) {
        this.anio = anio;
        this.ticketsGratis = ticketsGratis;
        this.consultasGratis = consultasGratis;
    }

    public int getAnio() { return anio; }
    public int getTicketsGratis() { return ticketsGratis; }
    public int getConsultasGratis() { return consultasGratis; }

    @Override
    public String toString() { return Integer.toString(anio); }

    /*@Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTBeneficioAnual)) return false;
        return anio == ((DTBeneficioAnual) objeto).anio;
    }

    @Override
    public int hashCode() { return Objects.hash(anio); } */ 
}
