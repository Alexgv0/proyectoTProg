package controladores;

final class Validaciones {
    private Validaciones() { }

    static String texto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
        return valor.trim();
    }

    static int positivo(int valor, String campo) {
        if (valor <= 0) throw new IllegalArgumentException(
                "El campo " + campo + " debe ser mayor que cero.");
        return valor;
    }

    static int noNegativo(int valor, String campo) {
        if (valor < 0) throw new IllegalArgumentException(
                "El campo " + campo + " no puede ser negativo.");
        return valor;
    }

    static float noNegativo(float valor, String campo) {
        if (Float.isNaN(valor) || Float.isInfinite(valor) || valor < 0) {
            throw new IllegalArgumentException(
                    "El campo " + campo + " debe ser un numero no negativo.");
        }
        return valor;
    }
}
