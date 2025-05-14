package es.ufv.homie.model;

import java.util.List;

public class Oferta {
    private String titulo;
    private String descripcion;
    private String universidad;
    private String ubicacion;
    private double precio;
    private List<String> imagenes;

    // Nuevos campos opcionales
    private Integer edadMaxima; // Puede ser null
    private String genero;      // "Masculino", "Femenino" o null
    private String piscina;     // "Sí", "No" o null

    public Oferta(String titulo, String descripcion, String universidad,
                  String ubicacion, double precio, List<String> imagenes) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.universidad = universidad;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.imagenes = imagenes;
    }

    // --- Setters para filtros ---
    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPiscina(String piscina) {
        this.piscina = piscina;
    }

    // --- Getters principales ---
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getUniversidad() { return universidad; }
    public String getUbicacion() { return ubicacion; }
    public double getPrecio() { return precio; }
    public List<String> getImagenes() { return imagenes; }

    // --- Getters para filtros ---
    public Integer getEdadMaxima() { return edadMaxima; }
    public String getGenero() { return genero; }
    public String getPiscina() { return piscina; }
}
