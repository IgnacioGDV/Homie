package es.ufv.homie.services;

import es.ufv.homie.model.Favorito;
import es.ufv.homie.model.Oferta;
import es.ufv.homie.model.Photo;
import es.ufv.homie.model.Usuario;
import es.ufv.homie.repository.FavoritoRepository;
import es.ufv.homie.repository.OfertaRepository;
import es.ufv.homie.repository.PhotoRepository;
import es.ufv.homie.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void guardarFavorito(Long userId, Long ofertaId) {
        if (!favoritoRepository.existsByUser_IdAndOffer_Idoffer(userId, ofertaId)) {
            Usuario user = usuarioRepository.findById(userId).orElse(null);
            Oferta oferta = ofertaRepository.findById(ofertaId).orElse(null);
            if (user != null && oferta != null) {
                Favorito fav = new Favorito();
                fav.setUser(user);
                fav.setOffer(oferta);
                favoritoRepository.save(fav);
            }
        }

    }

    public List<Oferta> obtenerFavoritosPorUsuario(Long userId) {
        List<Favorito> favoritos = favoritoRepository.findByUserId(userId);
        List<Oferta> resultado = new ArrayList<>();
        for (Favorito fav : favoritos) {
            Oferta o = fav.getOffer();
            List<Photo> fotos = photoRepository.findByOfferId(o.getIdoffer());
            o.setFotos(fotos.stream().map(Photo::getUrl).toList());
            resultado.add(o);
        }
        return resultado;
    }
    public List<Oferta> obtenerFavoritosPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) return List.of();
        return obtenerFavoritosPorUsuario(usuario.getId());
    }



}
