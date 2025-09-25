public class Articulo {
    private int id;
    private String nombre;
    private String categoria;
    private int existencias;
    private double precioPorUnidad;
    private int estado;

    public Articulo(int id, String nombre, String categoria, int existencias, double precioPorUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.precioPorUnidad = precioPorUnidad;
        this.estado = 0;
    }

    //Get
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getExistencias() {
        return existencias;
    }

    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public int getEstado() {
        return estado;
    }

    // Set
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public void setPrecioPorUnidad(double precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Categoría: " + categoria + 
               ", Existencias: " + existencias + ", Precio: $" + precioPorUnidad + 
               ", Estado: " + (estado == 0 ? "Activo" : "Inactivo");
    }
}
