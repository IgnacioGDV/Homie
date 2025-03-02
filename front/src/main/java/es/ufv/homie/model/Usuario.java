package es.ufv.homie.model;

import java.util.UUID;

public class Usuario {

    private String id;
    private String nombre;
    private String apellidos;
    private String nif;
    private String email;
    private Direccion direccion;

    public Usuario() {

    }

    public Usuario(String nombre, String apellidos, String nif, String email, Direccion direccion) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.email = email;
        this.direccion = direccion;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", direccion=" + direccion +
                '}';
    }
}
