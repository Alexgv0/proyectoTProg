package formularios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datatypes.DTFecha;

abstract class FormularioBase extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    protected FormularioBase(String titulo, int ancho, int alto) {
        super(titulo, true, true, true, true);
        setSize(ancho, alto);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(8, 8));
    }

    protected JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        return panel;
    }

    protected void agregarCampo(JPanel panel, int fila, String etiqueta, Component componente) {
        GridBagConstraints texto = new GridBagConstraints();
        texto.gridx = 0;
        texto.gridy = fila;
        texto.anchor = GridBagConstraints.LINE_END;
        texto.insets = new Insets(4, 4, 4, 8);
        panel.add(new JLabel(etiqueta), texto);

        GridBagConstraints campo = new GridBagConstraints();
        campo.gridx = 1;
        campo.gridy = fila;
        campo.weightx = 1;
        campo.fill = GridBagConstraints.HORIZONTAL;
        campo.anchor = GridBagConstraints.LINE_START;
        campo.insets = new Insets(4, 4, 4, 4);
        panel.add(componente, campo);
    }

    protected JPanel crearBotones(Runnable aceptar) {
        JPanel panel = new JPanel();
        JButton botonAceptar = new JButton("Aceptar");
        JButton botonCancelar = new JButton("Cancelar");
        botonAceptar.addActionListener(_ -> ejecutarSeguro(aceptar));
        botonCancelar.addActionListener(_ -> dispose());
        panel.add(botonAceptar);
        panel.add(botonCancelar);
        return panel;
    }

    protected void ejecutarSeguro(Runnable accion) {
        try {
            accion.run();
        } catch (RuntimeException excepcion) {
            JOptionPane.showMessageDialog(this, excepcion.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void informar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "salud.uy",
                JOptionPane.INFORMATION_MESSAGE);
    }

    protected DTFecha leerFecha(String texto, String campo) {
        try {
            LocalDate fecha = LocalDate.parse(texto.trim(), FORMATO_FECHA);
            return DTFecha.from(fecha);
        } catch (DateTimeParseException excepcion) {
            throw new IllegalArgumentException(
                    "La " + campo + " debe tener el formato dd/mm/aaaa.");
        }
    }

    protected int leerEntero(String texto, String campo) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException excepcion) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser entero.");
        }
    }

    protected float leerReal(String texto, String campo) {
        try {
            return Float.parseFloat(texto.trim().replace(',', '.'));
        } catch (NumberFormatException excepcion) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser numerico.");
        }
    }

    protected Set<String> leerLista(String texto) {
        Set<String> resultado = new LinkedHashSet<>();
        for (String valor : texto.split("[,;\\n]")) {
            if (!valor.trim().isEmpty()) resultado.add(valor.trim());
        }
        return resultado;
    }

    protected void recargar(JComboBox<String> combo, Collection<String> valores) {
        combo.removeAllItems();
        valores.stream().sorted(String.CASE_INSENSITIVE_ORDER).forEach(combo::addItem);
    }

    protected String seleccionado(JComboBox<String> combo, String campo) {
        Object valor = combo.getSelectedItem();
        if (valor == null) throw new IllegalArgumentException(
                "No hay " + campo + " disponible o seleccionado.");
        return valor.toString();
    }

    protected void ocuparCentro(JComponent componente) {
        getContentPane().add(componente, BorderLayout.CENTER);
    }
}
