package es.ufv.homie.services;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    private final Map<String, String> users = new HashMap<>(); // email -> password
    private final Map<String, String> roles = new HashMap<>(); // email -> rol

    public void registerUser(String email, String password, String phone, String birthDate, String role) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException(
                    "La contraseña debe tener al menos 1 mayúscula, un mínimo de 2 letras y al menos 8 caracteres"
            );
        }
        users.put(email, password);
        roles.put(email, role);
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }

    public boolean authenticate(String username, String password) {
        return password.equals(users.get(username));
    }

    public String getUserRole(String username) {
        return roles.get(username);
    }

    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        // Requiere al menos 8 caracteres, 2 letras (cualquier tipo), y 1 mayúscula
        String pattern = "^(?=(?:.*[A-Za-z]){2,})(?=.*[A-Z]).{8,}$";
        return password.matches(pattern);
    }
}
