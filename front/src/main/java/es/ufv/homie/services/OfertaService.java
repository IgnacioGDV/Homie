package es.ufv.homie.services;

import es.ufv.homie.model.Oferta;
import java.util.ArrayList;
import java.util.List;

public class OfertaService {

    private static final List<Oferta> ofertas = new ArrayList<>();

    public static void addOferta(Oferta oferta) {
        ofertas.add(oferta);
    }

    public static List<Oferta> getOfertas() {
        return new ArrayList<>(ofertas);
    }
}