package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.ufv.homie.model.Oferta;
import es.ufv.homie.services.NotificationService;
import es.ufv.homie.services.OfertaService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@CssImport("./themes/styles/crearoferta.css")
@Route("crear-oferta")
@PageTitle("Crear Oferta")
public class CrearOferta extends VerticalLayout {

    public CrearOferta(NotificationService notificationService) {
        addClassName("crear-oferta-layout");

        VerticalLayout formularioLayout = new VerticalLayout();
        formularioLayout.addClassName("formulario-oferta");

        // Logo y título
        Image logo = new Image("icons/homiepng.png", "Homie Logo");
        logo.addClassName("logo-homie");

        Span titulo = new Span("¡Bienvenido, Anfitrión!");
        titulo.getStyle().set("font-size", "24px").set("color", "#002147").set("font-weight", "bold");

        Span descripcionIntro = new Span("Crea tu oferta para que los inquilinos de Homie puedan aplicar a ella.");
        descripcionIntro.getStyle().set("color", "#555").set("font-size", "14px").set("text-align", "center");

        // Campos del formulario
        TextField tituloOferta = new TextField("Nombre de la oferta");

        ComboBox<String> ubicacion = new ComboBox<>("Ubicación por zona");
        ubicacion.setItems("Alcobendas", "Aluche", "Boadilla", "Brunete", "Pozuelo", "Majadahonda",
                "Madrid Centro", "Las Tablas", "Las Rozas", "Alcorcón", "Sanchinarro",
                "Getafe", "San Sebastián De Los Reyes", "Villaverde", "Villaviciosa De Odón");

        ComboBox<Double> numeroInquilinos = new ComboBox<>("Número de huéspedes necesarios");
        numeroInquilinos.setItems(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0);

        NumberField precio = new NumberField("Importe a pagar");
        precio.setPrefixComponent(new Span("€"));

        ComboBox<String> genero = new ComboBox<>("Género con el que prefiere convivir");
        genero.setItems("Masculino", "Femenino", "Sin preferencia");

        ComboBox<String> piscina = new ComboBox<>("¿Tiene piscina?");
        piscina.setItems("Sí", "No");

        ComboBox<String> universidad = new ComboBox<>("Universidad asociada (opcional)");
        universidad.setItems("UFV", "UCM", "UPM", "UAM", "UC3M", "URJC");

        NumberField edadMaxima = new NumberField("Edad máxima (opcional)");

        TextArea descripcion = new TextArea("Descripción");

        // Upload de imágenes
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(8);
        upload.setDropAllowed(true);
        upload.setUploadButton(new Button("Sube tus fotos"));

        List<String> nombresImagenes = new ArrayList<>();
        upload.addSucceededListener(event -> {
            String filename = event.getFileName();
            try (InputStream inputStream = buffer.getInputStream(filename)) {
                File targetFile = new File("front/src/main/resources/META-INF/resources/uploads/" + filename);
                targetFile.getParentFile().mkdirs();
                try (FileOutputStream out = new FileOutputStream(targetFile)) {
                    inputStream.transferTo(out);
                }
                nombresImagenes.add(filename);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Botón publicar
        Button publicarButton = new Button("Publicar", e -> {
            Oferta nueva = new Oferta(
                    tituloOferta.getValue(),
                    descripcion.getValue(),
                    universidad.getValue() != null ? universidad.getValue() : "",
                    ubicacion.getValue(),
                    precio.getValue(),
                    nombresImagenes
            );

            // Seteo de filtros opcionales
            if (edadMaxima.getValue() != null) {
                nueva.setEdadMaxima(edadMaxima.getValue().intValue());
            }
            if (genero.getValue() != null && !genero.getValue().equals("Sin preferencia")) {
                nueva.setGenero(genero.getValue());
            }
            if (piscina.getValue() != null) {
                nueva.setPiscina(piscina.getValue());
            }

            OfertaService.addOferta(nueva);
            notificationService.showNotification("¡Oferta publicada con éxito!");
            getUI().ifPresent(ui -> ui.navigate("inicio"));
        });

        publicarButton.addClassName("publicar-button");

        formularioLayout.add(
                logo, titulo, descripcionIntro, tituloOferta,
                ubicacion, numeroInquilinos, precio, genero, piscina,
                universidad, edadMaxima, descripcion, upload, publicarButton
        );

        add(formularioLayout);
    }
}
