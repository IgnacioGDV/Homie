package es.ufv.homie.services;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service // Se registra como un bean de Spring
public class NotificationService {

    private List<NotificationListener> listeners = new ArrayList<>();

    // Interfaz para los listeners que reciben las notificaciones
    public interface NotificationListener {
        void onNotification(String message);
    }

    // Método para añadir un listener que escuche las notificaciones
    public void addNotificationListener(NotificationListener listener) {
        listeners.add(listener);
    }

    // Método para eliminar un listener
    public void removeNotificationListener(NotificationListener listener) {
        listeners.remove(listener);
    }

    // Método para emitir una notificación y llamar a todos los listeners
    public void notifyListeners(String message) {
        // Notificar a todos los listeners registrados
        for (NotificationListener listener : listeners) {
            listener.onNotification(message);
        }
    }

    // Método para mostrar una notificación en pantalla
    public void showNotification(String message) {
        Notification notification = new Notification(message, 3000); // Aparece durante 3 segundos
        notification.open();
    }
}
