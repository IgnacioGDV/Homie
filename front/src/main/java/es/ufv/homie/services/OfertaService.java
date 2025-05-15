package es.ufv.homie.services;

import es.ufv.homie.model.OfertaDTOFront;
import es.ufv.homie.model.OfertaF;
import es.ufv.homie.model.PhotoFront;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String backendUrl = "http://localhost:8082/api/ofertas";

    private final List<OfertaF> favoritos = new ArrayList<>();

    public void publicarOferta(OfertaF ofertaF, List<PhotoFront> fotos) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        OfertaDTOFront dto = new OfertaDTOFront();
        dto.setOferta(ofertaF);
        dto.setFotos(fotos);

        HttpEntity<OfertaDTOFront> request = new HttpEntity<>(dto, headers);

        try {
            restTemplate.postForEntity(backendUrl + "/crear", request, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar la oferta al backend: " + e.getMessage());
        }
    }


    public List<OfertaF> obtenerOfertasDesdeBackend() {
        try {
            ResponseEntity<OfertaF[]> response = restTemplate.getForEntity(backendUrl, OfertaF[].class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return List.of(response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // ✅ FAVORITOS: Agregar y obtener
    public void addFavorito(Long userId, Long ofertaId) {
        String url = "http://localhost:8082/api/ofertas/favoritos/agregar?userId=" + userId + "&ofertaId=" + ofertaId;

        try {
            restTemplate.postForEntity(url, null, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al añadir favorito: " + e.getMessage());
        }
    }


    public List<OfertaF> getFavoritosDeUsuario(Long userId) {
        String url = backendUrl + "/favoritos/" + userId;
        try {
            ResponseEntity<OfertaF[]> response = restTemplate.getForEntity(url, OfertaF[].class);
            return response.getBody() != null ? List.of(response.getBody()) : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public List<OfertaF> getFavoritosByEmail(String email) {
        if (email == null) return new ArrayList<>();
        try {
            String url = backendUrl + "/favoritos?email=" + email;
            ResponseEntity<OfertaF[]> response = restTemplate.getForEntity(url, OfertaF[].class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return List.of(response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}


