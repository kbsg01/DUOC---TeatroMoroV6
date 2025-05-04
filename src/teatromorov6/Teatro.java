package teatromorov6;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase Teatro que gestiona la venta de entradas y reservas.
 * Contiene métodos para registrar clientes, reservar asientos, vender entradas,
 * imprimir entradas y mostrar un resumen de ventas.
 * 
 * @see Entrada
 * @see Cliente
 * @see Reserva
 * @see Promocion
 */

public class Teatro {
    public static final String[] TIPOS = { "VIP", "Platea Alta", "Platea Baja", "Palcos" };
    private static final double[] PRECIOS = { 30000, 18000, 15000, 13000 };
    private static final int CAPACIDAD = 300;
    private static final int[] AFORO = { CAPACIDAD / 4, CAPACIDAD / 4, CAPACIDAD / 4, CAPACIDAD / 8 };
    private static final DecimalFormat df = new DecimalFormat("#,###");

    private final Entrada[] ventas = new Entrada[CAPACIDAD];
    private final Cliente[] clientes = new Cliente[CAPACIDAD];
    private final String[][] asientosPorTipo = new String[4][];
    private final int[] nextAsientoIndex = new int[4];
    private final List<Promocion> promociones = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();

    private int cntVentas = 0;
    private int cntClientes = 0;

    public Teatro() {
        for (int i = 0; i < TIPOS.length; i++) {
            asientosPorTipo[i] = new String[AFORO[i]];
            for (int j = 0; j < AFORO[i]; j++) {
                asientosPorTipo[i][j] = TIPOS[i].charAt(0) + "-" + (j + 1);
            }
        }
        promociones.add(new Promocion("Estudiante", 10));
        promociones.add(new Promocion("Tercera Edad", 15));
    }

    public int registraCliente(Cliente c) {
        for (int i = 0; i < cntClientes; i++) {
            Cliente ex = clientes[i];
            if (ex.getNombre().equalsIgnoreCase(c.getNombre()) &&
                    ex.getApellido().equalsIgnoreCase(c.getApellido()) &&
                    ex.getEdad() == c.getEdad()) {
                return i;
            }
        }
        clientes[cntClientes] = c;
        return cntClientes++;
    }

    public int reservarAsiento(int idCli, int tipoIdx) {
        if (nextAsientoIndex[tipoIdx] >= AFORO[tipoIdx]) {
            throw new IllegalStateException("\nNo hay asientos disponibles para " + TIPOS[tipoIdx]);
        }
        String asiento = asientosPorTipo[tipoIdx][nextAsientoIndex[tipoIdx]++];
        Reserva r = new Reserva(idCli, TIPOS[tipoIdx], asiento);
        reservas.add(r);
        return r.getIdReserva();
    }

    /**
     * Usa una reserva existente para completar la compra.
     */
    public int comprarReserva(int idReserva) {
        Reserva found = null;
        for (Reserva r : reservas) {
            if (r.getIdReserva() == idReserva) {
                found = r;
                break;
            }
        }
        if (found == null) {
            throw new IllegalArgumentException("Reserva no encontrada: " + idReserva);
        }
        reservas.remove(found);
        // crear la entrada usando los datos de la reserva
        Cliente cli = clientes[found.getIdCliente()];
        int tipoIdx = Arrays.asList(TIPOS).indexOf(found.getTipo());
        Entrada e = new Entrada(TIPOS[tipoIdx], found.getAsiento(), PRECIOS[tipoIdx], cli,
                cli.getEdad() < 18 ? 10 : cli.getEdad() >= 60 ? 15 : 0);
        ventas[cntVentas++] = e;
        System.out.println("Compra registrada: " + e);
        return e.getIdEntrada();
    }

    public int venderEntrada(Cliente cli, int tipoIdx) {
        int idxCli = registraCliente(cli);
        String asiento;
        if (nextAsientoIndex[tipoIdx] >= AFORO[tipoIdx]) {
            throw new IllegalStateException("Teatro lleno para tipo " + TIPOS[tipoIdx]);
        }
        asiento = asientosPorTipo[tipoIdx][nextAsientoIndex[tipoIdx]++];
        double pctDesc = 0;
        if (cli.getEdad() < 18)
            pctDesc = promociones.get(0).getDescuentoPct();
        else if (cli.getEdad() >= 60)
            pctDesc = promociones.get(1).getDescuentoPct();

        Entrada e = new Entrada(TIPOS[tipoIdx], asiento, PRECIOS[tipoIdx], cli, pctDesc);
        ventas[cntVentas++] = e;
        System.out.println("Compra registrada: " + e);
        return e.getIdEntrada();
    }

    public void imprimirEntrada(int id) {
        for (int i = 0; i < cntVentas; i++) {
            if (ventas[i].getIdEntrada() == id) {
                ventas[i].imprimirTicket();
                return;
            }
        }
        System.out.println("\nEntrada con ID " + id + " no encontrada.");
    }

    public void mostrarResumen() {
        double total = 0;
        System.out.println("\n=======================");
        System.out.println("=== Resumen Ventas ===");
        for (int i = 0; i < cntVentas; i++) {
            System.out.println(ventas[i]);
            total += ventas[i].getPrecioFinal();
        }
        System.out.println("\n=======================");
        System.out.println("Total entradas vendidas: " + cntVentas);
        System.out.println("Total recaudado: $" + df.format(total));
    }
}
