package cajeroatm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JPasswordField txtPin;
    private JButton btnIngresar;
    private JLabel lblMensaje;
    private int intentos = 0;
    private final int MAX_INTENTOS = 3;
    private final String PIN_CORRECTO = "1234";

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cajero Automático - Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con color de fondo azul bancario
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(0, 70, 127));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);

        // Título
        JLabel lblTitulo = new JLabel("CAJERO AUTOMÁTICO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(lblTitulo, gbc);

        // Separador visual
        JSeparator separador = new JSeparator();
        separador.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(separador, gbc);

        // Label PIN
        JLabel lblPin = new JLabel("Ingrese su PIN:");
        lblPin.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPin.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblPin, gbc);

        // Campo de contraseña
        txtPin = new JPasswordField(12);
        txtPin.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelPrincipal.add(txtPin, gbc);

        // Botón Ingresar
        btnIngresar = new JButton("  Ingresar  ");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 13));
        btnIngresar.setBackground(new Color(0, 153, 76));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(btnIngresar, gbc);

        // Label de mensajes de error
        lblMensaje = new JLabel(" ");
        lblMensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        lblMensaje.setForeground(new Color(255, 200, 0));
        gbc.gridy = 4;
        panelPrincipal.add(lblMensaje, gbc);

        add(panelPrincipal);

        // Evento del botón
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarPin();
            }
        });

        // También responde a Enter en el campo de PIN
        txtPin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarPin();
            }
        });
    }

    private void verificarPin() {
        String pinIngresado = new String(txtPin.getPassword());

        if (pinIngresado.equals(PIN_CORRECTO)) {
            // PIN correcto: abrir menú principal
            MenuFrame menuPrincipal = new MenuFrame();
            menuPrincipal.setVisible(true);
            this.dispose();
        } else {
            // PIN incorrecto
            intentos++;
            txtPin.setText("");

            if (intentos >= MAX_INTENTOS) {
                // Bloquear el acceso
                JOptionPane.showMessageDialog(
                        this,
                        "Acceso Bloqueado\nHa superado el número máximo de intentos permitidos.",
                        "Acceso Bloqueado",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(0);
            } else {
                lblMensaje.setText("PIN incorrecto. Intentos restantes: " + (MAX_INTENTOS - intentos));
            }
        }
    }
}
