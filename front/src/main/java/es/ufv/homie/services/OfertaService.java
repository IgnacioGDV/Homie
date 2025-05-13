package es.ufv.homie.services;

import es.ufv.homie.model.Oferta;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String backendUrl = "http://localhost:8082/api/ofertas";

    private final List<Oferta> favoritos = new ArrayList<>();

    public void publicarOferta(Oferta oferta) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Oferta> request = new HttpEntity<>(oferta, headers);
        restTemplate.postForEntity(backendUrl + "/crear", request, Oferta.class);
    }

    public List<Oferta> obtenerOfertasDesdeBackend() {
        try {
            ResponseEntity<Oferta[]> response = restTemplate.getForEntity(backendUrl, Oferta[].class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return List.of(response.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // ✅ FAVORITOS: Agregar y obtener
    public void addFavorito(Oferta oferta) {
        if (!favoritos.contains(oferta)) {
            favoritos.add(oferta);
        }
    }

    public List<Oferta> getFavoritos() {
        return new ArrayList<>(favoritos);
    }
}
