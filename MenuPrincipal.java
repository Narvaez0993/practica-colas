import java.util.Scanner;

public class MenuPrincipal {
    private GestionTurnos gestionTurnos;
    private GestionVentas gestionVentas;
    private GestionInventario gestionInventario;
    private Scanner scanner;

    public MenuPrincipal() {
        this.gestionTurnos = new GestionTurnos();
        this.gestionInventario = new GestionInventario();
        this.gestionVentas = new GestionVentas(gestionInventario);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- Supermercado Akira Toriyama ---");
            System.out.println("1. Tomar turno");
            System.out.println("2. Visualizar quien va en turno");
            System.out.println("3. Atender Cliente");
            System.out.println("4. Visualizar estado de la cola");
            System.out.println("5. Gestionar Inventario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    tomarTurno();
                    break;
                case 2:
                    gestionTurnos.visualizarTurnos();
                    break;
                case 3:
                    atenderCliente();
                    break;
                case 4:
                    visualizarEstadoCola();
                    break;
                case 5:
                    gestionarInventario();
                    break;
                case 0:
                    System.out.println("Gracias por su visita.");
                    break;
                default:
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente de nuevo.");
                    }
            }
        } while (opcion != 0);
    }

    private void tomarTurno() {
        String cedula = leerCedula("Ingrese su cédula: ");
        String nombre = leerEntrada("Ingrese su nombre: ");
        gestionTurnos.tomarTurno(cedula, nombre);
    }

    private void atenderCliente() {
        Cliente clienteAtendido = gestionTurnos.llamarSiguiente();
        if (clienteAtendido != null) {
            gestionVentas.atenderCliente(clienteAtendido);
        }
    }

    private void visualizarEstadoCola() {
        System.out.println("\n--- Estado Actual ---");
        gestionTurnos.visualizarTurnos();
        System.out.println();
        gestionTurnos.visualizarClientesAtendidos();
        System.out.println();
        System.out.printf("Total de ventas del dia: $%.2f\n", gestionVentas.getTotalVentasDelDia());
        System.out.println("---------------------");
    }

    private void gestionarInventario() {
        int opcion = 0;
        do {
            System.out.println("\n--- Gestion Inventario ---");
            System.out.println("1. Visualizar inventario");
            System.out.println("2. Agregar artículo");
            System.out.println("3. Dar de baja un artículo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero valido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    gestionInventario.visualizarInventario();
                    break;
                case 2:
                    agregarArticulo();
                    break;
                case 3:
                    darDeBajaArticulo();
                    break;
                case 0:
                    break;
                default:
                    if (opcion != -1) System.out.println("Opción no valida.");
            }
        } while (opcion != 0);
    }

    private void agregarArticulo() {
        System.out.println("\n--- Agregar Nuevo Artículo ---");
        String nombre = leerEntrada("Ingrese el nombre del artículo: ");
        String categoria = leerEntrada("Ingrese la categoria del artículo: ");
        
        int existencias = 0;
        boolean existenciasValidas = false;
        while (!existenciasValidas) {
            try {
                System.out.print("Ingrese las existencias: ");
                existencias = Integer.parseInt(scanner.nextLine());
                if (existencias >= 0) {
                    existenciasValidas = true;
                } else {
                    System.out.println("Las existencias no pueden ser negativas.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero entero valido.");
            }
        }
        
        double precio = 0.0;
        boolean precioValido = false;
        while (!precioValido) {
            try {
                System.out.print("Ingrese el precio por unidad: ");
                precio = Double.parseDouble(scanner.nextLine());
                if (precio >= 0) {
                    precioValido = true;
                } else {
                    System.out.println("El precio no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero decimal valido.");
            }
        }

        gestionInventario.agregarArticulo(nombre, categoria, existencias, precio);
        System.out.println("¡Artículo agregado con éxito!");
    }

    private void darDeBajaArticulo() {
        System.out.println("\n--- Dar de Baja Artículo ---");
        gestionInventario.visualizarInventario();
        System.out.print("Ingrese el ID del artículo a dar de baja: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            gestionInventario.darDeBajaArticulo(id);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número.");
        }
    }

    private String leerCedula(String mensaje) {
        System.out.print(mensaje);
        String cedula = scanner.nextLine();
        if (cedula == null || cedula.trim().isEmpty()) {
            System.out.println("La cédula no puede estar vacía. Por favor, intente de nuevo.");
            return leerCedula(mensaje);
        }
        if (!cedula.matches("\\d+")) {
            System.out.println("La cédula solo debe contener números. Por favor, intente de nuevo.");
            return leerCedula(mensaje);
        }
        return cedula;
    }

    private String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine();
        if (entrada == null || entrada.trim().isEmpty()) {
            System.out.println("La entrada no puede estar vacía. Por favor, intente de nuevo.");
            return leerEntrada(mensaje);
        }
        return entrada;
    }
}
