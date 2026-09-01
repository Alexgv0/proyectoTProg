package datatypes;

import java.time.LocalDate;
import java.util.Objects;

public final class DTFecha implements Comparable<DTFecha> {
    private final int dia;
    private final int mes;
    private final int anio;
	
    
    // CHEQUEAR SI ESO DE LOCALDATE ESTA BIEN IMPLEMENTADO
    public DTFecha(int dia, int mes, int anio) {
        LocalDate.of(anio, mes, dia);
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
	
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getAnio() {
		return anio;
	}
	

    public LocalDate toLocalDate() { 
    	return LocalDate.of(anio, mes, dia);
    }

    public static DTFecha from(LocalDate fecha) {
        Objects.requireNonNull(fecha, "La fecha no puede ser nula.");
        return new DTFecha(fecha.getDayOfMonth(), fecha.getMonthValue(), fecha.getYear());
    }

    @Override
    public int compareTo(DTFecha otra) {
    	return toLocalDate().compareTo(otra.toLocalDate());
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTFecha)) return false;
        DTFecha otra = (DTFecha) objeto;
        return dia == otra.dia && mes == otra.mes && anio == otra.anio;
    }

    @Override
    public int hashCode() {
    	return Objects.hash(dia, mes, anio);
    }

    @Override
    public String toString() {
    	return String.format("%02d/%02d/%04d", dia, mes, anio);
    }
}

