package es.ufv.homie.controller;

import es.ufv.homie.model.Usuario;
import es.ufv.homie.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
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
