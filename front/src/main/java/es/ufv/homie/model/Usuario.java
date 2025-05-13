package es.ufv.homie.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false)
    private String role;

    private String universidad;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }
}
