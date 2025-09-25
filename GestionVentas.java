import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionVentas {
    private List<Articulo> inventario;
    private List<Articulo> carrito;
    private double totalVenta;
    private Scanner scanner;

    public GestionVentas() {
        this.inventario = new ArrayList<>();
        this.carrito = new ArrayList<>();
        this.totalVenta = 0.0;
        this.scanner = new Scanner(System.in);
        precargarInventario();
    }

    private void precargarInventario() {
        inventario.add(new Articulo(1, "Leche", "Lácteos", 20, 2500.0));
        inventario.add(new Articulo(2, "Pizzas Frias", "Comida", 50, 5000.0));
        inventario.add(new Articulo(3, "Manzanas", "Frutas", 30, 800.0));
        inventario.add(new Articulo(4, "Cola y pola", "Bebidas", 100, 2000.0));
    }

    public void atenderCliente(Cliente cliente) {
        System.out.println("\nAtendiendo a: " + cliente.getNombre());
        int opcion = 0;
        do {
            System.out.println("\n--- Menu Ventas---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Visualizar total a vender");
            System.out.println("3. Descartar artículo");
            System.out.println("4. Finalizar compra");
            System.out.println("5. Salir sin comprar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    visualizarTotal();
                    break;
                case 3:
                    descartarArticulo();
                    break;
                case 4:
                    finalizarCompra();
                    opcion = 5;
                    break;
                case 5:
                    System.out.println("La Venta fue cancelada.");
                    break;
                default:
                    if (opcion != -1) System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void registrarProducto() {
        System.out.println("\n--- Inventario Disponible ---");
        for (Articulo art : inventario) {
            System.out.println(art.getId() + ". " + art.getNombre() + " - $" + art.getPrecioPorUnidad());
        }
        System.out.print("Ingrese el ID del producto a agregar: ");
        try {
            int idProducto = Integer.parseInt(scanner.nextLine());
            Articulo articuloSeleccionado = null;
            for (Articulo art : inventario) {
                if (art.getId() == idProducto) {
                    articuloSeleccionado = art;
                    break;
                }
            }

            if (articuloSeleccionado != null && articuloSeleccionado.getExistencias() > 0) {
                carrito.add(articuloSeleccionado);
                articuloSeleccionado.setExistencias(articuloSeleccionado.getExistencias() - 1);
                totalVenta += articuloSeleccionado.getPrecioPorUnidad();
                System.out.println(articuloSeleccionado.getNombre() + " agregado al carrito.");
            } else {
                System.out.println("Producto no encontrado o sin existencias.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Intente de nuevo.");
        }
    }
    
    private void visualizarTotal() {
        System.out.println("\n--- Carrito de Compras ---");
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            for (Articulo art : carrito) {
                System.out.println("- " + art.getNombre() + ": $" + art.getPrecioPorUnidad());
            }
        }
        System.out.println("-----------------------------------------");
        System.out.printf("Total a pagar: $%.2f\n", totalVenta);
    }

    private void descartarArticulo() {
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío. No hay artículos para descartar.");
            return;
        }
        System.out.println("\n--- Artículos en el Carrito ---");
        for (int i = 0; i < carrito.size(); i++) {
            System.out.println((i + 1) + ". " + carrito.get(i).getNombre());
        }
        System.out.print("Ingrese el número del artículo a descartar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice >= 0 && indice < carrito.size()) {
                Articulo articuloRemovido = carrito.remove(indice);
                articuloRemovido.setExistencias(articuloRemovido.getExistencias() + 1);
                totalVenta -= articuloRemovido.getPrecioPorUnidad();
                System.out.println(articuloRemovido.getNombre() + " ha sido removido del carrito.");
            } else {
                System.out.println("Número de artículo no válido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Intente de nuevo.");
        }
    }

    private void finalizarCompra() {
        visualizarTotal();
        System.out.println("Compra finalizada con éxito. ¡Gracias por su visita!");
        carrito.clear();
        totalVenta = 0.0;
    }
}
