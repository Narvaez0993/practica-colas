import java.util.Scanner;

public class MenuPrincipal {
    private GestionTurnos gestionTurnos;
    private GestionVentas gestionVentas;
    private Scanner scanner;

    public MenuPrincipal() {
        this.gestionTurnos = new GestionTurnos();
        this.gestionVentas = new GestionVentas();
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
        System.out.printf("Total de ventas del día: $%.2f\n", gestionVentas.getTotalVentasDelDia());
        System.out.println("---------------------");
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
