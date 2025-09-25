
public class Cliente {
    private String cedula;
    private String nombre;
    private int turno;

    public Cliente(String cedula, String nombre, int turno) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.turno = turno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Turno #" + turno + ": " + nombre + " (Cédula: " + cedula + ")";
    }
}
