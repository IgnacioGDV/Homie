package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import es.ufv.homie.services.NotificationService;

import java.util.List;

@Route("inicio")
@CssImport("./themes/styles/styles.css")
@PageTitle("Inicio")
public class Inicio extends VerticalLayout {

    private List<CrearOferta.Oferta> ofertas;
    private final NotificationService notificationService; // Servicio de notificaciones

    public Inicio(List<CrearOferta.Oferta> ofertas, NotificationService notificationService) {
        this.ofertas = ofertas;
        this.notificationService = notificationService; // Inyectamos el servicio de notificaciones

        setSizeFull();
        setSpacing(false);
        setPadding(false);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        /** ==================== NOTIFICACIONES ==================== **/
        // Esta área se usará para mostrar las notificaciones debajo de la barra de búsqueda
        HorizontalLayout notificationLayout = new HorizontalLayout();
        notificationLayout.setWidthFull();
        notificationLayout.setHeight("50px");
        notificationLayout.setVisible(false); // Inicialmente invisible
        notificationLayout.addClassName("notification-area");

        add(notificationLayout);

        // Mostrar las notificaciones si es necesario
        notificationService.addNotificationListener(message -> {
            notificationLayout.setVisible(true);
            notificationLayout.removeAll();
            Span notificationMessage = new Span(message);
            notificationLayout.add(notificationMessage);
            // Desaparece después de 5 segundos
            notificationMessage.getUI().ifPresent(ui -> ui.access(() -> {
                ui.getPage().executeJs("setTimeout(() => { document.querySelector('.notification-area').style.visibility = 'hidden'; }, 5000);");
            }));
        });

        /** ==================== NAVBAR ==================== **/
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

        TextField searchField = new TextField();
        searchField.setPlaceholder("Busca tu piso...");
        searchField.addClassName("search-field");

        Button searchButton = new Button(new Icon(VaadinIcon.SEARCH));
        searchButton.addClassName("search-button");

        HorizontalLayout searchBar = new HorizontalLayout(searchField, searchButton);
        searchBar.addClassName("search-bar");

        mainContent.add(homieLogoCentered, searchBar);

        /** ==================== OFERTAS ==================== **/
        VerticalLayout ofertasLayout = new VerticalLayout();
        ofertasLayout.setWidthFull();
        ofertasLayout.addClassName("offer-container");

        for (CrearOferta.Oferta oferta : ofertas) {
            VerticalLayout ofertaCard = new VerticalLayout();
            ofertaCard.addClassName("offer-card");

            Image imagenOferta = new Image("ruta/a/tu/imagen.jpg", "Imagen de la oferta");
            imagenOferta.setWidth("100%");
            imagenOferta.setHeight("180px");

            Span nombreOferta = new Span(oferta.getTitulo());
            nombreOferta.addClassName("offer-title");

            Span descripcionOferta = new Span(oferta.getDescripcion());
            descripcionOferta.addClassName("offer-description");

            Span precioOferta = new Span("Precio: €" + oferta.getPrecio());
            precioOferta.addClassName("offer-price");

            Button masInfoButton = new Button("Más Info");
            masInfoButton.addClassName("more-info-button");

            ofertaCard.add(imagenOferta, nombreOferta, descripcionOferta, precioOferta, masInfoButton);
            ofertasLayout.add(ofertaCard);
        }

        mainContent.add(ofertasLayout);

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
