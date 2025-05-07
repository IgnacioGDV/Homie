package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import es.ufv.homie.services.UsuarioService;


@CssImport("./themes/styles/register.css")
@Route("register")
public class RegisterView extends Div {

    public RegisterView(UsuarioService usuarioService) {
        addClassName("register-container");

        // SECCIÓN IZQUIERDA
        Div leftSection = new Div();
        leftSection.addClassName("register-left");

        VerticalLayout formLayout = new VerticalLayout();
        formLayout.addClassName("register-form");

        Image logo = new Image("icons/homiepng.png", "Logo Homie");
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

        DatePicker birthDate = new DatePicker("Fecha de Nacimiento");
        birthDate.addClassName("register-input");

        RadioButtonGroup<String> userType = new RadioButtonGroup<>();
        userType.setLabel("Tipo de usuario");
        userType.setItems("Inquilino", "Anfitrión");
        userType.addClassName("register-input");

        Checkbox termsCheckbox = new Checkbox("Acepto los términos y condiciones");
        termsCheckbox.addClassName("register-checkbox");

        Button registerButton = new Button("Registrarse", e -> {
            if (
                    email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                            phone.isEmpty() || birthDate.isEmpty() || userType.isEmpty() || !termsCheckbox.getValue()
            ) {
                Notification.show("Por favor, complete todos los campos correctamente.");
                return;
            }
            if (!password.getValue().equals(confirmPassword.getValue())) {
                Notification.show("Las contraseñas no coinciden");
                return;
            }

            usuarioService.registerUser(
                    email.getValue(),
                    password.getValue(),
                    phone.getValue(),
                    birthDate.getValue().toString(),
                    userType.getValue()
            );
            Notification.show("Usuario registrado exitosamente");
            getUI().ifPresent(ui -> ui.navigate("login"));
        });
        registerButton.addClassName("register-button");
        RouterLink loginLink = new RouterLink("¿Ya tienes cuenta? Inicia sesión aquí", LoginView.class);
        Span loginText = new Span("Si ya eres miembro ");
        Div loginContainer = new Div(loginText, loginLink);

        formLayout.add(logo, title, description, email, password, confirmPassword,
                phone, birthDate, userType, termsCheckbox, registerButton, loginContainer);

        leftSection.add(formLayout);

        // SECCIÓN DERECHA
        Div rightSection = new Div();
        rightSection.addClassName("register-right");

        Image houseImage = new Image("icons/img.png", "Casa");
        houseImage.addClassName("register-main-img");

        Image quienesSomosImg = new Image("icons/registroquienesomos1.png", "Quiénes Somos");
        quienesSomosImg.addClassName("quienes-somos-img");

        rightSection.add(houseImage, quienesSomosImg);

        add(leftSection, rightSection);
    }
}
