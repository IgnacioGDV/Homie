package es.ufv.homie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Oferta offer;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUser() { return user; }
    public void setUser(Usuario user) { this.user = user; }

    public Oferta getOffer() { return offer; }
    public void setOffer(Oferta offer) { this.offer = offer; }
}
