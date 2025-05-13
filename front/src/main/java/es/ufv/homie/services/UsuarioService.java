package es.ufv.homie.services;

import es.ufv.homie.model.Usuario;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDate;


@Service
public class UsuarioService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String backendUrl = "http://localhost:8082/api/usuarios";

    public boolean isValidPassword(String password) {
        if (password == null) return false;
        return password.matches("^(?=(?:.*[A-Za-z]){2,})(?=.*[A-Z]).{8,}$");
    }

    public void registerUser(String email, String password, String phone, String birthDate, String role) {
        Usuario nuevo = new Usuario();
        nuevo.setEmail(email);
        nuevo.setPassword(password);
        nuevo.setPhone(phone);
        nuevo.setBirthDate(LocalDate.parse(birthDate));

        nuevo.setRole(role);
        nuevo.setUniversidad("UFV");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Usuario> request = new HttpEntity<>(nuevo, headers);
        restTemplate.postForEntity(backendUrl + "/crear", request, Usuario.class);
    }

    public boolean exists(String email) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(backendUrl + "/buscar")
                .queryParam("email", email);
        try {
            Usuario usuario = restTemplate.getForObject(builder.toUriString(), Usuario.class);
            return usuario != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean authenticate(String email, String password) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(backendUrl + "/login")
                .queryParam("email", email)
                .queryParam("password", password);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), null, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserRole(String email) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(backendUrl + "/buscar")
                .queryParam("email", email);
        try {
            Usuario usuario = restTemplate.getForObject(builder.toUriString(), Usuario.class);
            return usuario != null ? usuario.getRole() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
