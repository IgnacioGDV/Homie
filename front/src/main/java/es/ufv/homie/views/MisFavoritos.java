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
import es.ufv.homie.model.OfertaF;
import es.ufv.homie.services.OfertaService;

import java.util.List;

@CssImport("./themes/styles/favoritos.css")
@Route("mis-favoritos")
@PageTitle("Mis Favoritos")public class MisFavoritos extends VerticalLayout {

    public MisFavoritos(OfertaService ofertaService) {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        getStyle().set("margin", "0");

        addClassName("favoritos-view");

        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("favoritos-navbar");

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("favoritos-logo");

        Button exploreButton2 = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH),
                e -> getUI().ifPresent(ui -> ui.navigate("inicio")));
        Button savedButton2 = new Button("Guardados", new Icon(VaadinIcon.HEART));
        Button aboutButton2 = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE),
                e -> getUI().ifPresent(ui -> ui.navigate("quienessomos")));
        Button profileButton2 = new Button("Login", new Icon(VaadinIcon.USER),
                e -> getUI().ifPresent(ui -> ui.navigate("login")));

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton2, savedButton2, aboutButton2, profileButton2);
        navBar.add(homieLogo, navButtons);

        VerticalLayout content = new VerticalLayout();
        content.addClassName("favoritos-content");

        List<OfertaF> favoritos = ofertaService.getFavoritos(); // ✅ Ahora usando la instancia
        if (favoritos.isEmpty()) {
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
                card.add(titulo, precio, universidad, ubicacion, descripcion, imagen);
                content.add(card);
            }
        }

        HorizontalLayout footer = new HorizontalLayout();
        footer.add(new Span("© 2024 Homie. Todos los derechos reservados."));

        add(navBar, content, footer);
    }
}
