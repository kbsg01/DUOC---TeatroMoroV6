package teatromorov6.utils;

import java.util.Scanner;
import teatromorov6.*;

/** 
 * Clase MenuController que maneja la interacción del usuario con el menú principal del teatro.
 * Proporciona métodos para mostrar el menú, manejar reservas, compras, impresión de entradas y resumen de ventas.
 * Esta clase es utilizada por la clase TeatroMoroV6 para facilitar la interacción con el usuario.
 * 
 * @apiNote Esta clase es parte del paquete teatromorov6.utils y se utiliza para separar la lógica de presentación de la lógica de negocio.
 * @see TeatroMoroV6
 */

public class MenuController {

    public static void mostrarMenu() {
        System.out.println("\n============================================");
        System.out.println("========TEATRO MORO - MENÚ PRINCIPAL========");
        System.out.println("============================================");
        System.out.println("1). Reservar Asiento");
        System.out.println("2). Comprar Entrada / Confirmar Reserva");
        System.out.println("3). Imprimir Entrada por ID");
        System.out.println("4). Resumen de Ventas");
        System.out.println("5). Salir");
        System.out.println("============================================");
    }

    public static void handleReserva(Scanner sc, Teatro teatro) {
        do {
            Cliente c = solicitarCliente(sc);
            int tipo = seleccionarTipo(sc);
            int idRes = teatro.reservarAsiento(teatro.registraCliente(c), tipo);
            System.out.println("Reserva creada con ID: " + idRes + ". Use la opción 2 para comprar.");
        } while (promptYesNo(sc, "¿Desea realizar otra reserva? (S/N): "));
    }

    public static void handleCompra(Scanner sc, Teatro teatro) {
        do {
            int idEntrada;
            if (promptYesNo(sc, "¿Tiene alguna reserva pendiente? (S/N): ")) {
                System.out.print("ID de reserva: ");
                int idRes = sc.nextInt(); sc.nextLine();
                idEntrada = teatro.comprarReserva(idRes);
            } else {
                Cliente c = solicitarCliente(sc);
                int tipo = seleccionarTipo(sc);
                idEntrada = teatro.venderEntrada(c, tipo);
            }
            // impresión automática del ticket
            teatro.imprimirEntrada(idEntrada);
        } while (promptYesNo(sc, "¿Desea realizar otra compra? (S/N): "));
    }

    public static void handleImprimir(Scanner sc, Teatro teatro) {
        System.out.print("ID Entrada: ");
        int id = sc.nextInt();
        sc.nextLine();
        teatro.imprimirEntrada(id);
    }

    public static void handleResumen(Teatro teatro) {
        teatro.mostrarResumen();
    }

    public static boolean handleSalida(Scanner sc) {
        System.out.print("¿Está seguro que desea salir? (S/N): ");
        String resp = sc.nextLine().trim().toUpperCase();
        if (resp.equals("S")) {
            System.out.println("Gracias por su visita.");
            return true;
        } else {
            System.out.println("Continuando con el programa...");
            return false;
        }
    }

    //Métodos auxiliares    
    private static Cliente solicitarCliente(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        return new Cliente(nombre, apellido, edad);
    }

    private static int seleccionarTipo(Scanner sc) {
        System.out.println("Tipos de Asiento disponibles:");
        for (int i = 0; i < Teatro.TIPOS.length; i++) {
            System.out.printf(" %d) %s%n", i + 1, Teatro.TIPOS[i]);
        }
        System.out.print("Seleccione un tipo (1-" + Teatro.TIPOS.length + "): ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return Math.max(0, Math.min(Teatro.TIPOS.length - 1, opcion - 1));
    }

    private static boolean promptYesNo(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String resp = sc.nextLine().trim().toUpperCase();
        return resp.equals("S");
    }

}
