package es.ufv.homie.services;

import es.ufv.homie.model.Oferta;
import java.util.ArrayList;
import java.util.List;

public class OfertaService {

    private static final List<Oferta> ofertas = new ArrayList<>();
    private static final List<Oferta> favoritos = new ArrayList<>(); // <-- AÑADIDO

    public static void addOferta(Oferta oferta) {
        ofertas.add(oferta);
    }

    public static List<Oferta> getOfertas() {
        return new ArrayList<>(ofertas);
    }

    public static void addFavorito(Oferta oferta) {
        favoritos.add(oferta);
    }

    public static List<Oferta> getFavoritos() {
        return new ArrayList<>(favoritos);
    }
}
