package teatromorov6;

import java.text.DecimalFormat;

/**
 * Clase Entrada que representa una entrada de teatro.
 * Contiene información sobre el tipo de entrada, asiento, precio original,
 * precio final y cliente asociado.
 */

public class Entrada {
    private static int contadorGlobal = 0;
    private final int idEntrada;
    private final String tipo;
    private final String asiento;
    private final double precioOriginal;
    private final double porcentajeDescuento;
    private final double precioFinal;
    private final Cliente cliente;

    public Entrada(String tipo, String asiento, double precio, Cliente cliente, double descuentoPct) {
        this.idEntrada = ++contadorGlobal;
        this.tipo = tipo;
        this.asiento = asiento;
        this.precioOriginal = precio;
        this.porcentajeDescuento = descuentoPct;
        this.cliente = cliente;
        this.precioFinal = precio * (1 - descuentoPct / 100.0);
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###");
        return String.format("Entrada %d | %s | Asiento: %s | Cliente: %s | Precio: $%s",
                idEntrada, tipo, asiento, cliente, df.format(precioFinal));
    }

    public void imprimirTicket() {
        String border = "===============================";
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println(border);
        System.out.println("         TEATRO MORO");
        System.out.println(border);
        System.out.printf("Entrada ID      : %d%n", idEntrada);
        System.out.printf("Tipo            : %s%n", tipo);
        System.out.printf("Asiento         : %s%n", asiento);
        System.out.printf("Cliente         : %s%n", cliente);
        System.out.printf("Precio Original : $%s%n", df.format(precioOriginal));
        System.out.printf("Descuento       : %.0f%%%n", porcentajeDescuento);
        System.out.printf("Precio Final    : $%s%n", df.format(precioFinal));
        System.out.println(border);
    }
}
