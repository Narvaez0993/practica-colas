import java.util.ArrayList;
import java.util.List;

public class GestionInventario {
    private List<Articulo> inventario;
    private int proximoId;

    public GestionInventario() {
        this.inventario = new ArrayList<>();
        this.proximoId = 1;
        precargarInventario();
    }

    private void precargarInventario() {
        agregarArticulo("De todito", "Comida", 20, 2500);
        agregarArticulo("Pizzas Frias", "Comida", 50, 5000);
        agregarArticulo("Manzanas", "Frutas", 30, 1000);
        agregarArticulo("Cola y pola", "Bebidas", 1, 12000);
    }

    public void agregarArticulo(String nombre, String categoria, int existencias, double precio) {
        Articulo nuevoArticulo = new Articulo(proximoId, nombre, categoria, existencias, precio);
        inventario.add(nuevoArticulo);
        proximoId++;
    }

    public void visualizarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("\n--- Inventario Completo ---");
        for (Articulo art : inventario) {
            System.out.println(art.toString());
        }
        System.out.println("---------------------------");
    }

    public void darDeBajaArticulo(int id) {
        Articulo articuloEncontrado = buscarArticuloPorId(id);
        if (articuloEncontrado != null) {
            articuloEncontrado.setEstado(1);
            System.out.println("El artículo '" + articuloEncontrado.getNombre() + "' ha sido dado de baja.");
        } else {
            System.out.println("No se encontró ningún artículo con el ID proporcionado.");
        }
    }
    
    public Articulo buscarArticuloPorId(int id) {
        for (Articulo art : inventario) {
            if (art.getId() == id) {
                return art;
            }
        }
        return null;
    }

    public List<Articulo> getInventario() {
        return inventario;
    }
}
