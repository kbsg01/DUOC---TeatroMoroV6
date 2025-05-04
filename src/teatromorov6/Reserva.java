package teatromorov6;

/**
 * Clase Reserva que representa una reserva de asiento en el teatro.
 * Contiene información sobre el ID de reserva, ID de cliente,
 * tipo de asiento y número de asiento.
 * Esta clase es utilizada para crear instancias de reservas
 * que pueden ser realizadas por los clientes.
 * 
 */

public class Reserva {
    private static int contadorRes = 0;
    private final int idReserva;
    private final int idCliente;
    private final String tipo;
    private final String asiento;

    public Reserva(int idCliente, String tipo, String asiento) {
        this.idReserva = ++contadorRes;
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.asiento = asiento;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAsiento() {
        return asiento;
    }

    @Override
    public String toString() {
        return String.format("Reserva %d | Cliente ID: %d | %s asiento %s",
                idReserva, idCliente, tipo, asiento);
    }
}
