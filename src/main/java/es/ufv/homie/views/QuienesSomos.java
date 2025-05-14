package es.ufv.homie.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@CssImport("./themes/styles/quienessomos.css")
@Route("quienessomos")
public class QuienesSomos extends VerticalLayout {

    private int currentImage = 0;
    private final List<String> images = Arrays.asList(
            "icons/equipo1.jpg",
            "icons/equipo2.jpg",
            "icons/equipo3.jpg"
    );

    public QuienesSomos() {
        setSizeFull();
        getStyle().set("margin", "0");
        addClassName("qs-container");

        /** NAVBAR **/
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.addClassName("navbar");
        navBar.setWidthFull();

        Image homieLogo = new Image("icons/homiepng2.png", "Logo de Homie");
        homieLogo.addClassName("logo-navbar");

        Button exploreButton1 = new Button("Explorar Ofertas", new Icon(VaadinIcon.SEARCH), e -> UI.getCurrent().navigate("inicio"));
        Button savedButton1 = new Button("Guardados", new Icon(VaadinIcon.HEART), e -> UI.getCurrent().navigate("mis-favoritos"));
        Button aboutButton1 = new Button("Quienes somos", new Icon(VaadinIcon.INFO_CIRCLE));
        Button profileButton1= new Button("Login", new Icon(VaadinIcon.USER), e -> UI.getCurrent().navigate("login"));

        exploreButton1.addClassName("explore-button1");
        savedButton1.addClassName("saved-button1");
        aboutButton1.addClassName("about-button1");
        profileButton1.addClassName("profile-button1");

        HorizontalLayout navButtons = new HorizontalLayout(exploreButton1, savedButton1, aboutButton1, profileButton1);
        navButtons.addClassName("nav-buttons1");
        navBar.add(homieLogo, navButtons);

        /** CONTENIDO PRINCIPAL **/
        HorizontalLayout content = new HorizontalLayout();
        content.setWidthFull();
        content.setSpacing(true);

        /** Sección izquierda **/
        VerticalLayout leftSection = new VerticalLayout();
        leftSection.addClassName("qs-left");

        H1 title = new H1("Quiénes Somos");
        title.addClassName("qs-title");

        Paragraph description = new Paragraph("Somos Homie, una plataforma hecha por estudiantes para estudiantes. \n\nEnlazamos a inquilinos y anfitriones con total confianza y facilidad. Nacida en la Universidad Francisco de Vitoria en el proyecto final de 'Proyectos II', nuestra misión es ayudarte a encontrar tu hogar universitario ideal.");
        description.addClassName("qs-text");

        leftSection.add(title, description);

        /** Sección derecha con carrusel **/
        VerticalLayout rightSection = new VerticalLayout();
        rightSection.addClassName("qs-right");

        Image carruselImage = new Image(images.get(0), "Imagen equipo");
        carruselImage.setClassName("qs-image");

        rightSection.add(carruselImage);

        // Carrusel automático
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getUI().ifPresent(ui -> ui.access(() -> {
                    currentImage = (currentImage + 1) % images.size();
                    carruselImage.setSrc(images.get(currentImage));
                }));
            }
        }, 0, 3000); // cambia cada 3 segundos

        content.add(leftSection, rightSection);
        add(navBar, content);
    }
}
