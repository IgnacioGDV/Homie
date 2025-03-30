package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import es.ufv.homie.services.UsuarioService;
import com.vaadin.flow.component.html.Span;

@CssImport("./themes/styles/login.css")
@Route("login")
public class LoginView extends Div {

    private final UsuarioService usuarioService;

    public LoginView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        addClassName("login-container");

        // Sección izquierda (formulario)
        Div leftSection = new Div();
        leftSection.addClassName("login-left");

        Image logo = new Image("icons/homiepng.png", "Logo de Homie");
        logo.addClassName("login-logo");

        H1 title = new H1("Bienvenidos a Homie!");
        title.addClassName("login-title");

        Paragraph description = new Paragraph("La app que te facilitará el acceso a vivienda para estudiantes");

        TextField username = new TextField("User name");
        username.setPlaceholder("Introduce tu nombre de usuario");
        username.addClassName("login-input");

        PasswordField password = new PasswordField("Contraseña");
        password.setPlaceholder("Introduce tu contraseña");
        password.addClassName("login-input");

        Button loginButton = new Button("Login", e -> {
            String user = username.getValue();
            String pass = password.getValue();

            if (!usuarioService.exists(user)) {
                Notification.show("El usuario no está registrado. Por favor, regístrate.");
            } else if (!usuarioService.authenticate(user, pass)) {
                Notification.show("Contraseña incorrecta. Inténtalo de nuevo.");
            } else {
                String userRole = usuarioService.getUserRole(user);
                if ("Anfitrión".equals(userRole)) {
                    getUI().ifPresent(ui -> ui.navigate("crear-oferta"));
                } else {
                    getUI().ifPresent(ui -> ui.navigate("inicio"));
                }
            }
        });

        loginButton.addClassName("login-button");

        RouterLink registerLink = new RouterLink("Regístrate aquí", RegisterView.class);
        Div registerContainer = new Div(new Span("¿Eres nuevo? "), registerLink);

        VerticalLayout formLayout = new VerticalLayout(logo, title, description, username, password, loginButton, registerContainer);
        formLayout.addClassName("login-form");

        leftSection.add(formLayout);

        // Sección derecha (fondo azul con imágenes)
        Div rightSection = new Div();
        rightSection.addClassName("login-right");

        Image img1 = new Image("icons/img.png", "Casa 1");
        Image img2 = new Image("icons/img_1.png", "Casa 2");
        img1.addClassName("login-right-img");
        img2.addClassName("login-right-img");

        rightSection.add(img1, img2);

        // Agregar ambas secciones al contenedor principal
        add(leftSection, rightSection);
    }
}
