package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;

@Route("inicio")
@CssImport("./themes/styles/styles.css") // Importar estilos desde CSS
@PageTitle("Inicio")
public class Inicio extends HorizontalLayout {

    public Inicio() {
        setSizeFull();

        // Menú de filtros a la izquierda (más delgado)
        VerticalLayout filterMenu = new VerticalLayout();
        filterMenu.addClassName("filter-menu");
        filterMenu.setWidth("250px"); // Reducido el ancho
        filterMenu.getStyle().set("padding", "15px");

        // Filtro: Universidad
        ComboBox<String> universityFilter = new ComboBox<>("Universidad");
        universityFilter.setItems(
                "Universidad Francisco de Vitoria",
                "Universidad Complutense de Madrid",
                "Universidad Politécnica de Madrid",
                "Universidad Autónoma de Madrid",
                "Universidad Carlos III de Madrid",
                "Universidad Rey Juan Carlos"
        );
        universityFilter.setWidthFull();

        // Filtro: Rango de precio
        Span priceLabel = new Span("Rango de precio (€)");
        NumberField minPrice = new NumberField("Mín.");
        minPrice.setWidth("80px");
        minPrice.setValue(200.0);
        minPrice.setStep(50);

        NumberField maxPrice = new NumberField("Máx.");
        maxPrice.setWidth("80px");
        maxPrice.setValue(2000.0);
        maxPrice.setStep(50);

        HorizontalLayout priceRange = new HorizontalLayout(minPrice, maxPrice);
        priceRange.setWidthFull();

        // Filtro: Edad máxima
        Span ageLabel = new Span("Edad máxima");
        NumberField maxAge = new NumberField();
        maxAge.setWidth("100px");
        maxAge.setValue(30.0);
        maxAge.setStep(1);

        // Filtro: Género preferido
        RadioButtonGroup<String> genderFilter = new RadioButtonGroup<>();
        genderFilter.setLabel("Género");
        genderFilter.setItems("Masculino", "Femenino");

        // Botón para aplicar filtros
        Button applyFilters = new Button("Aplicar");
        applyFilters.addClassName("apply-filters-button");
        applyFilters.setWidthFull();

        // Agregar filtros al menú
        filterMenu.add(universityFilter, priceLabel, priceRange, ageLabel, maxAge, genderFilter, applyFilters);

        // Contenedor principal (a la derecha)
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setWidthFull();
        mainContent.setAlignItems(Alignment.CENTER);

        // Navbar
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("navbar");

        // Logo a la izquierda en el Navbar
        Image logoNavbar = new Image("icons/homiepng2.png", "Logo de Homie");
        logoNavbar.addClassName("logo-navbar");
        logoNavbar.getStyle().setWidth("60px").setHeight("60px");

        // Botones del navbar con iconos
        Button exploreButton = new Button("Explorar", new Icon(VaadinIcon.SEARCH));
        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART));
        Button settingsButton = new Button("Configuración", new Icon(VaadinIcon.COG));
        Button profileButton = new Button("Perfil", new Icon(VaadinIcon.USER));

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, settingsButton, profileButton);
        navButtons.addClassName("nav-buttons");

        // Estilo para Navbar y botones
        navBar.getStyle().set("background-color", "#003366");  // Azul oscuro para el navbar
        navBar.getStyle().set("color", "white");  // Texto en blanco para todo el navbar

        // Cambiar color de 'Explorar' a un azul menos oscuro
        exploreButton.getStyle().set("background-color", "#1976D2");  // Azul menos oscuro para "Explorar"
        exploreButton.getStyle().set("color", "white");  // Texto blanco para el botón
        savedButton.getStyle().set("color", "white");
        settingsButton.getStyle().set("color", "white");
        profileButton.getStyle().set("color", "white");

        navBar.add(logoNavbar, navButtons);

        // Logo encima de la barra de búsqueda
        Image logoSearch = new Image("icons/homiepng.png", "Logo de Homie");
        logoSearch.addClassName("logo-search");
        logoSearch.getStyle().setWidth("200px").setHeight("200px"); // Tamaño 200x200

        // Barra de búsqueda con botón
        TextField searchField = new TextField();
        searchField.setPlaceholder("Buscar pisos...");
        searchField.addClassName("search-field");
        searchField.setWidth("100%"); // Campo de búsqueda más largo

        Button searchButton = new Button("Buscar");
        searchButton.addClassName("search-button");

        HorizontalLayout searchBar = new HorizontalLayout(logoSearch, searchField, searchButton);
        searchBar.addClassName("search-bar");
        searchBar.setWidthFull(); // Asegura que la barra de búsqueda ocupe todo el espacio disponible

        // Agregar componentes al contenido principal
        mainContent.add(navBar, searchBar);

        // Agregar todo a la vista (filtros a la izquierda, contenido a la derecha)
        add(filterMenu, mainContent);
    }
}
