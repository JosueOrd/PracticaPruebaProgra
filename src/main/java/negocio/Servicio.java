package negocio;

import Excepciones.TareaNoExistenteException;

import java.util.List;

public interface Servicio {
    TareaDTO crearTarea(CrearTareaDTO crearTarea) throws Exception;
    List<TareaDTO> listarTareas();
    TareaDTO modificarTarea(String nuevaDescripcion, Long id) throws Exception;
    void eliminarTarea(Long id) throws Exception;
    TareaDTO buscarTareaPorId(Long id) throws Exception;
}
