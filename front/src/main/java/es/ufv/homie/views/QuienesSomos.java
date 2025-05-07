package es.ufv.homie.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./themes/styles/quienessomos.css")
@Route("quienessomos")
public class QuienesSomos extends Div {

    public QuienesSomos() {
        addClassName("qs-container");

        // Sección izquierda (texto)
        Div leftSection = new Div();
        leftSection.addClassName("qs-left");

        H1 title = new H1("Quiénes Somos");
        title.addClassName("qs-title");

        Paragraph description = new Paragraph(
                "Somos Homie, la plataforma que conecta estudiantes con " +
                        "viviendas de manera rápida y segura. Nuestro equipo está " +
                        "comprometido en ofrecerte la mejor experiencia para que " +
                        "encuentres tu nuevo hogar universitario." +
                        "\nEsta plataforma ha sido creada por 5 estudiantes de" +
                        "ingeniería informática en la UFV, en la asignatura 'Proyectos II'"
        );
        description.addClassName("qs-text");

        leftSection.add(title, description);

        // Sección derecha (imagen)
        Div rightSection = new Div();
        rightSection.addClassName("qs-right");

        Image teamImage = new Image("icons/nuestro-equipo.png", "Nuestro equipo");
        teamImage.addClassName("qs-image");

        rightSection.add(teamImage);

        add(leftSection, rightSection);
    }
}
