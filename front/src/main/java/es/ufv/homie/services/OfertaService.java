package es.ufv.homie.services;

import es.ufv.homie.model.OfertaF;
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

    public void publicarOferta(OfertaF ofertaF) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OfertaF> request = new HttpEntity<>(ofertaF, headers);
        restTemplate.postForEntity(backendUrl + "/crear", request, OfertaF.class);
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
    public void addFavorito(OfertaF ofertaF) {
        if (!favoritos.contains(ofertaF)) {
            favoritos.add(ofertaF);
        }
    }

    public List<OfertaF> getFavoritos() {
        return new ArrayList<>(favoritos);
    }
}
