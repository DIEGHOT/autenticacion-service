package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.Usuario;
import cl.duoc.backend_api.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
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

    // --- TEST 1: REGISTRO ---
    @Test
    @DisplayName("Debe registrar un usuario exitosamente")
    void debeRegistrarUsuarioExitosamente() {
        Usuario usuario = new Usuario(null, "testUser", "pass");
        Usuario usuarioGuardado = new Usuario(1L, "testUser", "pass");

        Mockito.when(repository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        Usuario resultado = service.registrar(usuario);

        assertNotNull(resultado.getId());
        assertEquals("testUser", resultado.getUsername());
        Mockito.verify(repository, Mockito.times(1)).save(any(Usuario.class));
    }

    // --- TEST 2: LOGIN (CASO FELIZ) ---
    @Test
    @DisplayName("Debe retornar usuario cuando el login es exitoso")
    void debeRetornarUsuario_CuandoLoginEsExitoso() {
        Usuario usuarioBD = new Usuario(1L, "admin", "1234");
        Mockito.when(repository.findByUsername("admin")).thenReturn(Optional.of(usuarioBD));

        Usuario resultado = service.login("admin", "1234");

        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsername());
    }

    // --- TEST 3: LOGIN (ERROR - CONTRASEÑA) ---
    @Test
    @DisplayName("Debe lanzar excepción cuando la contraseña es incorrecta")
    void debeLanzarExcepcion_CuandoPasswordEsIncorrecto() {
        Usuario usuarioBD = new Usuario(1L, "admin", "1234");
        Mockito.when(repository.findByUsername("admin")).thenReturn(Optional.of(usuarioBD));

        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            service.login("admin", "claveMala");
        });

        assertEquals("Contraseña incorrecta", excepcion.getMessage());
    }

    // --- TEST 4: LOGIN (ERROR - USUARIO NO ENCONTRADO) ---
    @Test
    @DisplayName("Debe lanzar excepción cuando el usuario no existe")
    void debeLanzarExcepcion_CuandoUsuarioNoExiste() {
        Mockito.when(repository.findByUsername("fantasma")).thenReturn(Optional.empty());

        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            service.login("fantasma", "1234");
        });

        assertEquals("Usuario no encontrado", excepcion.getMessage());
    }

    // --- TEST 5: BUSCAR POR ID (CASO FELIZ) ---
    @Test
    @DisplayName("Debe retornar un usuario si el ID existe")
    void debeRetornarUsuario_CuandoIdExiste() {
        Usuario usuarioBD = new Usuario(1L, "pedro", "secreta");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(usuarioBD));

        Usuario resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("pedro", resultado.getUsername());
    }

    // --- TEST 6: BUSCAR POR ID (ERROR) ---
    @Test
    @DisplayName("Debe lanzar excepción si el ID no existe en el sistema")
    void debeLanzarExcepcion_CuandoIdNoExiste() {
        Mockito.when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(99L);
        });

        assertEquals("Usuario con ID 99 no encontrado en el sistema", excepcion.getMessage());
    }
}
