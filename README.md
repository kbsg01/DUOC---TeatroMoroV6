# Teatro Moro - Sistema de venta/reserva de entradas

## Estructura proyecto
```
src/
└── teatromorov6/
    ├── Cliente.java
    ├── Entrada.java
    ├── Promocion.java
    ├── Reserva.java
    ├── Teatro.java
    ├── MenuController.java
    └── TeatroMoroV6.java    # Clase principal con método main

```

## Características

* **Registro de Clientes**: almacenamiento de nombre, apellido y edad.
* **Reservas de Asientos**: generación de ID de reserva y asignación de asientos disponibles.
* **Compra de Entradas**:

  * Compra directa o confirmación de reserva.
  * Descuentos automáticos:

    * **Estudiantes (< 18 años): 10%**
    * **Tercera Edad (>= 60 años): 15%**
* **Impresión de Tickets**: formato visual en consola con datos completos:

  * ID de entrada, tipo, asiento, cliente, precio original, descuento aplicado y precio final.
* **Resumen de Ventas**: listado de todas las entradas vendidas y total recaudado.
* **Menú Interactivo**:

  * Separación de la lógica de negocio (`Teatro`) y de presentación (`MenuController`).
  * Confirmación tras cada acción para continuar o volver al menú.
  * Confirmación de salida para prevenir cierres accidentales.

---

## Requisitos y Compilación

1. **Java 8+** instalado y configurado en `PATH`.

### Compilar manualmente

```bash
# Desde la carpeta raíz del proyecto
tree src
ejecutar:
javac -d out/production/ src/teatromorov6/*.java src/teatromorov6/utils/*.java
```

### Ejecutar la aplicación

```bash
java -cp out/production/ teatromorov6.TeatroMoroV6
```

---

## Descripción de Clases

* **Cliente**: datos personales del usuario (`nombre`, `apellido`, `edad`).
* **Promocion**: nombre y porcentaje de un descuento.
* **Reserva**: ID de reserva, referencia a cliente, tipo de asiento y código de asiento.
* **Entrada**: ID de entrada, cliente, tipo, asiento, precio original, descuento aplicado y precio final; incluye método `imprimirTicket()`.
* **Teatro**: lógica central del sistema:

  * Gestión de listas fijas y contadores (`ventas`, `clientes`).
  * Métodos: `reservarAsiento()`, `comprarReserva()`, `venderEntrada()`, `imprimirEntrada()`, `mostrarResumen()`.
* **MenuController** (en `utils/`): gestión del menú y flujos de interacción.
* **TeatroMoroV6**: clase con `main()` que invoca al `MenuController`.

---

## Uso del Menú

1. **Reservar Asiento**: solicita datos de cliente y tipo de asiento, retorna ID de reserva y pregunta si desea reservar otra vez.
2. **Comprar Entrada**: pregunta si hay reserva pendiente y completa compra, o realiza compra directa; imprime el ticket y pregunta si desea otra compra.
3. **Imprimir Ticket por ID**: muestra el ticket formateado en consola.
4. **Resumen de Ventas**: muestra resumen de todas las ventas y total recaudado.
5. **Salir**: confirma antes de cerrar la aplicación.

---
