package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.ufv.homie.model.Oferta;
import es.ufv.homie.services.OfertaService;

import java.util.Comparator;
import java.util.List;

@Route("inicio")
@CssImport("./themes/styles/styles.css")
@PageTitle("Inicio")
public class Inicio extends VerticalLayout {

    private ComboBox<String> universityFilter;
    private ComboBox<String> locationFilter;
    private NumberField minPrice;
    private NumberField maxPrice;
    private NumberField maxAge;
    private RadioButtonGroup<String> genderFilter;
    private RadioButtonGroup<String> poolFilter;

    private VerticalLayout mainContent;
    private List<Oferta> ofertas;
    private int ofertaActualIndex = 0;
    private int imagenActualIndex = 0;

    private Span nombreOferta;
    private Span descripcionOferta;
    private Span universidadSpan;
    private Span ubicacionSpan;
    private Span precioSpan;
    private Image imagenCarrusel;

    public Inicio() {
        setSizeFull();
        addClassName("inicio-background");
        setPadding(false);
        setSpacing(false);
        getStyle().set("margin", "0");

        // NAVBAR
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("navbar");
        navBar.setWidthFull();

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("logo-navbar");

        Button exploreButton = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH));
        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART), e -> getUI().ifPresent(ui -> ui.navigate("mis-favoritos")));
        Button aboutButton = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE), e -> getUI().ifPresent(ui -> ui.navigate("quienessomos")));
        Button profileButton = new Button("Login", new Icon(VaadinIcon.USER), e -> getUI().ifPresent(ui -> ui.navigate("login")));

        exploreButton.addClassName("explore-button");
        savedButton.addClassName("saved-button");
        aboutButton.addClassName("about-button");
        profileButton.addClassName("profile-button");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, aboutButton, profileButton);
        navButtons.addClassName("nav-buttons");

        navBar.add(homieLogo, navButtons);

        // FILTROS
        VerticalLayout filterMenu = new VerticalLayout();
        filterMenu.addClassName("filter-menu");
        filterMenu.setWidth("250px");

        Span filtroTitulo = new Span("Menú de Filtros");

        universityFilter = new ComboBox<>("Universidad");
        universityFilter.setItems("UFV", "UCM", "UPM", "UAM", "UC3M", "URJC");

        locationFilter = new ComboBox<>("Ubicación");
        locationFilter.setItems("Alcobendas", "Alcorcón", "Boadilla", "Las Rozas");

        minPrice = new NumberField("Mín.");
        minPrice.setValue(200.0);
        minPrice.setWidth("50px");

        maxPrice = new NumberField("Máx.");
        maxPrice.setValue(2000.0);
        maxPrice.setWidth("50px");

        HorizontalLayout priceRange = new HorizontalLayout(minPrice, maxPrice);

        maxAge = new NumberField("Edad máxima");
        maxAge.setValue(30.0);
        maxAge.setWidth("60px");

        genderFilter = new RadioButtonGroup<>();
        genderFilter.setLabel("Género");
        genderFilter.setItems("Masculino", "Femenino");

        poolFilter = new RadioButtonGroup<>();
        poolFilter.setLabel("Piscina");
        poolFilter.setItems("Sí", "No");

        Button applyFilters = new Button("Aplicar", e -> aplicarFiltros());
        applyFilters.addClassName("apply-filters-button");

        filterMenu.add(filtroTitulo, universityFilter, locationFilter,
                new Span("Rango de precio (€)"), priceRange,
                new Span("Edad máxima"), maxAge, genderFilter, poolFilter, applyFilters);

        // CONTENIDO
        mainContent = new VerticalLayout();
        mainContent.setWidthFull();
        mainContent.setAlignItems(Alignment.CENTER);
        mainContent.addClassName("main-content");

        Image homieLogoCentered = new Image("icons/homiepng.png", "Logo de Homie");
        homieLogoCentered.addClassName("logo-centered");
        mainContent.add(homieLogoCentered);

        // COMPONENTES DE LA OFERTA
        nombreOferta = new Span();
        nombreOferta.getStyle().set("font-weight", "bold").set("font-size", "20px");

        descripcionOferta = new Span();
        universidadSpan = new Span();
        ubicacionSpan = new Span();
        precioSpan = new Span();
        precioSpan.getStyle().set("font-weight", "bold").set("font-size", "18px");

        imagenCarrusel = new Image();
        imagenCarrusel.setWidth("100%");
        imagenCarrusel.setHeight("180px");

        Button anteriorImagen = new Button("←");
        Button siguienteImagen = new Button("→");

        HorizontalLayout carruselImagenes = new HorizontalLayout(anteriorImagen, imagenCarrusel, siguienteImagen);
        carruselImagenes.setAlignItems(Alignment.CENTER);

        Button masInfoButton = new Button("Más Info");
        masInfoButton.addClassName("more-info-button");

        Button guardarButton = new Button(new Icon(VaadinIcon.HEART));
        guardarButton.addClickListener(e -> {
            Oferta oferta = ofertas.get(ofertaActualIndex);
            OfertaService.addFavorito(oferta);
            Notification.show("¡Añadido a favoritos!");
        });

        HorizontalLayout botones = new HorizontalLayout(masInfoButton, guardarButton);

        VerticalLayout ofertaCard = new VerticalLayout(nombreOferta, descripcionOferta, universidadSpan, ubicacionSpan, precioSpan, carruselImagenes, botones);
        ofertaCard.addClassName("offer-card");
        ofertaCard.setWidth("80%");
        mainContent.add(ofertaCard);

        Button ofertaAnterior = new Button("← Oferta");
        Button ofertaSiguiente = new Button("Oferta →");
        ofertaAnterior.addClassName("oferta-button");
        ofertaSiguiente.addClassName("oferta-button");

        HorizontalLayout navegacionOfertas = new HorizontalLayout(ofertaAnterior, ofertaSiguiente);
        navegacionOfertas.setJustifyContentMode(JustifyContentMode.CENTER);
        navegacionOfertas.setWidthFull();
        mainContent.add(navegacionOfertas);

        anteriorImagen.addClickListener(e -> {
            if (!ofertas.isEmpty() && imagenActualIndex > 0) {
                imagenActualIndex--;
                actualizarVista();
            }
        });

        siguienteImagen.addClickListener(e -> {
            if (!ofertas.isEmpty() && imagenActualIndex < ofertas.get(ofertaActualIndex).getImagenes().size() - 1) {
                imagenActualIndex++;
                actualizarVista();
            }
        });

        ofertaAnterior.addClickListener(e -> {
            if (ofertaActualIndex > 0) {
                ofertaActualIndex--;
                imagenActualIndex = 0;
                actualizarVista();
            }
        });

        ofertaSiguiente.addClickListener(e -> {
            if (ofertaActualIndex < ofertas.size() - 1) {
                ofertaActualIndex++;
                imagenActualIndex = 0;
                actualizarVista();
            }
        });

        // FOOTER
        HorizontalLayout footer = new HorizontalLayout();
        footer.addClassName("footer");
        footer.setWidthFull();
        Span copyright = new Span("© 2024 Homie. Todos los derechos reservados.");
        footer.add(copyright);

        HorizontalLayout layout = new HorizontalLayout(filterMenu, mainContent);
        layout.setSizeFull();
        layout.setFlexGrow(1, mainContent);
        layout.addClassName("main-layout");

        add(navBar, layout, footer);

        // Inicialización
        this.ofertas = OfertaService.getOfertas();
        actualizarVista();
    }

    private void aplicarFiltros() {
        List<Oferta> todas = OfertaService.getOfertas();

        todas.sort(Comparator.comparingInt(this::calcularCoincidencias).reversed());
        this.ofertas = todas;
        this.ofertaActualIndex = 0;
        this.imagenActualIndex = 0;
        actualizarVista();
    }

    private int calcularCoincidencias(Oferta o) {
        int puntos = 0;

        if (universityFilter.getValue() != null && universityFilter.getValue().equalsIgnoreCase(o.getUniversidad())) puntos++;
        if (locationFilter.getValue() != null && locationFilter.getValue().equalsIgnoreCase(o.getUbicacion())) puntos++;
        if (o.getPrecio() >= minPrice.getValue() && o.getPrecio() <= maxPrice.getValue()) puntos++;
        if (maxAge.getValue() != null && o.getEdadMaxima() != null && o.getEdadMaxima() <= maxAge.getValue()) puntos++;
        if (genderFilter.getValue() != null && o.getGenero() != null && o.getGenero().equalsIgnoreCase(genderFilter.getValue())) puntos++;
        if (poolFilter.getValue() != null && o.getPiscina() != null && o.getPiscina().equalsIgnoreCase(poolFilter.getValue())) puntos++;

        return puntos;
    }

    private void actualizarVista() {
        if (ofertas == null || ofertas.isEmpty()) {
            nombreOferta.setText("No hay ofertas disponibles.");
            descripcionOferta.setText("");
            universidadSpan.setText("");
            ubicacionSpan.setText("");
            precioSpan.setText("");
            imagenCarrusel.setSrc("icons/piso1.jpg");
            return;
        }

        Oferta oferta = ofertas.get(ofertaActualIndex);

        nombreOferta.setText(oferta.getTitulo());
        descripcionOferta.setText(oferta.getDescripcion());
        universidadSpan.setText("Universidad: " + oferta.getUniversidad());
        ubicacionSpan.setText("Ubicación: " + oferta.getUbicacion());
        precioSpan.setText("Precio: €" + oferta.getPrecio());

        if (oferta.getImagenes() != null && !oferta.getImagenes().isEmpty()) {
            imagenCarrusel.setSrc("uploads/" + oferta.getImagenes().get(imagenActualIndex));
        } else {
            imagenCarrusel.setSrc("icons/piso1.jpg");
        }
    }
}
