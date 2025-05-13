package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.ufv.homie.model.Oferta;
import es.ufv.homie.model.OfertaDTO;
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

    public CrearOferta(NotificationService notificationService, OfertaService ofertaService) {
        addClassName("crear-oferta-layout");

        VerticalLayout formularioLayout = new VerticalLayout();
        formularioLayout.addClassName("formulario-oferta");

        Image logo = new Image("icons/homiepng.png", "Homie Logo");
        logo.addClassName("logo-homie");

        Span titulo = new Span("¡Bienvenido, Anfitrión!");
        titulo.getStyle().set("font-size", "24px").set("color", "#002147").set("font-weight", "bold");

        Span descripcionIntro = new Span("Crea tu oferta para que los inquilinos de Homie puedan aplicar a ella.");
        descripcionIntro.getStyle().set("color", "#555").set("font-size", "14px").set("text-align", "center");

        TextField tituloOferta = new TextField("Nombre de la oferta");

        ComboBox<String> ubicacion = new ComboBox<>("Ubicación por zona");
        ubicacion.setItems("Alcobendas", "Aluche", "Boadilla", "Brunete", "Pozuelo", "Majadahonda", "Madrid Centro",
                "Las Tablas", "Las Rozas", "Alcorcón", "Sanchinarro", "Getafe", "San Sebastián De Los Reyes",
                "Villaverde", "Villaviciosa De Odón");

        ComboBox<Integer> numeroInquilinos = new ComboBox<>("Número de huéspedes necesarios");
        numeroInquilinos.setItems(1, 2, 3, 4, 5, 6, 7, 8);

        NumberField precio = new NumberField("Importe a pagar");
        precio.setPrefixComponent(new Span("€"));

        ComboBox<String> genero = new ComboBox<>("Género con el que prefiere convivir");
        genero.setItems("Masculino", "Femenino", "Sin preferencia");

        TextArea descripcion = new TextArea("Descripción");

        TextField universidad = new TextField("Universidad");
        universidad.setPlaceholder("Ej. UFV");

        IntegerField edadMaxima = new IntegerField("Edad máxima permitida");
        edadMaxima.setMin(18);
        edadMaxima.setMax(99);
        edadMaxima.setStepButtonsVisible(true);

        Checkbox piscina = new Checkbox("¿Tiene piscina?");

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

        Button publicarButton = new Button("Publicar", e -> {
            OfertaDTO dto = new OfertaDTO();
            dto.setTitle(tituloOferta.getValue());
            dto.setLocation(ubicacion.getValue());
            dto.setGuests(numeroInquilinos.getValue());
            dto.setPrice(precio.getValue());
            dto.setPreferredGender(genero.getValue());
            dto.setDescription(descripcion.getValue());
            dto.setUniversidad(universidad.getValue());
            dto.setEdadmax(edadMaxima.getValue());
            dto.setHasPool(piscina.getValue());
            dto.setCreatedBy(1); // TODO: Reemplazar con ID del usuario autenticado
            dto.setFotos(nombresImagenes);

            Oferta oferta = mapDTOToOferta(dto);
            ofertaService.publicarOferta(oferta);

            notificationService.showNotification("¡Oferta publicada con éxito!");
            getUI().ifPresent(ui -> ui.navigate("inicio"));
        });

        publicarButton.addClassName("publicar-button");

        formularioLayout.add(
                logo, titulo, descripcionIntro,
                tituloOferta, ubicacion, numeroInquilinos,
                precio, genero, descripcion,
                universidad, edadMaxima, piscina,
                upload, publicarButton
        );

        add(formularioLayout);
    }

    private Oferta mapDTOToOferta(OfertaDTO dto) {
        Oferta oferta = new Oferta();
        oferta.setTitle(dto.getTitle());
        oferta.setDescription(dto.getDescription());
        oferta.setUniversidad(dto.getUniversidad());
        oferta.setLocation(dto.getLocation());
        oferta.setPrice(dto.getPrice());
        oferta.setFotos(dto.getFotos());
        oferta.setGuests(dto.getGuests());
        oferta.setPreferredGender(dto.getPreferredGender());
        oferta.setHasPool(dto.getHasPool());
        oferta.setCreatedBy(dto.getCreatedBy());
        oferta.setEdadmax(dto.getEdadmax());
        return oferta;
    }
}