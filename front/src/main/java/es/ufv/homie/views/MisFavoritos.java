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
        getStyle().set("margin", "0");
       ;
        addClassName("favoritos-view"); // NUEVA CLASE RAÍZ

        // NAVBAR
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("favoritos-navbar");

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("favoritos-logo");

        Button exploreButton2 = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH), e -> getUI().ifPresent(ui -> ui.navigate("inicio")));
        Button savedButton2 = new Button("Guardados", new Icon(VaadinIcon.HEART));
        Button aboutButton2 = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE), e -> getUI().ifPresent(ui -> ui.navigate("quienessomos")));
        Button profileButton2 = new Button("Logout", new Icon(VaadinIcon.USER), e -> getUI().ifPresent(ui -> ui.navigate("login")));

        exploreButton2.addClassName("favoritos-explore-button");
        savedButton2.addClassName("favoritos-saved-button"); // Este será azul claro
        aboutButton2.addClassName("favoritos-about-button");
        profileButton2.addClassName("favoritos-profile-button");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton2, savedButton2, aboutButton2, profileButton2);
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

                Span universidad = new Span(oferta.getUniversidad());
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
