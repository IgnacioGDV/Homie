package es.ufv.homie.controller;

import es.ufv.homie.model.Photo;
import es.ufv.homie.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhotoById(@PathVariable Long id) {
        Optional<Photo> photoOpt = photoRepository.findById(id);
        return photoOpt.map(this::buildImageResponse)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{filename}")
    public ResponseEntity<byte[]> getPhotoByName(@PathVariable String filename) {
        Optional<Photo> photoOpt = photoRepository.findByUrl(filename);
        return photoOpt.map(this::buildImageResponse)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<byte[]> buildImageResponse(Photo photo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Puedes ajustar si usas PNG
        return new ResponseEntity<>(photo.getData(), headers, HttpStatus.OK);
    }
}
