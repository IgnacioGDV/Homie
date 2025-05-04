package es.ufv.homie.services;

import es.ufv.homie.model.Oferta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaService {

    private List<Oferta> ofertas;

    // Constructor
    public OfertaService() {
        this.ofertas = new ArrayList<>();
        // Agregamos algunas ofertas de ejemplo
        ofertas.add(new Oferta("Piso en Madrid", "Apartamento moderno en Madrid", 1200, "imagen1.jpg"));
        ofertas.add(new Oferta("Estudio en Barcelona", "Estudio en el centro de Barcelona", 900, "imagen2.jpg"));
    }

    // Método para obtener todas las ofertas
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    // Método para agregar una nueva oferta
    public void addOferta(Oferta oferta) {
        ofertas.add(oferta);
    }
}
