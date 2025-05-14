// Oferta.java (ubicado en es.ufv.homie.model)
package es.ufv.homie.model;

import java.util.List;
import java.util.Locale;

public class Oferta {
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private double precio;
    private String university;
    private String edadMax;
    private String genero;
    private String piscina;
    private List<String> imagenes;

    public Oferta(String titulo, String descripcion, String ubicacion, double precio, String university, String edadMax, String genero,String piscina,List<String> imagenes) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.university = university;
        this.edadMax = edadMax;
        this.genero = genero;
        this.piscina = piscina;
        this.imagenes = imagenes;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getUbicacion() { return ubicacion; }
    public double getPrecio() { return precio; }
    public String getUniversity() { return university; }
    public String getEdadMax() { return edadMax; }
    public String getGenero(){ return genero; }
    public String getPiscina() { return piscina; }
    public List<String> getImagenes() { return imagenes; }
}