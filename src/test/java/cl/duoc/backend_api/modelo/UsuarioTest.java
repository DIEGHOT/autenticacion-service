package cl.duoc.backend_api.modelo;

import org.junit.jupiter.api.Test;
import cl.duoc.backend_api.model.Usuario;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    // 1. Test puro: Constructor y Setters
    @Test
    void debeCrearUsuarioYAsignarValores() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
        usuario.setPassword("1234");

        assertEquals(1L, usuario.getId());
        assertEquals("admin", usuario.getUsername());
        assertEquals("1234", usuario.getPassword());
    }

    // 2. Test puro: Constructor lleno (¡CORREGIDO!)
    @Test
    void debeCrearUsuarioConConstructorLleno() {
        // Ahora sí usamos el constructor lleno
        Usuario usuario = new Usuario(2L, "testUser", "pass");

        assertEquals(2L, usuario.getId());
        assertEquals("testUser", usuario.getUsername());
        assertEquals("pass", usuario.getPassword());
    }

    // 3. Test puro: Valores nulos iniciales
    @Test
    void debeTenerValoresNulosAlUsarConstructorVacio() {
        Usuario usuario = new Usuario();

        assertNull(usuario.getId());
        assertNull(usuario.getUsername());
        assertNull(usuario.getPassword());
    }
}
