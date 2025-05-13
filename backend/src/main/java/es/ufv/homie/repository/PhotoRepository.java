package es.ufv.homie.repository;

import es.ufv.homie.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByOfferId(Long offerId);
    Optional<Photo> findByUrl(String url); // opcional si accedes por nombre
}
