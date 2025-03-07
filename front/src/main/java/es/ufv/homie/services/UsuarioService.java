package es.ufv.homie.services;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import com.google.gson.JsonSyntaxException;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ufv.homie.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements Serializable {


//    private static final String URL = "http://back:8080/api/v1/usuarios%s";
    private static final String URL = "http://localhost:8080/api/v1/usuarios%s";

    public ArrayList<Usuario> getUsers() {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();

        // Crear una solicitud HTTP de tipo GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(URL, ""))) // Reemplaza con la URL de tu API
                .GET()
                .build();

        HttpResponse<String> response = null; // Inicializamos la variable aquí

        // Enviar la solicitud y manejar la respuesta
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Type mapType = new TypeToken<ArrayList<Usuario>>() {}.getType();

            // Imprimir la respuesta del servidor para verificar el contenido
            System.out.println("Respuesta del servidor: " + response.body());

            // Deserializar la respuesta usando Gson, esperando un array de usuarios
            ArrayList<Usuario> usuarios = gson.fromJson(response.body(), mapType);
            return usuarios;

        } catch (JsonSyntaxException e) {
            // Manejo de excepción si el JSON no tiene el formato esperado
            System.out.println("Error al deserializar la respuesta: " + e.getMessage());
            if (response != null) {
                System.out.println("Respuesta que causó el error: " + response.body());
            }
            return null;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }




    public Usuario addUser(Usuario usuario) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();

        // Crear una solicitud HTTP de tipo POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(URL, "")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(usuario)))
                .build();

        // Enviar la solicitud y manejar la respuesta
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Usuario user = gson.fromJson(response.body(), Usuario.class);
            return user;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario updateUser(String id, Usuario usuario) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();


        System.out.println(gson.toJson(usuario));
        // Crear una solicitud HTTP de tipo PUT
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(URL, "/" + id)))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(usuario)))
                .build();

        // Enviar la solicitud y manejar la respuesta
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Usuario user = gson.fromJson(response.body(), Usuario.class);
            return user;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createPDF () {
        HttpClient client = HttpClient.newHttpClient();

        // Crear una solicitud HTTP de tipo GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(URL, "/pdf" ))) // Reemplaza con la URL de tu API
                .GET()
                .build();

        // Enviar la solicitud y manejar la respuesta
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }



}
