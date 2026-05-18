package cl.duoc.backend_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cl.duoc.backend_api.model.Usuario;
import cl.duoc.backend_api.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // Registro
    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return service.registrar(usuario);
    }

    // Login
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return service.login(usuario.getUsername(), usuario.getPassword());
    }
}



