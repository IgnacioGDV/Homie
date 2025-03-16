package es.ufv.homie.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import es.ufv.homie.services.UsuarioService;
import com.vaadin.flow.component.html.Span;

@CssImport("./themes/styles/styles.css") // Importa la hoja de estilos
@Route("login") // Ruta de acceso a la vista de Login
public class LoginView extends VerticalLayout {

    private final UsuarioService usuarioService;

    public LoginView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        setAlignItems(Alignment.CENTER); // Centrar contenido

        // Logo de la aplicación
        Image logo = new Image("icons/homiepng.png", "Logo de Homie");
        logo.addClassName("login-logo");

        // Título del login
        Text title = new Text("¡Bienvenido a Homie!");
        Div titleContainer = new Div(title);
        titleContainer.addClassName("login-title");

        // Formulario de login
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName("login-form");

        TextField username = new TextField("Usuario");
        username.addClassName("login-input");

        PasswordField password = new PasswordField("Contraseña");
        password.addClassName("login-input");

        // Mensaje de error
        Span errorMessage = new Span();
        errorMessage.addClassName("error-message");
        errorMessage.getStyle().set("color", "red");
        errorMessage.getStyle().set("font-size", "14px");

        Button loginButton = new Button("Iniciar sesión", e -> {
            String user = username.getValue();
            String pass = password.getValue();

            if (!usuarioService.exists(user)) {
                errorMessage.setText("El usuario no está registrado. Por favor, regístrate.");
            } else if (!usuarioService.authenticate(user, pass)) {
                errorMessage.setText("Contraseña incorrecta. Inténtalo de nuevo.");
            } else {
                getUI().ifPresent(ui -> ui.navigate("inicio"));
            }
        });

        loginButton.addClassName("login-button");

        formLayout.add(username, password, loginButton, errorMessage);

        // Enlace de registro
        Div registerContainer = new Div(new Text("¿No tienes cuenta? "), new RouterLink("Regístrate aquí", RegisterView.class));

        // Agregar componentes en orden
        add(logo, titleContainer, formLayout, registerContainer);
    }
}
