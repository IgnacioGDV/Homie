package es.ufv.homie.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offers")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idoffer;

    private String title;
    private String location;
    private Integer guests;
    private Double price;

    @Column(name = "preferred_gender")
    private String preferredGender;

    private String description;

    @Column(name = "has_pool")
    private Boolean hasPool;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    private String universidad;

    private Integer edadmax;

    @Transient
    private List<String> fotos; // <-- Se añade esta propiedad virtual

    // Getters y setters

    public Long getIdoffer() {
        return idoffer;
    }

    public void setIdoffer(Long idoffer) {
        this.idoffer = idoffer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(String preferredGender) {
        this.preferredGender = preferredGender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasPool() {
        return hasPool;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Integer getEdadmax() {
        return edadmax;
    }

    public void setEdadmax(Integer edadmax) {
        this.edadmax = edadmax;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
