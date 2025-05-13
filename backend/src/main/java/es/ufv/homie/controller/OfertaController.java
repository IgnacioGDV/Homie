package es.ufv.homie.controller;

import es.ufv.homie.model.Oferta;
import es.ufv.homie.model.OfertaDTO;
import es.ufv.homie.model.Photo;
import es.ufv.homie.services.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @PostMapping("/crear")
    public ResponseEntity<Void> crearOferta(@RequestBody OfertaDTO dto) {
        Oferta oferta = dto.getOferta();

        List<Photo> photos = dto.getFotos().stream().map(f -> {
            Photo p = new Photo();
            p.setUrl(f.getUrl());
            p.setData(Base64.getDecoder().decode(f.getData()));
            return p;
        }).toList();

        ofertaService.crearOferta(oferta, photos);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public List<Oferta> obtenerTodas() {
        return ofertaService.obtenerTodas();
    }
}
