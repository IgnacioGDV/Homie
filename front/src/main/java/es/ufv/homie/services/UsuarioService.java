package es.ufv.homie.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final BCryptPasswordEncoder passwordEncoder;

    private String email = "usuario@dominio.com";
    private String passwordHash = "$2a$10$Vbv5YPxjSxtQys5kC0wQ8e.KPb.KbHHTQOuwNrQSiVOwNo4/LoqRi";  // "1234" cifrada con bcrypt
    private String phone;
    private LocalDate birthDate;
    private String userType;

    public UsuarioService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean authenticate(String email, String password) {
        if (this.email.equals(email)) {
            return passwordEncoder.matches(password, this.passwordHash);
        }
        return false;
    }

    public boolean registerUser(String email, String password, String phone, LocalDate birthDate, String userType) {
        this.email = email;
        this.passwordHash = passwordEncoder.encode(password);
        this.phone = phone;
        this.birthDate = birthDate;
        this.userType = userType;
        return true;
    }

    public boolean exists(String email) {
        return this.email.equals(email);  // Verifica si el usuario ya está registrado
    }

    public String getUserType() {
        return userType;
    }
}
