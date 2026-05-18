package cl.duoc.backend_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.backend_api.model.Usuario;
import cl.duoc.backend_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario registrar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario login(String username, String password) {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}


