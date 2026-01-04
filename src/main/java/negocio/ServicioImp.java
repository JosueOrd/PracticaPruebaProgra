package negocio;

import Excepciones.DtoVacioException;
import Excepciones.StringNoValidoException;
import Excepciones.TareaNoExistenteException;
import repositorio.Repositorio;
import repositorio.Tarea;

import java.util.List;

public class ServicioImp implements Servicio {

    private final Repositorio<Tarea> repositorio;

    public ServicioImp(Repositorio<Tarea> repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public TareaDTO crearTarea(CrearTareaDTO crearTarea) throws Exception {
        validarDatos(crearTarea);
        // Convertimos DTO a Entidad
        Tarea tareaParaGuardar = crearTareaDTOATarea(crearTarea);

        Tarea tareaGuardada = repositorio.crear(tareaParaGuardar);

        return tareaATareaDTO(tareaGuardada);
    }

    @Override
    public List<TareaDTO> listarTareas() {
        return repositorio.listar().stream()
                .map(this::tareaATareaDTO)
                .toList();
    }

    @Override
    public TareaDTO modificarTarea(String nuevaDescripcion, Long id) throws Exception {

        TareaDTO existente = buscarTareaPorId(id);
        CrearTareaDTO tareaValidar = new CrearTareaDTO(existente.nombre(), nuevaDescripcion);
        validarDatos(tareaValidar);
        Tarea tareaActualizada = new Tarea( id, existente.nombre(), nuevaDescripcion);

        repositorio.modificar(tareaActualizada);
        return tareaATareaDTO(tareaActualizada);
    }

    @Override
    public void eliminarTarea(Long id) throws Exception {

        if (buscarTareaPorId(id)==null) throw new TareaNoExistenteException("No existe" +
                " la tarea para eliminar ");
        repositorio.eliminar(id);
    }

    @Override
    public TareaDTO buscarTareaPorId(Long id) throws Exception {
        Tarea tarea = repositorio.buscarPorId(id);
        if (tarea == null) {
            throw new TareaNoExistenteException("La tarea con ID " + id + " no existe");
        }
        return tareaATareaDTO(tarea);
    }


    private Tarea crearTareaDTOATarea(CrearTareaDTO dto) {
        // Asumimos que el ID se genera en el repositorio o DB
        return new Tarea(0L, dto.nombre(), dto.descripcion());
    }

    private TareaDTO tareaATareaDTO(Tarea t) {
        return new TareaDTO(t.getNombre(), t.getDescripcion(), t.getIdTarea());
    }

    private void validarDatos(CrearTareaDTO dto) {
        if (dto == null) throw
                new DtoVacioException("El DTO no puede ser nulo");
        if (dto.nombre() == null || dto.nombre().isBlank()) throw
                new StringNoValidoException("El nombre es obligatorio");
        if (dto.descripcion() == null || dto.descripcion().isBlank()) throw
                new StringNoValidoException("La descripción es obligatoria");
    }
}