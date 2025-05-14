package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.ufv.homie.model.OfertaF;
import es.ufv.homie.services.OfertaService;
import jakarta.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@Route("inicio")
@CssImport("./themes/styles/styles.css")
@PageTitle("Inicio")
@PermitAll
public class Inicio extends VerticalLayout {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BACKEND_URL = "http://localhost:8082/api/ofertas";

    public Inicio(OfertaService ofertaService) {
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        getStyle().set("margin", "0");
        addClassName("inicio-background");

        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("navbar");
        navBar.setWidthFull();

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("logo-navbar");

        Button exploreButton = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH));
        exploreButton.addClassName("explore-button");

        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART), e -> getUI().ifPresent(ui -> ui.navigate("mis-favoritos")));
        savedButton.addClassName("saved-button");

        Button aboutButton = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE), e -> getUI().ifPresent(ui -> ui.navigate("quienessomos")));
        aboutButton.addClassName("about-button");

        Button profileButton = new Button("Login", new Icon(VaadinIcon.USER), e -> getUI().ifPresent(ui -> ui.navigate("login")));
        profileButton.addClassName("profile-button");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, aboutButton, profileButton);
        navButtons.addClassName("nav-buttons");

        navBar.add(homieLogo, navButtons);

        VerticalLayout filterMenu = new VerticalLayout();
        filterMenu.addClassName("filter-menu");
        filterMenu.setWidth("250px");

        filterMenu.add(
                new Span("Universidad"),
                new ComboBox<>("Universidad", "UFV", "UCM", "UPM", "UAM", "UC3M", "URJC"),
                new Span("Ubicación"),
                new ComboBox<>("Ubicación", "Alcobendas", "Alcorcón", "Boadilla", "Las Rozas"),
                new Span("Rango de precio (€)"),
                new HorizontalLayout(new NumberField("Min."), new NumberField("Max.")),
                new Span("Edad máxima"),
                new NumberField("Edad..."),
                new RadioButtonGroup<>("Género", "Masculino", "Femenino"),
                new RadioButtonGroup<>("Piscina", "Sí", "No"),
                new Button("Aplicar")
        );

        VerticalLayout mainContent = new VerticalLayout();
        mainContent.addClassName("main-content");
        mainContent.setWidthFull();
        mainContent.setAlignItems(FlexComponent.Alignment.CENTER);

        Image logoCentral = new Image("icons/homiepng.png", "Logo de Homie");
        logoCentral.addClassName("logo-centered");
        mainContent.add(logoCentral);

        List<OfertaF> ofertaFS = obtenerOfertasDesdeBackend();

        if (!ofertaFS.isEmpty()) {
            int[] ofertaActual = {0};
            int[] imagenActual = {0};

            VerticalLayout ofertaCard = new VerticalLayout();
            ofertaCard.addClassName("offer-card");
            ofertaCard.setWidth("80%");

            Span nombreOferta = new Span();
            nombreOferta.getStyle().set("font-weight", "bold").set("font-size", "20px");

            Span descripcionOferta = new Span();
            Span universidad = new Span();
            Span ubicacion = new Span();
            Span precioOferta = new Span();
            precioOferta.getStyle().set("font-weight", "bold").set("font-size", "18px");

            Image imagenCarrusel = new Image();
            imagenCarrusel.addClassName("imagen-oferta");

            Button anteriorImagen = new Button("\u2190");
            Button siguienteImagen = new Button("\u2192");

            Button guardarButton = new Button(new Icon(VaadinIcon.HEART));
            guardarButton.addClassName("more-info-button");
            guardarButton.addClickListener(e -> {
                ofertaService.addFavorito(ofertaFS.get(ofertaActual[0]));
                Notification.show("\u00a1A\u00f1adido a favoritos!");
            });

            Button masInfoButton = new Button("Más Info");
            masInfoButton.addClassName("more-info-button");

            HorizontalLayout botones = new HorizontalLayout(masInfoButton, guardarButton);
            HorizontalLayout carruselImagenes = new HorizontalLayout(anteriorImagen, imagenCarrusel, siguienteImagen);
            carruselImagenes.setAlignItems(FlexComponent.Alignment.CENTER);

            ofertaCard.add(nombreOferta, descripcionOferta, universidad, ubicacion, precioOferta, carruselImagenes, botones);
            mainContent.add(ofertaCard);

            Button ofertaAnterior = new Button("\u2190 Oferta");
            Button ofertaSiguiente = new Button("Oferta \u2192");
            ofertaAnterior.addClassName("oferta-button");
            ofertaSiguiente.addClassName("oferta-button");

            HorizontalLayout navegacionOfertas = new HorizontalLayout(ofertaAnterior, ofertaSiguiente);
            navegacionOfertas.addClassName("navegacion-ofertas");

            ofertaAnterior.addClickListener(e -> {
                if (ofertaActual[0] > 0) {
                    ofertaActual[0]--;
                    imagenActual[0] = 0;
                    actualizarVista(ofertaFS.get(ofertaActual[0]), nombreOferta, descripcionOferta, universidad, ubicacion, precioOferta, imagenCarrusel, imagenActual);
                }
            });

            ofertaSiguiente.addClickListener(e -> {
                if (ofertaActual[0] < ofertaFS.size() - 1) {
                    ofertaActual[0]++;
                    imagenActual[0] = 0;
                    actualizarVista(ofertaFS.get(ofertaActual[0]), nombreOferta, descripcionOferta, universidad, ubicacion, precioOferta, imagenCarrusel, imagenActual);
                }
            });

            anteriorImagen.addClickListener(e -> {
                if (imagenActual[0] > 0) {
                    imagenActual[0]--;
                    imagenCarrusel.setSrc("http://localhost:8082/api/photos/by-name/" + ofertaFS.get(ofertaActual[0]).getFotos().get(imagenActual[0]));
                }
            });

            siguienteImagen.addClickListener(e -> {
                if (imagenActual[0] < ofertaFS.get(ofertaActual[0]).getFotos().size() - 1) {
                    imagenActual[0]++;
                    imagenCarrusel.setSrc("http://localhost:8082/api/photos/by-name/" + ofertaFS.get(ofertaActual[0]).getFotos().get(imagenActual[0]));
                }
            });

            mainContent.add(navegacionOfertas);
            actualizarVista(ofertaFS.get(0), nombreOferta, descripcionOferta, universidad, ubicacion, precioOferta, imagenCarrusel, imagenActual);
        }

        HorizontalLayout footer = new HorizontalLayout(new Span("\u00a9 2024 Homie. Todos los derechos reservados."));
        footer.addClassName("footer");
        footer.setWidthFull();

        HorizontalLayout layout = new HorizontalLayout(filterMenu, mainContent);
        layout.setSizeFull();
        layout.setFlexGrow(1, mainContent);
        layout.addClassName("main-layout");

        add(navBar, layout, footer);
    }

    private List<OfertaF> obtenerOfertasDesdeBackend() {
        try {
            OfertaF[] response = restTemplate.getForObject(BACKEND_URL, OfertaF[].class);
            if (response != null) {
                List<OfertaF> lista = List.of(response);
                for (OfertaF o : lista) {
                    if (o.getFotos() == null) o.setFotos(new ArrayList<>());
                }
                return lista;
            }
        } catch (Exception e) {
            Notification.show("Error al obtener ofertas del backend.");
        }
        return new ArrayList<>();
    }

    private void actualizarVista(OfertaF ofertaF, Span nombre, Span desc, Span uni, Span ubic, Span precio, Image img, int[] imgIndex) {
        nombre.setText(ofertaF.getTitle());
        desc.setText(ofertaF.getDescription());
        uni.setText("Universidad: " + ofertaF.getUniversidad());
        ubic.setText("Ubicación: " + ofertaF.getLocation());
        precio.setText("Precio: €" + ofertaF.getPrice());

        if (ofertaF.getFotos() != null && !ofertaF.getFotos().isEmpty()) {
            img.setSrc("http://localhost:8082/api/photos/by-name/" + ofertaF.getFotos().get(imgIndex[0]));
        } else {
            img.setSrc("icons/piso1.jpg");
        }
    }
}
