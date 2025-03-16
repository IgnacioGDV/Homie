package es.ufv.homie.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    // Instancia del codificador de contraseñas
    private final BCryptPasswordEncoder passwordEncoder;

    // Aquí simulamos una base de datos con un usuario de ejemplo, con la contraseña cifrada
    private String email = "usuario@dominio.com";
    private String passwordHash = "$2a$10$Vbv5YPxjSxtQys5kC0wQ8e.KPb.KbHHTQOuwNrQSiVOwNo4/LoqRi";  // Contraseña "1234" cifrada con bcrypt
    private String phone; // Nuevo campo: teléfono
    private LocalDate birthDate; // Nuevo campo: fecha de nacimiento
    private String userType; // Nuevo campo: tipo de usuario ("Anfitrión" o "Huésped")

    // Constructor
    public UsuarioService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Método para autenticar al usuario
    public boolean authenticate(String email, String password) {
        if (this.email.equals(email)) {
            return passwordEncoder.matches(password, this.passwordHash); // Verifica si la contraseña introducida coincide con el hash
        }
        return false;
    }

    // Método para registrar un nuevo usuario
    public boolean registerUser(String email, String password, String phone, LocalDate birthDate, String userType) {
        this.email = email;
        this.passwordHash = passwordEncoder.encode(password); // Guarda la contraseña cifrada
        this.phone = phone; // Guarda el teléfono
        this.birthDate = birthDate; // Guarda la fecha de nacimiento
        this.userType = userType; // Guarda el tipo de usuario ("Anfitrión" o "Huésped")
        return true;
    }

    // Método para obtener el tipo de usuario (opcional, en caso de necesidad)
    public String getUserType() {
        return userType;
    }
}
