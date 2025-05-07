
package es.ufv.homie.services;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    private final Map<String, String> users = new HashMap<>(); // Almacena email -> password
    private final Map<String, String> roles = new HashMap<>(); // Almacena email -> rol

    // Método para registrar un nuevo usuario con rol
    public void registerUser(String email, String password, String phone, String birthDate, String role) {
        users.put(email, password);
        roles.put(email, role); // Guarda el rol del usuario
    }

    // Método para verificar si el usuario existe
    public boolean exists(String username) {
        return users.containsKey(username);
    }

    // Método para autenticar al usuario
    public boolean authenticate(String username, String password) {
        return password.equals(users.get(username)); // Verifica que la contraseña coincida
    }

    // Método para obtener el rol del usuario
    public String getUserRole(String username) {
        return roles.get(username); // Retorna el rol almacenado
    }
}