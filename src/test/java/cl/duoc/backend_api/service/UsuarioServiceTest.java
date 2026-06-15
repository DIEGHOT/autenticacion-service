package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Usuario;
import cl.duoc.backend_api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    // 1. Test Servicio: Registro Exitoso
    @Test
    void debeRegistrarUsuarioExitosamente() {
        Usuario usuario = new Usuario(null, "testUser", "pass");
        Usuario usuarioGuardado = new Usuario(1L, "testUser", "pass");

        Mockito.when(repository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        Usuario resultado = service.registrar(usuario);

        assertNotNull(resultado.getId());
        assertEquals("testUser", resultado.getUsername());
    }

    // 2. Test Servicio: Login Exitoso
    @Test
    void debeRetornarUsuario_CuandoLoginEsExitoso() {
        Usuario usuarioBD = new Usuario(1L, "admin", "1234");
        Mockito.when(repository.findByUsername("admin")).thenReturn(Optional.of(usuarioBD));

        Usuario resultado = service.login("admin", "1234");

        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsername());
    }

    // 3. Test Servicio: Login Fallido (¡CORREGIDO!)
    @Test
    void debeLanzarExcepcion_CuandoPasswordEsIncorrecto() {
        Usuario usuarioBD = new Usuario(1L, "admin", "1234");
        Mockito.when(repository.findByUsername("admin")).thenReturn(Optional.of(usuarioBD));

        // Verificamos que al meter una clave mala, el sistema arroje una RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.login("admin", "claveMala");
        });

        // Además verificamos que el mensaje del error sea exactamente el correcto
        assertEquals("Contraseña incorrecta", exception.getMessage());
    }
}

