package cajeroatm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame {

    private double saldo = 1000.00;
    private JLabel lblSaldoActual;

    public MenuFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cajero Automático - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(0, 70, 127));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ---- Panel Superior: Título y saldo ----
        JPanel panelSuperior = new JPanel(new GridLayout(3, 1, 5, 5));
        panelSuperior.setOpaque(false);

        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);

        JSeparator sep = new JSeparator();
        sep.setForeground(Color.LIGHT_GRAY);

        lblSaldoActual = new JLabel("Saldo disponible: Q" + String.format("%.2f", saldo), JLabel.CENTER);
        lblSaldoActual.setFont(new Font("Arial", Font.BOLD, 16));
        lblSaldoActual.setForeground(new Color(0, 255, 128));

        panelSuperior.add(lblTitulo);
        panelSuperior.add(sep);
        panelSuperior.add(lblSaldoActual);

        // ---- Panel Central: Botones del menú ----
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 0, 12));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JButton btnConsultarSaldo = new JButton("  Consultar Saldo  ");
        JButton btnRetirar = new JButton("  Retirar Dinero  ");
        JButton btnSalir = new JButton("  Salir  ");

        Font fuenteBoton = new Font("Arial", Font.BOLD, 15);

        btnConsultarSaldo.setFont(fuenteBoton);
        btnConsultarSaldo.setBackground(new Color(30, 110, 200));
        btnConsultarSaldo.setForeground(Color.WHITE);
        btnConsultarSaldo.setFocusPainted(false);

        btnRetirar.setFont(fuenteBoton);
        btnRetirar.setBackground(new Color(0, 153, 76));
        btnRetirar.setForeground(Color.WHITE);
        btnRetirar.setFocusPainted(false);

        btnSalir.setFont(fuenteBoton);
        btnSalir.setBackground(new Color(200, 30, 30));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);

        panelBotones.add(btnConsultarSaldo);
        panelBotones.add(btnRetirar);
        panelBotones.add(btnSalir);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);

        // ---- Eventos de los botones ----
        btnConsultarSaldo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarSaldo();
            }
        });

        btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retirarDinero();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }

    private void consultarSaldo() {
        JOptionPane.showMessageDialog(
                this,
                "Su saldo actual es:  Q" + String.format("%.2f", saldo),
                "Consulta de Saldo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void retirarDinero() {
        // Opciones de retiro disponibles
        String[] opciones = {"Q100", "Q300", "Q500"};

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el monto a retirar:",
                "Retiro de Dinero",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        // Si el usuario canceló no hacer nada
        if (seleccion == null) {
            return;
        }

        // Determinar el monto seleccionado
        double monto = 0;
        if (seleccion.equals("Q100")) {
            monto = 100;
        } else if (seleccion.equals("Q300")) {
            monto = 300;
        } else if (seleccion.equals("Q500")) {
            monto = 500;
        }

        // Validar que haya saldo suficiente
        if (monto > saldo) {
            JOptionPane.showMessageDialog(
                    this,
                    "Saldo insuficiente.\nSu saldo actual es: Q" + String.format("%.2f", saldo)
                    + "\nNo puede retirar Q" + String.format("%.2f", monto),
                    "Saldo Insuficiente",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            // Descontar el monto y actualizar la pantalla
            saldo = saldo - monto;
            lblSaldoActual.setText("Saldo disponible: Q" + String.format("%.2f", saldo));

            JOptionPane.showMessageDialog(
                    this,
                    "Retiro exitoso de Q" + String.format("%.2f", monto)
                    + "\nSaldo restante: Q" + String.format("%.2f", saldo),
                    "Retiro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void salir() {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gracias por usar el cajero automático.\n¡Hasta luego!",
                    "Sesión Finalizada",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
}
