package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@Route("configuracion")
@CssImport("./themes/styles/styles.css") // Importar estilos desde CSS
@PageTitle("Configuración")
public class Configuracion extends HorizontalLayout {

    public Configuracion() {
        setSizeFull();

        // Contenedor principal (a la derecha)
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setWidthFull();
        mainContent.setAlignItems(Alignment.CENTER);

        // Navbar
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("navbar");

        // Logo a la izquierda en el Navbar
        Image logoNavbar = new Image("icons/homiepng.png", "Logo de Homie");
        logoNavbar.addClassName("logo-navbar");
        logoNavbar.getStyle().setWidth("40px").setHeight("40px");

        // Botones del navbar con iconos
        Button exploreButton = new Button("Explorar", new Icon(VaadinIcon.SEARCH));
        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART));
        Button settingsButton = new Button("Configuración", new Icon(VaadinIcon.COG));
        Button profileButton = new Button("Perfil", new Icon(VaadinIcon.USER));

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, settingsButton, profileButton);
        navButtons.addClassName("nav-buttons");

        navBar.add(logoNavbar, navButtons);

        // Aquí puedes agregar más contenido a la página de configuración
        VerticalLayout configContent = new VerticalLayout();
        configContent.add(new Button("Configuración 1"), new Button("Configuración 2"));

        // Agregar componentes al contenido principal
        mainContent.add(navBar, configContent);

        // Agregar todo a la vista (filtros a la izquierda, contenido a la derecha)
        add(mainContent);
    }
}
