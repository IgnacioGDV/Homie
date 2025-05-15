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
import es.ufv.homie.services.UsuarioService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Route("inicio")
@CssImport("./themes/styles/styles.css")
@PageTitle("Inicio")
@PermitAll
public class Inicio extends VerticalLayout {

    private final String BACKEND_URL = "http://localhost:8082/api/ofertas";

    private static List<OfertaF> favoritosLocales = new ArrayList<>();

    private ComboBox<String> universityFilter;
    private ComboBox<String> locationFilter;
    private NumberField minPrice;
    private NumberField maxPrice;
    private NumberField maxAge;
    private RadioButtonGroup<String> genderFilter;
    private RadioButtonGroup<String> poolFilter;

    private List<OfertaF> ofertas;
    private int[] ofertaActual = {0};
    private int[] imagenActual = {0};

    private Span nombreOferta = new Span();
    private Span descripcionOferta = new Span();
    private Span universidad = new Span();
    private Span ubicacion = new Span();
    private Span precioOferta = new Span();
    private Image imagenCarrusel = new Image();

    @Autowired
    private UsuarioService usuarioService;

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
        Button profileButton = new Button("Logout", new Icon(VaadinIcon.USER), e -> getUI().ifPresent(ui -> ui.navigate("login")));
        profileButton.addClassName("profile-button");

        navBar.add(homieLogo, new HorizontalLayout(exploreButton, savedButton, aboutButton, profileButton));

        VerticalLayout filterMenu = new VerticalLayout();
        filterMenu.addClassName("filter-menu");
        filterMenu.setWidth("250px");

        universityFilter = new ComboBox<>("Universidad", "UFV", "UCM", "UPM", "UAM", "UC3M", "URJC");
        locationFilter = new ComboBox<>("Ubicación", "Alcobendas", "Aluche", "Boadilla", "Brunete", "Pozuelo", "Majadahonda", "Madrid Centro",
                "Las Tablas", "Las Rozas", "Alcorcón", "Sanchinarro", "Getafe", "San Sebastián De Los Reyes",
                "Villaverde", "Villaviciosa De Odón");
        minPrice = new NumberField("Min.");
        maxPrice = new NumberField("Max.");
        maxAge = new NumberField("Edad...");
        genderFilter = new RadioButtonGroup<>("Género", "Masculino", "Femenino");
        poolFilter = new RadioButtonGroup<>("Piscina", "Sí", "No");

        Button applyFilters = new Button("Aplicar", e -> aplicarFiltros());
        applyFilters.addClassName("apply-filters-button");

        filterMenu.add(new Span("Universidad"), universityFilter,
                new Span("Ubicación"), locationFilter,
                new Span("Rango de precio (€)"),
                crearLayoutPrecio(),
                new Span("Edad máxima"), maxAge, genderFilter, poolFilter, applyFilters);

        VerticalLayout mainContent = new VerticalLayout();
        mainContent.addClassName("main-content");
        mainContent.setWidthFull();
        mainContent.setAlignItems(FlexComponent.Alignment.CENTER);

        Image logoCentral = new Image("icons/homiepng.png", "Logo de Homie");
        logoCentral.addClassName("logo-centered");
        mainContent.add(logoCentral);

        precioOferta.getStyle().set("font-weight", "bold").set("font-size", "18px");
        imagenCarrusel.addClassName("imagen-oferta");

        Button anteriorImagen = new Button("←", e -> imagenAnterior());
        Button siguienteImagen = new Button("→", e -> imagenSiguiente());

        Button guardarButton = new Button(new Icon(VaadinIcon.HEART), e -> guardarOferta(ofertaService));
        guardarButton.addClassName("more-info-button");

        Button masInfoButton = new Button("Solicita Info", e -> {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            if (email != null && ofertas.size() > ofertaActual[0]) {
                ofertaService.contactarAnfitrion(ofertas.get(ofertaActual[0]).getIdoffer(), email);
                Notification.show("¡Le has mandado un correo al anfitrión!");
            } else {
                Notification.show("Debes iniciar sesión para contactar.");
            }
        });
        masInfoButton.addClassName("more-info-button");


        masInfoButton.addClassName("more-info-button");

        HorizontalLayout botones = new HorizontalLayout(masInfoButton, guardarButton);
        HorizontalLayout carrusel = new HorizontalLayout(anteriorImagen, imagenCarrusel, siguienteImagen);
        carrusel.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout ofertaCard = new VerticalLayout(nombreOferta, descripcionOferta, universidad, ubicacion, precioOferta, carrusel, botones);
        ofertaCard.addClassName("offer-card");
        ofertaCard.setWidth("80%");
        mainContent.add(ofertaCard);

        Button ofertaAnterior = new Button("← Oferta", e -> mostrarAnterior());
        Button ofertaSiguiente = new Button("Oferta →", e -> mostrarSiguiente());
        ofertaAnterior.addClassName("oferta-button");
        ofertaSiguiente.addClassName("oferta-button");
        mainContent.add(new HorizontalLayout(ofertaAnterior, ofertaSiguiente));

        HorizontalLayout footer = new HorizontalLayout(new Span("© 2024 Homie. Todos los derechos reservados."));
        footer.addClassName("footer");
        footer.setWidthFull();

        HorizontalLayout layout = new HorizontalLayout(filterMenu, mainContent);
        layout.setSizeFull();
        layout.setFlexGrow(1, mainContent);
        layout.addClassName("main-layout");

        add(navBar, layout, footer);

        ofertas = obtenerOfertasDesdeBackend();
        if (!ofertas.isEmpty()) actualizarVista(ofertas.get(0));
    }

    private void aplicarFiltros() {
        List<OfertaF> filtradas = new ArrayList<>(ofertas);

        filtradas = filtradas.stream()
                .sorted(Comparator.comparingInt(this::calcularCoincidencias).reversed())
                .collect(Collectors.toList());

        if (!filtradas.isEmpty()) {
            ofertaActual[0] = 0;
            imagenActual[0] = 0;
            actualizarVista(filtradas.get(0));
        } else {
            Notification.show("No hay ofertas que coincidan con los filtros.");
        }
    }

    private void mostrarAnterior() {
        if (ofertaActual[0] > 0) {
            ofertaActual[0]--;
            imagenActual[0] = 0;
            actualizarVista(ofertas.get(ofertaActual[0]));
        }
    }

    private void mostrarSiguiente() {
        if (ofertaActual[0] < ofertas.size() - 1) {
            ofertaActual[0]++;
            imagenActual[0] = 0;
            actualizarVista(ofertas.get(ofertaActual[0]));
        }
    }

    private void imagenAnterior() {
        if (imagenActual[0] > 0) {
            imagenActual[0]--;
            imagenCarrusel.setSrc("http://localhost:8082/api/photos/by-name/" + ofertas.get(ofertaActual[0]).getFotos().get(imagenActual[0]));
        }
    }

    private void imagenSiguiente() {
        if (imagenActual[0] < ofertas.get(ofertaActual[0]).getFotos().size() - 1) {
            imagenActual[0]++;
            imagenCarrusel.setSrc("http://localhost:8082/api/photos/by-name/" + ofertas.get(ofertaActual[0]).getFotos().get(imagenActual[0]));
        }
    }

    private void guardarOferta(OfertaService ofertaService) {
        OfertaF actual = ofertas.get(ofertaActual[0]);
        if (!favoritosLocales.contains(actual)) {
            favoritosLocales.add(actual);
            Notification.show("¡Añadido a favoritos!");
        } else {
            Notification.show("Ya está en tus favoritos.");
        }
    }





    private int calcularCoincidencias(OfertaF o) {
        int puntos = 0;
        if (universityFilter.getValue() != null && universityFilter.getValue().equalsIgnoreCase(o.getUniversidad())) puntos++;
        if (locationFilter.getValue() != null && locationFilter.getValue().equalsIgnoreCase(o.getLocation())) puntos++;
        if (minPrice.getValue() != null && maxPrice.getValue() != null && o.getPrice() != null && o.getPrice() >= minPrice.getValue() && o.getPrice() <= maxPrice.getValue()) puntos++;
        if (maxAge.getValue() != null && o.getEdadmax() != null && o.getEdadmax() <= maxAge.getValue()) puntos++;
        if (genderFilter.getValue() != null && o.getPreferredGender() != null && o.getPreferredGender().equalsIgnoreCase(genderFilter.getValue())) puntos++;
        if (poolFilter.getValue() != null && o.getHasPool() != null && ((poolFilter.getValue().equals("Sí") && o.getHasPool()) || (poolFilter.getValue().equals("No") && !o.getHasPool()))) puntos++;
        return puntos;
    }

    private List<OfertaF> obtenerOfertasDesdeBackend() {
        try {
            ResponseEntity<OfertaF[]> response = new RestTemplate().getForEntity(BACKEND_URL, OfertaF[].class);
            if (response.getBody() != null) {
                List<OfertaF> lista = List.of(response.getBody());
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

    private void actualizarVista(OfertaF ofertaF) {
        nombreOferta.setText(ofertaF.getTitle());
        descripcionOferta.setText(ofertaF.getDescription());
        universidad.setText("Universidad: " + ofertaF.getUniversidad());
        ubicacion.setText("Ubicación: " + ofertaF.getLocation());
        precioOferta.setText("Precio: €" + ofertaF.getPrice());

        if (ofertaF.getFotos() != null && !ofertaF.getFotos().isEmpty()) {
            imagenCarrusel.setSrc("http://localhost:8082/api/photos/by-name/" + ofertaF.getFotos().get(imagenActual[0]));
        } else {
            int randomLocal = new Random().nextInt(5) + 1; // genera número entre 1 y 5
            imagenCarrusel.setSrc("icons/piso" + randomLocal + ".jpg");

        }
    }

    private HorizontalLayout crearLayoutPrecio() {
        minPrice.setWidthFull();
        maxPrice.setWidthFull();

        HorizontalLayout layout = new HorizontalLayout(minPrice, maxPrice);
        layout.setWidthFull();
        layout.setSpacing(true);
        layout.setFlexGrow(1, minPrice, maxPrice); // Hace que ambos ocupen el mismo espacio sin desbordar

        return layout;
    }
    private void enviarCorreoAnfitrion(Long ofertaId) {
        String remitente = SecurityContextHolder.getContext().getAuthentication().getName();

        if (remitente != null && !remitente.isEmpty()) {
            String url = "http://localhost:8082/api/ofertas/enviar-correo?ofertaId=" + ofertaId + "&fromEmail=" + remitente;

            try {
                new RestTemplate().postForEntity(url, null, Void.class);
                Notification.show("¡Le has mandado un correo al anfitrión preguntando por información!");
            } catch (Exception e) {
                Notification.show("Error al enviar correo.");
            }
        } else {
            Notification.show("Debes iniciar sesión para enviar correos.");
        }
    }
    public static List<OfertaF> getFavoritosLocales() {
        return favoritosLocales;
    }


}
