package es.ufv.homie.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import es.ufv.homie.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route("register")
@CssImport("./themes/styles/styles.css") // Importa la hoja de estilos
public class RegisterView extends VerticalLayout {

    private final UsuarioService usuarioService;
    private final Span messageLabel = new Span(); // Componente mutable para mensajes

    @Autowired
    public RegisterView(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;


        // Título del formulario
        Span title = new Span("Formulario de Registro");
        title.getStyle().set("font-size", "3rem").set("font-weight", "bold").set("color", "#003366");
        add(title);

        // Layout para el formulario
        FormLayout formLayout = new FormLayout();

        // Campos del formulario
        TextField emailField = new TextField("Correo electrónico");
        emailField.addClassName("login-input");

        PasswordField passwordField = new PasswordField("Contraseña");
        passwordField.addClassName("login-input");

        PasswordField confirmPasswordField = new PasswordField("Confirmar Contraseña");
        confirmPasswordField.addClassName("login-input");

        TextField phoneField = new TextField("Número de Teléfono");
        phoneField.addClassName("login-input");

        DatePicker birthDateField = new DatePicker("Fecha de Nacimiento");
        birthDateField.addClassName("login-input");

        // Opción de selección de tipo de usuario
        RadioButtonGroup<String> userTypeGroup = new RadioButtonGroup<>();
        userTypeGroup.setLabel("¿Qué tipo de usuario eres?");
        userTypeGroup.setItems("Anfitrión", "Huésped");
        userTypeGroup.addClassName("login-input");

        // Botón de registro
        Button registerButton = new Button("Registrarse");
        registerButton.addClickListener(event ->
                registerUser(
                        emailField.getValue(),
                        passwordField.getValue(),
                        confirmPasswordField.getValue(),
                        phoneField.getValue(),
                        birthDateField.getValue(),
                        userTypeGroup.getValue()
                )
        );

        // Añadir todos los elementos al formulario
        formLayout.add(
                emailField,
                passwordField,
                confirmPasswordField,
                phoneField,
                birthDateField,
                userTypeGroup,
                registerButton,
                messageLabel
        );

        // Estilo para el formulario
        formLayout.setMaxWidth("400px"); // Limitar el ancho del formulario
        formLayout.setWidthFull(); // Hacer que el formulario ocupe todo el ancho disponible

        // Contenedor principal del formulario (caja con sombreado)
        VerticalLayout container = new VerticalLayout(formLayout);
        container.setAlignItems(Alignment.CENTER);
        container.setClassName("form-container");

        // Añadir al layout principal
        add(container);
    }

    // Método para manejar el registro de usuario
    private void registerUser(String email, String password, String confirmPassword, String phone, LocalDate birthDate, String userType) {
        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Las contraseñas no coinciden");
            return;
        }

        if (phone.isEmpty()) {
            messageLabel.setText("El número de teléfono es obligatorio");
            return;
        }

        if (birthDate == null) {
            messageLabel.setText("La fecha de nacimiento es obligatoria");
            return;
        }

        if (userType == null) {
            messageLabel.setText("Debes seleccionar un tipo de usuario");
            return;
        }

        boolean success = usuarioService.registerUser(email, password, phone, birthDate, userType);

        if (success) {
            messageLabel.setText("¡Usuario registrado exitosamente!");
        } else {
            messageLabel.setText("Error al registrar el usuario");
        }
    }
}
