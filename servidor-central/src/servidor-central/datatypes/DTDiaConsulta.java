package datatypes;

import java.util.Objects;

public class DTDiaConsulta {

    private String nombreDia;

    public DTDiaConsulta(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public String getNombreDia() {
        return nombreDia;
    }
    
    @Override
    public String toString() { return nombreDia; }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof DTDiaConsulta)) return false;
        return Objects.equals(nombreDia, ((DTDiaConsulta) objeto).nombreDia);
    }

    @Override
    public int hashCode() { return Objects.hash(nombreDia); }
}

}