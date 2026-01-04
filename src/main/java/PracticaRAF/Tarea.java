package PracticaRAF;

public class Tarea {

    private int  id;
    private String nombre;
    private String descripcion;
    private Prioridad prioridad;

    public Tarea(int id, String nombre, String descripcion, Prioridad prioridad) {
        this.id = id; //4 bytes
        this.nombre = nombre; //10 caracteres (20 bytes)
        this.descripcion = descripcion; //30 caracteres (60 bytes)
        this.prioridad = prioridad; // 10 bytes como maximo
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}
