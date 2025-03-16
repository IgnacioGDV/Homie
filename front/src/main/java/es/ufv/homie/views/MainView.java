package es.ufv.homie.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")  // Ruta raíz
public class MainView extends VerticalLayout {

    public MainView() {
        // Redirigir automáticamente a la vista de Login
        UI.getCurrent().navigate("login");
    }
}