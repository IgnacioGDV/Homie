package es.ufv.homie.services;

import es.ufv.homie.model.Usuario;
import es.ufv.homie.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public boolean existe(String email) {
        return usuarioRepository.findByEmail(email) != null;
    }
}
