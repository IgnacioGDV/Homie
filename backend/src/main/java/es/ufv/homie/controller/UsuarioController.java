package es.ufv.homie.controller;

import es.ufv.homie.model.Usuario;
import es.ufv.homie.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.existe(usuario.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El correo ya está registrado.");
            }
            Usuario guardado = usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }


    @GetMapping("/buscar")
    public Usuario buscarPorEmail(@RequestParam String email) {
        return usuarioService.obtenerPorEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Usuario usuario = usuarioService.obtenerPorEmail(email);
        if (usuario != null && password.equals(usuario.getPassword())) {
            return ResponseEntity.ok("Login correcto. Rol: " + usuario.getRole());
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
