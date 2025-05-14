package es.ufv.homie.model;

import java.util.List;

public class OfertaDTOFront {
    private OfertaF oferta;
    private List<PhotoFront> fotos;

    public OfertaDTOFront() {}

    public OfertaF getOferta() {
        return oferta;
    }

    public void setOferta(OfertaF oferta) {
        this.oferta = oferta;
    }

    public List<PhotoFront> getFotos() {
        return fotos;
    }

    public void setFotos(List<PhotoFront> fotos) {
        this.fotos = fotos;
    }
}
