package cl.duoc.backend_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.backend_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}

