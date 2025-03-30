package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import es.ufv.homie.services.UsuarioService;
import com.vaadin.flow.component.html.Span;

@CssImport("./themes/styles/register.css")
@Route("register")
public class RegisterView extends Div {

    private final UsuarioService usuarioService;

    public RegisterView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        addClassName("register-container");

        // Sección izquierda (Formulario de registro)
        Div leftSection = new Div();
        leftSection.addClassName("register-left");

        Image logo = new Image("icons/homiepng.png", "Logo de Homie");
        logo.addClassName("register-logo");

        H1 title = new H1("Formulario de Registro");
        title.addClassName("register-title");

        Paragraph description = new Paragraph("Únete a Homie y encuentra tu vivienda ideal para estudiantes");

        TextField email = new TextField("Correo electrónico");
        email.setPlaceholder("Introduce tu correo electrónico");
        email.addClassName("register-input");

        PasswordField password = new PasswordField("Contraseña");
        password.setPlaceholder("Introduce tu contraseña");
        password.addClassName("register-input");

        PasswordField confirmPassword = new PasswordField("Confirmar Contraseña");
        confirmPassword.setPlaceholder("Confirma tu contraseña");
        confirmPassword.addClassName("register-input");

        TextField phone = new TextField("Número de Teléfono");
        phone.setPlaceholder("Introduce tu número de teléfono");
        phone.addClassName("register-input");

        // Campo de fecha de nacimiento
        DatePicker birthDate = new DatePicker("Fecha de Nacimiento");
        birthDate.addClassName("register-input");

        // Selección de rol
        RadioButtonGroup<String> userType = new RadioButtonGroup<>();
        userType.setLabel("Tipo de usuario");
        userType.setItems("Inquilino", "Anfitrión");
        userType.addClassName("register-input");

        // Botón de registro con funcionalidad
        Button registerButton = new Button("Registrarse", e -> {
            String userEmail = email.getValue();
            String userPassword = password.getValue();
            String userConfirmPassword = confirmPassword.getValue();
            String userPhone = phone.getValue();
            String userBirthDate = birthDate.getValue() != null ? birthDate.getValue().toString() : "";
            String userRole = userType.getValue();

            // Validaciones
            if (userEmail.isEmpty() || userPassword.isEmpty() || userPhone.isEmpty() || userBirthDate.isEmpty() || userRole == null) {
                Notification.show("Por favor, complete todos los campos");
            } else if (!userPassword.equals(userConfirmPassword)) {
                Notification.show("Las contraseñas no coinciden");
            } else {
                // Registrar usuario en el servicio con el rol
                usuarioService.registerUser(userEmail, userPassword, userPhone, userBirthDate, userRole);

                Notification.show("Usuario registrado exitosamente");
                getUI().ifPresent(ui -> ui.navigate("login"));
            }
        });

        registerButton.addClassName("register-button");

        RouterLink loginLink = new RouterLink("¿Ya tienes cuenta? Inicia sesión aquí", LoginView.class);
        Span loginText = new Span("Si ya eres miembro ");
        Div loginContainer = new Div(loginText, loginLink);

        VerticalLayout formLayout = new VerticalLayout(logo, title, description, email, password, confirmPassword, phone, birthDate, userType, registerButton, loginContainer);
        formLayout.addClassName("register-form");

        leftSection.add(formLayout);

        // Sección derecha
        Div rightSection = new Div();
        rightSection.addClassName("register-right");

        Image img1 = new Image("icons/img.png", "Casa 1");
        Image img2 = new Image("icons/img_1.png", "Casa 2");
        img1.addClassName("register-right-img");
        img2.addClassName("register-right-img");

        rightSection.add(img1, img2);
        add(leftSection, rightSection);
    }
}
