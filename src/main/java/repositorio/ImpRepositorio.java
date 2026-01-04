package repositorio;

import java.io.*;
import java.util.*;

public class ImpRepositorio implements Repositorio<Tarea> {

    private final Map<Long, Tarea > tareas;
    private static final String NOMBRE_ARCHIVO = "Tarea.txt";
    private static Long id = 0L;

    public ImpRepositorio() {
        this.tareas=cargarTodo();
    }

    @SuppressWarnings("unchecked")
    private Map<Long, Tarea> cargarTodo(){
        File arc = new File(NOMBRE_ARCHIVO);
        if (!arc.exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arc))) {
            return (Map<Long, Tarea>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error al cargar archivo");
            return new HashMap<>();
        }
    }

    private void guardarTodo() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream
                (new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(tareas);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al guardar en base de datos");
        }

    }

    @Override
    public Tarea crear(Tarea tarea)  throws Exception {

        tarea.setIdTarea(++id);
        tareas.put(tarea.getIdTarea(),  tarea);

        guardarTodo();
        return tarea;
    }

    @Override
    public List<Tarea> listar() {
        return new ArrayList<>(tareas.values());
    }

    @Override
    public Tarea modificar(Tarea tarea) throws Exception{
        tareas.put(tarea.getIdTarea(), tarea);
        guardarTodo();
        return tarea;
    }

    @Override
    public void eliminar(Long id) throws Exception {

        tareas.remove(id);
        guardarTodo();
    }

    @Override
    public Tarea buscarPorId(Long id){

        return tareas.get(id);
    }

}
