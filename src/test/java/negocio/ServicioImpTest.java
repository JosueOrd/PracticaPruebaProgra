package negocio;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositorio.Repositorio;
import repositorio.Tarea;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServicioImpTest {

    @Mock
    private static Repositorio<Tarea> repo;

    @InjectMocks
    private ServicioImp servicioImp;
    @Test
    void crearTareaCorrecto() throws Exception {
        CrearTareaDTO dto= new CrearTareaDTO("dsf","sad");

        Tarea t = new Tarea(0L,"dsf","sad");
        when(repo.crear(any(Tarea.class))).thenReturn(t);

        servicioImp.crearTarea(dto);

        verify(repo, times(1)).crear(any(Tarea.class));

    }

    @Test
    void crearErrores(){
        CrearTareaDTO dto=null;

        assertThrows(Exception.class,()->
                servicioImp.crearTarea(dto));
        CrearTareaDTO dto1=new CrearTareaDTO("","");
        Exception e = assertThrows(Exception.class,()->
                servicioImp.crearTarea(dto1));
        assertEquals("El nombre es obligatorio",e.getMessage());
        CrearTareaDTO dto2=new CrearTareaDTO("Juan","");
        e = assertThrows(Exception.class,()->
                servicioImp.crearTarea(dto2));
        assertEquals("La descripción es obligatoria",e.getMessage());
    }
    @Test
    void listarTareas() {

        Tarea t1 = new Tarea(0L,"dsf","sad");
        List<Tarea> tareas = List.of(t1);
        when(repo.listar()).thenReturn(tareas);

        List<TareaDTO> tareasDTO = servicioImp.listarTareas();
        assertEquals(tareas.size(), tareasDTO.size());
        assertNotNull(tareasDTO);
        verify(repo, times(1)).listar();

    }

    @Test
    void modificarTareaError() {

        Tarea tareaExistente = new Tarea(1L, "Nombre Original", "Desc Original");
        when(repo.buscarPorId(1L)).thenReturn(tareaExistente);
        assertThrows(Exception.class,()-> {
            servicioImp.modificarTarea("",1L);
        });

    }

    @Test
    void modificarTareaCorrecto() {

        Tarea tareaExistente = new Tarea(1L, "Nombre Original", "Desc Original");
        when(repo.buscarPorId(1L)).thenReturn(tareaExistente);
        assertDoesNotThrow(()-> {
            servicioImp.modificarTarea("dddfs",1L);
        });

    }

    @Test
    void eliminarTareaError() {

        when(repo.buscarPorId(1L)).thenReturn(null);

        assertThrows(Exception.class,()-> {
           servicioImp.eliminarTarea(1L);
        });
    }

    @Test
    void eliminarTareaCorrecto() {

        Tarea tareaExistente = new Tarea(1L, "Nombre Original", "Desc Original");
        when(repo.buscarPorId(1L)).thenReturn(tareaExistente);
        assertDoesNotThrow(()-> {
            servicioImp.eliminarTarea(1L);
        });
    }


    @Test
    void buscarTareaPorId() {
    }
}
