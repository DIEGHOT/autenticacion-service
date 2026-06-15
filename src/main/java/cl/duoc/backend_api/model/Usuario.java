package cl.duoc.backend_api.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    // 👇 AGREGA ESTE NUEVO CONSTRUCTOR 👇
    // Este constructor recibe los 3 parámetros que envían los tests y le asigna un nombre por defecto
    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = "Usuario de Prueba"; // Evita que falle el nullable = false
        this.activo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean activo = true;

    public Usuario() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}


