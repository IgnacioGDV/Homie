// Inicio.java (con carrusel de OFERTAS y carrusel de imágenes por oferta)
package es.ufv.homie.views;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import es.ufv.homie.model.Oferta;
import es.ufv.homie.services.OfertaService;
import es.ufv.homie.services.NotificationService;

import java.util.List;

@Route("inicio")
@CssImport("./themes/styles/styles.css")
@PageTitle("Inicio")
public class Inicio extends VerticalLayout {

    public Inicio() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        /** ==================== BARRA DE NAVEGACIÓN Y LOGO ==================== **/
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("navbar");
        navBar.setWidthFull();

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("logo-navbar");

        Button exploreButton = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH));
        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART));
        Button aboutButton = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE));
        Button profileButton = new Button("Editar Perfil", new Icon(VaadinIcon.USER));
        exploreButton.addClassName("explore-button");
        savedButton.addClassName("saved-button");
        aboutButton.addClassName("about-button");
        profileButton.addClassName("profile-button");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, aboutButton, profileButton);
        navButtons.addClassName("nav-buttons");

        navBar.add(homieLogo, navButtons);

        /** ==================== MENÚ DE FILTROS ==================== **/
        VerticalLayout filterMenu = new VerticalLayout();
        filterMenu.addClassName("filter-menu");
        filterMenu.setWidth("250px");

        ComboBox<String> universityFilter = new ComboBox<>("Universidad");
        universityFilter.setItems("UFV", "UCM", "UPM", "UAM", "UC3M", "URJC");

        ComboBox<String> locationFilter = new ComboBox<>("Ubicación");
        locationFilter.setItems("Alcobendas", "Alcorcón", "Boadilla", "Las Rozas");

        Span priceLabel = new Span("Rango de precio (€)");
        NumberField minPrice = new NumberField("Mín.");
        minPrice.setValue(200.0);
        minPrice.setWidth("50px");
        NumberField maxPrice = new NumberField("Máx.");
        maxPrice.setValue(2000.0);
        maxPrice.setWidth("50px");
        HorizontalLayout priceRange = new HorizontalLayout(minPrice, maxPrice);

        Span ageLabel = new Span("Edad máxima");
        NumberField maxAge = new NumberField();
        maxAge.setValue(30.0);
        maxAge.setWidth("60px");

        RadioButtonGroup<String> genderFilter = new RadioButtonGroup<>();
        genderFilter.setLabel("Género");
        genderFilter.setItems("Masculino", "Femenino");

        RadioButtonGroup<String> poolFilter = new RadioButtonGroup<>();
        poolFilter.setLabel("Piscina");
        poolFilter.setItems("Sí", "No");

        Button applyFilters = new Button("Aplicar");
        applyFilters.addClassName("apply-filters-button");

        filterMenu.add(universityFilter, locationFilter, priceLabel, priceRange, ageLabel, maxAge, genderFilter, poolFilter, applyFilters);

        /** ==================== CONTENIDO PRINCIPAL ==================== **/
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setWidthFull();
        mainContent.setAlignItems(Alignment.CENTER);

        Image homieLogoCentered = new Image("icons/homiepng.png", "Logo de Homie");
        homieLogoCentered.addClassName("logo-centered");
        mainContent.add(homieLogoCentered);

        /** ==================== CARRUSEL DE OFERTAS ==================== **/
        List<Oferta> ofertas = OfertaService.getOfertas();
        if (!ofertas.isEmpty()) {
            VerticalLayout ofertaCard = new VerticalLayout();
            ofertaCard.addClassName("offer-card");
            ofertaCard.setWidth("80%");

            Span nombreOferta = new Span();
            Span descripcionOferta = new Span();
            Span universidad = new Span();
            Span ubicacion = new Span();
            Span precioOferta = new Span();
            Image imagenCarrusel = new Image();
            imagenCarrusel.setWidth("100%");
            imagenCarrusel.setHeight("180px");

            Button anteriorImagen = new Button("←");
            Button siguienteImagen = new Button("→");
            Button masInfoButton = new Button("Más Info");
            masInfoButton.addClassName("more-info-button");

            HorizontalLayout carruselImagenes = new HorizontalLayout(anteriorImagen, imagenCarrusel, siguienteImagen);
            carruselImagenes.setAlignItems(Alignment.CENTER);

            ofertaCard.add(nombreOferta, descripcionOferta, universidad, ubicacion, precioOferta, carruselImagenes, masInfoButton);

            int[] ofertaActual = {0};
            int[] imagenActual = {0};

            Runnable actualizarVista = () -> {
                Oferta oferta = ofertas.get(ofertaActual[0]);
                nombreOferta.setText(oferta.getTitulo());
                descripcionOferta.setText(oferta.getDescripcion());
                universidad.setText(oferta.getUniversidad());
                ubicacion.setText(oferta.getUbicacion());
                precioOferta.setText("Precio: €" + oferta.getPrecio());

                if (oferta.getImagenes() != null && !oferta.getImagenes().isEmpty()) {
                    imagenCarrusel.setSrc("/uploads/" + oferta.getImagenes().get(imagenActual[0]));

                } else {
                    imagenCarrusel.setSrc("icons/piso1.jpg");
                }
            };

            anteriorImagen.addClickListener(e -> {
                Oferta oferta = ofertas.get(ofertaActual[0]);
                if (imagenActual[0] > 0) {
                    imagenActual[0]--;
                    imagenCarrusel.setSrc("/uploads/" + oferta.getImagenes().get(imagenActual[0]));

                }
            });

            siguienteImagen.addClickListener(e -> {
                Oferta oferta = ofertas.get(ofertaActual[0]);
                if (imagenActual[0] < oferta.getImagenes().size() - 1) {
                    imagenActual[0]++;
                    imagenCarrusel.setSrc("/uploads/" + oferta.getImagenes().get(imagenActual[0]));

                }
            });

            Button ofertaAnterior = new Button("← Oferta");
            Button ofertaSiguiente = new Button("Oferta →");
            HorizontalLayout navegacionOfertas = new HorizontalLayout(ofertaAnterior, ofertaSiguiente);
            navegacionOfertas.setJustifyContentMode(JustifyContentMode.CENTER);

            ofertaAnterior.addClickListener(e -> {
                if (ofertaActual[0] > 0) {
                    ofertaActual[0]--;
                    imagenActual[0] = 0;
                    actualizarVista.run();
                }
            });

            ofertaSiguiente.addClickListener(e -> {
                if (ofertaActual[0] < ofertas.size() - 1) {
                    ofertaActual[0]++;
                    imagenActual[0] = 0;
                    actualizarVista.run();
                }
            });

            mainContent.add(ofertaCard, navegacionOfertas);
            actualizarVista.run();
        }

        /** ==================== FOOTER ==================== **/
        HorizontalLayout footer = new HorizontalLayout();
        footer.addClassName("footer");
        footer.setWidthFull();

        Span copyright = new Span("© 2024 Homie. Todos los derechos reservados.");
        footer.add(copyright);

        /** ==================== ESTRUCTURA FINAL ==================== **/
        HorizontalLayout layout = new HorizontalLayout(filterMenu, mainContent);
        layout.setSizeFull();
        layout.setFlexGrow(1, mainContent);

        add(navBar, layout, footer);
    }
}