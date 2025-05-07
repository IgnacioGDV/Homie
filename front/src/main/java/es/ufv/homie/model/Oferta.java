// Oferta.java (ubicado en es.ufv.homie.model)
package es.ufv.homie.model;

import java.util.List;

public class Oferta {
    private String titulo;
    private String descripcion;
    private String universidad;
    private String ubicacion;
    private double precio;
    private List<String> imagenes;

    public Oferta(String titulo, String descripcion, String universidad, String ubicacion, double precio, List<String> imagenes) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.universidad = universidad;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.imagenes = imagenes;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getUniversidad() { return universidad; }
    public String getUbicacion() { return ubicacion; }
    public double getPrecio() { return precio; }
    public List<String> getImagenes() { return imagenes; }
}