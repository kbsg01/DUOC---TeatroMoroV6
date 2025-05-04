package teatromorov6;

/**
 * Clase Cliente que representa a un cliente del teatro.
 * Contiene información sobre el nombre, apellido y edad del cliente.
 * Esta clase es utilizada para crear instancias de clientes
 * que pueden reservar asientos y comprar entradas.
 */

public class Cliente {
    private String nombre;
    private String apellido;
    private int edad;

    public Cliente(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + edad + " años)";
    }
}