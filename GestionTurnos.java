import java.util.LinkedList;
import java.util.Queue;

public class GestionTurnos {
    private Queue<Cliente> colaClientes;
    private int proximoTurno;

    public GestionTurnos() {
        this.colaClientes = new LinkedList<>();
        this.proximoTurno = 1;
    }

    public void tomarTurno(String cedula, String nombre) {
        Cliente nuevoCliente = new Cliente(cedula, nombre, proximoTurno);
        colaClientes.add(nuevoCliente);
        System.out.println(nombre + ", su turno es el #" + proximoTurno);
        proximoTurno++;
    }

    public void visualizarTurnos() {
        if (colaClientes.isEmpty()) {
            System.out.println("No hay clientes en espera.");
        } else {
            System.out.println("--- Clientes en espera ---");
            for (Cliente cliente : colaClientes) {
                System.out.println(cliente.toString());
            }
            System.out.println("-------------------------");
            if (colaClientes.peek() != null) {
                System.out.println("Próximo en ser atendido: " + colaClientes.peek().getNombre());
            }
        }
    }

    public Cliente llamarSiguiente() {
        if (colaClientes.isEmpty()) {
            System.out.println("No hay clientes en la cola para atender.");
            return null;
        }
        return colaClientes.poll();
    }
}
