package es.ufv.homie;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {
    public Home() {
        setTitle("Homie - Alquileres de Pisos para Estudiantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // NAVBAR - Menú superior
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        menuPanel.setBackground(new Color(25, 25, 112)); // Azul oscuro

        // Botones modernizados
        JButton explorarBtn = createModernButton("Explorar");
        JButton guardadosBtn = createModernButton("Guardados");
        JButton configuracionBtn = createModernButton("Configuración");
        JButton editarPerfilBtn = createModernButton("Editar Perfil");

        menuPanel.add(explorarBtn);
        menuPanel.add(guardadosBtn);
        menuPanel.add(configuracionBtn);
        menuPanel.add(editarPerfilBtn);

        // LOGO - En un panel independiente con centrado
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/homiepng.png"));

        if (logoIcon.getIconWidth() > 0) {
            Image scaledImage = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            logoLabel.setText("Logo no encontrado");
            logoLabel.setForeground(Color.RED);
        }

        logoPanel.add(logoLabel);

        // BUSCADOR - Justo debajo del logo
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setPreferredSize(new Dimension(300, 35));
        searchField.setBorder(new RoundedBorder(15)); // Bordes redondeados

        JButton searchButton = createModernButton("🔍 Buscar");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // FILTROS - Debajo del buscador
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel filterLabel = new JLabel("Filtros:");
        JComboBox<String> filterComboBox = new JComboBox<>(new String[]{
                "Universidad Francisco de Vitoria",
                "Universidad Politécnica de Madrid",
                "Universidad Autónoma de Madrid",
                "Universidad Pontificia Comillas",
                "Universidad Complutense de Madrid",
                "Universidad de Alcalá",
                "Universidad Carlos III de Madrid"
        });
        filterPanel.add(filterLabel);
        filterPanel.add(filterComboBox);

        // PANEL CENTRAL - Contiene logo y buscador en vertical
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(logoPanel);
        centerPanel.add(searchPanel);
        centerPanel.add(filterPanel);

        // AGREGAR COMPONENTES AL PANEL PRINCIPAL
        mainPanel.add(menuPanel, BorderLayout.NORTH);  // Navbar arriba
        mainPanel.add(centerPanel, BorderLayout.CENTER); // Logo + buscador en el centro

        add(mainPanel);
    }

    // Método para crear botones más estilizados (más modernos)
    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(166, 128, 226)); // Morado claro
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(15)); // Bordes más suaves
        button.setPreferredSize(new Dimension(160, 40));

        // Efecto hover - Cambio de color cuando el ratón pasa por encima
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(142, 107, 209)); // Un lila más oscuro
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(166, 128, 226)); // Color original
            }
        });

        return button;
    }

    // Clase para bordes redondeados
    static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home().setVisible(true));
    }
}
