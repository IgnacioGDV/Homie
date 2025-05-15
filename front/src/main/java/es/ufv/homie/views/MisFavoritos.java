package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.ufv.homie.model.OfertaF;

import java.util.List;

@CssImport("./themes/styles/favoritos.css")
@Route("mis-favoritos")
@PageTitle("Mis Favoritos")
public class MisFavoritos extends VerticalLayout {

    public MisFavoritos() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        getStyle().set("margin", "0");
        addClassName("favoritos-view");

        // NAVBAR
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("favoritos-navbar");

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("favoritos-logo");

        Button exploreButton = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH),
                e -> getUI().ifPresent(ui -> ui.navigate("inicio")));
        exploreButton.addClassName("favoritos-explore-button");

        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART));
        savedButton.addClassName("favoritos-saved-button");

        Button aboutButton = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE),
                e -> getUI().ifPresent(ui -> ui.navigate("quienessomos")));
        aboutButton.addClassName("favoritos-about-button");

        Button profileButton = new Button("Login", new Icon(VaadinIcon.USER),
                e -> getUI().ifPresent(ui -> ui.navigate("login")));
        profileButton.addClassName("favoritos-profile-button");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, aboutButton, profileButton);
        navButtons.addClassName("favoritos-nav-buttons");

        navBar.add(homieLogo, navButtons);

        // CONTENIDO
        VerticalLayout content = new VerticalLayout();
        content.addClassName("favoritos-content");

        List<OfertaF> favoritos = Inicio.getFavoritosLocales();

        if (favoritos == null || favoritos.isEmpty()) {
            content.add(new Span("No tienes ofertas en favoritos."));
        } else {
            for (OfertaF ofertaF : favoritos) {
                VerticalLayout card = new VerticalLayout();
                card.addClassName("favoritos-card");

                Span titulo = new Span(ofertaF.getTitle());
                Span precio = new Span("Precio: €" + ofertaF.getPrice());
                Span universidad = new Span("Universidad: " + ofertaF.getUniversidad());
                Span ubicacion = new Span("Ubicación: " + ofertaF.getLocation());
                Span descripcion = new Span(ofertaF.getDescription());

                Image imagen = new Image();
                if (ofertaF.getFotos() != null && !ofertaF.getFotos().isEmpty()) {
                    imagen.setSrc("http://localhost:8082/api/photos/by-name/" + ofertaF.getFotos().get(0));
                } else {
                    imagen.setSrc("icons/piso1.jpg");
                }
                imagen.setWidth("200px");

                Button eliminarBtn = new Button("Quitar", new Icon(VaadinIcon.TRASH), e -> {
                    Inicio.getFavoritosLocales().remove(ofertaF);
                    Notification.show("Oferta eliminada de favoritos");
                    getUI().ifPresent(ui -> ui.getPage().reload());
                });

                card.add(titulo, precio, universidad, ubicacion, descripcion, imagen, eliminarBtn);
                content.add(card);
            }
        }

        // FOOTER
        HorizontalLayout footer = new HorizontalLayout();
        footer.add(new Span("© 2024 Homie. Todos los derechos reservados."));
        footer.addClassName("favoritos-footer");

        add(navBar, content, footer);
    }
}
