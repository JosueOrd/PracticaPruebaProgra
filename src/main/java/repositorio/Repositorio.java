package repositorio;

import java.util.List;

public interface Repositorio<T> {
    T crear(T t) throws Exception;
    List<T> listar();
    T modificar(T t) throws Exception;
    void eliminar(Long id)  throws Exception;
    T buscarPorId(Long id);
}
