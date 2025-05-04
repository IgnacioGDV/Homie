package es.ufv.homie.views;

import com.vaadin.flow.component.dependency.CssImport;
import es.ufv.homie.services.NotificationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.util.ArrayList;
import java.util.List;

@CssImport("./themes/styles/crearoferta.css")
@Route("crear-oferta")
@PageTitle("Crear Oferta")
public class CrearOferta extends VerticalLayout {

    private final NotificationService notificationService; // Inyecta el servicio de notificación
    private List<Oferta> ofertas = new ArrayList<>(); // Lista para almacenar las ofertas

    public CrearOferta(NotificationService notificationService) {
        this.notificationService = notificationService;

        // Título de la oferta
        TextField tituloOferta = new TextField("Título de la oferta");

        // Descripción
        TextArea descripcion = new TextArea("Descripción");

        // Universidad
        ComboBox<String> universidad = new ComboBox<>("Universidad");
        universidad.setItems("Universidad Francisco de Vitoria", "Universidad Complutense de Madrid",
                "Universidad Politécnica de Madrid", "Universidad Autónoma de Madrid",
                "Universidad Carlos III de Madrid", "Universidad Rey Juan Carlos");

        // Ubicación
        ComboBox<String> ubicacion = new ComboBox<>("Ubicación");
        ubicacion.setItems("Alcobendas", "Alcorcón", "Boadilla del Monte", "Las Rozas", "San Sebastián de los Reyes",
                "Las Tablas", "Sanchinarro", "Aluche", "Getafe");

        // Número de inquilinos
        NumberField numeroInquilinos = new NumberField("Número de inquilinos");
        numeroInquilinos.setMin(1);
        numeroInquilinos.setStep(1);
        numeroInquilinos.setWidth("150px");

        // Edad máxima
        NumberField edadMaxima = new NumberField("Edad máxima");
        edadMaxima.setMin(18);
        edadMaxima.setMax(99);
        edadMaxima.setStep(1);
        edadMaxima.setWidth("150px");

        // Subir imágenes
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(8);
        upload.setDropAllowed(true);

        // Precio
        NumberField precio = new NumberField("Precio (€)");
        precio.setMin(0);
        precio.setStep(10);
        precio.setWidth("150px");

        // Botón para publicar
        Button publicarButton = new Button("Publicar Oferta");

        publicarButton.addClickListener(e -> {
            // Lógica para publicar la oferta
            String titulo = tituloOferta.getValue();
            String descripcionOferta = descripcion.getValue();
            String universidadSeleccionada = universidad.getValue();
            String ubicacionSeleccionada = ubicacion.getValue();
            double numeroInquilinosValor = numeroInquilinos.getValue();
            double edadMaximaValor = edadMaxima.getValue();
            String imagen = buffer.getFileName(); // Obtiene el nombre del archivo de la imagen subida
            double precioValor = precio.getValue();

            // Crear la oferta
            Oferta nuevaOferta = new Oferta(titulo, descripcionOferta, universidadSeleccionada, ubicacionSeleccionada,
                    numeroInquilinosValor, edadMaximaValor, imagen, precioValor);

            // Añadir la oferta a la lista
            ofertas.add(nuevaOferta);

            // Mostrar una notificación de éxito
            notificationService.showNotification("¡Oferta publicada con éxito!");

            // Redirigir a la página de inicio
            getUI().ifPresent(ui -> ui.navigate("inicio"));
        });

        // Añadir los componentes al layout
        add(tituloOferta, descripcion, universidad, ubicacion, numeroInquilinos, edadMaxima, upload, precio, publicarButton);
    }

    // Clase interna que representa una oferta
    public static class Oferta {
        private String titulo;
        private String descripcion;
        private String universidad;
        private String ubicacion;
        private double numeroInquilinos;
        private double edadMaxima;
        private String imagen;
        private double precio;

        public Oferta(String titulo, String descripcion, String universidad, String ubicacion, double numeroInquilinos,
                      double edadMaxima, String imagen, double precio) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.universidad = universidad;
            this.ubicacion = ubicacion;
            this.numeroInquilinos = numeroInquilinos;
            this.edadMaxima = edadMaxima;
            this.imagen = imagen;
            this.precio = precio;
        }

        // Getters
        public String getTitulo() {
            return titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getUniversidad() {
            return universidad;
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public double getNumeroInquilinos() {
            return numeroInquilinos;
        }

        public double getEdadMaxima() {
            return edadMaxima;
        }

        public String getImagen() {
            return imagen;
        }

        public double getPrecio() {
            return precio;
        }
    }
}
