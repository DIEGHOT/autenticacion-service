package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    // 1. RÚBRICA: Probar save
    @Test
    void debeGuardarUsuario() {
        Usuario nuevo = new Usuario(null, "nuevoUser", "1234");
        Usuario guardado = repository.save(nuevo);

        assertNotNull(guardado.getId());
        assertEquals("nuevoUser", guardado.getUsername());
    }

    // 2. RÚBRICA: Probar findById
    @Test
    void debeEncontrarUsuarioPorId() {
        Usuario usuario = repository.save(new Usuario(null, "user2", "abcd"));
        
        Optional<Usuario> encontrado = repository.findById(usuario.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("user2", encontrado.get().getUsername());
    }

    // 3. RÚBRICA: Probar findAll
    @Test
    void debeListarTodosLosUsuarios() {
        repository.save(new Usuario(null, "userA", "111"));
        repository.save(new Usuario(null, "userB", "222"));

        List<Usuario> lista = repository.findAll();

        assertTrue(lista.size() >= 2);
    }
}
