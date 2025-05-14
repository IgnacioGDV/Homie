// MisFavoritos.java
package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.ufv.homie.model.Oferta;
import es.ufv.homie.services.OfertaService;

import java.util.List;
@CssImport("./themes/styles/favoritos.css")
@Route("mis-favoritos")
@PageTitle("Mis Favoritos")
public class MisFavoritos extends VerticalLayout {

    public MisFavoritos() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        addClassName("favoritos-view"); // NUEVA CLASE RAÍZ

        // NAVBAR
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("favoritos-navbar");

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("favoritos-logo");

        Button exploreButton = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH), e -> getUI().ifPresent(ui -> ui.navigate("inicio")));
        Button savedButton = new Button("Guardados", new Icon(VaadinIcon.HEART));
        Button aboutButton = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE), e -> getUI().ifPresent(ui -> ui.navigate("quienessomos")));
        Button profileButton = new Button("Editar Perfil", new Icon(VaadinIcon.USER), e -> getUI().ifPresent(ui -> ui.navigate("login")));

        exploreButton.addClassName("favoritos-explore-button");
        savedButton.addClassName("favoritos-saved-button"); // Este será azul claro
        aboutButton.addClassName("favoritos-about-button");
        profileButton.addClassName("favoritos-profile-button");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton, savedButton, aboutButton, profileButton);
        navButtons.addClassName("favoritos-nav-buttons");

        navBar.add(homieLogo, navButtons);

        // CONTENIDO
        VerticalLayout content = new VerticalLayout();
        content.addClassName("favoritos-content");

        List<Oferta> favoritos = OfertaService.getFavoritos();
        if (favoritos.isEmpty()) {
            content.add(new Span("No tienes ofertas en favoritos."));
        } else {
            for (Oferta oferta : favoritos) {
                VerticalLayout card = new VerticalLayout();
                card.addClassName("favoritos-card");

                Span titulo = new Span(oferta.getTitulo());
                titulo.getStyle().set("font-weight", "bold").set("font-size", "20px");

                Span precio = new Span("Precio: €" + oferta.getPrecio());
                precio.getStyle().set("font-weight", "bold").set("font-size", "18px");

                Span universidad = new Span(oferta.getUniversity());
                Span ubicacion = new Span(oferta.getUbicacion());
                Span descripcion = new Span(oferta.getDescripcion());

                Image imagen = new Image();
                if (oferta.getImagenes() != null && !oferta.getImagenes().isEmpty()) {
                    imagen.setSrc("/uploads/" + oferta.getImagenes().get(0));
                } else {
                    imagen.setSrc("icons/piso1.jpg");
                }
                imagen.setWidth("200px");

                card.add(titulo, precio, universidad, ubicacion, descripcion, imagen);
                content.add(card);
            }
        }

        // FOOTER
        HorizontalLayout footer = new HorizontalLayout();
        footer.addClassName("favoritos-footer");
        footer.setWidthFull();
        Span copyright = new Span("© 2024 Homie. Todos los derechos reservados.");
        footer.add(copyright);

        add(navBar, content, footer);
    }
}
