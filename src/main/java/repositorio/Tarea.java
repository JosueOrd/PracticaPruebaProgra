package repositorio;

import java.io.Serializable;

public class Tarea implements Serializable {
    private Long idTarea;
    private String nombre;
    private String descripcion;
    private static long serialVersionIUD = 1L;
    public Tarea(Long idTarea, String nombre, String descripcion) {
        this.idTarea = idTarea;
        this.nombre = nombre;
        this.descripcion = descripcion;

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

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }
}
