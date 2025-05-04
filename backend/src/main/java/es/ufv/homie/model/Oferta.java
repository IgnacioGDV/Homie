package es.ufv.homie.model;

import java.util.UUID;

public class Oferta {

    private String id;
    private String titulo;
    private String descripcion;
    private double precio;
    private String imagen;

    // Constructor por defecto
    public Oferta() {
        this.id = UUID.randomUUID().toString(); // Aquí generamos un ID único
    }

    // Constructor con parámetros
    public Oferta(String titulo, String descripcion, double precio, String imagen) {
        this.id = UUID.randomUUID().toString(); // Convertimos el UUID a String
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
