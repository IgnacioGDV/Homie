package es.ufv.homie.controller;

import es.ufv.homie.model.Oferta;
import es.ufv.homie.services.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    // Constructor con inyección de dependencia
    @Autowired
    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    // Endpoint para obtener todas las ofertas
    @GetMapping
    public List<Oferta> getOfertas() {
        return ofertaService.getOfertas();
    }

    // Endpoint para agregar una nueva oferta
    @PostMapping
    public void addOferta(@RequestBody Oferta oferta) {
        ofertaService.addOferta(oferta);
    }
}
