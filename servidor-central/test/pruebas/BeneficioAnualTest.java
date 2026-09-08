package pruebas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import clases.BeneficioAnual;

class BeneficioAnualTest {
    @Test
    void testGettersYSetters() {
        BeneficioAnual b = new BeneficioAnual(2026, 4, 5);
        assertEquals(2026, b.getAnio());
        assertEquals(4, b.getTicketsGratis());
        assertEquals(5, b.getConsultasGratis());

        b.setTicketsGratis(10);
        b.setConsultasGratis(7);
        assertEquals(10, b.getTicketsGratis());
        assertEquals(7, b.getConsultasGratis());
    }

    @Test
    void testConsumirConsultaGratisDescuenta() {
        BeneficioAnual b = new BeneficioAnual(2026, 4, 1);
        b.consumirConsultaGratis();
        assertEquals(0, b.getConsultasGratis());
    }

    @Test
    void testConsumirConsultaGratisSinDisponibles() {
        BeneficioAnual b = new BeneficioAnual(2026, 4, 0);
        assertThrows(IllegalStateException.class, b::consumirConsultaGratis);
    }
}