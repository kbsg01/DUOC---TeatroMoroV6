package teatromorov6;

/**
 * Clase Promocion que representa una promoción de descuento en el teatro.
 * Contiene información sobre el nombre de la promoción y el porcentaje de
 * descuento.
 * Esta clase es utilizada para crear instancias de promociones
 * que pueden aplicarse a las entradas vendidas.
 */

public class Promocion {
    private final String nombre;
    private final double descuentoPct;

    public Promocion(String nombre, double descuentoPct) {
        this.nombre = nombre;
        this.descuentoPct = descuentoPct;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDescuentoPct() {
        return descuentoPct;
    }
}
