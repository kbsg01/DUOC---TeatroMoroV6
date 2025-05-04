/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatromorov6;

import java.util.Scanner;
import teatromorov6.utils.MenuController;
/**
 *
 * @author kabes
 */
public class TeatroMoroV6 {

    /**
     * Este método proporciona un menú para que el usuario interactúe con la clase Teatro.
     * Permite al usuario reservar asientos, vender entradas, imprimir entradas por ID, y mostrar un resumen de las ventas.
     * 
     * @apiNote Este método es el punto de entrada de la aplicación y utiliza un bucle para mostrar el menú hasta que el usuario decida salir.
     * 
     * @see Teatro
     * @see MenuController
     * 
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Teatro teatro = new Teatro();
        int op;
        boolean salir = false;
        do {
            MenuController.mostrarMenu();
            System.out.print("> Seleccione una opción (1-5): ");
            op = sc.nextInt(); sc.nextLine();
            try {
                switch (op) {
                    case 1 -> MenuController.handleReserva(sc, teatro);
                    case 2 -> MenuController.handleCompra(sc, teatro);
                    case 3 -> MenuController.handleImprimir(sc, teatro);
                    case 4 -> MenuController.handleResumen(teatro);
                    case 5 -> salir = MenuController.handleSalida(sc);
                    default -> 
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (!salir);
        sc.close();
    }

    
}
