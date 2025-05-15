package es.ufv.homie.repository;

import es.ufv.homie.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUserId(Long userId);

    // ✔️ Este es el correcto con los nombres reales de las columnas de la entidad
    boolean existsByUser_IdAndOffer_Idoffer(Long userId, Long ofertaId);


}
