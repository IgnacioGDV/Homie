package es.ufv.homie.model;

import java.util.List;

public class OfertaF {

    private Long idoffer;
    private String title;
    private String location;
    private Integer guests;
    private Double price;
    private String preferredGender;
    private String description;
    private Boolean hasPool;
    private Integer createdBy;
    private String universidad;
    private Integer edadmax;
    private List<String> fotos;

    public OfertaF() {}

    public OfertaF(String title, String description, String universidad, String location, Double price, List<String> fotos) {
        this.title = title;
        this.description = description;
        this.universidad = universidad;
        this.location = location;
        this.price = price;
        this.fotos = fotos;
    }

    // Getters y setters (asegúrate de tenerlos todos generados)
    public Long getIdoffer() { return idoffer; }
    public void setIdoffer(Long idoffer) { this.idoffer = idoffer; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Integer getGuests() { return guests; }
    public void setGuests(Integer guests) { this.guests = guests; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getPreferredGender() { return preferredGender; }
    public void setPreferredGender(String preferredGender) { this.preferredGender = preferredGender; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getHasPool() { return hasPool; }
    public void setHasPool(Boolean hasPool) { this.hasPool = hasPool; }

    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }

    public Integer getEdadmax() { return edadmax; }
    public void setEdadmax(Integer edadmax) { this.edadmax = edadmax; }

    public List<String> getFotos() { return fotos; }
    public void setFotos(List<String> fotos) { this.fotos = fotos; }
}
