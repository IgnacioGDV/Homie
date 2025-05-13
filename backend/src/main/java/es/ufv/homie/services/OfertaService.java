package es.ufv.homie.services;

import es.ufv.homie.model.Oferta;
import es.ufv.homie.model.Photo;
import es.ufv.homie.repository.OfertaRepository;
import es.ufv.homie.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public Oferta crearOferta(Oferta oferta, List<Photo> fotosAdjuntas) {
        // Guarda la oferta primero
        Oferta savedOferta = ofertaRepository.save(oferta);

        // Asocia y guarda las fotos
        if (fotosAdjuntas != null && !fotosAdjuntas.isEmpty()) {
            for (Photo foto : fotosAdjuntas) {
                foto.setOfferId(savedOferta.getIdoffer());
                photoRepository.save(foto);
            }
        }

        return savedOferta;
    }

    public List<Oferta> obtenerTodas() {
        List<Oferta> ofertas = ofertaRepository.findAll();
        for (Oferta o : ofertas) {
            List<Photo> fotos = photoRepository.findByOfferId(o.getIdoffer());
            o.setFotos(fotos.stream().map(Photo::getUrl).toList()); // envías los nombres para renderizar
        }
        return ofertas;
    }
}
