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
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@Route("crear-oferta")
@PageTitle("Crear Oferta")
@CssImport("./themes/styles/styles.css") // Importar estilos desde CSS
public class CrearOferta extends VerticalLayout {

    public CrearOferta() {
        setAlignItems(Alignment.CENTER);
        setWidth("50%");

        // Logo de Homie
        Image homieLogo = new Image("icons/homiepng.png", "Logo de Homie");
        homieLogo.addClassName("logo-centered");

        // Título de la oferta
        TextField tituloOferta = new TextField("Título de la oferta");
        tituloOferta.setWidthFull();

        // Descripción
        TextArea descripcion = new TextArea("Descripción");
        descripcion.setWidthFull();

        // Universidad
        ComboBox<String> universidad = new ComboBox<>("Universidad");
        universidad.setItems(
                "Universidad Francisco de Vitoria",
                "Universidad Complutense de Madrid",
                "Universidad Politécnica de Madrid",
                "Universidad Autónoma de Madrid",
                "Universidad Carlos III de Madrid",
                "Universidad Rey Juan Carlos"
        );
        universidad.setWidthFull();

        // Ubicación
        ComboBox<String> ubicacion = new ComboBox<>("Ubicación");
        ubicacion.setItems(
                "Alcobendas", "Alcorcón", "Boadilla del Monte", "Las Rozas", "San Sebastián de los Reyes",
                "Las Tablas", "Sanchinarro", "Aluche", "Getafe"
        );
        ubicacion.setWidthFull();

        // Número de inquilinos
        NumberField numeroInquilinos = new NumberField("Número de inquilinos");
        numeroInquilinos.setMin(1);
        numeroInquilinos.setStep(1);
        numeroInquilinos.setWidth("150px");

        // Preferencia de género
        RadioButtonGroup<String> genero = new RadioButtonGroup<>();
        genero.setLabel("Género preferido");
        genero.setItems("Masculino", "Femenino", "Indiferente");

        // Edad máxima
        NumberField edadMaxima = new NumberField("Edad máxima");
        edadMaxima.setMin(18);
        edadMaxima.setMax(99);
        edadMaxima.setStep(1);
        edadMaxima.setWidth("150px");

        // Subir imágenes (máximo 8)
        Span uploadLabel = new Span("Sube hasta 8 imágenes");
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(8);
        upload.setDropAllowed(true);

        // Botón para publicar
        Button publicar = new Button("Publicar Oferta");
        publicar.addClassName("publish-button");

        // Agregar componentes al layout
        add(homieLogo, tituloOferta, descripcion, universidad, ubicacion, numeroInquilinos, genero, edadMaxima, uploadLabel, upload, publicar);
    }
}