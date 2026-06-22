# Autenticacion-service ## 🧪 Plan de Pruebas Unitarias (Testing)

Se implementaron pruebas unitarias con **JUnit 5** y **Mockito** para asegurar el 100% de cobertura en la capa de Servicios (`Service`), aislando la base de datos para no afectar los datos reales.

### Microservicio Autenticación (`UsuarioService`)
* **Cobertura:** ✅ 100% (6/6 tests exitosos).
* **Reglas de negocio validadas:** Se probó que el login lanza una `RuntimeException` cuando la contraseña es incorrecta o cuando el usuario no existe. Además, se validó el comportamiento esperado al registrar y buscar por ID.