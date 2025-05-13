package es.ufv.homie.model;

import java.util.List;

public class OfertaDTO {
    private Oferta oferta;
    private List<PhotoDTO> fotos;

    // Getters y setters

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public List<PhotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<PhotoDTO> fotos) {
        this.fotos = fotos;
    }
}
